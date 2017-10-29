<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="David Parr">
    <link rel="icon" href="../../favicon.ico">

    <title>School Portal</title>

    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/sidebar-stylesheet.css" />" rel="stylesheet">
    <link href="<c:url value="/css/student-stylesheet.css" />" rel="stylesheet">
  </head>
  
  <body>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button> <!-- /.navbar-toggle collapsed -->
          <a class="navbar-brand" href="#">MCIT Portal</a>
        </div> <!-- ./navbar-header -->
        <div class="collapse navbar-collapse" id="navbar">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><c:out value="${user.firstName}"/>&nbsp;<c:out value="${user.lastName}" /></a></li>
          </ul> <!-- /.nav .navbar-nav .navbar-right -->
        </div> <!-- /.navbar-collapse -->
      </div> <!-- /.container-fluid -->
    </nav> <!-- ./navbar navbar-default navbar-fixed-top -->

    <div class="sidebar">  
      <div class="container-fluid">
        <div class="row">
        	<div class="col-sm-2">
            <nav class="nav-sidebar">
              <ul class="nav tabs">
                <li class=""><a href="#tab1" data-toggle="tab">Students</a></li>
                <li class="active"><a href="#tab2" data-toggle="tab">Courses</a></li>
                <li class=""><a href="#tab3" data-toggle="tab">Results</a></li>
              </ul> <!-- /.nav .tabs -->
            </nav> <!-- /.nav-sidebar -->
          </div> <!-- /.col-sm-2 -->
          <div class="col-sm-10">
            <div class="tab-content">
              <div class="tab-pane text-style" id="tab2">
                <h2>Dolor asit amet</h2>
                 <p>Dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt 
                  ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo 
                  dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. 
                  Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore 
                  et dolore magna aliquyam erat, sed diam voluptua.</p>
                  <hr>
                  <img src="http://placehold.it/150x90" class="img-rounded pull-left">
              </div> <!-- /.tab-pane .text-style -->
            </div> <!-- /.tab-content -->
          </div> <!-- /.col-sm-10 -->
        </div> <!-- /.row -->
      </div> <!-- /.container-fluid -->
    </div> <!-- /.sidebar -->
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
  </body>
</html>