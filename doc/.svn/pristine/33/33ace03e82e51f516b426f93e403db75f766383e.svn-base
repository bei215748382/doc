<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <link rel="stylesheet" type="text/css" href="../common/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../common/easyui/themes/icon.css">
    <script type="text/javascript" src="../common/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../common/easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <h2>图书管理</h2>
    <p></p>
    <!-- 列表 -->
    <table id="dg" title="图书管理" class="easyui-datagrid" style="width:1000px;height:500px"
            url="../manager/booksJson"
            toolbar="#telephonetoolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="bookId" width="50">id</th>
                <th field="bookAuthorname" width="50">作者</th>
                <th field="bookName" width="50">书名</th>
            </tr>
        </thead>
    </table>
    <!-- 功能按钮 -->
    <div id="telephonetoolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加图书</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑图书</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除图书</a>
    </div>
    <!-- 编辑框及功能 -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">图书信息</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>作者:</label>
                <input name="bookAuthorname" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>书名:</label>
                <input name="bookName" class="easyui-validatebox" required="true">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','添加图书');
            $('#fm').form('clear');
            url = '../manager/bookAdd';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','编辑图书');
                $('#fm').form('load',row);
                url = '../manager/bookEdit?bookId='+row.bookId;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('删除电话','你确认要删除这个电话嘛?',function(r){
                    if (r){
                        $.post('../manager/bookDel',{bookId:row.bookId},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
</body>
</html>