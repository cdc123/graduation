$(function() {
	getVideoSort();
	getVideoRegion();
	getVideoType();
	getSelectVideo();
});

function getVideoSort() {
	$("#flSort").val("全部");
	$
			.ajax({
				type : "post",
				url : "/filmLibrary/getVideoSort",
				dataType : "json",
				async : false,
				success : function(result) {
					if (result != null && result != "") {
						$
								.each(
										result,
										function(index, item) {
											var sort = item.video_sort;
											$node = $("<a name="
													+ sort
													+ " onclick='getSelectSort(this)' style='margin-left:10px'>"
													+ sort + "</a>");
											$("a[name='上传']").before($node);
										});
					}
				}
			});
}

function getSelectSort(event) {
	var sort = event.name;
	$("#flSort").val(sort);
	$("#flSort a").css("color", "#666");
	event.style.color = "red";
	if (sort == "上传") {
		$("#flRegion").hide();
		$("#flType").hide();
		$("#flOrder").children().eq(2).hide();
		$("#flOrder").children().eq(3).hide();
		$("#flOrder").children().eq(0).prop("selected", true);
	} else {
		$("#flRegion").show();
		$("#flType").show();
		$("#flOrder").children().eq(2).show();
		$("#flOrder").children().eq(3).show();
	}
	start = 0;
	getSelectVideo();
}

function getVideoRegion() {
	$("#flRegion").val("全部");
	$
			.ajax({
				type : "post",
				url : "/filmLibrary/getVideoRegion",
				dataType : "json",
				async : false,
				success : function(result) {
					if (result != null && result != "") {
						$
								.each(
										result,
										function(index, item) {
											var region = item.video_region;
											$node = $("<a name="
													+ region
													+ " onclick='getSelectRegion(this)' style='margin-left:10px'>"
													+ region + "</a>");
											$("#flRegion").append($node);
										});
					}
				}
			});
}

function getSelectRegion(event) {
	var region = event.name;
	$("#flRegion").val(region);
	$("#flRegion a").css("color", "#666");
	event.style.color = "red";
	start = 0;
	getSelectVideo();
}

function getVideoType() {
	$("#flType").val("全部");
	$
			.ajax({
				type : "post",
				url : "/filmLibrary/getVideoType",
				dataType : "json",
				async : false,
				success : function(result) {
					$
							.each(
									result,
									function(index, item) {
										var type = item.video_type;
										$node = $("<a name="
												+ type
												+ " onclick='getSelectType(this)' style='margin-left:10px'>"
												+ type + "</a>");
										$("#flType").append($node);
									});
				}
			});
}

function getSelectType(event) {
	var type = event.name;
	$("#flType").val(type);
	$("#flType a").css("color", "#666");
	event.style.color = "red";
	start = 0;
	getSelectVideo();
}

var start = 0;
/* 获取视频 */
function getSelectVideo() {
	var sort = String($("#flSort").val());
	var region = String($("#flRegion").val());
	var type = String($("#flType").val());
	var order = Number($("#flOrder").val());
	if (sort != "上传") {
		$
				.ajax({
					type : "post",
					url : "/filmLibrary/getLimitVideoBySRTO",
					data : {
						"sort" : sort,
						"region" : region,
						"type" : type,
						"order" : order
					},
					dataType : "json",
					async : false,
					success : function(result) {
						$("#videoArea").html("");
						if (result != null && result != "") {
							var totalVideoNum = result.length;
							/* 显示总视频数 */
							$("#totalVideoNum").text(totalVideoNum);
							/* 显示总页数 */
							var shang = Number(totalVideoNum) / 18;
							shang = Math.floor(shang);
							var yu = Number(totalVideoNum) % 18;
							if (yu > 0) {
								shang = shang + 1;
							}
							$("#totalPageNum").text(shang);
							/* 显示各页数选项 */
							$("#pageNumItem")
									.html(
											"<a title='1' onclick='choosePageNumItem(this)'>1</a> ");
							for (var i = 2; i < (shang + 1); i++) {
								$pageNumItem = $("<a title=" + i
										+ " onclick='choosePageNumItem(this)'>"
										+ i + "</a> ");
								$("#pageNumItem").append($pageNumItem);
							}
							/* 显示当前页数为1 */
							$("#currentPageNum").val(1);
							cleanUnderline();
							var num = 0;
							$
									.each(
											result,
											function(index, item) {

												if (num > 17) {
													return false;
												} else {
													var name = item.video_name;
													if (name.length > 10) {
														name = name.substring(
																0, 10)
																+ "...";
													}
													var videoImage = "../image/wcg_images/noPicture.jpg";
													if (item.video_image != null
															&& item.video_image != "") {
														videoImage = item.video_image;
													}
													var $node = $("<div style='width: 200px; height: 300px; float: left; margin-left: 13px; margin-top: 10px;'><div style='width: 180px; height: 260px; margin: 0 auto;'><img onclick='playVideo(this)' title="
															+ item.video_name
															+ " src="
															+ videoImage
															+ " style='width: 100%; height: 100%;'></div><div style='width: 180px; height: 40px; margin: 0 auto;'><p onclick='playVideo(this)' title="
															+ item.video_name
															+ " style='width: 100%; height: 100%; font-size: 16px; line-height: 40px; text-align: center;'>"
															+ name
															+ "</p></div></div>");
													$("#videoArea").append(
															$node);
													num = num + 1;
												}
											});
						}
					}
				});
	} else {
		$
				.ajax({
					type : "post",
					url : "/filmLibrary/getLimitUpVideoBySRTO",
					data : {
						"order" : order
					},
					dataType : "json",
					async : false,
					success : function(result) {
						$("#videoArea").html("");
						if (result != null && result != "") {
							var totalVideoNum = result.length;
							/* 显示总视频数 */
							$("#totalVideoNum").text(totalVideoNum);
							/* 显示总页数 */
							var shang = Number(totalVideoNum) / 18;
							shang = Math.floor(shang);
							var yu = Number(totalVideoNum) % 18;
							if (yu > 0) {
								shang = shang + 1;
							}
							$("#totalPageNum").text(shang);
							/* 显示各页数选项 */
							$("#pageNumItem")
									.html(
											"<a title='1' onclick='choosePageNumItem(this)'>1</a> ");
							for (var i = 2; i < (shang + 1); i++) {
								$pageNumItem = $("<a title=" + i
										+ " onclick='choosePageNumItem(this)'>"
										+ i + "</a> ");
								$("#pageNumItem").append($pageNumItem);
							}
							/* 显示当前页数为1 */
							$("#currentPageNum").val(1);
							cleanUnderline();
							var num = 0;
							$
									.each(
											result,
											function(index, item) {
												if (num > 17) {
													return false;
												} else {
													var name = item.upv_name;
													if (name.length > 10) {
														name = name.substring(
																0, 10)
																+ "...";
													}
													var videoImage = "../image/wcg_images/noPicture.jpg";
													var $node = $("<div style='width: 200px; height: 300px; float: left; margin-left: 13px; margin-top: 10px;'><div style='width: 180px; height: 260px; margin: 0 auto;'><img onclick='playUpVideo(this)' name="
															+ item.upv_id
															+ " title="
															+ item.upv_name
															+ " src="
															+ videoImage
															+ " style='width: 100%; height: 100%;'></div><div style='width: 180px; height: 40px; margin: 0 auto;'><p onclick='playUpVideo(this)' title="
															+ item.upv_name
															+ " style='width: 100%; height: 100%; font-size: 16px; line-height: 40px; text-align: center;'>"
															+ name
															+ "</p></div></div>");
													$("#videoArea").append(
															$node);
													num = num + 1;
												}
											});
						}
					}
				});
	}
}
/* 分页start */
function showFirstPage() {
	var currentPageNum = Number($("#currentPageNum").val());
	if (currentPageNum != 1) {
		$("#currentPageNum1").val(1);
		start = 0;
		cleanUnderline();
		refresh();
	}
}

function showPreviousPage() {
	var currentPageNum = Number($("#currentPageNum").val());
	if (currentPageNum != 1) {
		var previousPageNum = Number(currentPageNum - 1);
		$("#currentPageNum").val(previousPageNum);
		start = Number(previousPageNum - 1) * 18;
		cleanUnderline();
		refresh();
	} else {
		layer.msg("这已经是第一页了");
	}
}

function choosePageNumItem(event) {
	var currentPageNum = Number($("#currentPageNum").val());
	var choose = Number(event.title);
	if (currentPageNum != choose) {
		$("#currentPageNum").val(choose);
		start = Number(choose - 1) * 18;
		cleanUnderline();
		refresh();
	}
}

function showNextPage() {
	var currentPageNum = Number($("#currentPageNum").val());
	var totalPageNum = Number($("#totalPageNum").text());
	if (currentPageNum != totalPageNum) {
		var nextPageNum = Number(currentPageNum + 1);
		$("#currentPageNum").val(nextPageNum);
		start = currentPageNum * 18;
		cleanUnderline();
		refresh();
	} else {
		layer.msg("这已经是最后一页了");
	}
}

function showLastPage() {
	var currentPageNum = Number($("#currentPageNum").val());
	var totalPageNum = Number($("#totalPageNum").text());
	if (currentPageNum != totalPageNum) {
		$("#currentPageNum").val(totalPageNum);
		start = Number(totalPageNum - 1) * 18;
		cleanUnderline();
		refresh();
	}
}

function gotoPage() {
	var gotoPageNum = $("#pageNum").val();
	var totalPageNum = Number($("#totalPageNum").text());
	if (gotoPageNum != null && gotoPageNum != "") {
		var regexNum = /^[0-9]+.?[0-9]*$/;
		if (!regexNum.test(gotoPageNum)) {
			layer.msg("请输入数字");
			$("#pageNum").val("")
		} else if (gotoPageNum < 1 || gotoPageNum > totalPageNum) {
			layer.msg("请输入正确的页数");
			$("#pageNum").val("")
		} else {
			$("#currentPageNum").val(Number(gotoPageNum));
			start = (Number(gotoPageNum) - 1) * 18;
			cleanUnderline();
			refresh();
		}
	}
}

function cleanUnderline() {
	var currentPageNum = Number($("#currentPageNum").val());
	$("#pageNumItem a").attr("style",
			"text-decoration: underline;cursor: pointer;");
	$("#pageNumItem a[title=" + currentPageNum + "]").attr("style",
			"text-decoration: none;cursor: pointer;");
}

function refresh() {
	var sort = String($("#flSort").val());
	var region = String($("#flRegion").val());
	var type = String($("#flType").val());
	/*
	 * 1:video_id desc; 2:video_id asc; 3:video_playtimes desc;
	 * 4:video+playtimes asc
	 */
	var order = Number($("#flOrder").val());
	if (sort != "上传") {
		$
				.ajax({
					type : "post",
					url : "/filmLibrary/getLimitVideoBySRTO",
					data : {
						"sort" : sort,
						"region" : region,
						"type" : type,
						"order" : order,
						"start" : Number(start)
					},
					dataType : "json",
					async : false,
					success : function(result) {
						if (result != null && result != "") {
							$("#videoArea").html("");
							$
									.each(
											result,
											function(index, item) {
												var name = item.video_name;
												if (name.length > 10) {
													name = name
															.substring(0, 10)
															+ "...";
												}
												var videoImage = "../image/wcg_images/noPicture.jpg";
												if (item.video_image != null
														&& item.video_image != "") {
													videoImage = item.video_image;
												}
												var $node = $("<div style='width: 200px; height: 300px; float: left; margin-left: 13px; margin-top: 10px;'><div style='width: 180px; height: 260px; margin: 0 auto;'><img onclick='playVideo(this)' title="
														+ item.video_name
														+ " src="
														+ videoImage
														+ " style='width: 100%; height: 100%;'></div><div style='width: 180px; height: 40px; margin: 0 auto;'><p onclick='playVideo(this)' title="
														+ item.video_name
														+ " style='width: 100%; height: 100%; font-size: 16px; line-height: 40px; text-align: center;'>"
														+ name
														+ "</p></div></div>");
												$("#videoArea").append($node);
											});
						}
					}
				});
	} else {
		$
				.ajax({
					type : "post",
					url : "/filmLibrary/getLimitUpVideoBySRTO",
					data : {
						"order" : order,
						"start" : Number(start)
					},
					dataType : "json",
					async : false,
					success : function(result) {
						if (result != null && result != "") {
							$("#videoArea").html("");
							$
									.each(
											result,
											function(index, item) {
												var name = item.upv_name;
												if (name.length > 10) {
													name = name
															.substring(0, 10)
															+ "...";
												}
												var videoImage = "../image/wcg_images/noPicture.jpg";
												var $node = $("<div style='width: 200px; height: 300px; float: left; margin-left: 13px; margin-top: 10px;'><div style='width: 180px; height: 260px; margin: 0 auto;'><img onclick='playUpVideo(this)' name="
														+ item.upv_id
														+ " title="
														+ item.upv_name
														+ " src="
														+ videoImage
														+ " style='width: 100%; height: 100%;'></div><div style='width: 180px; height: 40px; margin: 0 auto;'><p onclick='playUpVideo(this)' title="
														+ item.upv_name
														+ " style='width: 100%; height: 100%; font-size: 16px; line-height: 40px; text-align: center;'>"
														+ name
														+ "</p></div></div>");
												$("#videoArea").append($node);
											});
						}
					}
				});
	}
}
/* 分页end */
/* 视频播放(title = name) */
function playVideo(event) {
	var videoName = event.title;
	$.ajax({
		type : "post",
		url : "/home/getVideoByName",
		data : {
			"videoName" : videoName
		},
		dataType : "json",
		success : function(result) {
			window.location.href = "play.html";
		}
	});

}
/* 上传视频播放(title = name) */
function playUpVideo(event) {
	var upvName = event.title;
	$.ajax({
		type : "post",
		url : "/home/getUpVideoByName",
		data : {
			"videoName" : upvName
		},
		dataType : "json",
		success : function(result) {
			window.location.href = "play.html";
		}
	});
}
