<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生交流平台</a></li>
			<li><a href="index.do">预约信息管理</a></li>
			<li><a href="#">编辑预约</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>预约信息</span>
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
				<h4 class="page-header">填写预约信息</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="defaultForm" action="order_edit_json.do">
					<input type="hidden" name="id" value="${order.id}" />
					<div class="form-group">
						<label class="col-sm-2 control-label">预约者</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="doctor_id"
								id="doctor_id">
								<c:forEach items="${doctors}" var="doctor">
									<c:if test="${doctor.id==order.doctor_id}">
										<option value='${doctor.id}' selected>${doctor.name}（是否要修改？）</option>
									</c:if>
									<c:if test="${doctor.id!=order.doctor_id}">
										<option value='${doctor.id}'>${doctor.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">被预约者</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="friend_id"
								id="friend_id">
								<c:forEach items="${doctors}" var="doctor">
									<c:if test="${doctor.id==order.friend_id}">
										<option value='${doctor.id}' selected>${doctor.name}（是否要修改？）</option>
									</c:if>
									<c:if test="${doctor.id!=order.friend_id}">
										<option value='${doctor.id}'>${doctor.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group has-feedback">
						<label class="col-sm-2 control-label">预约时间</label>
						<div class="col-sm-2">
							<input type="date" id="input_date" class="form-control"
								placeholder="Date" name="date"><span
								class="fa fa-calendar txt-danger form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">预约状态</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="state" id="order_state">
								<option value='0'>未预约成功</option>
								<option value='1'>预约成功</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">提醒次数</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="remind"
								value="${order.remind}" />
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2">
							<button type="cancel" class="btn btn-default btn-label-left">
								<span><i class="fa fa-clock-o txt-danger"></i></span> Cancel
							</button>
						</div>
						<div class="col-sm-2">
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
	function DemoSelect2() {
		$('#doctor_id').select2();
		$('#friend_id').select2();
		$('#order_state').select2();
	}
	$(document).ready(function() {
		// Initialize datetimepicker
		$('#input_date').datetimepicker({
			timeFormat : "HH:mm:ss",
			dateFormat : "yy-mm-dd"
		});
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadSelect2Script(DemoSelect2);
		// Load example of form validation
		LoadBootstrapValidatorScript(OrderAddFormValidator);
		// Add drag-n-drop feature to boxes
		WinMove();
	});
	function OrderAddFormValidator() {
		$('#defaultForm').bootstrapValidator({
			feedbackIcons : {
				valid : 'fa fa-check',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			message : 'This value is not valid',
			fields : {

				doctor_id : {
					validators : {
						different : {
							field : 'friend_id',
							message : '预约者和被预约者为同一个人'
						}
					}
				},
				friend_id : {
					validators : {
						different : {
							field : 'doctor_id',
							message : '预约者和被预约者为同一个人'
						}
					}
				},
				date : {
					validators : {
						notEmpty : {
							message : '日期不能为空'
						}
					}
				}
			}
		});
	}
</script>
