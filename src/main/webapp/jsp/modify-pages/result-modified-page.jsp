<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
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
  </head>
  <body>
  
    <section class="update-result-header">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <h2>Update Result Page</h2>
          </div> <!-- /.col-sm-12 -->
        </div> <!-- /.row -->
      </div> <!-- /.container -->
    </section> <!-- /.update-result-header -->
    
    <section class="update-result-body">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            
            <form action="${pageContext.request.contextPath}/school/result/update" method="post">
              
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-group">
                    <input type="hidden" name="resultId" class="form-control" id="resultId" value="${result.resultId}">
                  </div> <!-- /.form-group -->
                </div> <!-- /.col-sm-6 -->
              </div> <!-- /.row -->
              
              <%-- <c:forEach var="student" items="${result.list } --%>
              
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-group">
                    <label for="studentFirstName">First Name</label>
                    <input type="text" name="firstName" class="form-control" id="studentFirstName" value="${result.student.firstName}" disabled>
                    <input type="hidden" name="firstName" class="form-control" id="studentFirstName" value="${result.student.firstName}" >
                  </div> <!-- /.form-group -->
                </div> <!-- /.col-sm-6 -->
                
                <div class="col-sm-6">
                  <div class="form-group">
                    <label for="studentLastName">Last Name</label>
                    <input type="text" name="lastName" class="form-control" id="studentLastName" value="${result.student.lastName}" disabled>
                    <input type="hidden" name="lastName" class="form-control" id="studentLastName" value="${result.student.lastName}">
                  </div> <!-- /.form-group -->
                </div> <!-- /.col-sm-6 -->
              </div> <!-- /.row -->
              
              <div class="row">
                <div class="col-sm-6">
                  <label for="courseName">Course Name</label>
                  <input type="text" name="courseName" class="form-control" id="courseName" value="${result.course.courseName}" disabled>
                  <input type="hidden" name="courseName" class="form-control" id="courseName" value="${result.course.courseName}" >
                </div> <!-- /.col-sm-6 -->
                
                <div class="col-sm-6">
                  <label for="mark">Mark</label>
                  <input type="text" name="mark" class="form-control" id="mark" value="${result.marks}">
                </div> <!-- /.col-sm-6 -->
              </div> <!-- /.row -->
              
              <div class="row">
                <div class="col-sm-6">
                  <label for="semester">Semester</label>
                  <c:forEach var="sem" items="${result.listOfSemesters}">
                    <c:forEach var="res" items="${sem.listOfResults}">
                      <c:choose>
                        <c:when test="${result.resultId eq res.resultId }">
                          <input type="text" name="semester" class="form-control" id="semester" value="${sem.semester}" disabled>
                          <input type="hidden" name="semester" class="form-control" id="semester" value="${sem.semester}">
                        </c:when>
                      </c:choose>
                    </c:forEach>
                  </c:forEach>
                </div> <!-- /.col-sm-6 -->
              </div> <!-- /.row -->
              
              <div class="row">
                <div class="col-sm-12">
                  <button type="submit" name="option" value="update" class="btn btn-primary">Submit</button>
                  <button type="submit" name="option" value="cancel" class="btn btn-danger">Cancel</button>
                </div> <!-- /.col-sm-12 -->
              </div> <!-- /.row -->
           
            </form>
            
          </div> <!-- /.col-sm-12 -->
        </div> <!-- /.row -->
      </div> <!-- /.container -->
    </section> <!-- /.update-result-body -->
  
  </body>
</html>