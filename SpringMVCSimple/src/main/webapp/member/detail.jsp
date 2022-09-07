<%@page import="com.academy.springmvcsimple.domain.Emp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% Emp emp = (Emp)request.getAttribute("emp"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
 <%@ include file="/inc/header.jsp" %>
</head>
<body>
	<div class="card card-info">
		<div class="card-header">
			<h3 class="card-title">사원정보</h3>
		</div>
		<!-- /.card-header -->
		<!-- form start -->
		<form class="form-horizontal">
			<div class="card-body">
				<!-- 사원번호 -->
				<div class="form-group row">
					<div class="col-sm-10">
						<input type="hidden" class="form-control" name="empno" value="<%=emp.getEmpno()%>">
					</div>
				</div>
				
				<!-- 사원명 -->
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">사원명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="ename" value="<%=emp.getEname()%>">
					</div>
				</div>
				
				<!-- 급여 -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">급여</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="sal" value="<%=emp.getSal()%>">
					</div>
				</div>
				
				<!-- 입사일 -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">입사일</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="hiredate"value="<%=emp.getHiredate()%>">
					</div>
				</div>
				
				<!-- 부서번호 -->
				<div class="form-group row">
					<div class="col-sm-10">
						<input type="hidden" class="form-control" name="dept.deptno"value="<%=emp.getDept().getDeptno()%>">
					</div>
				</div>
				
				<!-- 부서명 -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">부서명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="dept.dname"value="<%=emp.getDept().getDname()%>">
					</div>
				</div>
				
				<!-- 부서위치 -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">부서위치</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="dept.loc"value="<%=emp.getDept().getLoc()%>">
					</div>
				</div>
				
			</div>
			<!-- /.card-body -->
			<div class="card-footer">
				<button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-primary">삭제</button>
				<button type="submit" class="btn btn-default float-right">Cancel</button>
			</div>
			<!-- /.card-footer -->
		</form>
	</div>

<%@ include file="/inc/footer.jsp" %>
<!-- AdminLTE for demo purposes -->
<!-- <script src="/resources/admin/dist/js/demo.js"></script> -->
<!-- Page specific script -->
<script>
$(function () {
  //bsCustomFileInput.init();
  $($("button[type='button']")[0]).click(function(){
	 if(confirm("수정하시겠습니까?")){
		 $(".form-horizontal").attr({
			 action:"/member/edit",
			 method:"post"
		 });
		 $(".form-horizontal").submit();
	 }
  });
  
  $($("button[type='button']")[1]).click(function(){
	  if(confirm("삭제하시겠습니까?")){
			 location.href="/member/delete?empno=<%=emp.getEmpno()%>";
		 }
  });
  
});
</script>
</body>
</html>