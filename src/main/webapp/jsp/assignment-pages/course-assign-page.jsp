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
    <link href="<c:url value="/css/course-stylesheet.css" />" rel="stylesheet">
    
    <script src="<c:url value="/js/course-page.js" />"></script>
    
  </head>
  <body>
    
    <div class="update-course-page">
      <div class="container">
    
        <div class="update-course-header">
          <div class="row">
            <div class="col-sm-12">
              <h2>Course Assignment Page</h2>
            </div> <!-- /.col-sm-12 -->
          </div> <!-- /.row -->
        </div> <!-- /.update-course-header -->
        
        <div class="update-course-form">
          <div class="row">
            <div class="col-sm-12">
              <form action="${pageContext.request.contextPath}/school/course-assign" onsubmit="return validateResultForm()" method="post">
                
                <div class="row">
                  <div class="col-sm-12">
                    <div class="form-group">
                      <input type="hidden" name="courseId" class="form-control" id="courseId" value="${course.courseId}">
                    </div> <!-- /.form-group -->
                  </div> <!-- /.col-sm-12 -->
                </div> <!-- /.row -->
                
                <div class="row">
                  <div class="col-sm-6">
                    <div class="form-group">
                      <label for="courseCode">Course Code</label>
                      <input type="text" name="courseCode" class="form-control" id="courseCode" value="${course.courseCode}" disabled>
                      <input type="hidden" name="courseCode" class="form-control" id="courseCode" value="${course.courseCode}">
                    </div> <!-- /.form-group -->
                  </div> <!-- /.col-sm-6 -->
                  <div class="col-sm-6">
                    <div class="form-group">
                      <label for="courseName">Course Name</label>
                      <input type="text" name="courseName" class="form-control" id="courseName" value="${course.courseName}" disabled>
                      <input type="hidden" name="courseName" class="form-control" id="courseName" value="${course.courseName}">
                    </div> <!-- /.form-group -->
                  </div> <!-- /.col-sm-6 -->
                </div> <!-- /.row -->
                
                <div class="row">
                  <div class="col-sm-5">
                    <div class="form-group">
                      <label for="sel1">Available Students (hold shift to select more than one)</label>
                        <select multiple name="students" class="form-control" id="sel1">
                          <c:forEach var="student" items="${allStudents}">
                            <option>
                              <c:out value="${student.user.username} (${student.firstName} ${student.lastName})"/>
                            </option>
                          </c:forEach>
                        </select>
                    </div>
                  </div>
                    
                  <div class="col-sm-2">
                    <div class="select-arrows">
                      <button type="button" onclick="rightBox()" name="right" value=right class="btn btn-default"><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></button>
                      <button type="button" onclick="leftBox()" name="left" value="left" class="btn btn-default"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button>
                    </div>
                  </div>
                  
                  <div class="col-sm-5">
                    <div class="form-group">
                      <label for="sel2">Active Students (hold shift to select more than one)</label>
                        <select multiple="multiple" name="activeStudents" class="form-control" id="sel2">
                        </select>
                    </div>
                  </div>
                </div>
                
                <div class="row">
                  <div class="col-sm-6">
                    <div class="form-group">
                      <label for="dropdown">Semester</label>
                      <select name="semester" class="form-control" id="form-group">
                        <c:forEach var="sem" items="${course.listOfSemesters}">
                          <option>
                            <c:out value="${sem.semester}"/>
                          </option>
                        </c:forEach>
                      </select> <!-- /.form-control -->
                    </div> <!-- /.form-group -->
                  </div> <!-- /.col-sm-6 -->
                </div> <!-- /.row -->
                
                <div class="row">
                  <div class="col-sm-12">
                    <button type="submit" name="option" value="save" class="btn btn-primary">Submit</button>
                    <button type="submit" name="option" value="cancel" class="btn btn-danger">Cancel</button>
                  </div>
                </div>
                
              </form>
            </div> <!-- /.col-sm-12 -->
          </div> <!-- /.row -->
        </div> <!-- /.update-course-form  -->
        
        <div class="table-section">
          <div class="row">
            <div class="col-sm-12">
              
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>User Name</th>
                    <th>Semester</th>
                    <th>Delete</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="student" items="${course.listOfStudents}">
                    <tr>
                      <td><c:out value="${student.firstName}" /></td>
                      <td><c:out value="${student.lastName}" /></td>
                      <td><c:out value="${student.user.username}" /></td>
                      <c:forEach var="sem" items="${student.listOfSemesters}">
                        <c:forEach var="stu" items="${sem.listOfStudents}">
                          <c:forEach var="semCrs" items="${course.listOfSemesters}">
                            <c:choose>
                              <c:when test="${student.studentId eq stu.studentId and sem.semesterId eq semCrs.semesterId}">
                                <td>
                                  <c:out value="${sem.semester}" />
                                  <c:set var="semId" scope="request" value="${sem.semesterId}"/>
                                </td>
                              </c:when>
                            </c:choose>
                          </c:forEach>
                        </c:forEach>
                      </c:forEach>
                      <td><a href=<c:url value="/school/course-assign?action=delete&studentId=${student.studentId}&courseId=${course.courseId}&semesterId=${semId}"/>>Delete</a></td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table> <!-- /.table .table-hover -->
              
            </div> <!-- /.col-sm-12 -->
          </div> <!-- /.row -->
        </div> <!-- /.table-section -->
      
      </div> <!-- /.container -->
    </div> <!-- /.update-course-page -->

  </body>
</html>