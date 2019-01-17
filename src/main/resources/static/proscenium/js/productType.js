mui.ready(function() {
	// mui.toast('提示延长延长延长');
	// 选择物品类型
	(function() {
		var id = mui("#wplx");
		var btn = mui(".j_wp");
		var dText = mui("#kdwpText");
		id.on("tap", ".j_wp", function() {
			btn.each(function() {
				this.classList.remove("mui-btn-primary");
			});
			this.classList.add("mui-btn-primary");
			dText[0].innerHTML = this.innerHTML;
			$("#goodsinfomation").val(this.innerHTML);
			mui('#picture').popover('toggle');
		});
	})();

	// 物品重量/保价金额
	(function() {
		var d = mui(".g-m-r-input1");
		var text = mui(".j_p3Text");
		mui('#picture3').on("tap", "#p3Define", function() {
			if (d[0].value)
				$("#spWeight").html(d[0].value);
			else
				$("#spWeight").html("0.00");
			if (d[1].value)
				$("#spInsure").html(d[1].value);
			else
				$("#spInsure").html("0.00");

		});
	})();
});

// 判断选中阅读并同意《快递鸟寄件协议》
(function() {
	mui(document).on("tap", ".k-sp-check", function() {
		if ($(this).hasClass("k-sp-active")) {
			$(this).removeClass("k-sp-active");
		} else {
			$(this).addClass("k-sp-active");
		}
	});
})();
// 智选快递逻辑判断test，
;
(function() {
	mui(document).on("tap", "#k-next", function() {
		if ($(".k-sp-check").hasClass("k-sp-active")) {
			$("#company-back").hide();
			$("#auto_couries").show();
			$("#company").hide();
		}
	});
	mui(document).on("tap", "#protocol-details", function() {
		$("#company-back").hide();
		$("#protocol").show();
	});
	mui(document).on("tap", "#protocol-back", function() {
		$("#company-back").show();
		$("#protocol").hide();

	});

	mui(document).on("tap", "#auto_couries_btn", function() {
		$("#company-back").show();
		$("#auto_couries").hide();
		$("#company").hide();
	});
	mui(document).on("tap", "#company-showBtn", function() {
		$("#company").show();
		$("#company-back").hide();
		$("#auto_couries").hide();
	})

	mui(document).on(
			"tap",
			".j_wp",
			function() {
				$(this).addClass("mui-btn-primary").siblings().removeClass(
						"mui-btn-primary");
				var txt = $(this).text();

				$(".g-goods").html(txt)

			})
})();