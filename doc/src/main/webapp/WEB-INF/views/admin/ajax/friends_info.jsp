<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生交流平台</a></li>
			<li><a href="index.do">医生好友信息管理</a></li>
			<li>医生信息列表</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>医生好友信息列表</span>
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
							<th>用户id</th>
							<th>好友id</th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${friends}" var="friend">
							<tr>
								<td>${friend.id}</td>
								<td><a href="#" onclick="openDoctorInfoBox(${friend.doctor_id})">${friend.doctor_id}</a></td>
								<td><a href="#" onclick="openDoctorInfoBox(${friend.friend_id})">${friend.friend_id}</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>用户id</th>
							<th>好友id</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function openDoctorInfoBox(id){
	$.ajax({
		type : 'get',
		url : '${ctx}/doctors/find/'+id+'/doctor.do',
		data : {},
		cache : false,
		dataType : 'json',
		success : function(data) {
			var str="";
			var header="id为："+data.id+"的医生详情";
			for(var a in data){
				if(a!='password'){
					str+= a + ":" + data[a]+"<br/>";
				}
				console.log(a!='password');
			}
			OpenModalBox(header, str,header);
		},
		error : function() {
			console.log("异常");
		}
	});
	
}
	
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
	function deleteDoctor(id) {
		$.ajax({
			type : 'post',
			url : 'doctor_delete_json.do?id=' + id,
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
