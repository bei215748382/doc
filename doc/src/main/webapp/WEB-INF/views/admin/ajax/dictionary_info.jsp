<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#" onclick="javacript:LoadAjaxContent('index.do')">医生交流平台</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('patients_info.do')">字典信息管理</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('patients_info.do')">字典信息列表</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>省列表</span> | <a href="#"
						onclick="javacript:LoadAjaxContent('dictionary_add.do?title=province')">添加省</a>
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
					class="table table-bordered table-striped table-hover table-heading table-datatable datatable-4"
					id="datatable-2">
					<thead>
						<tr>
							<th>id</th>
							<th>省名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${provinces}" var="province">
							<tr>
								<td>${province.id}</td>
								<td>${province.name}</td>
								<td><a href="#"
						onclick="javacript:LoadAjaxContent('dictionary_add.do?title=city&foreign_id=${province.id}')">添加市</a>|<a href="#"
									onclick="javacript:LoadAjaxContent('dictionary_edit.do?title=province&primary_id=${province.id}&name=${province.name}')">编辑</a>|<a
									href="dictionary_delete_json.do?primary_id=${province.id}&type=province">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>省名称</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>城市列表</span> 
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
					class="table table-bordered table-striped table-hover table-heading table-datatable  datatable-4"
					id="datatable-2">
					<thead>
						<tr>
							<th>id</th>
							<th>城市名称</th>
							<th>关联省</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${cities}" var="city">
							<tr>
								<td>${city.id}</td>
								<td>${city.name}</td>
								<td>${city.province_id}</td>
								<td><a href="#"
						onclick="javacript:LoadAjaxContent('dictionary_add.do?title=hospital&foreign_id=${city.id}')">添加医院</a>|<a href="#"
									onclick="javacript:LoadAjaxContent('dictionary_edit.do?title=city&primary_id=${city.id}&name=${city.name}')">编辑</a>|<a
									href="dictionary_delete_json.do?primary_id=${city.id}&type=city">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>城市名称</th>
							<th>关联省</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>医院列表</span> 
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
					class="table table-bordered table-striped table-hover table-heading table-datatable datatable-4"
					id="datatable-2">
					<thead>
						<tr>
							<th>id</th>
							<th>医院名称</th>
							<th>关联城市</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${hospitals}" var="hospital">
							<tr>
								<td>${hospital.id}</td>
								<td>${hospital.name}</td>
								<td>${hospital.city_id}</td>
								<td><a href="#"
						onclick="javacript:LoadAjaxContent('dictionary_add.do?title=domain&foreign_id=${hospital.id}')">添加病区</a>|<a href="#"
									onclick="javacript:LoadAjaxContent('dictionary_edit.do?title=hospital&primary_id=${hospital.id}&name=${hospital.name}')">编辑</a>|<a
									href="dictionary_delete_json.do?primary_id=${hospital.id}&type=hospital">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>医院名称</th>
							<th>关联城市</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>病区列表</span> 
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
					class="table table-bordered table-striped table-hover table-heading table-datatable datatable-4"
					id="datatable-2">
					<thead>
						<tr>
							<th>id</th>
							<th>病区名称</th>
							<th>关联医院</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${domains}" var="domain">
							<tr>
								<td>${domain.id}</td>
								<td>${domain.name}</td>
								<td>${domain.hospital_id}</td>
								<td><a href="#"
						onclick="javacript:LoadAjaxContent('dictionary_add.do?title=room&foreign_id=${domain.id}')">添加病房</a>|<a href="#"
									onclick="javacript:LoadAjaxContent('dictionary_edit.do?title=domain&primary_id=${domain.id}&name=${domain.name}')">编辑</a>|<a
									href="dictionary_delete_json.do?primary_id=${domain.id}&type=domain">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>病区名称</th>
							<th>关联医院</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>房间列表</span> 
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
					class="table table-bordered table-striped table-hover table-heading table-datatable datatable-4"
					id="datatable-2">
					<thead>
						<tr>
							<th>id</th>
							<th>房间名称</th>
							<th>关联病区</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${rooms}" var="room">
							<tr>
								<td>${room.id}</td>
								<td>${room.name}</td>
								<td>${room.domain_id}</td>
								<td><a href="#"
						onclick="javacript:LoadAjaxContent('dictionary_add.do?title=bed&foreign_id=${room.id}')">添加床位</a>|<a href="#"
									onclick="javacript:LoadAjaxContent('dictionary_edit.do?title=room&primary_id=${room.id}&name=${room.name}')">编辑</a>|<a
									href="dictionary_delete_json.do?primary_id=${room.id}&type=room">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>房间名称</th>
							<th>关联病区</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>床位列表</span> 
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
					class="table table-bordered table-striped table-hover table-heading table-datatable datatable-4"
					id="datatable-2">
					<thead>
						<tr>
							<th>id</th>
							<th>床位名称</th>
							<th>关联房间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${beds}" var="bed">
							<tr>
								<td>${bed.id}</td>
								<td>${bed.name}</td>
								<td>${bed.room_id}</td>
							<td><a href="#"
									onclick="javacript:LoadAjaxContent('dictionary_edit.do?title=bed&primary_id=${bed.id}&name=${bed.name}')">编辑</a>|<a
									href="dictionary_delete_json.do?primary_id=${bed.id}&type=bed">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>床位名称</th>
							<th>关联房间</th>
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
		TestTable4();
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
	function TestTable4() {
		$('.datatable-4')
				.dataTable(
						{
							"aaSorting" : [ [ 0, "asc" ] ],
							"sDom" : "<'box-content'<'col-sm-6'f><'col-sm-6 text-right'l><'clearfix'>>rt<'box-content'<'col-sm-6'i><'col-sm-6 text-right'p><'clearfix'>>",
							"sPaginationType" : "bootstrap",
							"oLanguage" : {
								"sSearch" : "",
								"sLengthMenu" : '_MENU_'
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
