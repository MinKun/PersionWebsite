var pageIndex = "2";
var defaultPageSize = 10;
var pageNumber = 1;
var totalSize = 0;
var totalPage = 0;
var baseUrl = sessionStorage.getItem("baseUrl");

function queryList() {
	var queryData = {pageNumber: pageNumber, pageSize : defaultPageSize};
	jQuery.ajax({
		url: baseUrl + "/persion-website/article",
		type: "GET",
		contentType: "application/json",
		data: queryData,
		dataType: "json",
		success: initTable
	});
}

function initTable(result) {
	var code = result.code;
	if (code != null && code == "1") {
		var resp = result.resp;
		totalPage = resp.totalPage;
		totalSize = resp.totalSize;
		buildTableBody(resp.dataList);
	}
}

function buildTableBody(dataList) {
	var tableHtml = "";
	if (dataList != null && dataList.length > 0) {
		var data = null;
		var articleListTmp = null;
		var articleLabel = null;
		var articleLabelSpan = "";
		var articleStatus = null;
		var articleStatusName = null;
		var updateTime = null;
		var releaseTime = null;
		var btnName = null;
		for (var i = 0; i < dataList.length; i++) {
			articleListTmp = $("#article_list_tmp").html();
			data = dataList[i];

			articleLabel = data.articleLabel;
			articleLabelSpan = "";
			if (articleLabel != null && articleLabel.length > 0) {
				var labelArray = articleLabel.split(" ");
				for(var j = 0; j < labelArray.length; j++) {
					articleLabelSpan += "<span class='label label-success'>" + labelArray[j] + "</span>&nbsp;"
				}
			}

			articleStatus = data.articleStatus;
			if (articleStatus == "1") {
				articleStatusName = "<p>草稿</p>";
				btnName = "上架";
			} else if (articleStatus == "2") {
				articleStatusName = "<p>发布</p>";
				btnName = "下架";
			} else if (articleStatus == "3") {
				articleStatusName = "<p>下架</p>";
				btnName = "上架";
			}
			updateTime = data.updateTime;
			if (updateTime == null) {
				updateTime = "";
			}
			releaseTime = data.releaseTime;
			if (releaseTime == null) {
				releaseTime = "";
			} else {
				releaseTime = releaseTime.substring(0, 10);
			}
			var html = articleListTmp.replace("@title", data.articleTitle)
				.replace("@type", data.articleTypeName)
				.replace("@label", articleLabelSpan)
				.replace("@status", articleStatusName)
				.replace("@create_time", data.createTime.substring(0, 10))
				.replace("@release_time", releaseTime)
				.replace("@btnName", btnName);
			tableHtml += html;
		}
		$("#article_list_tbody").html(tableHtml);
	}
}

$(function(){
	var baseUrl = sessionStorage.getItem("baseUrl");
	var page_head_url = baseUrl + "/persion-website/admin/page_head.html";
    $("#head_div").load(page_head_url, function(){
        // 回调
        setActiveMenu(pageIndex);
    });

	queryList();
});