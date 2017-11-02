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
  
    <section class="update-student-header">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <h2>Update Student Page</h2>
          </div> <!-- /.col-sm-12 -->
        </div> <!-- /.row -->
      </div> <!-- /.container -->
    </section> <!-- /.update-student-header -->
    
    <section class="update-student-body">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <form action="${pageContext.request.contextPath}/school/student" method="post">
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-group">
                    <input type="hidden" name="studentId" class="form-control" id="studentId" value="${student.studentId}">
                  </div> <!-- /.form-group -->
                </div> <!-- /.col-sm-6 -->
              </div> <!--  /.row -->
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-group">
                    <label for="studentFirstName">First Name</label>
                    <input type="text" name="firstName" class="form-control" id="studentFirstName" value="${student.firstName}">
                  </div> <!-- /.form-group -->
                </div> <!-- /.col-sm-6 -->
                <div class="col-sm-6">
                  <div class="form-group">
                    <label for="studentLastName">Last Name</label>
                    <input type="text" name="lastName" class="form-control" id="studentLastName" value="${student.lastName}">
                  </div> <!-- /.form-group -->
                </div> <!-- /.col-sm-6 -->
              </div> <!-- /.row -->
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-group">
                    <label for="studentEmail">Email Address</label>
                    <input type="email" name="email" class="form-control" id="studentEmail" value="${student.email}">
                  </div> <!-- /.form-group --> 
                </div> <!-- /.col-sm-6 -->
              </div> <!-- .row -->
              <button type="submit" name="option" value="update" class="btn btn-primary">Submit</button>
              <button type="submit" name="option" value="cancel" class="btn btn-danger">Cancel</button>
            </form>
          </div> <!-- /.col-sm-12 -->
        </div> <!-- /.row -->
      </div> <!-- /.container -->
    </section> <!-- /.update-student-body -->
    
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>  
  </body>
</html>