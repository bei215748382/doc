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
			<li>修改病人</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>修改病人信息</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<h4 class="page-header">修改病人信息</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="defaultForm" action="patient_edit_json.do"
					enctype="multipart/form-data">
					<input type="hidden" name="id" value="${patient.id}" />
					<input type="hidden" name="patient_id" value="${patient.patient_id}"/>
					<input type="hidden" name="pic" value="${patient.pic}">
					<div class="form-group">
						<label class="col-sm-2 control-label">名字</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="name"
								value="${patient.name}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="phone"
								value="${patient.phone}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">头像</label>
						<div id="localImag" class="col-sm-4">
							<img id="preview" class="img-rounded" src="${patient.pic }"
								alt="${patient.pic }" /> <input type="file" name="file"
								value="${patient.pic}"
								onchange="javascript:setImagePreview(this,localImag,preview);"
								class="margin-top-15">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-4">
							<c:if test="${patient.sex == '男' }">
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="男"
										checked> 男 <i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="女">
										女 <i class="fa fa-circle-o"></i>
									</label>
								</div>
							</c:if>
							<c:if test="${patient.sex == '女' }">
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="男">
										男 <i class="fa fa-circle-o"></i>
									</label>
								</div>
								<div class="radio-inline">
									<label> <input type="radio" name="sex" value="女"
										checked> 女 <i class="fa fa-circle-o"></i>
									</label>
								</div>
							</c:if>
						</div>
					</div>
					<div class="form-group has-feedback">
						<label class="col-sm-2 control-label">出生年月</label>
						<div class="col-sm-2">
							<input type="text" id="input_date" class="form-control"
								placeholder="Date" name="date"
								value='<fmt:formatDate value="${patient.date}" pattern="dd/MM/yyyy"/>'>
							<span class="fa fa-calendar txt-danger form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">所属医院</label>
						<div class="col-sm-2">
							<select class="populate placeholder" id="province">
								<option value=''>-- 选择省 --</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select class="populate placeholder" id="city">
								<option value="">-- 选择市 --</option>
							</select>
						</div>
						<div class="col-sm-3">
							<select class="populate placeholder" name="hospital"
								id="hospital">
								<option value="${patient.hospital}">-- 选择医院 --</option>
							</select>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">科室</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" placeholder="科室"
								name="office" value="${patient.office}">
						</div>
					</div>

					<div class="form-group ">
						<label class="col-sm-2 control-label">对应床位号</label>
						<div class="col-sm-2">
							<select class="populate placeholder" id="domain" name="domain">
								<option value='${patient.domain }'>-- 选择病区 --</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select class="populate placeholder" id="room" name="room">
								<option value="${patient.room }">-- 选择房间 --</option>
							</select>
						</div>
						<div class="col-sm-3">
							<select class="populate placeholder" id="bed" name="bed_id">
								<option value="${patient.bed_id }">${patient.bed}</option>
							</select>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">是否在疗</label>
						<div class="col-sm-2">
							<select class="populate placeholder" name="state" id="state">
								<c:if test="${patient.state == 1}">
									<option value='1'>-- 在疗 --</option>
									<option value='2'>-- 出院 --</option>
								</c:if>
								<c:if test="${patient.state == 2}">
									<option value='2'>-- 出院 --</option>
									<option value='1'>-- 在疗 --</option>
								</c:if>
							</select>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">所属医生</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="doctor_id"
								id="doctor_id">
								<option value="${patient.doctor_id}">-- 默认关联医生 --</option>
								<c:forEach items="${doctors}" var="doctor">
									<option value="${doctor.id}">${doctor.name}</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-2">
							<button type="submit" class="btn btn-primary btn-label-left">
								<span><i class="fa fa-clock-o"></i></span> Submit
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// Run Select2 plugin on elements
	function DemoSelect2() {
		$('#province').select2();
		$('#city').select2();
		$('#hospital').select2();

		$('#domain').select2();
		$('#room').select2();
		$('#bed').select2();

		$("#state").select2();
		$("#doctor_id").select2();
	}
	function loadProvince() {
		$.ajax({
			mimeType : 'text/html; charset=utf-8',
			url : "../provinces/all.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择省 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#province").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	function bang() {
		$("#province").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_city .select2-chosen").html("-- 选择市 --");
			loadCities(p1);
		});
		$("#city").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_hospital .select2-chosen").html("-- 选择医院 --");
			loadHospitals(p1);
		});
		$("#hospital").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_domain .select2-chosen").html("-- 选择病区 --");
			loadDomains(p1);
		});
		$("#domain").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_room .select2-chosen").html("-- 选择房间 --");
			loadRooms(p1);
		});
		$("#room").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_bed .select2-chosen").html("-- 选择床位 --");
			loadBeds(p1);
		});
	}
	function loadCities(provinceId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../cities/find/province/" + provinceId + "/cities.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择市 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#city").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	function loadHospitals(cityId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../hospitals/find/city/" + cityId + "/hospital.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择医院 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#hospital").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}

	function loadDomains(hospitalId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../domains/find/hospital/" + hospitalId + "/domains.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择病区 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#domain").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	function loadRooms(domainId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../rooms/find/domain/" + domainId + "/rooms.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择房间--</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#room").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	function loadBeds(roomId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../beds/find/room/" + roomId + "/beds.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择床号--</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#bed").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}

	$(document).ready(function() {
		// Create Wysiwig editor for textare
		TinyMCEStart('#skill', null);
		TinyMCEStart('#background', null);
		TinyMCEStart('#achievement', null);
		// Initialize datepicker
		$('#input_date').datepicker({
			setDate : new Date()
		});
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadSelect2Script(DemoSelect2);
		// Load example of form validation
		LoadBootstrapValidatorScript(DemoFormValidator);
		// Add drag-n-drop feature to boxes
		WinMove();
		//加載省
		loadProvince();
		//加載市
		bang();
		//加载医院
	});
</script>
