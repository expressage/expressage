/**
 * Ingrid : JQuery Datagrid Control
 *
 * Copyright (c) 2007 Matthew Knight (http://www.reconstrukt.com http://slu.sh)
 * Dual licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) 
 * and GPL (http://www.opensource.org/licenses/gpl-license.php) licenses.
 *
 * @requires jQuery v1.2
 *
 * Revision: $Id: jquery.ingrid.js,v 1.00 2007/10/02 09:00:00 mknight Exp $
 * Version: .1
 *
 * Some notes... 
 * using dimensions plugin, the call to width() temporarily styles the element to 
 * have 0px padding, border, margin, measures width() -- then removes these inline styles.
 * This sometimes causes the element to BLINK - as all those margins/widths are updated!
 *
 * removed dependency on dimensions plugin
 *
 * need to support a "row selection" model - either single-select, or multi-select (checkboxes)
 *
 * need to implement loading JSON data
 *
 */

jQuery.fn.ingrid = function(o){

	return this.each(function(){
		
		var cfg = {
			height: 200, 										// height of our datagrid (scrolling body area)
			
			initalLoad : false,							// when Ingrid is initialized, should it load data?

			colWidths: [225,225,225,225],		// width of each column
			minColWidth: 60,								// minimum column width
			headerHeight: 30,								// height of our header
			headerClass: 'grid-header-bg',	// header bg
			resizableCols: true,						// make columns resizable via drag + drop
			
			gridClass: 'datagrid',							// class of head & body
			rowClasses: [],
			colClasses: [],											// array of classes : i.e. ['','grid-col-2','','']
			rowHoverClass: 'grid-row-hover',		// hovering over a row? use this class
			
			/* sorting */
			sorting: true,
			colSortParams: [],								// value to pass as sort param when header clicked (i.e. '&sort=param') ex: ['col1','col2','col3','col4']
			sortAscParam: 'asc',							// param passed on ascending sort (i.e. '&dir=asc)
			sortDescParam: 'desc',						// param passed on ascending sort (i.e. '&dir=desc)
			sortedCol: 'col1',								// current data's sorted column (can be a key from 'colSortParams', or an int 0-n (for n columns)
			sortedColDir: 'desc',							// current data's sorted dorections
			sortDefaultDir: 'desc',						// on 1st click, sort tihs direction
			sortAscClass: 'grid-sort-asc',		// class for ascending sorted col
			sortDescClass: 'grid-sort-desc',	// class for descending sorted col
			sortNoneClass: 'grid-sort-none',	// ... not sorted? use this class
			
			/* paging */
			paging: true,											// create a paging toolbar
			pageNumber: 1,
			recordsPerPage: 0,
			totalRecords: 0,
			pageToolbarHeight: 25,
			pageToolbarClass: 'grid-page-toolbar',
			pageStartClass: 'grid-page-start',
			pagePrevClass: 'grid-page-prev',
			pageInfoClass: 'grid-page-info',
			pageInputClass: 'grid-page-input',
			pageNextClass: 'grid-page-next',
			pageEndClass: 'grid-page-end',
			pageLoadingClass: 'grid-page-loading',
			pageLoadingDoneClass: 'grid-page-loading-done',
			pageViewingRecordsInfoClass: 'grid-page-viewing-records-info',

			/* ajax stuff */
			url: 'remote.html',							// url to fetch data
			type: 'GET',										// 'POST' or 'GET'
			dataType: 'html',								// 'html' or 'json' - expected dataType returned
			extraParams: {},								// a map of extra params to send to the server 				
			loadingClass: 'grid-loading',		// loading modalmask div
			loadingHtml: '<div>&nbsp;</div>',			
			
			/* should seldom change */
			resizeHandleHtml: '',									// resize handle html + css
			resizeHandleClass: 'grid-col-resize',
			scrollbarW: 16,									// width allocated for scrollbar
			columnIDAttr: '_colid',					// attribute name used to groups TDs in columns
			
			/* not yet implemented */
			minHeight: 100,
			resizableGrid: true,
			dragDropCols: true,
			sortType: 'server|client|none',
			rowSelection: 'single|multiple|none'
			
		};
		jQuery.extend(cfg, o);
		
		// break into 2 tables: header, body.
		var cols = new Array();
		var h = jQuery('<table cellpadding="0" cellspacing="0"></table>')
						.html(jQuery(this).find('thead'))
						.addClass(cfg.gridClass)
						.addClass(cfg.headerClass)
						.height(cfg.headerHeight)
						.extend({
							cols : cols
						});
				
		// surround body with container div for scrolling
		var b = jQuery('<div />')
						.html(jQuery('<table cellpadding="0" cellspacing="0"></table>').html(jQuery(this).find('tbody'))
						.addClass(cfg.gridClass))
						.css('overflow', 'auto')
						.height(cfg.height);
						
		// create a container div with both inside
		var g = jQuery('<div />').html(h).append(b);
		
		// append to doc
		jQuery(this).replaceWith(g);
		
		// create a vertical resize divider
		var z	= jQuery('<div id="vertical-resize-divider"></div>')
						.css({
							backgroundColor: '#ababab', 
							height: g.height(),
							width: '4px',
							position: 'absolute',
							zIndex: '10',
							display: 'block'
						})
						.extend({
							resizeStart : function(th, eventX){
								// this is fired onmousedown of the column's resize handle 						
								var pos	= th.offset();
								jQuery(this).show().css({
									top: pos.top,
									left: eventX
								})
								// when resizing, bind some listeners for mousemove & mouseup events
								jQuery('body').bind('mousemove', {col : th}, function(e){		
									// on mousemove, move the vertical-resize-divider
									var th 		= e.data.col;
									var pos		= th.offset();
									var col_w	= e.clientX - pos.left;
									// make sure cursor isn't trying to make column smaller than minimum
									if (col_w > cfg.minColWidth) {
										jQuery('#vertical-resize-divider').css('left', e.clientX);										
									}																		
								})
								jQuery('body').bind('mouseup', {col : th}, function(e){
									// on mouseup, 
									// 1.) unbind resize listener events from body
									// 2.) hide the vertical-resize-divider
									// 3.) trigger the resize event on the column
									jQuery(this).unbind('mousemove').unbind('mouseup');
									jQuery('#vertical-resize-divider').hide();
									var th 		= e.data.col;
									var pos		= th.offset();
									var col_w	= e.clientX - pos.left;
									if (col_w > cfg.minColWidth) {
										th.trigger('resizeColumn', [col_w]);
									} else {
										th.trigger('resizeColumn', [cfg.minColWidth]);
									}
								})
							}
						})
		g.append(z.hide());		

		// initialize columns
		g.find('th').each(function(i){
															 
			// put column text in a div, make unselectable
			var col_label = jQuery('<div />')
											.html(jQuery(this).html())
											.css({float: 'left', display: 'block'})
											.css('-moz-user-select', 'none')
											.css('-khtml-user-select', 'none')
											.css('user-select', 'none')
											.attr('unselectable', 'on');

			// column sorting?
			if (cfg.sorting) {
				// make column headers resizable
				var key = cfg.colSortParams[i] ? cfg.colSortParams[i] : i;
				// is this column the default sorted column?
				var cls = (key == cfg.sortedCol || i == cfg.sortedCol) ? 
										( cfg.sortedColDir == cfg.sortAscParam ? cfg.sortAscClass : cfg.sortDescClass ) :
										( cfg.sortNoneClass );

				col_label.addClass(cls).click(function(){
					var dir = col_label.hasClass(cfg.sortNoneClass) ? 
											cfg.sortDefaultDir : ( col_label.hasClass(cfg.sortAscClass) ? cfg.sortDescParam : cfg.sortAscParam );

					var params = { sort : key, dir : dir };					
					if (p) jQuery.extend(params, { page : p.getPage() } );
					
					g.load( params, function(){						
						var cls = col_label.hasClass(cfg.sortNoneClass) ? 
												( cfg.sortDefaultDir == cfg.sortAscParam ? cfg.sortAscClass : cfg.sortDescClass ) :
												( col_label.hasClass(cfg.sortAscClass) ? cfg.sortDescClass : cfg.sortAscClass );

						g.getHeaders(function(col){
							col.find('div:first').addClass(cfg.sortNoneClass).removeClass(cfg.sortAscClass).removeClass(cfg.sortDescClass);
						});
						col_label.removeClass(cfg.sortAscClass).removeClass(cfg.sortDescClass).addClass(cls).removeClass(cfg.sortNoneClass);

					});
				});
			}
			
			// replace contents of <th>
			jQuery(this).html(col_label);
			
			// bind an event to easily resize columns
			jQuery(this).bind('resizeColumn', {col_num : i}, function(e, w){
				jQuery(this).width(w);				
				// set body cells to this width
				g.resize();
				g.getColumn(e.data.col_num).each(function(){
					jQuery(this).width(w);
				});
			});
			
			// append resize handle?
			if (cfg.resizableCols) {
				// make column headers resizable
				var handle = jQuery('<div />').html(cfg.resizeHandleHtml == '' ? '-' : cfg.resizeHandleHtml).addClass(cfg.resizeHandleClass);
				handle.bind('mousedown', function(e){
					// start resize drag
					var th 		= jQuery(this).parent();
					var left  = e.clientX;
					z.resizeStart(th, left);
				});
				jQuery(this).append(handle);
			}
			
		});
		
		// paging?
		if (cfg.paging) {

			// create a paging toolbar
			var totr  = cfg.recordsPerPage > 0 ? cfg.recordsPerPage : b.find('tr').length;
			
			// total records viewing message (if we know total records)
			// total record count might not be passed in config, it's sometimes an expensive hit to the DB
			var pv;
			if (cfg.totalRecords > 0) {
				pv = jQuery('<div />')
							.addClass(cfg.pageViewingRecordsInfoClass)
							.extend({
								updateViewInfo : function(loaded_rows, page){
									var _start = ( (loaded_rows * (page - 1) + 1) );
									var _end   = ( (loaded_rows * page) > cfg.totalRecords ? cfg.totalRecords : loaded_rows * page );
									this.html('Viewing Rows ' + _start + ' - ' + _end + ' of ' + cfg.totalRecords);
									return this;
								}
							});
				// update the "viewing x of y" record info
				pv.updateViewInfo(totr, cfg.pageNumber);
			}
			
			// create our paging control container
			var p 		= jQuery('<div />')
									.addClass(cfg.pageToolbarClass)
									.height(cfg.pageToolbarHeight)
									.width(b.width())
									.extend({										
											setPage : function(p){
												var input = this.find('input.' + cfg.pageInputClass);
												pload.removeClass(cfg.pageLoadingDoneClass);
												g.load( {page : p}, function(){
													input.val(p);
													if (cfg.totalRecords > 0) {
														var totr = b.find('tr').length;
														pv.updateViewInfo(totr, p);
													}
													pload.addClass(cfg.pageLoadingDoneClass);
												});
												return this;
											},
											getPage : function(){
												var p = Number(this.find('input.' + cfg.pageInputClass).val());
												return p;
											}
									});

			// start page button
			var pb1		= jQuery('<a href="#">&laquo;</a>').addClass(cfg.pageStartClass).click(function(){
										p.setPage(1);
									});

			// prev page button
			var pb2		= jQuery('<a href="#">&lt;</a>').addClass(cfg.pagePrevClass).click(function(){
										var _p = p.getPage();																															
										if (_p > 1) {
											_p--;
											p.setPage(_p);
										}										
									});

			// next page button
			if (cfg.totalRecords > 0) {
				var totp = Math.ceil(cfg.totalRecords / totr);
			}
			var pb3		= jQuery('<a href="#">&gt;</a>').addClass(cfg.pageNextClass).click(function(){
										var _p = p.getPage(); _p++;
										if (totp) {
											 if (_p < totp) p.setPage(_p);
										} else {
											p.setPage(_p);
										}
									});
			
			// loading indicator
			var pload	= jQuery('<div />').addClass(cfg.pageLoadingClass).addClass(cfg.pageLoadingDoneClass);
			
			// page field & form
			var pfld  = jQuery('<input type="text" value="' + cfg.pageNumber + '"/>').addClass(cfg.pageInputClass);
			var pinfo = jQuery('<div />')
									.addClass(cfg.pageInfoClass)
									.append(pfld);
			var pform = jQuery('<form></form>').append(pinfo).submit(function(){
										var _p = parseInt(p.getPage());
										if (_p) {
											if (totp) {
												 if (_p < totp) p.setPage(_p);
											} else if (_p > 0) {
												p.setPage(_p);
											}
										} else {
											alert('Please Enter a Valid Page Number.');
										}
										return false;
									});
			
			// last page button & info (if we know total records)
			var pb4;
			if (cfg.totalRecords > 0) {
				pinfo.html('Page ' + pinfo.html() + ' of ' + totp);
				var pb4		= jQuery('<a href="#">&raquo;</a>').addClass(cfg.pageEndClass).click(function(){
											var _p = p.getPage(); _p++;
											if (totp) {
												 if (_p < totp) p.setPage(totp);
											}
										});
			} else {
				pinfo.html('Page ' + pinfo.html());
			}
			
			g.append( p.append(pb1).append(pb2).append(pform).append(pb3).append(pb4).append(pload).append(pv) );
			
		}		
		
		// load via ajax (either column sort or paging)
		g.extend({
			load : function(params, cb) {
				var data = jQuery.extend(cfg.extraParams, params);
				
				// show loading canvas
				modalmask.width(b.width()).show();																			
				
				jQuery.ajax({
					type: cfg.type.toUpperCase(),
					url: cfg.url,
					data: data,
					success: function(result){						
						// for JSON return type
						if (cfg.dataType == 'json') {
							var rows  = eval( '(' + result + ')' );
							alert('json = ' + rows);
						}
						// for HTML (Table) return type
						if (cfg.dataType == 'html') {
							var table = jQuery(result);
							if (table.find('tbody tr:first td').length == cfg.colWidths.length) {
								// just swap the tbody's
								b.find('tbody').html(table.find('tbody').html());
								g.initGrid();
								
							} else {
								// inconsistent results... too many (or too few) columns returned
								alert('Error: Inconsistent results - Number of Columns in returned data [' + table.find('tbody tr:first td').length + '] do not match the number in this grid ['+ cfg.colWidths.length +'].');
							}
						}
						if (cb) cb();
					},
					error: function(){
						alert('Error: Could not load "' + cfg.url + '". Please check the URL and try again. ');
					},
					complete: function(){
						modalmask.hide();
					}
				});
				return this;
			},
			
			// returns <th> els
			getHeaders : function(cb) {
				var ths = this.find('th');
				if (cb) {
					ths.each(function(){
						cb(jQuery(this));							 
					});
					return this;
				} else {
					return ths;							 
				}
			},
			
			// returns single <th> el
			getHeader : function(i, cb) {
				var th = this.find('th').slice(i, i+1);
				if (cb) {
					cb(jQuery(this));
					return this;
				} else {
					return th;
				}
			},
			
			// returns <td> els in column i
			getColumn : function(i, cb) {
				var tds = this.find("tbody td[@" + cfg.columnIDAttr + "='" + i + "']");
				if (cb) {
					tds.each(function(){
						cb(jQuery(this));							 
					});
					return this;
				} else {
					return tds;							 
				}
			},
			
			// returns <tr> els
			getRows : function(cb) {
				var trs = this.find("tbody tr");
				if (cb) {
					trs.each(function(){
						cb(jQuery(this));							 
					});
					return this;
				} else {
					return trs;							 
				}
			},
			
			resize : function() {
				// set body table width based on header table 
				// minimize calls to width() and offset()
				var outer_w = h.width() + cfg.scrollbarW;
				b.width(outer_w);

				if (p) p.width(outer_w);
				
				if (gap) {
					var pos = h.offset();
					gap.css('left', outer_w - cfg.scrollbarW + pos.left).css('top', pos.top);
				}
			},
			
			initGrid : function() {
				
				var colWidths = new Array();
				this.getHeaders().each(function(i){
					// don't use width() - makes column headers jitter
					// colWidths[i] = jQuery(this).width();
					colWidths[i] = jQuery(this).css('width');
				});

				// make one pass of the grid, 
				// initialize properties on rows & columns
				this.getRows().each(function(r){
					
					// custom row styles (striping, etc) & hover
					if (cfg.rowClasses.length > 0) {
						var cursor = (r == 0 ? 0 : r % cfg.rowClasses.length);
						if (cfg.rowClasses[cursor] != '') {
							// custom row class
							jQuery(this).addClass(cfg.rowClasses[cursor]);							
						}
						if (cfg.rowHoverClass != '') {
							// hover class
							jQuery(this).hover(
								function() { jQuery(this).removeClass(cfg.rowClasses[cursor]).addClass(cfg.rowHoverClass); },
								function() { jQuery(this).removeClass(cfg.rowHoverClass).addClass(cfg.rowClasses[cursor]); }
							);
						}						
					}
					
					// setup column IDs & classes on row's cells
					jQuery(this).find('td').each(function(i){
						// column IDs & width
						// wrap the cell text in a div with overflow hidden, so cells aren't stretched wider by long text
						var txt = jQuery(this).html();
						jQuery(this).attr(cfg.columnIDAttr, i)
												.width(colWidths[i])
												.html( jQuery('<div />').html(txt).css('overflow', 'hidden') );
						// column colors
						if (cfg.colClasses.length > 0) {
							if (cfg.colClasses[i] != '') {
								jQuery(this).addClass(cfg.colClasses[i]);
							}
						}
					});
				});
			}
			
		})
		
		// init column width
		g.getHeaders().each(function(i){
			jQuery(this).trigger('resizeColumn', [cfg.colWidths[i]]);
		});
		
		// create a gap to fill gap over scrollport		
		var gap = jQuery('<div />').width(cfg.scrollbarW).addClass(cfg.headerClass).height(cfg.headerHeight).css({
			position: 'absolute',
			zIndex: '0'
		}).appendTo(g);

		// init grid styles, etc
		g.initGrid();
		
		// sync grid size to headers
		g.resize();

		// create a loading modalmask
		var modalmask = jQuery('<div />').width(h.width() + cfg.scrollbarW).height(b.height()).html(cfg.loadingHtml).addClass(cfg.loadingClass).css({
			position: 'absolute',
			top: b.offset().top,
			left: b.offset().left,
			zIndex: '1000'
		}).appendTo(g).hide();
		
		// load it up?
		if (cfg.initialLoad) {
			g.load();
		}

	});
};

