function departmentList(){
    jQuery('#ajaxResponseDepartment').empty();
    $.ajax({
        dataType: 'json',
        url: '/departmentPage.html?isAjax=true',
        method :'GET',
        success: function (data) {
            var table = $("<table>").appendTo($("#ajaxResponseDepartment"))
            $(table).append($("<table id='Table'><tr><th>Department ID</th><th>Department Name</th></tr>"))
            jQuery.each(data, function(index, dept){
                $("<tr>").appendTo(table)
                    .append($("<td>").text(dept.departmentId))
                    .append($("<td>").text(dept.departmentName))
                    .append('<a href="updateDepartment.html?UserId='+dept.departmentId+'"><button type="button">Update Department</button></a>')
                    .append('<input type="button" value="Delete Department" id="#EI'+dept.departmentId+'" onclick="deleteDepartment('+dept.departmentId+')">')
            });
        }
    });
};