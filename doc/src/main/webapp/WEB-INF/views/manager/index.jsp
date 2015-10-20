<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城</title>
 	<link rel="stylesheet" type="text/css" href="../common/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../common/easyui/themes/icon.css">
    <script type="text/javascript" src="../common/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../common/easyui/jquery.easyui.min.js"></script>
</head>
<body style="margin:0 0">
    <div class="easyui-layout" style="width:1280px;height:922px;position: absolute; top: 0; left: 0;">
        <!-- 头部 -->
        <div data-options="region:'north'" style="height:50px;text-align:center">
        	<img src="../common/img/logintitle.gif" height="45px">
        </div>
        <!-- 底部 -->
        <div data-options="region:'south',split:true" style="height:50px;text-align:center;padding:10px">
        需求设计人员：李思慧  苏小霜  古琪,	美工:魏斯琦  朱韩娟,	开发人员：董雨 饶愈卉子
        </div>
        <!-- 左边功能 -->
        <div class="easyui-panel" data-options="region:'west',split:true" title="书趣功能管理" style="width:150px;">
        <ul class="easyui-tree">
            <li>
                <span>商品管理</span>
                <ul>
                    <li><a href="#"  onclick="addPanel('商品管理','../manager/books')">商品管理</a></li>
                </ul>
            </li>
            
            <li>
                <span>订单管理</span>
                <ul>
                    <li>订单管理管理</li>
                </ul>
            </li>
        </ul>
			
        </div>
        <div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools',region:'center',">
           
        </div>
    </div>
	  <script type="text/javascript">
	        function addPanel(title,url){
	        	if($('#tt').tabs('exists',title)){
	        		//更新
	        		$('#tt').tabs('select',title);
	        	}else{
	        		//添加
	        		$('#tt').tabs('add',{
	                    title: title,
	                    content: '<iframe src="'+url+'" style="width:100%;height:100%" frameborder="0" scrolling="no"></iframe>',
	                    closable: true
	                });
	        	}
	            
	        }
	        function removePanel(){
	            var tab = $('#tt').tabs('getSelected');
	            if (tab){
	                var index = $('#tt').tabs('getTabIndex', tab);
	                $('#tt').tabs('close', index);
	            }
	        }
	    </script>
</body>
</html>


