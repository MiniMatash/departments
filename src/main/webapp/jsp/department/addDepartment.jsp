<!DOCTYPE HTML>

<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Employee registration</title>

  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="/js/jquery-form.js"></script>
  <script type="text/javascript">
    Final = function () {
      jQuery('#addForm').ajaxSubmit();
    };
  </script>

</head>
<body>
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Department registration</title>
</head>
<body>
<form action = "./addDepartment.html" method="post" name ="addForm" id="addForm">
  <table cellpadding=4 cellspacing=2 border=0>

    <th bgcolor="#CCCCFF" colspan=2>
      <font size=5>DEPARTMENT REGISTRATION</font>
    </th>

    <tr bgcolor="#c8d8f8">
      <td valign=top>
        Department Name
        <input type="text" name="departmentName" value="" size=15 maxlength=20></td>
    </tr>

    <tr bgcolor="#c8d8f8">
      <td align=center colspan=2>
        <input type="button" value="Submit" onclick="Final()"> <input type="reset" value="Reset">
      </td>
    </tr>

    <tr bgcolor="#c8d8f8">
      <td align=center colspan=2>
        <input type="button" value="Return to previous page" onclick="window.location.href = 'departmentPage.html'">
      </td>
    </tr>

  </table>
</form>
</body>
</html>