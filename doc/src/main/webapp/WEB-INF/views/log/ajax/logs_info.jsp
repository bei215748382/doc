<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生交流平台</a></li>
			<li><a href="index.do">日志信息管理</a></li>
			<li>日志信息列表</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>日志信息列表</span>
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
							<th>id</th>
							<th>内容</th>
							<th>类型</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${logs}" var="log">
							<tr>
								<td>${log.id}</td>
								<td>${log.content}</td>
								<td><c:if test="${log.type==1}">客户端APP</c:if></td>
								<td>${log.date}</td>
								<td><a
									href="#" onclick="deleteLog(${log.id})">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>内容</th>
							<th>类型</th>
							<th>时间</th>
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
	function deleteLog(id){
		$.ajax({
			 type:'post',  
		     url:'delete_client_log.do?id='+id,  
		     data:{},  
		     cache:false,  
		     dataType:'json',  
		     success:function(data){  
			    console.log(data);
		      	alert(data.msg);
		      	LoadAjaxContent("index.do"); 
		      },  
		      error:function(){
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
