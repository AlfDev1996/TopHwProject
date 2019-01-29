
<%@page import="java.util.ArrayList"%>
<%@ page import="it.unisa.tophw.server.model.beans.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">    <!-- Libreria bootsrao (min) utilizzata dagli sviluppatori durante la fase di debugging o di sito in lavorazione, possiede meno proprieta del bootstrap.css  -->   
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"> <!-- Libreria responsive -->
		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="themes/css/flexslider.css" rel="stylesheet"/> <!-- classe jquery per realizzare immagini, gallerie di immagini responsive !! -->
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script> <!-- Libreeia jQuery: preferisco linkarla da google -->
		<script src="bootstrap/js/bootstrap.min.js"></script> <!-- Funzioni bootstrap per eventuali animazioni  -->				
		<script src="themes/js/superfish.js"></script> 	<!-- Classe javascript per implementare il menu, sembra non utilizzata da una prima analisi -->
		<script src="themes/js/jquery.scrolltotop.js"></script> <!-- http://www.dynamicdrive.com libreria di questo sito che permette di swipppare al top della pagina o in una posizione precisa -->
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<script src="themes/js/generateAddProduct.js"></script>
<% UserBean utente = (UserBean) request.getAttribute("utente");
	if(utente!=null && utente.getId_utente()!=0) {
		if(utente.getRuolo().equalsIgnoreCase("admin")) {%>
			<title>Lista Ordini</title>
		<% } else {%>
			<title>I miei ordini</title>
			<% }  } %>
</head>
<body>
	
	<%@ include file="header.jsp" %>
	
	<% ArrayList<OrderBean> ordini = (ArrayList<OrderBean>) request.getAttribute("ordini");
		if(ordini!=null )
		{	
			if(ordini.size()>0){
				
	%>
	<!--  se ci sono ordini -->
	<div class="row">
	
	<div class="span12">
	<table class="table table-hover">
  <tbody>
  <thead>
  <tr >
  	<th>Num. Ordine </th>
  	<th>Cliente </th>
  	<th>Data </th>
  	<th>Stato </th>
  	<th>Prezzo Totale </th>
  </tr>
  </thead>
  <% 	
  		for(OrderBean ordine : ordini){  %>
    <tr > 
    <% int id_ordine = ordine.getId_ordine(); %>
	<td><a href='ServletFindOrderById?id_ordine=<%=id_ordine%>'   > #<%= ordine.getId_ordine() %> </a>  </td>
		<td> <%= ordine.getUtente().getEmail() %></td>
		<td> <%= ordine.getData_creazione() %></td>
		<td> <%= ordine.getStato() %></td>
		<td> <%= ordine.getTotale() %> </td>
	</tr> 
    <%} %>
  	
  </tbody>
</table>
	
	</div>
	
	</div>
	
	
	
	<% } else { %>
	<!--  Se non ci sono ordini stampo messaggio  -->
	
	
	<% }  }
	%>
	<%@ include file="footer.jsp" %>
	
</body>
</html>