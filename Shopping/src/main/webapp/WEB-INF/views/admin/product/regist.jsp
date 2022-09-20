<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Advanced form elements</title>
  <!--  include header-->
 <%@ include file="../inc/header_link.jsp" %>
 
 <style>
 .col-md-9 *{
 	margin: 3px;
 }
 </style>
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
            <h1>상품 등록</h1>
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
      	<form>
      	<!--select 2-->
	        <div class="card card-primary">
	          <div class="card-header">
	            <h3 class="card-title">상품등록 폼</h3>
	
	            <div class="card-tools">
	              <button type="button" class="btn btn-tool" data-card-widget="collapse">
	                <i class="fas fa-minus"></i>
	              </button>
	              <button type="button" class="btn btn-tool" data-card-widget="remove">
	                <i class="fas fa-times"></i>
	              </button>
	            </div>
	          </div>
	          <!-- /.card-header -->
	          <div class="card-body">
	            <div class="row">
	            
	            <!-- 좌측 3 영역 차지 begin-->
	            	<div class="col-md-3">
		              <div class="col-md-12">
		                <div class="form-group">
		                  <label>상위카테고리</label>
		                  <select class="form-control select" style="width: 100%;" size="7">
		                    <option selected="selected"></option>
		                  </select>
		                </div>
		              </div>
		              <!-- /.col -->
		              
		              
		            <div class="col-md-12">
		           
		                <div class="form-group">
		                  <label>하위 카테고리</label>
		                  <select class="form-control select" style="width: 100%;" size="7" name="subcategory.subcategory_id">
		                  </select>
		                </div>
		              </div>
	            	</div>
	             <!-- 좌측 3 영역 차지  end-->
	             
	             <!-- 우측 9 영역 차지 begin-->
	            	<div class="col-md-9">
		            		<input type="text" placeholder="상품명" class="form-control" name="product_name">
		            		<input type="text" placeholder="브랜드" class="form-control" name="brand">
		            		<input type="number" placeholder="원가격" class="form-control" name="price">
		            		<input type="number" placeholder="할인가격" class="form-control" name="discount">
							<textarea class="form-control" placeholder="간략설명" name="memo"></textarea>            		
							<textarea class="form-control" placeholder="상품상세설명" id="summernote" name="detail"></textarea>
							<input type="file" class="form-control" placeholder="상품이미지 선택" name="photo">            		
							<button type="button" class="btn btn-info" onClick="registProduct()">상품 등록</button>
							<button type="button" class="btn btn-info" onClick="location.href='/admin/product/list';">목록 보기</button>
	            	</div>
	             <!-- 우측 9 영역 차지  end-->
	            </div>
	          </div>
	          <!-- /.card-body -->
	          <div class="card-footer">
	   			원하시는 아이템을 선택 후 삭제 및 수정이 가능합니다.
	          </div>
	        </div>
        <!-- /.card -->
		</form>
        <!-- SELECT2 EXAMPLE -->
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
<%@ include file="../inc/footer_link.jsp" %>
<script>
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
function registProduct(){
	if(confirm("상품을 등록하시겠습니까?")){
		$("form").attr({
			"action":"/admin/product/regist",
			"method":"post",
			"enctype":"multipart/form-data"
		});
		$("form").submit();
	}
}
$(function () {
	getTopList();
	
	$($("select")[0]).change(function(){
		//alert("당신이 선택한 아이템의 value값은 " + $(this).val())
		getSubList($(this).val());
		
	});
	 $('#summernote').summernote({
		 height: 200
	 });

});
</script>
</body>
</html>