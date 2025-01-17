<%@page import="com.academy.shopping.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<TopCategory> topCategoryList = (List)request.getAttribute("topCategoryList");
%>
 <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="#"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="/static/shop/index.html"><img src="/static/shop/img/logo.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
            <a href="/shop/member/loginForm">Login</a>
            <a href="/shop/member/registForm">Register</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="/shop"><img src="/static/shop/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <!-- <li class="active"><a href="/static/shop/index.html">Home</a></li> -->
                            
                            <%for(int i=0; i<topCategoryList.size(); i++){ %>
                            <%TopCategory topCategory = topCategoryList.get(i); %>
                            <li><a href="/shop/product?topcategory_id=<%=topCategory.getTopcategory_id()%>"><%=topCategory.getCategory_name() %></a></li>
                            <%} %>
                            
                            <li><a href="/shop/product">Shop</a></li>
                            
                          	<!--   <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="/static/shop/product-details.html">Product Details</a></li>
                                    <li><a href="/static/shop/shop-cart.html">Shop Cart</a></li>
                                    <li><a href="/static/shop/checkout.html">Checkout</a></li>
                                    <li><a href="/static/shop/blog-details.html">Blog Details</a></li>
                                </ul>
                            </li> -->
                            
                            <li><a href="/static/shop/blog.html">Blog</a></li>
                            <li><a href="/static/shop/contact.html">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                        <div class="header__right__auth">
                        	<%if(session.getAttribute("member")==null) {%>
                            	<a href="/shop/member/loginForm">Login</a>
                            	<a href="/shop/member/registForm">Register</a>
                        	<%}else{ %>
                            	<a href="javascript:logout()">Logout</a>
                            <%} %>
                        </div>
                        <ul class="header__right__widget">
                            <li><span class="icon_search search-switch"></span></li>
                            <li><a href="#"><span class="icon_heart_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                            <li><a href="/shop/cart/list"><span class="icon_bag_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
	<script>
	function logout(){
		if(confirm("로그아웃 하시겠습니까")){
			location.href="/shop/member/logout";
		}
	}
	</script>
