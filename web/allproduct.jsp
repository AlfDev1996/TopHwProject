<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.tophw.server.model.beans.ProductBean" %><%--
  Created by IntelliJ IDEA.
  User: Alfonso
  Date: 23/01/2019
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prodotti</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">

</head>
<body>

<div  class="super_container">
    <%@include  file="header.jsp" %>


        <div class="products">
            <div class="container">
                <div class="row">
                    <div class="col" style="margin-top: 5%">

                        <div class="product_grid">

                           <%if(request.getAttribute("prodotti")!=null) {
                               ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) request.getAttribute("prodotti");

                           %>
    <%if(prodotti.size()>0){
        for(int i =0;i<prodotti.size();++i)
    {%>
    <div class="product">
        <div class="product_image"><img src='<%="images/products/"+prodotti.get(i).getPathImg1()%>' alt=""></div>
        <div class="product_extra product_new"><a href="categories.jsp">New</a></div>
        <div class="product_content">
            <div class="product_title"><a href='ServletFindProductById?id_prodotto=<%=prodotti.get(i).getId_prodotto()%>'><%=prodotti.get(i).getNome()%></a></div>
            <div class="product_price">€ <%=prodotti.get(i).getPrezzo()%></div>
        </div>
    </div>




                            <%}}
                           else{%>

                            <div class="alert alert-danger" style="margin-top: 10%;margin-left: 20%;margin-right: 20%;">
                                <strong>La Ricerca non ha prodotto alcun risultato. Riprova.</strong>
                            </div>


                           <% }

                           }%>

                        </div>
                    </div>
                </div>
            </div>
        </div>




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
<script src="js/custom.js"></script>

</body>
</html>
