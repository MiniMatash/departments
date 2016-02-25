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
                var id = dept.depId
                $("<tr>").appendTo(table)
                    .append($("<td>").text(dept.depId))
                    .append($("<td>").text(dept.depName))
                    .append('<a href="updateDepartment.html?UserId='+id+'"><button type="button">Update Department</button></a>')
                    .append('<input type="button" value="Delete Department" id="#EI'+dept.depId+'" onclick="deleteDepartment('+dept.depId+')">')
            });
        }
    });
};