<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Update employee</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="/resources/jquery-form.js"></script>

  <script type="text/javascript">
    Final = function () {
      jQuery('#updateForm').ajaxSubmit();
      $(document).ready(function(){
        window.location.href = "/employeePage.html";})
    };
  </script>

</head>
<form action = "./updateEmployee.html" method="post" name ="updateForm" id="updateForm">
  <table cellpadding=4 cellspacing=2 border=0>
    <th bgcolor="#CCCCFF" colspan=2>
      <font size=5>UPDATE EMPLOYEE</font>
    </th>

    <tr bgcolor="#c8d8f8">
      <td valign=top>
        First Name

        <input type="text" name="firstName" value="${employeeInfo.firstName}" size=15 maxlength=20></td>
      <td valign=top>
        Last Name

        <input type="text" name="lastName" value="${employeeInfo.lastName}" size=15 maxlength=20></td>
    </tr>

    <tr bgcolor="#c8d8f8">
      <td valign=top colspan=2>
        Date of birth
        <input type="date" name="dateOfBirth" value="${employeeInfo.dateOfBirth}">
      </td>
    </tr>

    <tr bgcolor="#c8d8f8">
      <td valign=top>
        Department_ID

        <input type="number" name="departmentID" value="${employeeInfo.departmentId}" max="15" min="1"/>
      </td>
    </tr>

    <input type="hidden" name="employeeID" value="${employeeInfo.employeeId}"/>

    <tr bgcolor="#c8d8f8">
      <td valign=top>
        Validity of contract

        <input type="number" name="validityOfContract" value="${employeeInfo.validityOfContract}" max="10" min="1"/>

      </td>
    </tr>

    <tr bgcolor="#c8d8f8">
      <td align=center colspan=2>
        <input type="button" value="Submit" onclick="Final()"> <input type="reset" value="Reset">
      </td>
    </tr>

  </table>
</form>
</body>
</html>
