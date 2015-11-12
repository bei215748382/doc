<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">Dashboard</a></li>
			<li><a href="#">Tables</a></li>
			<li><a href="#">Data Tables</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i>
					<span>The World's billionaries</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-1">
					<thead>
						<tr>
							<th>id</th>
							<th>头像姓名</th>
							<th>医院</th>
							<th>用户名</th>
							<th>手机号</th>
							<th>注册时间</th>
						</tr>
					</thead>
					<tbody>
					<!-- Start: list_row -->
						<c:forEach items="${doctors}" var="doctor">
							<tr>
							<td>${doctor.id}</td>
							<td><img class="img-rounded" src="${doctor.pic}" alt="${doctor.pic}" onerror="this.src='http://i.forbesimg.com/media/lists/people/carlos-slim-helu_50x50.jpg'"/>${doctor.name}</td>
							<td>${doctor.hospital}</td>
							<td>${doctor.username}</td>
							<td>${doctor.phone}</td>
							<td>${doctor.date}</td>
						</tr>
						</c:forEach>
					<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>头像姓名</th>
							<th>医院</th>
							<th>用户名</th>
							<th>手机号</th>
							<th>注册时间</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
function AllTables(){
	TestTable1();
	TestTable2();
	TestTable3();
	LoadSelect2Script(MakeSelect2);
}
function MakeSelect2(){
	$('select').select2();
	$('.dataTables_filter').each(function(){
		$(this).find('label input[type=text]').attr('placeholder', 'Search');
	});
}
$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	LoadDataTablesScripts(AllTables);
	// Add Drag-n-Drop feature
	WinMove();
});
</script>
