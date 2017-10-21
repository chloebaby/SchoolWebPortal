<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Person Details</title>
    <style>
      table{
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 500px;
      }
      td, th{
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
      }
      tr:nth-child(even){
        background-color: #dddddd;
      }
    </style>
  </head>
  
  <body>
    <table>
      <tr>
        <th>ID</th>
        <th>Name</th>
      </tr>
      <c:forEach var="person" items="${allPersons}">
      <tr>
        <td>${person.id}</td>
        <td>${person.name}</td>
        <td><a href="personDetails?action=delete&personId=<c:out value="${person.id}"/>">Delete</a></td>
      </tr>
      </c:forEach>
    </table>
  
  </body>
</html>