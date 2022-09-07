<%@page import="com.academy.springmvcsimple.domain.Emp"%>
<%@page import="com.academy.springmvcsimple.util.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% List<Emp> memberList =(List) request.getAttribute("memberList"); 
	Pager pager = new Pager();
	pager.init(memberList, request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록</title>
 <%@ include file="/inc/header.jsp" %>

</head>
<body>

	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">Responsive Hover Table</h3>

					<div class="card-tools">
						<div class="input-group input-group-sm" style="width: 150px;">
							<input type="text" name="table_search"
								class="form-control float-right" placeholder="Search">

							<div class="input-group-append">
								<button type="submit" class="btn btn-default">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.card-header -->
				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap">
						<thead>
							<tr>
								<th>사원번호</th>
								<th>사원명</th>
								<th>급여</th>
								<th>입사일</th>
								<th>부서번호</th>
								<th>부서위치</th>
								<th>부서명</th>
							</tr>
						</thead>
						<tbody>
							<% for(Emp emp : memberList){ %>
							<tr>
								<td><%=emp.getEmpno()%></td>
								<td><a href="/member/detail?empno=<%=emp.getEmpno()%>"><%=emp.getEname()%></a></td>
								<td><%=emp.getSal()%></td>
								<td><%=emp.getHiredate() %></td>
								<td><%=emp.getDept().getDeptno() %></td>
								<td><%=emp.getDept().getDname() %></td>
								<td><%=emp.getDept().getLoc() %></td>
							</tr>
							<%} %>
						</tbody>
					</table>
					<button type="button" class="btn btn-primary" style ="margin-left:10px">사원등록</button>
				</div>
				<!-- /.card-body -->
				
			</div>
			<!-- /.card -->
		</div>

	</div>
	

	<!-- /.row -->
<%@ include file="/inc/footer.jsp" %>
 <script>
$(function(){
	$("button[type='button']").click(function(){
		location.href="/member/regist.jsp"
	});
}); 
 
</script>
</body>
</html>