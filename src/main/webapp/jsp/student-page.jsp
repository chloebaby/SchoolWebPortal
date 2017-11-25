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
    
    <script src="<c:url value="/js/student-page.js" />"></script>
    
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
            <li><a href="#"><c:out value="${user}"/></a></li>
            <li><a href="<c:url value="/logout.jsp"/>">Sign Out</a></li>
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
                <li class="active"><a href="<c:url value="/school/student?action=#"/>">Students</a></li>
                <li class=""><a href="<c:url value="/school/course?action=#"/>">Courses</a></li>
                <li class=""><a href="<c:url value="/school/result?action=#"/>">Results</a></li>
              </ul> <!-- /.nav .tabs -->
            </nav> <!-- /.nav-sidebar -->
          </div> <!-- /.col-sm-2 -->
          
          <div class="col-sm-10">
            <div class="tab-content">
              <div class="tab-pane active text-style" id="tab1">
                <div class="student-form">
                  <div class="row">
                    <div class="col-sm-12">
                      <h2>Student Details</h2>
                    </div> <!-- /.col-sm-12 -->
                  </div> <!-- /.row -->
	                <div class="row">
	                  <div class="col-sm-12">
	                    
	                    <form action="${pageContext.request.contextPath}/school/student" onsubmit="return validateStudentForm()" method="post">
	                      <div class="row">
	                        <div class="col-sm-6">
			                      <div class="form-group">
			                        <label for="studentFirstName">First Name</label>
			                        <input type="text" name="firstName" class="form-control" id="studentFirstName">
			                      </div> <!-- /.form-group -->
		                      </div> <!-- /.col-sm-6 -->
		                      <div class="col-sm-6">
		                       <div class="form-group">
		                         <label for="studentLastName">Last Name</label>
		                         <input type="text" name="lastName" class="form-control" id="studentLastName">
		                       </div> <!-- /.form-group -->
		                      </div> <!-- /.col-sm-6 -->
	                      </div> <!-- /.row -->
	                      
	                      <div class="row">
	                        <div class="col-sm-6">
	                          <div class="form-group">
	                            <label for="studentEmail">Email Address</label>
	                            <input type="email" name="email" class="form-control" id="studentEmail">
	                          </div> <!-- /.form-group -->
	                        </div> <!-- /.col-sm-6 -->
                          <div class="col-sm-6">
                            <div class="form-group">
                              <label for="studentUsername">User Name</label>
                              <input type="text" name="username" class="form-control" id="studentUsername">
                            </div> <!-- /.form-group -->
                          </div> <!-- /.col-sm-6 -->
	                      </div> <!-- /.row -->
                        
                        <div class="row">
                          <div class="col-sm-6">
                            <div class="form-group">
                              <label for="sel1">Role</label>
                              <select name="rolename" class="form-control" id="sel1">
                                <c:forEach var="role" items="${allRoles}">
                                  <option>
                                    <div style="display:none;"><c:out value="${role.roleId}"/></div>&nbsp;<c:out value="${role.rolename}"/>
                                  </option>
                                </c:forEach>
                              </select> <!-- /.form-control -->
                            </div> <!-- /.form-group -->
                          </div> <!-- /.col-sm-6 -->
                          <div class="col-sm-6">
                            <div class="form-group">
                              <label for="studentPassword">Password</label>
                              <input type="password" name="password" class="form-control" id="studentPassword">
                            </div> <!-- /.form-group -->
                          </div> <!-- /.col-sm-6 -->
                        </div> <!-- /.row -->
                        
                        <div class="row">
                           <div class="col-sm-12">
                             <button type="submit" name="option" value="save" class="btn btn-primary">Submit</button>
                           </div> <!-- /.col-sm-12 -->
                        </div> <!-- /.row -->
	                    
                      </form>
	                  
	                  </div> <!-- /.col-sm-12 -->
	                </div> <!-- /.row -->
                </div> <!-- /.student-form -->
                
                <div class="table-section">
	                <div class="row">
		                <div class="col-sm-12">
			                
			                <table class="table table-hover">
			                 <thead>
				                  <tr>
				                    <th>First Name</th>
				                    <th>Last Name</th>
                            <th>User Name</th>
                            <th>Role Name</th>
                            <th>email</th>
				                    <th>Edit</th>
				                    <th>Delete</th>
				                    <th>Send Email</th>
				                  </tr>
			                  </thead>
                        <tbody>
				                  <c:forEach var="student" items="${allStudents}">
					                  <tr>
					                    <td><c:out value="${student.firstName}"/></td>
					                    <td><c:out value="${student.lastName}"/></td>
                              <td><c:out value="${student.user.username}"/></td>
                              <td><c:out value="${student.role.rolename }"/></td>
					                    <td><c:out value="${student.email}"/></td>
                              <td><a href=<c:url value="/school/student?action=edit&studentId=${student.studentId}"/>>Edit</a></td>
	                            <td><a onclick="return confirmStudentDelete()" href=<c:url value="/school/student?action=delete&studentId=${student.studentId}&userId=${student.user.userId}&username=${student.user.username}"/>>Delete</a></td>
					                    <td><a href=<c:url value="/school/student/email?action=email&studentEmail=${student.email}"/>>Send Email</a></td>
					                  </tr>
				                  </c:forEach>
			                  </tbody>
			                </table> <!-- /.table .table-hover -->
		                
		                </div> <!-- /.col-sm-12 -->
	                </div> <!-- /.row -->
                </div> <!-- /.table-section -->
              </div> <!-- /.tab-pane active .text-style -->
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