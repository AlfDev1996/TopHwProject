<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.tophw.server.model.beans.ProductBean" %><%--
  Created by IntelliJ IDEA.
  User: the_l
  Date: 12/01/2019
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TopHw-Store</title>
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
  <div class="super_container">

  <%@include  file="header.jsp" %>

    <!-- Home -->

    <div class="home">
      <div class="home_slider_container">

        <!-- Home Slider -->
        <div class="owl-carousel owl-theme home_slider">

          <!-- Slider Item -->
          <div class="owl-item home_slider_item">
            <div class="home_slider_background" style="background-image:url(images/home_slider_1.jpg)"></div>
            <div class="home_slider_content_container">
              <div class="container">
                <div class="row">
                  <div class="col">
                    <div class="home_slider_content"  data-animation-in="fadeIn" data-animation-out="animate-out fadeOut">
                      <div class="home_slider_title">Top Hardware, una nuova esperienza di acquisto Online.</div>
                      <div class="home_slider_subtitle">Il meglio dell'Hardware per il tuo pc.</div>
                      <div class="button button_light home_button"><a href="#">Acquista Ora.</a></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Slider Item -->
          <div class="owl-item home_slider_item">
            <div class="home_slider_background" style="background-image:url(images/home_slider_1.jpg)"></div>
            <div class="home_slider_content_container">
              <div class="container">
                <div class="row">
                  <div class="col">
                    <div class="home_slider_content"  data-animation-in="fadeIn" data-animation-out="animate-out fadeOut">
                      <div class="home_slider_title">A new Online Shop experience.</div>
                      <div class="home_slider_subtitle">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros. Sed viverra velit venenatis fermentum luctus.</div>
                      <div class="button button_light home_button"><a href="#">Shop Now</a></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Slider Item -->
          <div class="owl-item home_slider_item">
            <div class="home_slider_background" style="background-image:url(images/home_slider_1.jpg)"></div>
            <div class="home_slider_content_container">
              <div class="container">
                <div class="row">
                  <div class="col">
                    <div class="home_slider_content"  data-animation-in="fadeIn" data-animation-out="animate-out fadeOut">
                      <div class="home_slider_title">A new Online Shop experience.</div>
                      <div class="home_slider_subtitle">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros. Sed viverra velit venenatis fermentum luctus.</div>
                      <div class="button button_light home_button"><a href="#">Shop Now</a></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>

        <!-- Home Slider Dots -->

        <div class="home_slider_dots_container">
          <div class="container">
            <div class="row">
              <div class="col">
                <div class="home_slider_dots">
                  <ul id="home_slider_custom_dots" class="home_slider_custom_dots">
                    <li class="home_slider_custom_dot active">01.</li>
                    <li class="home_slider_custom_dot">02.</li>
                    <li class="home_slider_custom_dot">03.</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>

    <!-- Ads -->

    <div class="avds">
      <div class="avds_container d-flex flex-lg-row flex-column align-items-start justify-content-between">
        <div class="avds_small">
          <div class="avds_background" style="background-image:url(images/mobo.jpg)"></div>
          <div class="avds_small_inner">
            <div class="avds_discount_container">
              <img src="images/discount.png" alt="">
              <div>
                <div class="avds_discount">
                  <div>20<span>%</span></div>
                  <div>Discount</div>
                </div>
              </div>
            </div>
            <div class="avds_small_content">
              <div class="avds_title">Nuove Mo.Bo.</div>

            </div>
          </div>
        </div>
        <div class="avds_large">
          <div class="avds_background" style="background-image:url(images/avds_large.jpg)"></div>
          <div class="avds_large_container">
            <div class="avds_large_content">
              <div class="avds_title">Professional Cameras</div>
              <div class="avds_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros. Sed viver ra velit venenatis fermentum luctus.</div>

            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Products -->
    <!--
        <div class="products">
          <div class="container">
            <div class="row">
              <div class="col">

                <div class="product_grid">


              <div class="product">
                <div class="product_image"><img src="images/product_1.jpg" alt=""></div>
                <div class="product_extra product_new"><a href="categories.jsp">New</a></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>



              <div class="product">
                <div class="product_image"><img src="images/product_2.jpg" alt=""></div>
                <div class="product_extra product_sale"><a href="categories.jsp">Sale</a></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>


              <div class="product">
                <div class="product_image"><img src="images/product_3.jpg" alt=""></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>


              <div class="product">
                <div class="product_image"><img src="images/product_4.jpg" alt=""></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>


              <div class="product">
                <div class="product_image"><img src="images/product_5.jpg" alt=""></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>


              <div class="product">
                <div class="product_image"><img src="images/product_6.jpg" alt=""></div>
                <div class="product_extra product_hot"><a href="categories.jsp">Hot</a></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>


              <div class="product">
                <div class="product_image"><img src="images/product_7.jpg" alt=""></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>


              <div class="product">
                <div class="product_image"><img src="images/product_8.jpg" alt=""></div>
                <div class="product_extra product_sale"><a href="categories.jsp">Hot</a></div>
                <div class="product_content">
                  <div class="product_title"><a href="product.jsp">Smart Phone</a></div>
                  <div class="product_price">$670</div>
                </div>
              </div>

            </div>

          </div>
        </div>
      </div>
    </div> -->

    <!-- Ad -->

   <!-- <div class="avds_xl">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="avds_xl_container clearfix">
              <div class="avds_xl_background" style="background-image:url(images/avds_xl.jpg)"></div>
              <div class="avds_xl_content">
                <div class="avds_title">Amazing Devices</div>
                <div class="avds_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus.</div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div> -->

    <!-- Icon Boxes -->

    <div class="icon_boxes">
      <div class="container">
        <div class="row icon_box_row">

          <!-- Icon Box -->
          <div class="col-lg-4 icon_box_col">
            <div class="icon_box">
              <div class="icon_box_image"><img src="images/icon_1.svg" alt=""></div>
              <div class="icon_box_title">Free Shipping Worldwide</div>
              <div class="icon_box_text">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie.</p>
              </div>
            </div>
          </div>

          <!-- Icon Box -->
          <div class="col-lg-4 icon_box_col">
            <div class="icon_box">
              <div class="icon_box_image"><img src="images/icon_2.svg" alt=""></div>
              <div class="icon_box_title">Free Returns</div>
              <div class="icon_box_text">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie.</p>
              </div>
            </div>
          </div>

          <!-- Icon Box -->
          <div class="col-lg-4 icon_box_col">
            <div class="icon_box">
              <div class="icon_box_image"><img src="images/icon_3.svg" alt=""></div>
              <div class="icon_box_title">24h Fast Support</div>
              <div class="icon_box_text">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie.</p>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!-- Newsletter -->

    <div class="newsletter">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="newsletter_border"></div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-8 offset-lg-2">
            <div class="newsletter_content text-center">
              <div class="newsletter_title">Subscribe to our newsletter</div>
              <div class="newsletter_text"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros</p></div>
              <div class="newsletter_form_container">
                <form action="#" id="newsletter_form" class="newsletter_form">
                  <input type="email" class="newsletter_input" required="required">
                  <button class="newsletter_button trans_200"><span>Subscribe</span></button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


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
  <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
  <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
  <script src="plugins/easing/easing.js"></script>
  <script src="plugins/parallax-js-master/parallax.min.js"></script>
  <script src="js/custom.js"></script>
  </body>
</html>
