<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>FindTomato - Search Images</title>

  <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
  <link href="content/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="content/css/freelancer.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="content/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<body id="page-top" class="index">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header page-scroll">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#page-top">FindTomato</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li class="hidden">
          <a href="#page-top"></a>
        </li>
        <li class="page-scroll">
          <a href="#portfolio">Portfolio</a>
        </li>
        <li class="page-scroll">
          <a href="#about">About</a>
        </li>
        <li class="page-scroll">
          <a href="#contact">Contact</a>
        </li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>


<!-- Header -->
<header>
  <div class="container">
    <img class="img-responsive" src="content/img/profile.png" alt="">

    <hr class="star-light">
    <hr class="star-light">


    <form name="search" action="/QueryController" method="get">
      <input type="text" class="form-control" placeholder="Search" name="text"/>
      <input type="submit" value="Go" class="btn btn-success-index btn-lg"/>
    </form>

  </div>
</header>


<!-- Footer -->
<footer class="text-center">
  <div class="footer-below">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          Copyright &copy; Tomato 2015
        </div>
      </div>
    </div>
  </div>
</footer>



<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
<div class="scroll-top page-scroll visible-xs visble-sm">
  <a class="btn btn-primary" href="#page-top">
    <i class="fa fa-chevron-up"></i>
  </a>
</div>

<!-- jQuery -->
<script src="content/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="content/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="content/js/classie.js"></script>
<script src="content/js/cbpAnimatedHeader.js"></script>

<!-- Custom Theme JavaScript -->
<script src="content/js/freelancer.js"></script>

</body>

</html>
