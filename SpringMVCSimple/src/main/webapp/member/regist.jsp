<%@ page contentType="text/html;charset=UTF-8"%>
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
			<h3 class="card-title">사원등록</h3>
		</div>
		<!-- /.card-header -->
		<!-- form start -->
		<form class="form-horizontal">
			<div class="card-body">
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">사원명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="ename" placeholder="사원명을 적으세요 ">
					</div>
				</div>
				<!-- sal -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망급여</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" name="sal" placeholder="희망 급여를 적으세요..">
					</div>
				</div>
				<!-- 희망부서 -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망부서명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="dept.dname" placeholder="희망 부서명을 적으세요..">
					</div>
				</div>
				<!-- 부서 위치 -->
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">부서 위치</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="dept.loc" placeholder="희망 부서위치를 적으세요..">
					</div>
				</div>
			</div>
			<!-- /.card-body -->
			<div class="card-footer">
				<button type="button" class="btn btn-primary">사원등록</button>
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
  $("button[type='button']").click(function(){
	  $(".form-horizontal").attr({
		  action:"/member/regist",
		  method:"post"
		  });
	  $(".form-horizontal").submit();
  });
  
});
</script>
</body>
</html>