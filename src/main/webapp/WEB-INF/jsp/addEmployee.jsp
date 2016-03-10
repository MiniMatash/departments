<!DOCTYPE HTML>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee registration</title>

    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="/resources/jquery-form.js"></script>
    <script type="text/javascript">
        Final = function () {
            jQuery('#addForm').ajaxSubmit();
        };
    </script>

</head>
<body>


<form action = "./addEmployee.html" method="post" name ="addForm" id="addForm">
    <table cellpadding=4 cellspacing=2 border=0>

        <th bgcolor="#CCCCFF" colspan=2>
            <font size=5>EMPLOYEE REGISTRATION</font>
        </th>

        <tr bgcolor="#c8d8f8">
            <td valign=top>
                First Name

                <input type="text" name="firstName" value="" size=15 maxlength=20></td>
            <td valign=top>
                Last Name

                <input type="text" name="lastName" value="" size=15 maxlength=20></td>
        </tr>

        <tr bgcolor="#c8d8f8">
            <td valign=top colspan=2>
                Date of birth
                <input type="date" name="DateOfBirth">
            </td>
        </tr>

        <tr bgcolor="#c8d8f8">
            <td valign=top>
                Department_ID

                <input type="number" name="department_ID" max="15" min="1"/>
            </td>
        </tr>

        <tr bgcolor="#c8d8f8">
            <td valign=top>
                Validity of contract

                <input type="number" name="validityOfContract" max="10" min="1"/>

            </td>
        </tr>

        <tr bgcolor="#c8d8f8">
            <td align=center colspan=2>
                <input type="button" value="Submit" onclick="Final()"> <input type="reset" value="Reset">
            </td>
        </tr>

        <tr bgcolor="#c8d8f8">
            <td align=center colspan=2>
                <input type="button" value="Return to previous page" onclick="window.location.href = 'employeePage.html'">
            </td>
        </tr>

    </table>
</form>
</body>
</html>
