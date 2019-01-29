<%@ page import="it.unisa.tophw.server.model.beans.ProductBean" %><%--
  Created by IntelliJ IDEA.
  User: Alfonso
  Date: 28/01/2019
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>Modifica Prodotto </title>

    <%  ProductBean prodotto=null;
        if(request.getAttribute("prodotto")!=null ){
             prodotto = (ProductBean) request.getAttribute("prodotto");

        }else
        { prodotto = new ProductBean();}
    %>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="js/productFunction.js"></script>






</head>
<body onload="loadCatalog();loadMarche()" >
<div  class="super_container">
    <%@include  file="header.jsp" %>

   <div class ="row" style="margin-top: 10%">
    <div style="margin-left: auto; margin-right: auto;" >
        <p class="text-danger" style="font-size: 200%">Stai modificando : <%=prodotto.getNome()%></p>

        <form onsubmit="return confirm('Sei Sicuro di voler Salvare le modifiche apportate al prodotto : <%=prodotto.getNome()%> ')" method="POST" action="ServletUpdateProduct" >
            <input type="hidden" name="id_prodotto" value='<%=prodotto.getId_prodotto()%>'>
            <div class="form-group"  >

                <div class="col-xs-12" style="margin-bottom: 10px;">
                    <label for="product_name"><h4>Nome Prodotto</h4></label>
                    <input  type="text"  class="form-control" name="Nome_Prodotto" value='<%=prodotto.getNome()%>' id="product_name" placeholder="" title="">
                </div>
            </div>
            <div class="form-group" >

                <div class="col-xs-12" style="margin-bottom: 10px;" >
                    <label for="desc_breve"><h4>Descrizione Breve</h4></label>
                    <input type="text"  class="form-control"  name="Descrizione_Breve" value='<%=prodotto.getDescrizione_breve()%>' id="desc_breve" >
                </div>
            </div>

            <div class="form-group">

                <div class="col-xs-12" style="margin-bottom: 10px;" >
                    <label for="desc_estesa"><h4>Descrizione Estesa</h4></label>
                    <textarea class="form-control"  name="Descrizione"  id="desc_estesa"><%=prodotto.getDescrizione_estesa()%></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12" style="margin-bottom: 10px;">
                    <label for="quantita"><h4>Quantità</h4></label>
                    <input type="number" min="0" class="form-control" value='<%=prodotto.getQuantita()%>' name="quantita" id="quantita">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12" style="margin-bottom: 10px;">
                    <label for="prezzo"><h4>Prezzo</h4></label>
                    <input type="number" min="0" class="form-control"  value='<%=prodotto.getPrezzo()%>' name="Prezzo" id="prezzo">
                </div>
            </div>



            <div class="form-group">
                <div class="col-xs-12" style="margin-bottom: 10px;">
                    <label for="prezzo"><h4>Marca</h4></label>
                    <select name ="selectMarca" id="selectMarca">

                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12" style="margin-bottom: 10px;">
                    <label for="prezzo"><h4>Catalogo</h4></label>
                    <select name="selectCatalog" id="selectCatalog"></select>
                </div>
            </div>



            <div class="form-group">
                <div class="col-xs-12">
                    <br>

                    <input type="submit" class="btn btn-success btn-lg" name ="saveButton" value="Modifica Articolo" onclick="">


                </div>
            </div>

        </form>



    </div>
   </div>



    <%@include  file="footer.jsp" %>
</div>




</body>
</html>
