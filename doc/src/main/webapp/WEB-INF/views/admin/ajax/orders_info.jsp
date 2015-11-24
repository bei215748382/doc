<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生交流平台</a></li>
			<li><a href="index.do">预约信息管理</a></li>
			<li><a href="#">预约信息列表</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<a href="#"
						onclick="javacript:LoadAjaxContent('orders_info.do')"><i class="fa fa-usd"></i> <span>预约信息列表</span></a> | <a href="#"
						onclick="javacript:LoadAjaxContent('order_add.do')">添加</a>|<a href="#"
						onclick="javacript:LoadAjaxContent('unaccept_orders_info.do?state=1')">已接受列表</a>
						|<a href="#"
						onclick="javacript:LoadAjaxContent('unaccept_orders_info.do?state=0')">未接受列表</a>
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
							<th>预约者</th>
							<th>被预约</th>
							<th>预约时间</th>
							<th>接受状态</th>
							<th>提醒次数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.id}</td>
								<td>${order.doctor_id}</td>
								<td>${order.friend_id}</td>
								<td>${order.date}</td>
								<td>${order.state}</td>
								<td>${order.remind}</td>
								<td><a href="#"
									onclick="javacript:LoadAjaxContent('order_edit.do?id=${order.id}')">编辑</a>|<a
									href="#" onclick="deleteOrder(${order.id})">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>预约者</th>
							<th>被预约</th>
							<th>预约时间</th>
							<th>接受状态</th>
							<th>提醒次数</th>
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
		TestTable2();
		TestTable3();
		LoadSelect2Script(MakeSelect2);
	}
	function MakeSelect2() {
		$('select').select2();
		$('.dataTables_filter').each(
				function() {
					$(this).find('label input[type=text]').attr('placeholder',
							'Search');
				});
	}
	function deleteOrder(id){
		$.ajax({
			 type:'post',  
		     url:'order_delete_json.do?id='+id,  
		     data:{},  
		     cache:false,  
		     dataType:'json',  
		     success:function(data){  
			    console.log(data);
		      	alert(data.msg);
		      	LoadAjaxContent("orders_info.do"); 
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
