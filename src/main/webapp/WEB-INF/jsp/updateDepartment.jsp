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
        window.location.href = "/departmentPage.html";})
    };
  </script>

</head>
<form action = "./updateDepartment.html" method="post" name ="updateForm" id="updateForm">
  <table cellpadding=4 cellspacing=2 border=0>
    <th bgcolor="#CCCCFF" colspan=2>
      <font size=5>UPDATE Department</font>
    </th>

    <tr bgcolor="#c8d8f8">
      <td valign=top>
        Department Name
        <input type="text" name="departmentName" value="${departmentInfo.departmentName}" size=15 maxlength=20></td>
    </tr>

    <input type="hidden" name="departmentID" value="${departmentInfo.departmentId}"></td>

    <tr bgcolor="#c8d8f8">
      <td align=center colspan=2>
        <input type="button" value="Submit" onclick="Final()"> <input type="reset" value="Reset">
      </td>
    </tr>

  </table>
</form>
</body>
</html>
