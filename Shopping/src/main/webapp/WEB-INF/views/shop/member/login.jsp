<%@page import="com.academy.shopping.model.util.CurrencyFormatter"%>
<%@page import="com.academy.shopping.model.domain.Product"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>
    
	<!-- include css Begin -->
	<%@ include file="../inc/css.jsp" %>
	<!-- include css End -->
	
</head>

<body>
   
    
	<!-- include header section Begin-->
	<%@ include file="../inc/topbar.jsp" %>
	<!-- include header section End -->
	

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->


	<section class="product-details spad">
        <div class="container">
            <div class="row">
	            <!-- Horizontal Form -->
	            <div class="card card-info col-sm-12">
	            	<div class="card-header"> 
	                <h3 class="card-title">Login</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form>
	                <div class="card-body">
	                
	                  <div class="form-group row">
	                    <label for="customer_id" class="col-sm-2 col-form-label">ID</label>
	                    <div class="col-sm-10">
                     		<input type="text" class="form-control" id="customer_id" placeholder="ID" name="customer_id">
	                    </div>
	                  </div>
	                  
	                  <div class="form-group row">
	                    <label for="customer_pass" class="col-sm-2 col-form-label">Password</label>
	                    <div class="col-sm-10">
	                      <input type="password" class="form-control" id="customer_pass" placeholder="Password" name="customer_pass">
	                    </div>
	                  </div>
	                  
	                  
	                </div>
	                <!-- /.card-body -->
	                <div class="card-footer">
	                  <button type="button" class="btn btn-info" >로그인</button>
	                  <button type="button" class="btn btn-info">회원가입</button>
	                </div>
	                <!-- /.card-footer -->
	              </form>
	            </div>
	            <!-- /.card -->
            </div>
         </div>
     </section>



	<!-- include instar Begin -->
	<%//@ include file="../inc/instar.jsp" %>
	<!-- include instar End  -->


	<!-- include footer Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- include footer End -->

	<!-- include search Begin -->
	<%@ include file="../inc/search.jsp" %>
	<!-- include search End -->

	<!-- include jsPlugins  -->
	<%@ include file="../inc/plugin.jsp" %>
<script>
function login(){
	$.ajax({
		url:"/rest/member/login",
		type:"post",
		data:{
			customer_id:$("#customer_id").val(),
			customer_pass:$("#customer_pass").val()
		},
		success:function(result,stauts, xhr){
			alert(result.msg);
			if(result.code==1){
				location.href="/shop"	
			}
		}
	})
}


$(function(){
	//회원 등록 버튼에 이벤트 연결
	$($("form button")[0]).click(function(){//로그인
		login();
	});
	
	$($("form button")[1]).click(function(){//회원가입
		location.href="/shop/member/registForm";
	});
	
});
</script>
</body>

</html>