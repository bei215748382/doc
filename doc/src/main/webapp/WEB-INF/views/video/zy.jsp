<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>造影</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx}/common/plugins/bootstrap/bootstrap.css"
	rel="stylesheet">
<link href="${ctx}/common/css/admin/font-awesome.css" rel="stylesheet">
<%-- <link href="${ctx}/common/css/admin/style.css" rel="stylesheet"> --%>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.top-15 {
	margin-top: 15px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-sm-4">造影列表</div>
		<div class="col-sm-8">造影图像</div>

		<div class="col-sm-4 top-15">
			<c:forEach items="${rooms}" var="room">
				<a href="#" onclick="bulidVideo(${room.cameraID})">${room.name}</a>
				<br />
			</c:forEach>
		</div>
		<div class="col-sm-8 top-15">
			<div>
				<object width="100%" height="450" id="ocx1"
					classid="CLSID:7EFFB2C5-F8BF-4C5D-A2B4-116336833B17"
					codebase="${ctx}/common/object/WMWPV.CAB#version=1,0,0,5"
					style="font-family: 微软雅黑; font-size: small;">
					<PARAM NAME="_Version" VALUE="65536">
					<PARAM NAME="_StockProps" VALUE="0">
					<param name="_ExtentX" value="2646">
					<param name="_ExtentY" value="1323">
				</object>
			</div>
			<div class="video_download">下载插件地址</div>
			<div class="video_content"></div>
		</div>
	</div>
	<script src="${ctx}/common/plugins/jquery/jquery-2.1.0.min.js"></script>
	<script type="text/javascript">
	//播放页面视频
	function bulidVideo(id) {
		var ocx1 = document.getElementById("ocx1");
	    $.ajax({
	        type: "GET",
	        url: "get_meet_place.do",
	        data: "YYSPID=" + id,
	        dataType: "json",
	        async: false,
	        cache: false,
	        success: function (data) {
	            try {
	                console.log(data);
	           
	                for (var i = 0; i < data.length; i++) {
	//                    ocx1.SetSVInfo(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7]);
	                    //                    ocx1.StartRealPlay();
	                    ocx1.SelectSubWnd(i);
	                    ocx1.SetRuleInfo(1000, 0, 0);
	//                     ocx1.StartRealPlay(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7]);
	//   ocx1.StartRealPlay('192.168.101.50', '8000', 'admin', '12345', '0', '0', '192.168.2.153', '554');
	                     ocx1.StartRealPlay(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], "0", data[i][5], data[i][6]);
						ocx1.ArrangeOutputs(1);
	                      $(".video_download").remove();
	                }
	            }
	            catch (e) {
	                console.log("请使用IE浏览器，查看视频,IE浏览器下若未安装插件请先安装插件");
	                $(".video_download").remove();
	                $(".video_content").append("<a  class='video_download' style='color:#fff' href='${ctx}/common/object/WMWPV.CAB'>点击下载视频插件</a>");
	            }
	
	        }
	    });
	}
	$(document).ready(function() {
		var ocx1 = document.getElementById("ocx1");
		console.log("heheh"+ocx1);
		
// 		 ocx1.SelectSubWnd(0);
// 	     ocx1.SetRuleInfo(1000, 0, 0);
// 	//      ocx1.StartRealPlay(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7]);

		ocx1.ArrangeOutputs(1);
// 		ocx1.StartRealPlay('192.168.101.50', '8000', 'admin', '12345', '0', '0', '192.168.2.153', '554');
	});
</script>
</body>
</html>
