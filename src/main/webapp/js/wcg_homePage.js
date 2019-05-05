$(function() {
	getUserBySession();
	/* 事件绑定,点击搜索栏下拉框选项时将选项填入搜索框 */
	$("#forSearch ul").on("click", "li", function(event) {
		var target = $(event.target);
		var value = target.text();
		$("input[name='search']").val(value);
		$("#forSearch").hide();
	});
	/* 事件委托,续播 */
	$("#forHistory").on("click", "a", function(event) {
		var target = $(event.target);
		var videoName = target.text();
		$.ajax({
			type : "post",
			url : "/home/getVideoByName",
			data : {
				"videoName" : videoName
			},
			dataType : "json",
			success : function(res) {
				window.location.href = "play.html";
			}
		});
	});
	// 电影显示
	$.ajax({
		url : "/HomeShowServlet",
		data : {
			"video_sort" : "电影"
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			for (var j = 1; j < 6; j++) {
				$("#body_two_content" + j + "").children().eq(1).children()
						.children().children().text(result[j].video_name);
				$("#body_two_content" + j + "").children().eq(0).attr("src",
						result[j].video_image);
				$("#body_two_content" + j + "").children().eq(0).attr("id",
						result[j].video_id);
			}
		},
		error : function() {
			// alert("展示数据错误！！");
		}
	});
	// 电影传session
	for (var i = 1; i < 6; i++) {
		// 立即调用的函数表达式（IIFE）
		(function(i) {
			$("#body_two_content" + i + "").children().eq(0).click(
					function() {
						var $vid_session = $("#body_two_content" + i + "")
								.children().eq(0).attr("id");
						$.ajax({
							url : "/CdcMidSetsessionServlet",
							data : {
								"video_id" : $vid_session
							},
							type : "post",
							dataType : "json",
							success : function(result) {
								// alert(2);
								location.href = "play.html";
							},
							error : function() {
								// alert("展示数据错误！！");
							}
						});
					});
		})(i);
	}
	;
	// 剧集显示
	$.ajax({
		url : "/HomeShowServlet",
		data : {
			"video_sort" : "剧集"
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			// alert($("#body_three_content1").children().eq(1).children().eq(0).children().text());
			for (var j = 1; j < 6; j++) {
				$("#body_three_content" + j + "").children().eq(1).children()
						.eq(0).children().text(result[j].video_name);
				$("#body_three_content" + j + "").children().eq(0).attr("src",
						result[j].video_image).css("width", "192px").css(
						"height", "288px");
				$("#body_three_content" + j + "").children().eq(0).attr("id",
						result[j].video_id);
			}
		},
		error : function() {
			// alert("展示数据错误！！");
		}
	});
	// 剧集传session
	for (var i = 1; i < 6; i++) {
		// 立即调用的函数表达式（IIFE）
		(function(i) {
			$("#body_three_content" + i + "").children().eq(0).click(
					function() {
						var $vid_session = $("#body_three_content" + i + "")
								.children().eq(0).attr("id");
						$.ajax({
							url : "/CdcMidSetsessionServlet",
							data : {
								"video_id" : $vid_session
							},
							type : "post",
							dataType : "json",
							success : function(result) {
								// alert(2);
								location.href = "play.html";
							},
							error : function() {
								// alert("展示数据错误！！");
							}
						});
					});
		})(i);
	}
	;
});
/* 轮播数组 */
var $lunboarr = new Array("../image/lh_images/0.jpg",
		"../image/lh_images/1.jpg", "../image/lh_images/2.jpg",
		"../image/lh_images/3.jpg", "../image/lh_images/4.jpg",
		"../image/lh_images/5.jpg", "../image/lh_images/6.jpg");
var $i = 1;
/* 单独加载轮播 */
$(function() {
	setTimeout("changeImage()", 3000);
});

/* 轮播函数(小花立功) */
function changeImage() {
	if ($i > 6) {
		$i = 0;
	}
	$("#body").css("background-image", "url(" + $lunboarr[$i] + ")");
	/* 设置背景图片 */
	$i++;
	setTimeout("changeImage()", 3000);
}

/*--------------------------------------------------------------------------------------------------------------------------------------------------*/
/* 查询session是否有有用户信息 */
function getUserBySession() {
	$.ajax({
		type : "post",
		url : "/home/getUserBySession",
		dataType : "json",
		success : function(result) {
			if (result != null && "" != result) {
				$("#isLogin").val("1");
				$.each(result, function(index, item) {
					$("#userId").val(item.userId);
					$("#userPhone").val(item.userPhone);
					$("#userPower").val(item.userPower);
					$("#userName").val(item.userName);
					$("#userSex").val(item.userSex);
					$("#userAddress").val(item.userAddress);
					$("#userBirthday").val(item.userBirthday);
					$("#userIntroduce").val(item.userIntroduce);
					$("#userImage").val(item.userImage);
					if ($("#userImage").val() != null
							&& $("#userImage").val() != "") {
						$("#photoImg").css("background-image",
								"url(item.get('userImage'))");
					} else {
						$("#photoImg").css("background-image",
								"url('../image/wcg_images/loginPhoto.jpg')");
					}
				});
			}
		}
	});
}
/* 点击导航显示隐藏菜单 */
var forNavState;
function showForNav() {
	forNavState = $("#forNav").css('display');
	if (forNavState == "none") {
		$("#forNav").show(100);
	} else {
		$("#forNav").hide(100);
	}
}
/* 隐藏导航菜单 */
function hideForNav() {
	$("#forNav").hide(100);
}
/* 根据搜索框输入关键字进行模糊查询 */
function getVideoByFirstName() {
	$("#forSearch ul").html("");
	$("#forSearch").show();
	if ($("input[name='search']").val() != null
			&& $("input[name='search']").val() != "") {
		$.ajax({
			type : "post",
			url : "/home/getVideoByFirstName",
			data : {
				"firstName" : $("input[name='search']").val()
			},
			dataType : "json",
			success : function(result) {
				var keyWord;
				var array = [];
				$.each(result, function(n, value) {
					keyWord = $("input[name='search']").val();
					array = value.split(keyWord);
					var $node = $("<li style='cursor:pointer'>" + array[0]
							+ "<span style='color:red'>" + keyWord + "</span>"
							+ array[1] + "</li>");
					$("#forSearch ul").append($node);
				});
			}
		});
	}
}
/* 点击搜索按钮查询影片 */
function getVideoByName() {
	if ($("input[name='search']").val() != null
			&& $("input[name='search']").val() != '') {
		$.ajax({
			type : "post",
			url : "/home/getVideoByName",
			data : {
				"videoName" : $("input[name='search']").val()
			},
			dataType : "json",
			success : function(result) {
				if (result.length > 0) {
					location.href = "wcg_videoSearched.html";
				} else {
					layer.msg('未找到该影片 ！！！');
				}
			}
		});
	} else {
		layer.msg('请输入视频关键字 ！！！');
	}
}
/* 点击头像判断登录状态响应 */
/* 鼠标经过头像 */
function showForPhoto() {
	if ($("#isLogin").val() == "1") {
		$("#forPhoto").show(100);
	}
}
/* 鼠标离开头像弹出列表 */
function hideForPhoto() {
	$("#forPhoto").hide(200);
}
/* 点击头像 */
function ShowLoginText() {
	if ($("#isLogin").val() == "0") {
		layer.open({
			type : 2,
			title : false,
			area : [ '760px', '350px' ],
			content : 'login.html',
		});
	} else {
		window.location.href = "wcg_userInfo.html";
	}
}/**/
/* 登出 */
function logOut() {
	$.ajax({
		url : "/home/logOut",
		type : "post",
		dataType : "text",
		success : function(result) {
			window.location.reload();
		}
	});
}/* 上传 */
/* 上传功能 */
function upload() {
	if ($("#isLogin").val() == "1") {
		window.location.href = "wcg_userInfo.html#up";
	} else {
		layer.open({
			type : 2,
			title : false,
			area : [ '760px', '350px' ],
			content : 'login.html',
		});
	}
}
/* 点击历史纪录 */
function showHistory() {
	if ($("#isLogin").val() == "1") {
		/* 清空历史 */
		$("#forHistory").html("");
		/* 查询显示个人观看历史 */
		$
				.ajax({
					type : "post",
					url : "/home/getHistoryByUserId",
					data : {
						"userId" : $("#userId").val()
					},
					dataType : "json",
					async : false,
					success : function(result) {
						/* 最终的$node */
						var $history;
						/* 设置判断条件 */
						var yearfirst = 0;
						var monthfirst = 0;
						var datefirst = 0;
						var hourfirst = 0;
						var minutefirst = 0;
						/* 取出每一条历史记录 */
						$
								.each(
										result,
										function(n, value) {
											/* 取出video_id */
											var videoId = value.video_id;
											var videoName = "";
											/* 根据videoId查出视频名称,存入videoName */
											$.ajax({
												type : "post",
												url : "/home/getVideoById",
												data : {
													"videoId" : videoId
												},
												dataType : "text",
												async : false,
												success : function(res) {
													videoName = res;
												}
											});
											/* 取出历史时间 */
											var historyTime = new Date(
													value.history_time);/* value.history_time */
											/* 取出年份 */
											var year = historyTime
													.getFullYear()
													+ "年";
											/* 取出月，日 */
											var month_date = historyTime
													.getMonth()
													+ 1
													+ "月"
													+ historyTime.getDate()
													+ "日";
											/* 取出时，分 */
											var hour_minute = historyTime
													.getHours()
													+ "时"
													+ historyTime.getMinutes()
													+ "分";
											/* 取出数据计算出百分比 */
											var historyHolder = Number(value.history_holder);
											var historyTotal = Number(value.history_total);
											var percent = Number(
													(historyHolder / historyTotal) * 100)
													.toFixed()
													+ "%";
											/* 组装$node */
											var y = "<div class='year'>" + year
													+ "</div>";
											var m = "<div class='month_day'>"
													+ month_date + "</div>";
											var h = "<div class='sItem'><div class='hour_minute'>"
													+ hour_minute
													+ "</div><div class='context'><a id='play' href='#videoPlay(本视频播放页)'>"
													+ videoName
													+ "</a></div><div class='rate'>看到"
													+ percent + "</div></div>";
											if (Number(historyTime
													.getFullYear()) > yearfirst) {
												$history = $(y + m + h);
												$("#forHistory").append(
														$history);
												yearfirst = Number(historyTime
														.getFullYear());
												monthfirst = Number(historyTime
														.getMonth());
												datefirst = Number(historyTime
														.getDate());
												hourfirst = Number(historyTime
														.getHours());
												minutefirst = Number(historyTime
														.getMinutes());
											} else {
												if ((Number(historyTime
														.getMonth()) > monthfirst)
														|| (Number(historyTime
																.getMonth()) == monthfirst && Number(historyTime
																.getDate()) > datefirst)) {
													$history = $(m + h);
													$("#forHistory").children()
															.eq(0).after(
																	$history);
													monthfirst = Number(historyTime
															.getMonth());
													datefirst = Number(historyTime
															.getDate());
													hourfirst = Number(historyTime
															.getHours());
													minutefirst = Number(historyTime
															.getMinutes());
												} else {
													if ((historyTime.getHours() > hourfirst)
															|| ((historyTime
																	.getHours() == hourfirst) && historyTime
																	.getMinutes() > minutefirst)) {
														$history = $(h);
														$("#forHistory")
																.children()
																.eq(1)
																.after($history);
														hourfirst = Number(historyTime
																.getHours());
														minutefirst = Number(historyTime
																.getMinutes());
													}
												}
											}
										});
					}
				});
		$("#forHistory").show(100);
	} else {
		layer.open({
			type : 2,
			title : false,
			area : [ '760px', '350px' ],
			content : 'login.html',
		});
	}
}
/* 隐藏历史纪录 */
function hideHistory() {
	$("#forHistory").hide(200);
}
/* 点击收藏 */
function collection() {
	if ($("#isLogin").val() == "0") {
		layer.open({
			type : 2,
			title : false,
			area : [ '760px', '350px' ],
			content : 'login.html',
		});
	} else {
		window.location.href = "wcg_userInfo.html#miao";
	}
}