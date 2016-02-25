<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Main Page Department</title>


  <script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
  <script type="text/javascript" src="/js/jquery-form.js"></script>
  <script type="text/javascript" src="/js/department/departmentList.js"></script>
  <script type="text/javascript" src="/js/department/deleteDepartment.js"></script>
  <script type="text/javascript" src="/js/department/findAllDepartmentScript.js"></script>


</head>
<body>
<form action = "/departmentPage" method="GET" id="departmentForm">
  <input type="button" value="Add Department" onclick="location.href='addDepartment.html'">
</form>
<div id="answerSectionDepartment">
  <fieldset>
    <legend><h1>All Department</h1></legend>
    <div id="ajaxResponseDepartment"></div>
  </fieldset>
</div>

</body>
</html>
