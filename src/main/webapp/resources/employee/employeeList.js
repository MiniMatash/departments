function employeeList(){
    jQuery('#ajaxResponseEmployee').empty();
    $.ajax({
        dataType: 'json',
        url: '/employeeList',
        method :'GET',
        success: function (data) {
            var table = $("<table>").appendTo($("#ajaxResponseEmployee"))
            $(table).append($("<table id='Table'><tr><th>First name</th><th>Last name</th><th>Date of birth</th><th>Validity of contract</th><th>Department ID</th></tr>"))
            jQuery.each(data, function(index, empl){
                $("<tr>").appendTo(table)
                    .append($("<td>").text(empl.firstName))
                    .append($("<td>").text(empl.lastName))
                    .append($("<td>").text(empl.dateOfBirth))
                    .append($("<td>").text(empl.validityOfContract))
                    .append($("<td>").text(empl.departmentId))
                    .append('<a href="updateEmployee.html?userId='+ empl.employeeId+'"><button type="button">Update Employee</button></a>')
                    .append('<input type="button" value="Delete employee" id="'+empl.employeeId+'" onclick="deleteEmployee('+empl.employeeId+')">')
            });
        }
    });
};