<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#" onclick="javacript:LoadAjaxContent('index.do')">医生交流平台</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('patients_info.do')">病人信息管理</a></li>
			<li>病人信息列表</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>病人列表</span> | <a href="#"
						onclick="javacript:LoadAjaxContent('patient_add.do')">添加</a>
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
									value="病人名" class="search_init" /></label></th>
							<th><label><input type="text" name="search_votes"
									value="电话" class="search_init" /></label></th>
							<th><label><input type="text" name="search_homepage"
									value="状态" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="医院" class="search_init" /></label></th>
							<th><label><input type="text" name="search_homepage"
									value="床位号" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="照片" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="医生id" class="search_init" /></label></th>
										<th><label><input type="text" name="search_version"
									value="病人id" class="search_init" /></label></th>
							<th><label><input type="text" name="search_version"
									value="操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${patients}" var="patient">
							<tr>
								<td>${patient.id}</td>
								<td>${patient.name}</td>
								<td>${patient.phone}</td>
								<td>${patient.state}</td>
								<td>${patient.hospital}</td>
								<td>${patient.bed_id}</td>
								<td><img class="img-rounded" src="${patient.pic}"
									alt="${patient.pic}"
									onerror="this.src='../common/img/admin/avatar.jpg'" />${patient.pic}</td>
								<td>${patient.doctor_id}</td>
								<td>${patient.patient_id}</td>
								<td><a href="#"
									onclick="javacript:LoadAjaxContent('patient_edit.do?id=${patient.id}')">编辑</a>|<a
									href="#" onclick="deletePatient(${patient.id})">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>名字</th>
							<th>电话</th>
							<th>状态</th>
							<th>医院</th>
							<th>床位号</th>
							<th>照片</th>
							<th>管理医生id</th>
							<th>心电病人id</th>
							<th>操作</th>
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
