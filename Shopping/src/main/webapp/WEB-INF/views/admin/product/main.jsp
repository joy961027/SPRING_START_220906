<%@page import="com.academy.shopping.model.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% List<Product> productList = (List)request.getAttribute("productList");%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Advanced form elements</title>
  <!--  include header-->
 <%@ include file="../inc/header_link.jsp" %>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
<%@ include file="../inc/topbar.jsp" %>
<%@ include file="../inc/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>상품 목록</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Advanced Form</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        
   
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Responsive Hover Table</h3>

                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

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
                      <th>No</th>
                      <th>카테고리</th>
                      <th>사진</th>
                      <th>상품명</th>
                      <th>브랜드</th>
                      <th>가격</th>
                      <th>할인가</th>
                      <th>파일명</th>
                    </tr>
                  </thead>
                  <tbody>
                  <%for(int i=0; i<productList.size(); i++){ %>
                  <% Product product = productList.get(i); %>
                    <tr>
                      <td><%=i %></td>
                      <td><%=product.getSubcategory().getCategory_name() %></td>
                      <td><img src="/static/data/<%=product.getProduct_img() %>" style="width:45px; height:45x;"></td>
                      <td><a href="/admin/product/detail?product_id=<%=product.getProduct_id()%>"><%=product.getProduct_name() %></a></td>
                      <td><%=product.getBrand() %></td>
                      <td><%=product.getPrice() %></td>
                      <td><%=product.getDiscount() %></td>
                      <td><%=product.getProduct_img() %></td>
                    </tr>
                    <%} %>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
        <button class="btn btn-primary" onClick="location.href='/admin/product/registForm';">상품 등록</button>
        <button class="btn btn-primary" onClick="showExcel()">엑셀 등록</button>
        <div style="display:none" id="excel-area">
        
        	<form id="excel-form">
        		<input type="file" name="excel">
        		<button type ="button" class="btn btn-info" onClick="registExcel()">등록</button>
        	</form>
        	
        </div>
        <!-- /.row -->
       <!-- ------------------------ -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<%@ include file="../inc/footer_link.jsp"%>

<script>


function registTop(){
	$.ajax({
		url:"/rest/admin/topcategory",
		type:"post",
		data:{
			category_name:$($("input[name='category_name']")[0]).val()
		},
		success:function(result, status, xhr){
			alert(status);
			getTopList();
		},
		error:function(xhr,status,error){
			alert(status+","+error);
		}
		
	});
	
}

function getTopList(){
	$.ajax({
		url:"/rest/admin/topcategory",
		type:"get",
		success:function(result,status,xhr){
			printTopList(result);
		},
		error:function(xhr,status,error){
			alert(status+","+error);
		}
	});
}

function printTopList(jsonArray){
	var sel=$($("select")[0]);
	$(sel).empty();
	for(var i=0; i<jsonArray.length; i++){
		var topcategory =jsonArray[i];
		$(sel).append("<option value=\""+topcategory.topcategory_id +"\">"+topcategory.category_name+"</option>");
	}
	
}

function registSub(){
	if($($("select")[0]).prop('selectedIndex')==-1){
		alert("좌측 영역에서 하나라도 선택하세요");
		return;
	}
	
	$.ajax({
		url:"/rest/admin/subcategory",
		type:"post",
		data:{
			"category_name":$($("input[name='category_name']")[1]).val(),
			"topcategory.topcategory_id":$($("select")[0]).val()
		},
		success:function(result,status,xhr){
			alert(result);
			getSubList($($("select")[0]).val());
		},
		error:function(xhr,status,error){

		}
	});
}
function getSubList(topcategory_id){
	$.ajax({
		url:"/rest/admin/subcategory/"+topcategory_id ,
		type:"get",
		success:function(result,status,xhr){
			printSubList(result);
		},
		error:function(xhr,status,error){
			alert(status+","+error);
		}
		
	});
	
}
function printSubList(jsonArray){
	var sel=$($("select")[1]);
	$(sel).empty();
	for(var i=0; i<jsonArray.length; i++){
		var subcategory =jsonArray[i];
		$(sel).append("<option value=\""+subcategory.subcategory_id +"\">"+subcategory.category_name+"</option>");
	}
	
}
//엑셀 파일을 위한 상품 일괄 등록
function registExcel(){
	if($("input[name='excel']").val()==""){
		alert("선택한 파일이 없습니다");
		return;
	}
	if(confirm("엑셀로 등록할까요?")){
		$("#excel-form").attr({
			action:"/admin/product/excel",
			method:"post",
			enctype:"multipart/form-data"
		});
		$("#excel-form").submit();
	}
	
}
//가려져 있던 excel 등록 폼을 등록
function showExcel(){
	$("#excel-area").show();
}

$(function () {
	getTopList();
	
	$($("select")[0]).change(function(){
		//alert("당신이 선택한 아이템의 value값은 " + $(this).val())
		getSubList($(this).val());
		
	});
	
	$('.nav-link').click(function(){
		$('.nav-link').removeClass('active');
		$(this).addClass('active');
	});
	
	$("input[name='excel']").change(function(){
		var ext =getExt($(this).val());
		if(ext!="xls" && ext!="xlsx"){
			alert("excel만 선택해주세요");
			$(this).val(null);
		}		
	});
		
  
});
</script>
</body>
</html>
