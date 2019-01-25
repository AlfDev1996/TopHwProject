<%@ page import="it.unisa.tophw.server.model.beans.ProductBean" %><%--
  Created by IntelliJ IDEA.
  User: the_l
  Date: 12/01/2019
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<title>Product</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Sublime project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="styles/product.css">
<link rel="stylesheet" type="text/css" href="styles/product_responsive.css">
	<script src="js/productFunction.js"></script>


</head>
<body>
<%@include  file="header.jsp" %>
	<%
		if(request.getAttribute("prodotto")==null)
		{	String redirectURL = "/index.jsp";
			response.sendRedirect(request.getContextPath() + redirectURL);
		}
		else
		{ProductBean prodotto= (ProductBean)request.getAttribute("prodotto");

	%>
<script>
    var qta=0;
    function updateqta (){
        qta= document.getElementById("quantity_input").value;
        var link = "ServletAddProductToCart?id_prodotto=<%=prodotto.getId_prodotto()%>&quantita="+qta;
        window.location=link;
    }

</script>


	<!-- Product Details -->

	<div class="product_details" style="margin-top:9%">
		<div class="container">
			<div class="row details_row">

				<!-- Product Image -->
				<div class="col-lg-6">
					<div class="details_image">
						<div class="details_image_large"><img src='<%="images/products/"+prodotto.getPathImg1()%>' alt=""><div class="product_extra product_new"><a href="categories.jsp">New</a></div></div>
						<div class="details_image_thumbnails d-flex flex-row align-items-start justify-content-between">
							<!--<div class="details_image_thumbnail active" data-image="images/details_1.jpg"><img src="images/details_1.jpg" alt=""></div> -->
							<div class="details_image_thumbnail" data-image='<%="images/products/"+prodotto.getPathImg1()%>'><img src='<%="images/products/"+prodotto.getPathImg1()%>' alt=""></div>
							<div class="details_image_thumbnail" data-image='<%="images/products/"+prodotto.getPathImg2()%>'><img src='<%="images/products/"+prodotto.getPathImg2()%>' alt=""></div>
							<div class="details_image_thumbnail" data-image='<%="images/products/"+prodotto.getPathImg3()%>'><img src='<%="images/products/"+prodotto.getPathImg3()%>' alt=""></div>
						</div>
					</div>
				</div>

				<!-- Product Content -->
				<div class="col-lg-6">
					<div class="details_content">
						<div class="details_name"><%=prodotto.getNome()%></div>
						<%if(prodotto.getPerc_sconto()!=0 ){ double tmp= (prodotto.getPrezzo()/100)*prodotto.getPerc_sconto();
						Double prezzo_aumentato = prodotto.getPrezzo()+tmp;%>
						<div class="details_discount">€ <%=prezzo_aumentato%></div>
						<%}%>
						<div class="details_price">€ <%=prodotto.getPrezzo()%></div>

						<!-- In Stock -->
						<div class="in_stock_container">
							<div class="availability">Disponibilità:</div>
							<span>Dispobibile(pezzi: <%=prodotto.getQuantita()%>)</span>
						</div>
						<div class="details_text">
							<p><%=prodotto.getDescrizione_estesa()%></p>
						</div>

						<!-- Product Quantity -->
						<div class="product_quantity_container">
							<div class="product_quantity clearfix">
								<span>Qtà</span>
								<input id="quantity_input" type="text"  value="1">
								<div class="quantity_buttons">
									<div id="upQuantity" onclick='controlQuantity(<%=prodotto.getQuantita()%>)' class="quantity_inc quantity_control"><i class="fa fa-chevron-up" aria-hidden="true"></i></div>
									<div id="quantity_dec_button" class="quantity_dec quantity_control"><i class="fa fa-chevron-down" aria-hidden="true"></i></div>
								</div>
							</div>
							<div class="button cart_button" ><a onclick="updateqta();"> Aggiungi al carrello</a></div>
						</div>

						<!-- Share -->
						<div class="details_share">
							<span>Condividi:</span>
							<ul>
								<li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="row description_row">
				<div class="col">
					<div class="description_title_container">
						<div class="description_title">Descrizione</div>
						<div class="reviews_title"><a href="#">Reviews <span>(1)</span></a></div>
					</div>
					<div class="description_text">
						<p><%=prodotto.getDescrizione_estesa()%></p>
					</div>
				</div>
			</div>
		</div>
	</div>
<%}%>
	<!-- Products -->

	<div class="products">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="products_title">Related Products</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					
					<div class="product_grid">

						<!-- Product -->
						<div class="product">
							<div class="product_image"><img src="images/product_1.jpg" alt=""></div>
							<div class="product_extra product_new"><a href="categories.jsp">New</a></div>
							<div class="product_content">
								<div class="product_title"><a href="product.jsp">Smart Phone</a></div>
								<div class="product_price">$670</div>
							</div>
						</div>

						<!-- Product -->
						<div class="product">
							<div class="product_image"><img src="images/product_2.jpg" alt=""></div>
							<div class="product_extra product_sale"><a href="categories.jsp">Sale</a></div>
							<div class="product_content">
								<div class="product_title"><a href="product.jsp">Smart Phone</a></div>
								<div class="product_price">$520</div>
							</div>
						</div>

						<!-- Product -->
						<div class="product">
							<div class="product_image"><img src="images/product_3.jpg" alt=""></div>
							<div class="product_content">
								<div class="product_title"><a href="product.jsp">Smart Phone</a></div>
								<div class="product_price">$710</div>
							</div>
						</div>

						<!-- Product -->
						<div class="product">
							<div class="product_image"><img src="images/product_4.jpg" alt=""></div>
							<div class="product_content">
								<div class="product_title"><a href="product.jsp">Smart Phone</a></div>
								<div class="product_price">$330</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter_border"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 offset-lg-2">
					<div class="newsletter_content text-center">
						<div class="newsletter_title">Subscribe to our newsletter</div>
						<div class="newsletter_text"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros</p></div>
						<div class="newsletter_form_container">
							<form action="#" id="newsletter_form" class="newsletter_form">
								<input type="email" class="newsletter_input" required="required">
								<button class="newsletter_button trans_200"><span>Subscribe</span></button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- Footer -->

<div class="footer_overlay"></div>
<%@include  file="footer.jsp" %>
</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/product.js"></script>
</body>
</html>