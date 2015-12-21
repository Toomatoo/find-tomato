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

    <title>Toomatoo - Search Images</title>

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
                <a class="navbar-brand" href="/index.jsp">Toomatoo</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                
                        <form name="search" action="/QueryController" method="get">
			          		<input type="text" class="form-control" placeholder="Search" name="text"/>
				        </form>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

	<!-- ------Java Scripts------ -->
	<%@ page import="view.ResultViewer, java.util.ArrayList, model.*" %>
	
	<%
	ResultViewer resultViewer = new ResultViewer();
	
	ArrayList<String> pictureList = resultViewer.getPictures(request.getParameter("text"));
	%>
	<!-- ------------------------ -->
	
    <!-- Header -->

    <header>
        <div class="container">
	    <% for(int i=0; i<pictureList.size(); i++) { %>
	    <% String img = "content/data/image/" + pictureList.get(i); %>
	    <img class="img-responsive" src=<%= img %> alt="">
	    <% } %>
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