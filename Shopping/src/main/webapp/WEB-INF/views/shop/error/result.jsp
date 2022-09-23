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
<%-- 	<%@ include file="../inc/topbar.jsp" %> --%>
	<!-- include header section End -->
	

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <span>Shopping cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->



    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <p>이용에 불편을 드려 죄송합니다.</p>
                <%RuntimeException e = (RuntimeException) request.getAttribute("e");
                	out.print(e.getMessage());
                %>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->





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
			//alert(result.msg);
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
	
	$($("form button")[1]).click(function(){//로그인
		location.href="/shop/member/registForm";
	});
	
});
</script>
</body>

</html>