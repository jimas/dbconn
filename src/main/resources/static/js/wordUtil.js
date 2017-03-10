function countWords(text, language) {
	if (language == 2) {
		var words = text.replace(/[0-9]/g,'').replace(/[,;.!:—\/]/g, ' ').replace(/[^a-zA-Z\d\s&:]/g, '').match(/\S+/g);
	} else {
		var words = text.replace(/[,;.!:—\/]/g, ' ').replace(/[^a-zA-Z\d\s&:]/g, '').match(/\S+/g);
	}
	return (words !== null ? words.length : 0);
};

function countChinese(text) {
	iTotal = 0;
	for (i=0; i<text.length; i++) {
		var c = text.charAt(i);
		if (c.match(/[\u4e00-\u9fa5]/)) {
			iTotal++;
		}
	}
	return iTotal;
};
function  countDigit(text){
	var words = text.replace(/[^0-9]/g,'').replace(/[,;.!:—\/]/g, ' ')
	return (words !==null ? words.length : 0);
};
function isCheckboxChecked(id) {
	var rst = false;
	$('#' + id + ':checked').each(function() {
		rst = true;
	});
	return rst;
};
function wordCountInternational() {
	var box = $("#box");
	var count = [];
	count['digit'] = countDigit(box.val());
	count['words'] = countWords(box.val(), (isCheckboxChecked('foreignSupport') ? 2 : 0));
	chars = box.val().match(/(?:[^\r\n]|\r(?!\n))/g);
	count['chars'] = (chars !== null ? chars.length : 0);
	count['chinese'] = countChinese(box.val());
	chars_no_spaces = box.val().match(/\S/g);
	count['chars_no_spaces'] = (chars_no_spaces !== null ? chars_no_spaces.length : 0);
	sentences = box.val().match(/[^.!?\s][^.!?]*(?:[.!?](?!['"]?\s|$)[^.!?]*)*[.!?]?['"]?(?=\s|$)/g);
	count['sentences'] = (sentences !== null ? sentences.length : 0);
	paragraphs = box.val().match(/(\n\n?|^).*?(?=\n\n?|$)/g);
	count['paragraphs'] = (box.val() != '' ? (paragraphs !== null ? paragraphs.length : 0) : 0);
	count['avg_sentence_words'] = (box.val() != '' ? Math.ceil(count['words'] / count['sentences']) : 0);
	count['avg_sentence_chars'] = (box.val() != '' ? Math.ceil(count['chars'] / count['sentences']) : 0);

	displayCount(count);
	displayTextBoxes(count);
};
function displayCount(count) {
	if (count['words'] == 1) {
		wordOrWords = "（单词）";
	} else {
		wordOrWords = "（单词）";
	}
	if (count['chars'] == 1) {
		charOrChars = "（字符）";
	} else {
		charOrChars = "（字符）";
	}
	$(".counted").text("共" + count['chars'] + charOrChars + " " + count['words'] + wordOrWords + " " + count['chinese'] + "（汉字）"+count['digit'] +"（数字）");
};

function displayTextBoxes(count) {
	$("#word_count").text(count['words']);
	$("#character_count").text(count['chars']);
	$("#character_count_no_spaces").text(count['chars_no_spaces']);
	$("#chinese_count_no_spaces").text(count['chinese']);

	$("#sentence_count").text(count['sentences']);
	$("#paragraph_count").text(count['paragraphs']);
	$("#avg_sentence_words").text(count['avg_sentence_words']);
	$("#avg_sentence_chars").text(count['avg_sentence_chars']);
};
$(function(){
	var box = $("#box");
	//将函数绑定到 keypress 事件
	box.keypress(wordCountInternational).blur(wordCountInternational).focus(wordCountInternational).change(wordCountInternational).keyup(wordCountInternational).keydown(wordCountInternational).load(wordCountInternational);
	$('#stopWordsCommon').change(function(){
		wordCountInternational();
	});
	$('#foreignSupport').change(function(){
		wordCountInternational();
	});
	wordCountInternational();
});