Date.prototype.format = function(format) {
	var date = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '')
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k]
					: ("00" + date[k]).substr(("" + date[k]).length));
		}
	}
	return format;
};
$('.form_datetime').datetimepicker({
	language : 'zh-CN',//中文，需要引用zh-CN.js包
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	format : "yyyy-mm-dd hh:ii:ss",
	beforeShow : getCurrentTime,
	todayHighlight : 1,
	startView : 2,
	forceParse : 0,
	showMeridian : 1
});
$('.form_date').datetimepicker({
	language : 'zh-CN',
	weekStart : 1,
	todayBtn : 1,
	format : 'yyyy-mm-dd',
	autoclose : 1,
	beforeShow : getCurrentTime,
	todayHighlight : 1,
	startView : 2,
	minView : 2,
	forceParse : 0
});
$('.form_time').datetimepicker({
	language : 'zh-CN',
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	format : "hh:ii:ss",
	beforeShow : getCurrentTime,
	todayHighlight : 1,
	startView : 1,
	minView : 0,
	maxView : 1,
	forceParse : 0
});

function getCurrentTime() {
	$(".ui-datepicker-current").click();
}

$(function() {
	//时间截转换日期
	$('#times_tamp_to_times_date').click(function() {
		var times_tamp = $('#times_tamp').val();
		times_tamp = times_tamp.replace(/(^\s*)|(\s*$)/g, "");
		if (/^\d{10}$/.test(times_tamp)) {
			times_tamp *= 1000;
		} else if (/^\d{13}$/.test(times_tamp)) {
			times_tamp = parseInt(times_tamp);
		} else {
			Utils.alert('时间戳格式不正确！');
			return;
		}
		var newDate = new Date();
		newDate.setTime(times_tamp);
		$('#times_date').val(newDate.format('yyyy-MM-dd hh:mm:ss'));
	});

	//日期转换时间截
	$('#times_date_to_times_tamp').click(function() {
		var times_date2 = $('#times_date2').val();
		var timestamp2 = Date.parse(new Date(times_date2));
		$('#times_tamp2').val(timestamp2 / 1000);
	});

	//日期时间差
	$('#start_end_date').click(function() {
		var date1 = new Date($('#start_date').val()); //开始时间
		var date2 = new Date($('#end_date').val()); //结束时间
		var date3 = date2.getTime() - date1.getTime() //时间差的毫秒数

		//计算出相差天数
		var days = Math.floor(date3 / (24 * 3600 * 1000))

		//计算出小时数
		var leave1 = date3 % (24 * 3600 * 1000) //计算天数后剩余的毫秒数
		var hours = Math.floor(leave1 / (3600 * 1000))
		//计算相差分钟数
		var leave2 = leave1 % (3600 * 1000) //计算小时数后剩余的毫秒数
		var minutes = Math.floor(leave2 / (60 * 1000))

		//计算相差秒数
		var leave3 = leave2 % (60 * 1000) //计算分钟数后剩余的毫秒数
		var seconds = Math.round(leave3 / 1000)

		$('#result_day').val(days + '天');
	});

	//前几天
	$('#js_old_date').click(function() {
		var currentDate = new Date($('#start_date').val());
		var old_date_count = $('#old_date').val();
		var timeStamp=currentDate.valueOf()-old_date_count*24*60*60*1000;
		var result_old_date = new Date();
		result_old_date.setTime(parseInt(timeStamp));
		$('#result_old_date').val(result_old_date.format("yyyy-MM-dd"));
	});

	//后几天
	$('#js_new_date').click(function() {
		var currentDate = new Date($('#start_date').val());
		var new_date_count = $('#new_date').val();
		var timeStamp=currentDate.valueOf()+new_date_count*24*60*60*1000;
		var result_new_date =new Date();
		result_new_date.setTime(parseInt(timeStamp));
		$('#result_new_date').val(result_new_date.format("yyyy-MM-dd"));
	});

	var newDate = new Date();
	var now_date = newDate.format('yyyy-MM-dd');
	$('#start_date').val(now_date);
	$('#end_date').val(now_date);
});
