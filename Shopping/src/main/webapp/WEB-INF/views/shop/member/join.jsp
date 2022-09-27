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
	                <h3 class="card-title">회원가입</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form>
	                <div class="card-body">
	                
	                  <div class="form-group row">
	                    <label for="customer_id" class="col-sm-2 col-form-label">ID</label>
	                    
	                    <div class="col-sm-8">
                     		<input type="text" class="form-control" id="customer_id" placeholder="ID" name="customer_id">
                      		<button type="button" class="btn btn-warning">중복체크</button>
	                    </div>
	                  </div>
	                  
	                          
	                  <div class="form-group row">
	                    <label for="customer_name" class="col-sm-2 col-form-label">Name</label>
	                    <div class="col-sm-10">
	                      <input type="text" class="form-control" id="customer_name" placeholder="Name" name="customer_name">
	                    </div>
	                  </div>
	                  
	                  
	                  <div class="form-group row">
	                    <label for="customer_pass" class="col-sm-2 col-form-label">Password</label>
	                    <div class="col-sm-10">
	                      <input type="password" class="form-control" id="customer_pass" placeholder="Password" name="customer_pass">
	                    </div>
	                  </div>
	                  
	                          
	                  <div class="form-group row">
	                    <label for="customer_email" class="col-sm-2 col-form-label">Email</label>
	                    <div class="col-sm-10">
	                      <input type="email" class="form-control" id="customer_email" placeholder="Email" name="customer_email">
	                    </div>
	                  </div>
	                  
	                </div>
	                <!-- /.card-body -->
	                <div class="card-footer">
	                  <button type="button" class="btn btn-info">로그인</button>
	                  <button type="button" class="btn btn-info">등록</button>
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
var isCheck=false;
var validId=false;
function regist(){
	if(!isCheck){
		alert("중복 체크를 해주세요.");
		return;
	}
	if(!validId){
		alert("중복된 아이디 입니다");
		return;
	}
	
	//폼전송
	$.ajax({
		url:"/rest/member",
		type:"post",
		data:{
			customer_id:$("#customer_id").val(),
			customer_name:$("#customer_name").val(),
			customer_pass:$("#customer_pass").val(),
			customer_email:$("#customer_email").val()
		},
		success:function(result,status,xhr){
			if(result.code==1){
				alert(result.msg);
			}else{
				alert("회원가입을 실패\n 계속될 경우 관리자에게 연락바랍니다");
			}
		},
		error:function(xhr,status,error){
			alert(error);
		}
	});
	
}
function login(){
	location.href="/shop/member/loginForm"
}

function checkId(){
	if($("#customer_id").val()==""){
		alert("아이디를 입력하세요");
		return;
	}
	isCheck=true;
	$.ajax({
		url:"/rest/member/check?customer_id="+$("#customer_id").val(),
		type:"get",
		success:function(result,status,xhr){
			(result.code==1)?validId=true:validId=false;
			alert(result.msg);
		}
	});
}

$(function(){
	//회원 등록 버튼에 이벤트 연결
	$($("form button")[0]).click(function(){//중복확인
		checkId();
	});
	
	$($("form button")[1]).click(function(){//로그인
		login();
	});
	
	$($("form button")[2]).click(function(){//가입
		regist();
	});
});
</script>
</body>

</html>