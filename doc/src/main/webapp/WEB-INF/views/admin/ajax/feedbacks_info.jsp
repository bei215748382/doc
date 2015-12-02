<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#" onclick="javacript:LoadAjaxContent('index.do')">医生交流平台</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('feedbacks_info.do')">问题反馈管理</a></li>
			<li>反馈列表</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>反馈列表</span> | <span>由用户反馈生成的列表，没有增删改</span>
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
					id="datatable-2">
					<thead>
						<tr>
							<th><label><input type="text" name="search_rate"
									value="id" class="search_init" /></label></th>
							<th><label><input type="text" name="search_name"
									value="title" class="search_init" /></label></th>
							<th><label><input type="text" name="search_votes"
									value="content" class="search_init" /></label></th>
							<th><label><input type="text" name="search_homepage"
									value="date" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="user_id" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${feedbacks}" var="feedback">
							<tr>
								<td>${feedback.id}</td>
								<td>${feedback.title}</td>
								<td>${feedback.content}</td>
								<td>${feedback.date}</td>
								<td>${feedback.user_id}</td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>标题</th>
							<th>内容</th>
							<th>时间</th>
							<th>用户</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function AllTables() {
		TestTable2();
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
	function deletePatient(id) {
		$.ajax({
			type : 'post',
			url : 'patient_delete.do?id=' + id,
			data : {},
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				alert(data.msg);
				LoadAjaxContent("patients_info.do");
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
