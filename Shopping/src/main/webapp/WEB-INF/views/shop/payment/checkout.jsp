<%@page import="com.academy.shopping.model.domain.Paymethod"%>
<%@page import="com.academy.shopping.model.domain.Member"%>
<%@page import="com.academy.shopping.model.domain.Cart"%>
<%@page import="com.academy.shopping.model.util.CurrencyFormatter"%>
<%@page import="com.academy.shopping.model.domain.Product"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
Member member =(Member) request.getAttribute("member");
	List<Cart> cartList =(List) request.getAttribute("cartList");
	List<Paymethod> payMethodList =(List) request.getAttribute("payMethodList");
	int totalPrice=0;
	int totalDiscount =0;
%>
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
                        <span>Shopping cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->



    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="#">Have a coupon?</a> Click
                    here to enter your code.</h6>
                </div>
            </div>
            <form id="pay-form" class="checkout__form">
            	
                <div class="row">
                    <div class="col-lg-8">
                        <h5>구매자</h5>
                        <div class="row">
                        
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>회원 ID <span>*</span></p>
                                    <input type="text" value="<%=member.getCustomer_id()%>">
                                </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>이름 <span>*</span></p>
                                    <input type="text" value="<%=member.getCustomer_name()%>">
                                </div>
                            </div>
              
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="text" value="<%=member.getCustomer_email()%>">
                                </div>
                            </div>
             
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="checkout__order">
                                <h5>결제 정보</h5>
                                <div class="checkout__order__product">
                                    <ul>
                                        <li>
                                            <span class="top__text">Product</span>
                                            <span class="top__text__right">Total</span>
                                        </li>
                                        <%
                                        for(int i=0; i<cartList.size(); i++){
                                        %>
                                        <%
                                        Cart cart = cartList.get(i);
                                        %>
                                        <li><%=i+1%>. <%=cart.getProduct_name()%> <span><%=CurrencyFormatter.getCurrency(cart.getDiscount())%> * <%=cart.getQuantity()%></span></li>
                                        <%
                                        totalDiscount += cart.getDiscount()*cart.getQuantity() ;
                                        %>
                                        <%
                                        }
                                        %>
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                    <ul>
                                        <li>구매총액 <span><%=CurrencyFormatter.getCurrency(totalDiscount)%></span></li>
                                        <li>최종결제금액 <span><%=CurrencyFormatter.getCurrency(totalDiscount)%></span></li>
                                    </ul>
                                </div>
                                <div class="checkout__order__widget">
                                	<%
                                	for(int i=0; i<payMethodList.size(); i++){
                                	%>
                                	<%
                                	Paymethod payMethod = payMethodList.get(i);
                                	%>
                                    <label for="o-acc<%=i%>">
                                      <%=payMethod.getPayname() %>
                                        <input type="radio" id="o-acc<%=i%>" name="paymethod.paymethod_id" value="<%=payMethod.getPaymethod_id()%>">
                                        <span class="checkmark"></span>
                                    </label>
                                    <%} %>
                                </div>
                                <button type="button" class="site-btn" onClick="pay()">Place order</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="totalbuy" value="<%=totalDiscount %>">
            		<input type="hidden" name="totalpay" value="<%=totalDiscount %>">
                </form>
            </div>
        </section>
        <!-- Checkout Section End -->




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
function pay(){
	if(confirm("입력하신 정보로 결제 하시겠습니까?")){
		//사실은 각종 선택한 포인트, 쿠폰, 배송지, 배송정보
		$("#pay-form").attr({
			action:"/shop/pay",
			method:"post"
		});
		$("#pay-form").submit();
	}
	
}

$(function(){
	
});
</script>
</body>

</html>