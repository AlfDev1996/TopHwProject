<%@ page import="it.unisa.tophw.server.model.beans.ProductBean" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: the_l
  Date: 12/01/2019
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<title>Cart</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Sublime project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/cart.css">
<link rel="stylesheet" type="text/css" href="styles/cart_responsive.css">
	<script src="js/productFunction.js"></script>

</head>
<body>

<div class="super_container">
	<%@include  file="header.jsp" %>

	<div class="row">
		<div class="col-xl-10" style="margin-top: 9%;margin-left: auto;margin-right: auto;">
			<h4 class="title"><span class="text"><strong>Il Tuo</strong> Carrello</span></h4>
			<div class="table-responsive">
				<table class="table table-striped" id="tableProdotti">
					<thead>
					<tr>
						<th>Remove</th>
						<th>Immagine</th>
						<th>Nome Prodotto</th>
						<th>Quantita'</th>

						<th>Prezzo Unitario</th>
						<th>Prezzo Totale</th>
					</tr>
					</thead>
					<tbody id="listProdotti">
					<%
						carrello = (CartBean) session.getAttribute("carrello");
						if(carrello!=null)
						{
							if(carrello.getProdotti()!=null && carrello.getProdotti().size()>0)
							{
								for(ProductBean prodotto : carrello.getProdotti())
								{
									int val = new Integer(prodotto.getQuantita());
					%>
					<tr id="<%=prodotto.getNome()%>">
						<td id="Alfonso"><input type="checkbox" name="prodotti[]" id='<%= prodotto.getId_prodotto()%>%>'></td>
						<% if( prodotto.getPathImg1()!=null) { %>
						<td style="width:25%"><a href=""><img class="imgCarrello" style="width: 50%;height: 50%;" alt="" src='<%="images/products/"+prodotto.getPathImg1()%>' onerror='this.onerror=null;this.src="themes/images/defaultImages/<%=prodotto.getPathImg1() %>"'></a></td>
						<% } else {  %>
						<td><a href=""><img alt="" src="themes/images/non-disponibile.png"></a></td>
						<% } %>
						<td><%= prodotto.getNome() %></td>
						<td><input type="text" class="input-mini" name="qtaProdotti[]" id="<%=prodotto.getId_prodotto()%>" value="<%=val%>"> </td>
						<td><input type="text" class="input-mini" value="<%=prodotto.getQuantita()%>"> </td>

						<td><%= prodotto.getPrezzo() %></td>
						<td><%= prodotto.getPrezzo()*prodotto.getQuantita() %></td>
					</tr>
					<%

						}

					%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>

						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><strong><%= carrello.getPrezzoTotale() %></strong></td>
					</tr>
					<%

							}
						} //End for %>


					</tbody>
				</table>
			</div>
			<hr>

			<% if(carrello!=null && carrello.getProdotti()!=null && carrello.getProdotti().size()>0) {
				DecimalFormat twoDForm = new DecimalFormat("#.##");


			%>
			<p class="cart-total right">
				<strong>Sub-Totale</strong>:<%=Math.floor((carrello.getPrezzoTotale()/1.22) * 100.0) / 100.0%><br>
				<strong>Iva (22%)</strong>: <%=Math.floor((carrello.getPrezzoTotale() - (carrello.getPrezzoTotale()/1.22)) * 100.0) / 100.0%><br>
				<strong>Totale</strong>: <%=Math.floor((carrello.getPrezzoTotale()) * 100.0) / 100.0 %><br>
			</p>
			<hr/>
			<% } %>

			<p class="buttons center">

				<button class="btn" type="button" onclick="updateProductsFromCart()">Update</button>
				<a href="checkout.jsp"> <button class="btn btn-inverse" id="checkout">Checkout</button> </a>

			</p>
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
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/cart.js"></script>
</body>
</html>