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
</head>
<body>

<div  class="super_container">
    <%@include  file="header.jsp" %>


        <div class="products">
            <div class="container">
                <div class="row">
                    <div class="col">

                        <div class="product_grid">

                           <%if(request.getAttribute("prodotti")!=null) {
                               ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) request.getAttribute("prodotti");

                           %>
    <%if(prodotti.size()>0)
        for(int i =0;i<prodotti.size();++i)
    {%>
    <div class="product">
        <div class="product_image"><img src='<%="/out/artifacts/images/products/"+prodotti.get(i).getPathImg1()%>' alt=""></div>
        <div class="product_extra product_new"><a href="categories.jsp">New</a></div>
        <div class="product_content">
            <div class="product_title"><a href="product.jsp"><%=prodotti.get(i).getNome()%></a></div>
            <div class="product_price"><%=prodotti.get(i).getPrezzo()%></div>
        </div>
    </div>




                            <%}}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>




    <%@include  file="footer.jsp" %>
</div>


</body>
</html>
