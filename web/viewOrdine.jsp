<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="it.unisa.tophw.server.model.beans.OrderVoiceBean"%>
<%@page import="it.unisa.tophw.server.model.beans.OrderBean"%>
<%@ page import="it.unisa.tophw.server.model.beans.AddressBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ordine </title>
</head>
<body>

	<% String error = request.getParameter("error");
	   if(error==null)
		   error="";
	%>
	
	<% OrderBean ordine = (OrderBean) request.getAttribute("ordine");
		AddressBean address = new AddressBean();
		String indirizzo = "";
	int totalePezzi = 0;
	   if(ordine!=null)
	   {
	   	if(ordine.getUtente()!=null){
	   		if(ordine.getUtente().getIndirizzo()!=null)
				address= ordine.getUtente().getIndirizzo();
		}
	   	if(address!=null && address.getId_indirizzo()>0)
	   		indirizzo= address.getVia()+" "+address.getCivico()+" "+address.getCap()+" "+address.getComune()+" ("+address.getProvincia()+") "+address.getNazione();
	   	if(ordine.getVociOrdine()!=null && ordine.getVociOrdine().size()>0){
			for ( OrderVoiceBean v : ordine.getVociOrdine())
				totalePezzi+=v.getQuantita();
		}
		if(!error.equals("")) {
	%>
	<div class="alert alert-success">
 		 <strong><%= error %></strong> 
	</div>
	<% } %>
	<div class="row">
	
	<div class="span6">
	<table class="table borderless">
  <tbody>
    <tr>
      <th scope="row">Num. Ordine</th>
      <td><%= ordine.getId_ordine() %></td>
    </tr>
    <tr>
      <th scope="row">Stato</th>
      <td><%= ordine.getStato() %></td>
    </tr>
    <tr>
      <th scope="row">Totale Pezzi </th>
      <td> <%= totalePezzi %> </td>
    </tr>
    <tr>
      <th scope="row">Totale </th>
      <td> <%= ordine.getTotale() %> </td>
    </tr>
  </tbody>
</table>
	
	</div>
	<div class="span6">
	 <div class="table-responsive">
		<table class="table borderless">
  <tbody>
  	<tr>
      <th scope="row">Data Ordine</th>
      <td><%= ordine.getData_creazione() %></td>
    </tr>
    <tr>
      <th scope="row">Indirizzo</th>
      <td><%= indirizzo %></td>
    </tr>
    
    <tr>
      <th scope="row">Corriere </th>
      <td> DHL </td>
    </tr>
  </tbody>
</table>
</div>
	</div>
	<br>
	<br>
	<br>
	</div>
	<!--  Articoli -->
	
	<div class="row">
	
	<div class="span12">
	<table class="table table-hover">
  <tbody>
  <thead>
  <tr>
  	<th>Prodotto </th>
  	<th>Pr. Unitario </th>
  	<th>Quantita </th>
  </tr>
  </thead>
  <% 	if(ordine!=null)
  		for(OrderVoiceBean v : ordine.getVociOrdine()){
  %>
    <tr> 
	<td><%= v.getProdotto().getNome() %>  </td>
		<td> <%= v.getPrezzo_unitario() %> </td>
		<td> <%= v.getQuantita() %></td>
	 </tr>
    <%} %>
  	
  </tbody>
</table>
	
	</div>
	
	</div>
	
	<% }  else { 
		 String redirectURL = "index.jsp";
		    response.sendRedirect(redirectURL);
	}%>
	
	
	


</body>
</html>