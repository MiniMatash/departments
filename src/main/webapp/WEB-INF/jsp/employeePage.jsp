<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Main Page Employee</title>


  <script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
  <script type="text/javascript" src="/js/jquery-form.js"></script>
  <script type="text/javascript" src="/js/employee/employeeList.js"></script>
  <script type="text/javascript" src="/js/employee/deleteEmployee.js"></script>
  <script type="text/javascript" src="/js/employee/findAllEmployeeScript.js"></script>


</head>
<body>
<form action = "/employeePage" method="GET" id="employeeForm">
  <input type="button" value="Add Employee" onclick="location.href='addEmployee.html'">
</form>
<div id="answerSectionEmployee">
  <fieldset>
    <legend><h1>All Stuff</h1></legend>
    <div id="ajaxResponseEmployee"></div>
  </fieldset>
</div>

</body>
</html>