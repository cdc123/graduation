$(function() {
	getData();
});

function getData() {
	$.ajax({
		type : "post",
		url : "/videoSearched/getDataForVideoSearched",
		dataType : "json",
		success : function(result) {
			$.each(result, function(index, item) {
				console.log(item.video_id + ":" + item.video_name);
				alert(item.video_id + ":" + item.video_name);
			});
		}
	});
}