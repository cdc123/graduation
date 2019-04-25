var i = 1;
var arr = new Array('全部','全部','全部','最多播放');
var resCount = 0;
$(function () {
    //二级菜单
    $("#sorting_2_hidden").hide();
    $("#sorting_2").hover(function () {
        $("#sorting_2_hidden").show();
        //$(this).css({'border':'1px rgb(221,221,221) solid','border-bottom':'none'})
    },function () {
        $("#sorting_2_hidden").hide();
        //$(this).css('border','none')
    });
    $("#sorting_2_hidden").hover(function () {
        $(this).show();
    },function () {
        $(this).hide();
    });
    //JS改变二级菜单排序类型
    $("#sorting_2_hidden_1").click(function () {
        var $sortname = $(this).text();
        $("#sorting_2_show").text($sortname);
    });
    $("#sorting_2_hidden_2").click(function () {
        var $sortname = $(this).text();
        $("#sorting_2_show").text($sortname);
    });
    //展开收起
    $("#show_and_hidden").click(function () {
        if($("#be_cotrolled").is(":visible")){
            $("#be_cotrolled").hide();
            $("#header_part2").css('height','220px');
            $("#show_and_hidden").css('background','url("../image/video_image/showtype.png") 0px -30px');
        }else{
            $("#be_cotrolled").show();
            $("#header_part2").css('height','330px');
            $("#show_and_hidden").css('background','url("../image/video_image/showtype.png") 0px 0px');
        }
    })
    //类型click事件改变css
    $("#sort li").click(function () {
        $(this).children().css({'color':'white','background-color':'#2fb3ff'})
        $(this).siblings().children().css({'color':'black','background-color':'white'});
    });
    $("#region li").click(function () {
        $(this).children().css({'color':'white','background-color':'#2fb3ff'});
        $(this).siblings().children().css({'color':'black','background-color':'white'});
    });
    $("#type li").click(function () {
        $(this).children().css({'color':'white','background-color':'#2fb3ff'});
        $(this).siblings().children().css({'color':'black','background-color':'white'});
    })
//______________________________________________________________________________________________________________________

    showVideo();
    //按分类分类
    $("#sort a").click(function () {
        arr[0]=$(this).text();
        showVideo();
    });
    //按地区分类
    $("#region a").click(function () {
        arr[1]=$(this).text();
        showVideo();
    });
    //按类型分类
    $("#type a").click(function () {
        arr[2]=$(this).text();
        showVideo();
    });
    //排序
    $("#sorting_2_hidden a").click(function () {
        arr[3]=$(this).text();
        showVideo();
    });
    //分页
    //下一页
    $("#next").click(function () {
        var count = Math.ceil( resCount/ 5);
        if (i < count) {
            i ++;
        } else {
            i = count;
        }
        //$("#video_list").children().remove();
        showVideo();
    })
    //上一页
    $("#pre").click(function () {
        if (i <= 1) {
            i = 1;
        } else {
            i--;
        }
        // $("#video_list").children().remove();
        showVideo();
    });
    //把video_id存到session里面，给达成调用
    $("body").on("click",".video",function () {
        var video_name = $(this).find("a").eq(0).text();
        //alert(video_name);
        $.ajax({
            url: "/GetVideo_idByVideo_nameServlet",
            type: "post",
            data: {"video_name":video_name},
            dataType: "json",
            success: function (result) {
                showVideo();
                location.href = "play.html";
            }
        })
    })
    //搜索框
    $("#search_list").hide();
    $("#search_button").click(function () {
        var search = $("#search").val();
        $("#sul").children().remove();
        if(search !=null && search!="") {
            $.ajax({
                url: "/SearchVideoServlet",
                type: "post",
                data: {"video_name": search},
                dataType: "json",
                success: function (result) {
                    if (result.length > 0) {
                        $("#search_list").show();
                        for (i = 0; i < result.length; i++) {
                            $("#sul").append('<li><p class="searchitem">' + result[i].video_name + '</p></li>');
                        }
                    }else {
                        $("#search_list").show();
                        $("#sul").append('<li><i>1</i>&nbsp;海绵宝宝</li><li><i>2</i>&nbsp;小猪佩奇</li><li><i>3</i>&nbsp;了不起的盖茨比</li>');
                    }
                }, error: function () {
                    alert(result);
                }
            });
        }
    });
    //搜索框视频名点击跳转到播放页
    $("#search_list").on("click",".searchitem",function () {
        var video_name = $(this).text();
        //alert(video_name);
        $.ajax({
            url: "/GetVideo_idByVideo_nameServlet",
            type: "post",
            data: {"video_name":video_name},
            dataType: "json",
            success: function (result) {
                location.href="play.html";
            }
        });
    });
});
function showVideo(){
    $.ajax({
        url: "/YfShowVideoServlet",
        type: "post",
        data: {"sort": arr[0], "region": arr[1], "type": arr[2], "order": arr[3],"num":i},
        dataType: "json",
        success: function (result) {
            $("#video_list").children().remove();
            for (var i = 0; i < result.length-1; i++) {
                var node = '<div class="video"><img src="' + result[i].video_image + '"><ul><li><a href="" class="video_name">' + result[i].video_name + '</a></li><li><a class="playtimes"><i>' + result[i].video_playtimes + '</i>次播放</a></ul></ul></div>';
                $("#video_list").append(node);
                $("#sorting_4 i").text(result[result.length-1]);
            }
            //查询条数
            resCount = result[result.length-1];
        }
    })
}
