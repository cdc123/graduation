$(function() {
	getData();
});

function getData() {
	$
			.ajax({
				type : "post",
				url : "/videoSearched/getDataForVideoSearched",
				dataType : "json",
				async : false,
				success : function(result) {
					var $node;
					$
							.each(
									result,
									function(index, item) {
										var name = item.video_name;
										var image = item.video_image;
										var type = item.video_sort;
										var area = item.video_region;
										var playtimes = item.video_playtimes;
										var introduce = item.video_introduce;
										if (name.length > 13) {
											name = name.substring(0, 13)
													+ "...";
										}
										if (image == null || image == '') {
											image = "../image/wcg_images/noPicture.jpg";
										}
										if (type == null || type == '') {
											type = "暂未分类";
										}
										if (area == null || area == '') {
											area = "暂未分类";
										}
										if (playtimes == null
												|| playtimes == '') {
											playtimes = "0";
										}
										if (introduce != null
												&& introduce != '') {
											if (introduce.length > 60) {
												introduce = introduce
														.substring(0, 60)
														+ "...";
											}
										} else {
											introduce = "暂无简介";
										}
										$node = $("<div class='searchedVideo'><div class='videoItem'><div class='videoImg' title="
												+ item.video_name
												+ " style='background-image: url("
												+ image
												+ ");background-repeat: no-repeat;background-size: 100% 100%;'></div><div class='videoItro'><div class='videoItroText'><div class='textOut'><div class='textIn1'><p>名称</p></div><div class='textIn2'><p>:</p></div><div class='textIn3'><p title="
												+ item.video_name
												+ ">《<span>"
												+ name
												+ "</span>》</p></div></div><div class='textOut'><div class='textIn1'><p>类型</p></div><div class='textIn2'><p>:</p></div><div class='textIn3'><p>"
												+ type
												+ "</p></div></div><div class='textOut'><div class='textIn1'><p>地区</p></div>	<div class='textIn2'><p>:</p></div><div class='textIn3'><p>"
												+ area
												+ "</p></div></div><div class='textOut'><div class='textIn1'><p>播放次数</p></div><div class='textIn2'><p>:</p></div><div class='textIn3'><p><span>"
												+ playtimes
												+ "</span>次</p></div></div><div class='IntroTextOut'><div class='textIn1'><p>视频简介</p></div><div class='textIn2'><p>:</p></div><div class='textIn3'><p title="
												+ item.video_introduce
												+ ">"
												+ introduce
												+ "</p></div></div><div class='textOut'><div class='playTextIn'><a title="
												+ item.video_name
												+ ">播放</a></div></div></div></div></div></div>");

										$("#recommended").before($node);
									});
				}
			});
	$(".playTextIn").on("click", "a", function(event) {
		var target = $(event.target);
		var videoName = target.attr("title");
		$.ajax({
			type : "post",
			url : "/home/getVideoByName",
			data : {
				"videoName" : videoName
			},
			dataType : "json",
			async : false,
			success : function(result) {
				window.location.href = "play.html";
			}
		});
	});
}