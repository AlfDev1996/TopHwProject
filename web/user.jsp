<%--
  Created by IntelliJ IDEA.
  User: Alfonso
  Date: 15/01/2019
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo</title>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/userFunction.js"></script>


</head>
<body>
<%
    UserBean utente = new UserBean();

       if( session.getAttribute("utente")!= null)
            utente = (UserBean) session.getAttribute("utente");
        else
       {String redirectURL = "/index.jsp";
           response.sendRedirect(request.getContextPath() + redirectURL);
       }

%>

<div  class="super_container">
<%@include  file="header.jsp" %>

<hr>
<div class="container bootstrap snippet" style="margin-top: 140px;">
    <div class="row">
        <div class="col-sm-10"><h1><%=utente.getNome()%></h1></div>
    </div>
    <div class="row">
        <div class="col-sm-3"><!--left col-->


            <div class="text-center">
                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">

            </div></hr><br>






        </div><!--/col-3-->
        <div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home">Dati Personali</a></li>
                <li><a data-toggle="tab" href="#messages">Indirizzo di spedizione</a></li>
                <li><a data-toggle="tab" href="#tab3">Azioni Account</a></li>

            </ul>


            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <hr>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="first_name"><h4>Nome</h4></label>
                                <input readonly="readonly" type="text" value='<%=utente.getNome()%>' class="form-control" name="first_name" id="modnome" placeholder="" title="">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="last_name"><h4>Cognome</h4></label>
                                <input type="text"  readonly="readonly" class="form-control"  value='<%=utente.getCognome()%>' name="last_name" id="modcognome" placeholder="last name" title="enter your last name if any.">
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="phone"><h4>E-mail</h4></label>
                                <input type="text"   disabled class="form-control"  value='<%=utente.getEmail()%>' name="phone" id="modemail" placeholder="" title="">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="mobile"><h4>Password</h4></label>
                                <input type="password"  readonly="readonly" class="form-control"  value='<%=utente.getPassword()%>' name="mobile" id="modpassword" placeholder="" title="">
                            </div>
                        </div>





                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <input type="button" class="btn btn-warning" value="Modifica" id="mod" onclick="enableMod(this)">
                                <input type="button" class="btn btn-success" name ="saveButton" value="Salva" id="save" onclick="save(this)" style="display:none;">

                            </div>
                        </div>


                    <hr>

                </div><!--/tab-pane-->
                <div class="tab-pane" id="messages">

                    <h2></h2>

                    <hr>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="modvia"><h4>Via</h4></label>
                                <input type="text" class="form-control"   name="modvia" value ='<%=utente.getIndirizzo().getVia()%>' id="modvia" placeholder="es : Via Roma" readonly="readonly" title="">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="modcivico"><h4>civico</h4></label>
                                <input type="number" class="form-control" name="last_name" value ='<%=utente.getIndirizzo().getCivico()%>' id="modcivico" placeholder="es: 10" readonly="readonly" title="">
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="modcomune"><h4>Comune</h4></label>
                                <input type="text" class="form-control"  name="phone" value ='<%=utente.getIndirizzo().getComune()%>' id="modcomune" placeholder="es: Somma Vesuviana" title="" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="modprovincia"><h4>Provincia</h4></label>
                                <input type="text" class="form-control"  name="mobile" value ='<%=utente.getIndirizzo().getProvincia()%>' id="modprovincia" placeholder="Napoli" title="" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="modcap"><h4>CAP</h4></label>
                                <input type="number" class="form-control" name="email" id="modcap" value ='<%=utente.getIndirizzo().getCap()%>' placeholder="es: 80049" title="" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="modnazione"><h4>Nazione</h4></label>
                                <input type="text" class="form-control" id="modnazione" value ='<%=utente.getIndirizzo().getNazione()%>' placeholder="es: ITALIA " title="" readonly="readonly">
                            </div>
                        </div>

                    <input type="hidden" id= "id_utente" value='<%=utente.getId_utente()%>'>

                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <input type="button" class="btn btn-warning" value="Modifica" id="modIndirizzo" onclick="enableMod(this)">
                                <input type="button" class="btn btn-success" value="Salva" name ="saveButton" id="saveIndirizzo" onclick="saveAddress(this)" style="display:none;">
                            </div>
                        </div>


                </div><!--/tab-pane-->

                <div class="tab-pane" id="tab3">

                    <h2></h2>

                    <hr>
                    <form method="POST" action ="ServletDeleteUser">
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="modvia"><h4>Operazioni Disponibili</h4></label>
                            <select name ="selectAction">
                                <option>Elimina Profilo</option>


                            </select>
                        </div>
                    </div>


                    <input type="hidden" name="id_utente" value='<%=utente.getId_utente()%>'>

                    <div class="form-group">
                        <div class="col-xs-12">
                            <br>

                            <input type="submit" class="btn btn-danger" value="Esegui Azione" >
                        </div>
                    </div>
                    </form>

                </div>


            </div><!--/tab-pane-->
        </div><!--/tab-content-->

    </div><!--/col-9-->
</div><!--/row-->
<div class="footer_overlay"></div>
<%@include  file="footer.jsp" %>
</div>
</body>
</html>
