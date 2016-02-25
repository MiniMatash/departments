function employeeList(){
    jQuery('#ajaxResponseEmployee').empty();
    $.ajax({
        dataType: 'json',
        url: '/employeePage.html?isAjax=true',
        method :'GET',
        success: function (data) {
            var table = $("<table>").appendTo($("#ajaxResponseEmployee"))
            $(table).append($("<table id='Table'><tr><th>First name</th><th>Last name</th><th>Date of birth</th><th>Validity of contract</th><th>Department ID</th></tr>"))
            jQuery.each(data, function(index, empl){
                var id = empl.id
                $("<tr>").appendTo(table)
                    .append($("<td>").text(empl.firstName))
                    .append($("<td>").text(empl.lastName))
                    .append($("<td>").text(empl.dateOfBirth))
                    .append($("<td>").text(empl.validityOfContract))
                    .append($("<td>").text(empl.depId))
                    .append('<a href="updateEmployee.html?UserId='+id+'"><button type="button">Update Employee</button></a>')
                    .append('<input type="button" value="Delete employee" id="#EI'+empl.id+'" onclick="deleteEmployee('+empl.id+')">')
            });
        }
    });
};