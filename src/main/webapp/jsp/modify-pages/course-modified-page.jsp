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
              <h2>Update Course Page</h2>
            </div> <!-- /.col-sm-12 -->
          </div> <!-- /.row -->
        </div> <!-- /.update-course-header -->
        
        <div class="update-course-form">
          <div class="row">
            <div class="col-sm-12">
              <form action="${pageContext.request.contextPath}/school/course" onsubmit="return validateCourseForm()" method="post">
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
                      <input type="text" name="courseCode" class="form-control" id="courseCode" value="${course.courseCode}">
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label for="courseName">Course Name</label>
                        <input type="text" name="courseName" class="form-control" id="courseName" value="${course.courseName}">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <button type="submit" name="option" value="update" class="btn btn-primary">Submit</button>
                    <button type="submit" name="option" value="cancel" class="btn btn-danger">Cancel</button>
                  </div>
                </div>
                
              </form>
            </div> <!-- /.col-sm-12 -->
          </div> <!-- /.row -->
        </div> <!-- /.update-course-form  -->
      
      </div> <!-- /.container -->
    </div> <!-- /.update-course-page -->
  
  </body>
</html>