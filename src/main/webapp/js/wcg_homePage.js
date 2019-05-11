$(function() {
	/* 查询当前登录信息 */
	getUserBySession();
	/* 首页获取电影 */
	getMovie();
	/* 首页获取剧集 */
	getTeleplay();
	/* 首页获取上传 */
	getUpload();
	/* 点击搜索栏下拉框选项时直接播放影片 */
	$("#forSearch ul").on("click", "li", function(event) {
		var target = $(event.target);
		var value = target.attr("title");
		$.ajax({
			type : "post",
			url : "/home/getVideoByName",
			data : {
				"videoName" : value
			},
			dataType : "json",
			success : function(result) {
				window.location.href = "play.html";
			}
		});
	});
	/* 续播 */
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
				location.href = "play.html";
			}
		});
	});
	/* 点击电影，剧集文字播放 */
	$(".mt").on("click", "a", function(event) {
		var target = $(event.target);
		var videoName = target.text();
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
	});
	/* 点击电影，剧集图片播放 */
	$(".mt").on("click", "img", function(event) {
		var target = $(event.target);
		var videoName = target.attr("title");
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
	});
	/* 上传视频点击绑定 */
	// $(".u").on("click", "a", function(event) {
	// var target = $(event.target);
	// var videoName = target.text();
	// $.ajax({
	// type : "post",
	// url : "",
	// data : {
	// "videoName" : videoName
	// },
	// dataType : "json",
	// success : function(result) {
	// window.location.href = "play.html";
	// }
	// });
	// });
	// $(".u").on("click", "img", function(event) {
	// var target = $(event.target);
	// var videoName = target.attr("title");
	// $.ajax({
	// type : "post",
	// url : "",
	// data : {
	// "videoName" : videoName
	// },
	// dataType : "json",
	// success : function(result) {
	// window.location.href = "play.html";
	// }
	// });
	// });
});
/*--------------------------------------------------*/
function getUserBySession() {
	$.ajax({
		type : "post",
		url : "/home/getUserBySession",
		dataType : "json",
		async : false,
		success : function(result) {
			if (result != null && "" != result) {
				$("#isLogin").val("1");
				$.each(result, function(index, item) {
					$("#userId").val(item.user_id);
					$("#userPhone").val(item.user_phone);
					$("#userPower").val(item.user_power);
					$("#userName").val(item.user_name);
					if ($("#userName").val() != null
							&& $("#userName").val() != "") {
						/* 为个人信息页面用户名赋值 */
						$("#selfUsername b").text($("#userName").val());
					}
					$("#userSex").val(item.user_sex);
					$("#userAddress").val(item.user_address);
					$("#userBirthday").val(item.user_birthday);
					$("#userIntroduce").val(item.user_introduce);
					if ($("#userIntroduce").val() != null
							&& $("#userIntroduce").val() != "") {
						/* 为个人信息页面个人简介赋值 */
						$("#introduce").text($("#userIntroduce").val());
					}
					$("#userImage").val(item.user_image);
					if ($("#userImage").val() != null
							&& $("#userImage").val() != "") {
						/* 为头部头像赋值 */
						$("#photoImg").css("background-image",
								"url('" + $("#userImage").val() + "')");
						/* 为信息修改页面头像赋值 */
						$("#finalImg").attr("src", $("#userImage").val());
						/* 为个人信息页面头像赋值 */
						$("#pictrue").css("background-image",
								"url('" + $("#userImage").val() + "')");
					} else {
						/* 当没有头像数据时，为各页面赋默认值 */
						$("#photoImg").css("background-image",
								"url('../image/wcg_images/loginPhoto.jpg')");
						$("#finalImg").attr("src",
								"../image/wcg_images/loginPhoto.jpg");
						$("#pictrue").css("background-image",
								"url('../image/wcg_images/loginPhoto.jpg')");
					}
					/* 信息修改页面其余项初始化赋值 */
					showUserInfo();
					/* 个人信息页面显示收藏视频 */
					showFavourite();
					/* 个人信息页面显示上传视频 */
					showUpLoad();
					/* 为个人信息页生日赋初值 */
					var date = new Date(item.user_birthday);
					var year = date.getFullYear() + "";
					var month = date.getMonth() + 1 + "";
					if (month.length == 1) {
						month = "0" + month;
					}
					var day = date.getDate() + "";
					if (day.length == 1) {
						day = "0" + day;
					}
					var dateString = year + "-" + month + "-" + day;
					$("input[name='birth']").attr("value", dateString);
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
				$.each(result,
						function(n, value) {
							keyWord = $("input[name='search']").val();
							var num1 = value.indexOf(keyWord);
							var num2 = num1 + keyWord.length;
							var str1 = value.substring(0, num1);
							var str2 = value.substring(num2, value.length);
							var $node;
							var str1KeyWord = str1 + keyWord;
							var str1KeywordStr2 = str1 + keyWord + str2;
							var cha;
							if (str1.length > 15) {
								$node = $("<li style='cursor:pointer' title="
										+ str1KeywordStr2 + ">"
										+ str1.substring(0, 15) + "...</li>");
							} else if (str1KeyWord.length > 15) {
								cha = 15 - str1.length;
								$node = $("<li style='cursor:pointer' title="
										+ str1KeywordStr2 + ">" + str1
										+ "<span style='color:red'>"
										+ keyWord.substring(0, cha)
										+ "...</span></li>");
							} else if (str1KeywordStr2.length > 15) {
								cha = 15 - str1KeyWord.length;
								$node = $("<li style='cursor:pointer' title="
										+ str1KeywordStr2 + ">" + str1
										+ "<span style='color:red'>" + keyWord
										+ "</span>" + str2.substring(0, cha)
										+ "...</li>");
							} else {
								$node = $("<li style='cursor:pointer' title="
										+ str1KeywordStr2 + ">" + str1
										+ "<span style='color:red'>" + keyWord
										+ "</span>" + str2 + "</li>");
							}
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
}
/* 登出 */
function logOut() {
	$.ajax({
		url : "/home/logOut",
		type : "post",
		dataType : "text",
		success : function(result) {
			window.location.href = "homepage.html";
		}
	});
}/* 上传 */
/* 上传按钮 */
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
		var forHistoryState = $("#forHistory").css('display');
		if (forHistoryState == "none") {
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
														+ historyTime
																.getMinutes()
														+ "分";
												/* 取出数据计算出百分比 */
												var historyHolder = Number(value.history_holder);
												var historyTotal = Number(value.history_total);
												var percent = Number(
														(historyHolder / historyTotal) * 100)
														.toFixed()
														+ "%";
												/* 组装$node */
												var y = "<div class='year'>"
														+ year + "</div>";
												var m = "<div class='month_day'>"
														+ month_date + "</div>";
												var h = "<div class='sItem'><div class='hour_minute'>"
														+ hour_minute
														+ "</div><div class='context'><a id='play' href='#videoPlay(本视频播放页)'>"
														+ videoName
														+ "</a></div><div class='rate'>看到"
														+ percent
														+ "</div></div>";
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
														$("#forHistory")
																.children()
																.eq(0)
																.after($history);
														monthfirst = Number(historyTime
																.getMonth());
														datefirst = Number(historyTime
																.getDate());
														hourfirst = Number(historyTime
																.getHours());
														minutefirst = Number(historyTime
																.getMinutes());
													} else {
														if ((historyTime
																.getHours() > hourfirst)
																|| ((historyTime
																		.getHours() == hourfirst) && historyTime
																		.getMinutes() > minutefirst)) {
															$history = $(h);
															$("#forHistory")
																	.children()
																	.eq(1)
																	.after(
																			$history);
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
			$("#forHistory").hide(100);
		}
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

function getMovie() {
	var videoSort = "电影";
	$
			.ajax({
				url : "/home/getVideoBySort",
				data : {
					"videoSort" : videoSort
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(result) {
					var $node = null;
					var num = 0;
					var firstId = "body_two_content1";
					var otherId = "body_two_content2";
					var v = "mt";
					$
							.each(
									result,
									function(index, item) {
										var name = item.video_name;
										if (name.length > 12) {
											name = name.substring(0, 12)
													+ "...";
										}
										num = num + 1;
										if (num > 6) {
											$("#moreMovie").show(100);
											return false;
										} else if (num > 1) {
											if (item.video_image != null) {
												$node = $("<div id="
														+ otherId
														+ " class="
														+ v
														+ "><img src="
														+ item.video_image
														+ " title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											} else {
												$node = $("<div id="
														+ otherId
														+ " class="
														+ v
														+ "><img src='../image/wcg_images/noPicture.jpg' title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											}
											$("#moreMovie").before($node);
										} else {
											if (item.video_image != null) {
												$node = $("<div id="
														+ firstId
														+ " class="
														+ v
														+ "><img src="
														+ item.video_image
														+ " title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											} else {
												$node = $("<div id="
														+ otherId
														+ " class="
														+ v
														+ "><img src='../image/wcg_images/noPicture.jpg' title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											}
											$("#moreMovie").before($node);
										}
									});
				}
			});

}

function getTeleplay() {
	var videoSort = "剧集";
	$
			.ajax({
				url : "/home/getVideoBySort",
				data : {
					"videoSort" : videoSort
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(result) {
					var $node = null;
					var num = 0;
					var firstId = "body_two_content1";
					var otherId = "body_two_content2";
					var v = "mt";
					$
							.each(
									result,
									function(index, item) {
										var name = item.video_name;
										if (name.length > 12) {
											name = name.substring(0, 12)
													+ "...";
										}
										num = num + 1;
										if (num > 6) {
											$("#moreTvplay").show(100);
											return false;
										} else if (num > 1) {
											if (item.video_image != null) {
												$node = $("<div id="
														+ otherId
														+ " class="
														+ v
														+ "><img src="
														+ item.video_image
														+ " title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											} else {
												$node = $("<div id="
														+ otherId
														+ " class="
														+ v
														+ "><img src='../image/wcg_images/noPicture.jpg' title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											}
											$("#tvplayArea").before($node);
										} else {
											if (item.video_image != null) {
												$node = $("<div id="
														+ firstId
														+ " class="
														+ v
														+ "><img src="
														+ item.video_image
														+ " title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											} else {
												$node = $("<div id="
														+ otherId
														+ " class="
														+ v
														+ "><img src='../image/wcg_images/noPicture.jpg' title="
														+ item.video_name
														+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
														+ name + "</a></div>");
											}
											$("#tvplayArea").before($node);
										}
									});
				}
			});
}

function getUpload() {
	$
			.ajax({
				url : "/home/getUploadVideo",
				type : "post",
				dataType : "json",
				async : false,
				success : function(result) {
					var $node = null;
					var num = 0;
					var firstId = "u1";
					var otherId = "u2";
					var v = "u";
					$
							.each(
									result,
									function(index, item) {
										var name = item.upv_name;
										if (name.length > 12) {
											name = name.substring(0, 12)
													+ "...";
										}
										num = num + 1;
										if (num > 6) {
											$("#moreUpload").show(100);
											return false;
										} else if (num > 1) {
											$node = $("<div id="
													+ otherId
													+ " class="
													+ v
													+ "><img src='../image/wcg_images/upPic.jpg' title="
													+ item.upv_name
													+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
													+ name + "</a></div>");
											$("#uploadArea").before($node);
										} else {
											$node = $("<div id="
													+ firstId
													+ " class="
													+ v
													+ "><img src='../image/wcg_images/upPic.jpg' title="
													+ item.upv_name
													+ "><br/><a style='font-size: 14px; line-height: 40px;cursor:pointer'>"
													+ name + "</a></div>");
											$("#uploadArea").before($node);
										}
									});
				}
			});
}

function showUserInfo() {
	/* 昵称初始值 */
	$("input[name='user_name']").attr("value", $("#userName").val());
	/* 性别初始值 */
	var gender = $("#userSex").val();
	if (gender == "女") {
		$("input[value='女']").attr("checked", true);
	}
	/* 地址初始值 */
	$("input[name='user_address']").attr("value", $("#userAddress").val());
	/* 简介初始值 */
	$("textarea").text($("#userIntroduce").val());
}

function showFavourite() {
	var userId = $("#userId").val();
	$
			.ajax({
				type : "post",
				url : "/infoChange/getFavouriteByUserId",
				data : {
					"userId" : userId
				},
				dataType : "json",
				async : false,
				success : function(result) {
					var sum = result.length;
					$("#sowingList span").text(sum);
					var shang = sum / 5;
					var yu = sum % 5;
					shang = Math.floor(shang);
					if (yu > 0) {
						shang += 1;
					}
					var height = shang * 320 + "px";
					$("#sowingListBody").css("height", height);
					var num = 0;
					var $node;
					$
							.each(
									result,
									function(index, item) {
										var name = item.video_name;
										if (name.length > 11) {
											name = name.substring(0, 11)
													+ "...";
										}
										num = num + 1;
										if (item.video_image != null
												&& item.video_image != '') {
											$node = $("<div class='collection'><img title="
													+ item.video_name
													+ " src='"
													+ item.video_image
													+ "'> <a style='cursor: pointer;'>"
													+ name + "</a></div>");
										} else {
											$node = $("<div class='collection'><img title="
													+ item.video_name
													+ " src='../image/wcg_images/noPicture.jpg'> <a style='cursor: pointer;'>"
													+ name + "</a></div>");
										}
										$("#sowingListBody").append($node);
									});
				}
			});
	$(".collection").on("click", "a", function(event) {
		var target = $(event.target);
		var videoName = target.text();
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
	});
	$(".collection").on("click", "img", function(event) {
		var target = $(event.target);
		var videoName = target.attr("title");
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
	});
}

function showUpLoad() {
	var userId = $("#userId").val();
	$
			.ajax({
				type : "post",
				url : "/infoChange/getUploadByUserId",
				data : {
					"userId" : userId
				},
				dataType : "json",
				async : false,
				success : function(result) {
					var sum = result.length;
					$("#videos span").text(sum);
					var shang = sum / 5;
					var yu = sum % 5;
					shang = Math.floor(shang);
					if (yu > 0) {
						shang += 1;
					}
					var height = shang * 320 + "px";
					$("#videosBody").css("height", height);
					var num = 0;
					var $node;
					$
							.each(
									result,
									function(index, item) {
										var name = item.upv_name;
										if (name.length > 11) {
											name = name.substring(0, 11);
										}
										num = num + 1;
										$node = $("<div class='collection'><img title="
												+ item.upv_name
												+ " src='../image/wcg_images/noPicture.jpg'> <a style='cursor: pointer;'>"
												+ name + "</a></div>");
										$("#videosBody").append($node);
									});
				}
			});
}