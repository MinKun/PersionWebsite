var pageIndex = "1";

$(function(){
	var baseUrl = sessionStorage.getItem("baseUrl");

    var page_head_url = baseUrl + "/persion-website/admin/page_head.html";
    $("#head_div").load(page_head_url, function(){
        // 回调
        setActiveMenu(pageIndex);
    });
});