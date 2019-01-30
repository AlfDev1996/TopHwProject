<%@ page import="it.unisa.tophw.server.model.beans.AddressBean" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.concurrent.atomic.AtomicReference" %>
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
<title>Checkout</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Sublime project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/checkout.css">
<link rel="stylesheet" type="text/css" href="styles/checkout_responsive.css">

	<script src="js/userFunction.js"></script>
</head>
<body>

<%UserBean utente = new UserBean();
	Integer numProdotti = 0;
	if( session.getAttribute("utente")!= null)
	{
		utente = (UserBean) session.getAttribute("utente");
		if(utente.getIndirizzo()!=null){
			;
		}else{
			utente.setIndirizzo(new AddressBean());
		}
	}
	else
	{String redirectURL = "/index.jsp";

		response.sendRedirect(request.getContextPath() + redirectURL);
	}

	CartBean cartBean = new CartBean();
	if(session.getAttribute("carrello")!=null){
		cartBean = (CartBean) session.getAttribute("carrello");
		if(cartBean!=null && cartBean.getProdotti()!=null && cartBean.getProdotti().size()>0){
			for (ProductBean p : cartBean.getProdotti()){
				numProdotti+=p.getQuantita();
			}
		}

	}else{
		;
	}

	DecimalFormat f = new DecimalFormat("##.00");






%>

<div class="super_container">
	<%@include  file="header.jsp" %>
	


	<!-- Checkout -->
	
	<div class="checkout">
		<div class="container">
			<div class="row">

				<!-- Billing Info -->
				<div class="col-lg-6">
					<div class="billing checkout_section">
						<div class="section_title">Indirizzo di spedizione</div>
						<div class="checkout_form_container">
							<form action="#" id="checkout_form" class="checkout_form">
								<div>
									<!-- Via -->
									<label for="modvia">Via</label>
									<input type="text" id="modvia" name="modvia" class="checkout_input" readonly="readonly"  value ='<%=utente.getIndirizzo().getVia()%>'>
								</div>
								<div>
									<!-- Civico -->
									<label for="modcivico">Civico</label>
									<input type="text" id="modcivico" name="modcivico" class="checkout_input" readonly="readonly"  value ='<%=utente.getIndirizzo().getCivico()%>'>
								</div>
								<div>
									<!-- Comune -->
									<label for="modcomune">Comune</label>
									<input type="text" id="modcomune" name="modcomune" class="checkout_input" required="required" readonly="readonly"  value ='<%=utente.getIndirizzo().getComune()%>'>
								</div>
								<div>
									<!-- Zipcode -->
									<label for="modcap">Codice Postale</label>
									<input type="text" id="modcap" name="modcap" class="checkout_input" required="required" readonly="readonly"  value ='<%=utente.getIndirizzo().getCap()%>'>
								</div>
								<div>
									<!-- Provincia -->
									<label for="modprovincia">Provincia</label>
									<input type="text" id="modprovincia" name="modprovincia" class="checkout_input" required="required" readonly="readonly"  value ='<%=utente.getIndirizzo().getProvincia()%>'>
								</div>
								<div>
									<!-- Nazione -->
									<label for="modnazione">Nazione</label>
									<input type="text" id="modnazione" name="modnazione" class="checkout_input" required="required" readonly="readonly"  value ='<%=utente.getIndirizzo().getNazione()%>'>
								</div>
								<input type="hidden" id= "id_utente" value='<%=utente.getId_utente()%>'>
								<div class="form-group">
									<div class="col-xs-12">
										<br>
										<input type="button" class="btn btn-warning" value="Modifica" id="modIndirizzo" onclick="enableMod(this)">
										<input type="button" class="btn btn-success" value="Salva" name ="saveButton" id="saveIndirizzo" onclick="saveAddress(this)" style="display:none;">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- Order Info -->

				<div class="col-lg-6">
					<div class="order checkout_section">
						<div class="section_title">Il tuo ordine</div>
						<div class="section_subtitle">Dettagli ordine</div>

						<!-- Order details -->
						<div class="order_list_container">
							<div class="order_list_bar d-flex flex-row align-items-center justify-content-start">
								<div class="order_list_title">Num. Prodotti</div>
								<div class="order_list_value ml-auto"> <%= numProdotti%></div>
							</div>
							<ul class="order_list">
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div class="order_list_title">Subtotale</div>
									<div class="order_list_value ml-auto"> <%= f.format(cartBean.getPrezzoTotale()/(1.22))   %> </div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div class="order_list_title">Spedizione</div>
									<div class="order_list_value ml-auto">Gratuita</div>
								</li>
								<li class="d-flex flex-row align-items-center justify-content-start">
									<div class="order_list_title">Totale</div>
									<div class="order_list_value ml-auto"> <%= cartBean.getPrezzoTotale()  %> </div>
								</li>
							</ul>
						</div>

						<!-- Payment Options -->
						<div class="payment">
							<div class="payment_options">
								<form action="https://www.paypal.com/cgi-bin/webscr" method="post" >
									<div class=text-right>
										<!-- Identify your business so that you can collect the payments. -->
										<input type="hidden" name="business" value="pagamenti@eshoes.it">

										<!-- Specify a Buy Now button. -->
										<input type="hidden" name="cmd" value="_xclick">

										<!-- Specify details about the item that buyers will purchase. -->
										<input type="hidden" name="item_name" value="">

										<input type="hidden" name="amount" value='<%=cartBean.getPrezzoTotale() %>'>
										<input type="hidden" name="currency_code" value="EUR">

										<!-- Display the payment button. -->
										<input class="pull-right" type="image" name="submit" border="0"
											   src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
											   alt="Buy Now">
										<img alt="" border="0" width="1" height="1"
											 src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" >
									</div>
								</form>
								<label class="payment_option clearfix">Paypal
								</label>
								<form action="https://www.paypal.com/cgi-bin/webscr" method="post" >
									<div class=text-right>
										<!-- Identify your business so that you can collect the payments. -->
										<input type="hidden" name="business" value="pagamenti@eshoes.it">

										<!-- Specify a Buy Now button. -->
										<input type="hidden" name="cmd" value="_xclick">

										<!-- Specify details about the item that buyers will purchase. -->
										<input type="hidden" name="item_name" value="">

										<input type="hidden" name="amount" value='<%=cartBean.getPrezzoTotale() %>'>
										<input type="hidden" name="currency_code" value="EUR">

										<!-- Display the payment button. -->
										<input class="pull-right" type="image" name="submit" border="0"
											   src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
											   alt="Buy Now">
										<img alt="" border="0" width="1" height="1"
											 src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" >
									</div>
								</form>
								<label class="payment_option clearfix">Credit card
								</label>

							</div>
						</div>

						<!-- Order Text -->
						<div class="button order_button"><a href="ServletCreateOrder">Effettua l'ordine</a></div>
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
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/checkout.js"></script>
</body>
</html>