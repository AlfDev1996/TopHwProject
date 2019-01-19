<%--
  Created by IntelliJ IDEA.
  User: Alfonso
  Date: 14/01/2019
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

    <!-- Website CSS style -->
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <link rel="stylesheet" type="text/css" href="styles/registrationForm.css">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" type="text/css" href="styles/registrationForm.css">
    <title>Login-TopHw</title>
</head>
<body>

<%@include  file="header.jsp" %>

<div class="container">
    <div class="row main">
        <!--<div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Company Name</h1>
                <hr />
            </div>
        </div>-->
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="ServletLogin">

                <div class="form-group">
                    <label  class="cols-sm-2 control-label">E-mail</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="email" id="email"  placeholder="es : Mario" required />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="cols-sm-2 control-label">Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="pass" id="pass"  placeholder="es : Rossi" required/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button">Login</button>
                </div>
                <div class="login-register">
                    <a href="registration.jsp">Non sei registrato? REGISTRATI</a>
                </div>

            </form>
        </div>
    </div>
</div>


<div class="footer_overlay"></div>
<%@include  file="footer.jsp" %>
<script type="text/javascript" src="assets/js/bootstrap.js"></script>

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
