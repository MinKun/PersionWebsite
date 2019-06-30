function setActiveMenu(pageIndex) {
	var li = $("#head_div li");
	for (var i = 0; i < li.length; i++) {
		if ($(li[i]).attr("pageIndex") == pageIndex) {
			$(li[i]).attr("class", "active");
			break;
		}
	}
}