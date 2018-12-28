//记录当前被选中的复选框的个数
var CheckedNum = 0;
/*
 * 选中所有的复选框，checkallbox是提供选择所有复选框的复选框，checksbox是要选中的复选框数组。
 */
function checkAll(checkallbox,checksbox){
	var length = checksbox == undefined ? 0 : checksbox.length == undefined ? 1 : checksbox.length;
	if ( length > 1 ){
	  for( i = 0 ; i < length ; i ++ ){
		  checksbox[i].checked = checkallbox.checked;
	  }
	}                
	else{
	  if ( length == 1 ) checksbox.checked = checkallbox.checked;
	}  

	if ( checkallbox.checked ) CheckedNum = length;
	else CheckedNum = 0;
}

/*
 * 选中所有的复选框，checkallbox是提供选择所有复选框的复选框，checksbox是要选中的复选框数组。
 */
function checkAllEnabled(checkallbox,checksbox){
	var length = checksbox == undefined ? 0 : checksbox.length == undefined ? 1 : checksbox.length;
	if ( length > 1 )
	  for( i = 0 ; i < length ; i ++ ){
		  if (checksbox[i].disabled == false)
				checksbox[i].checked = checkallbox.checked;
	  }
	else
	  if ( length == 1 ) checksbox.checked = checkallbox.checked;

	if ( checkallbox.checked ) CheckedNum = length;
	else CheckedNum = 0;
}

/*
 * 选中一个复选框obj,checkallbox是提供选中所有复选框功能的复选框，obj所在的复选框数组
 */
function checkA(checkallbox,checksbox,obj){
	var length = checksbox == undefined ? 0 : checksbox.length == undefined ? 1 : checksbox.length;

	if ( obj.checked )  CheckedNum ++;
	else  CheckedNum --;

	if ( CheckedNum == length ) checkallbox.checked = true;
	else checkallbox.checked = false;
}

/*
 *  检查checksbox是否有复选框被选中，如果checksbox没定义则显示noDefineHint并返回1,如果没选中则显示noSelectHint并返回2。
 *  如果有则返回0。
 */
function checkSelectByHint(checksbox,noDefineHint,noSelectHint){
	var result = checkSelect(checksbox)
	if( result == 1)
	  alert(noDefineHint);
	else if( result ==2 )
	  alert(noSelectHint);

	return result;
}

/*
 *  检查checksbox是否有复选框被选中，如果checksbox没定义则返回1,如果没选中则并返回2。
 *  如果有则返回0。
 */
function checkSelect(checksbox){
	if( checksbox == undefined || checksbox==null )
		return 1;
	if( checksbox.length == undefined ){
		if( checksbox.checked )
			return 0;
		else
			return 2;
	}else{
		var i;
		for(i=0;i<checksbox.length;i++)
		   if( checksbox[i].checked )
			  return 0;
		return 2;
	}
}


