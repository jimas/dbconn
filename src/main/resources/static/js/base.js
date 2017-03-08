
Utils = {
	$js_i18n : null,
	initjs_i18n : function(js_i18n) {
		var defaults_js_i18n = {
			close : "关闭",
			tips : "提示",
			makeSure : "确定",
			cancle : "取消",
			title : "标题",
			disposes : "处理中..."
		};
		js_i18n = $.extend(defaults_js_i18n, js_i18n);
		$js_i18n = js_i18n;
	},
	lang : "cn",
	alert : function(content, fun) {
//		Utils.initjs_i18n();
		var r = (Math.random() + '').replace(/\./g, '');
		if (this.$alert) {
			this.$alert.find('.modal-body').html(content);
			// this.$alert.remove();
			// $('.modal-backdrop:eq(0)').remove();
		} else {
			var id = 'alert' + r;
			var alert = '<div class="modal fade bs-example-modal-sm '
					+ id
					+ '">'
					+ '<div class="modal-dialog modal-sm">'
					+ '<div class="modal-content">'
					+ '<div class="modal-header">'
					+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
					+ '<h4 class="modal-title">'
					+ $js_i18n.tips
					+ '</h4>'
					+ '</div>'
					+ '<div class="modal-body">'
					+ content
					+ '</div>'
					+ '<div class="modal-footer">'
					+ '<button type="button" class="btn btn-default" data-dismiss="modal">'
					+ $js_i18n.close + '</button>' + '</div>'
					+ '</div></div></div>';
			$(document.body).append(alert);
			this.$alert = $('.' + id);
		}
		if (typeof fun == 'function') {
			this.$alert.find('.modal-footer button').unbind('click').click(
					function() {
						fun.call(this);
					});
		}
		this.$alert.modal('show');
		this.$alert.css("z-index", r); //解决多个弹窗同时调用时出现层覆盖问题
	}

}