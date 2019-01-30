
<%@page import="java.util.ArrayList"%>
<%@ page import="it.unisa.tophw.server.model.beans.OrderBean" %>
<%@ page import="it.unisa.tophw.server.model.beans.UserBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->

<% UserBean utente = (UserBean) request.getAttribute("utente");
	if(utente!=null && utente.getId_utente()!=0) {
		if(utente.getRuolo().equalsIgnoreCase("admin")) {%>
			<title>Lista Ordini</title>
		<% } else {%>
			<title>I miei ordini</title>
			<% }  } %>
</head>
<body>

	
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

	
</body>
</html>