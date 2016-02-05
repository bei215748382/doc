<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生个人信息管理平台</a></li>
			<li><a href="index.do">医生个人信息</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>医生个人信息</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-1">
					<thead>
						<tr>
							<th>用户名</th>
							<th>头像姓名</th>
							<th>手机号</th>
							<th>所属医院</th>
							<th>性别</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${activeDoc.username}</td>
							<td><img class="img-rounded" src="${activeDoc.pic}"
								alt="${activeDoc.pic}"
								onerror="this.src='../common/img/admin/avatar.jpg'" />${activeDoc.name}</td>
							<td>${activeDoc.phone}</td>
							<td>${activeDoc.hospital}</td>
							<td>${activeDoc.sex}</td>
							<td>${activeDoc.date}</td>
							<td><a href="#"
								onclick="javacript:LoadAjaxContent('doctor_edit.do?id=${activeDoc.id}')">编辑</a>|<a
								href="#"
								onclick="javacript:LoadAjaxContent('doctor_upload_pic.do?id=${activeDoc.id}')">上传头像</a></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<th>用户名</th>
							<th>头像姓名</th>
							<th>手机号</th>
							<th>所属医院</th>
							<th>性别</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// Run Datables plugin and create 3 variants of settings
	function AllTables() {
		TestTable1();
	}
	function deleteLog(id) {
		$.ajax({
			type : 'post',
			url : 'delete_client_log.do?id=' + id,
			data : {},
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				alert(data.msg);
				LoadAjaxContent("index.do");
			},
			error : function() {
				console.log("异常");
			}
		});
	}
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
