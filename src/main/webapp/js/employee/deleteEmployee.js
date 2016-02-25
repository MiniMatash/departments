function deleteEmployee(id){
    $.ajax({
        url: '/deleteEmployee.html?employeeID='+id,
        method :'GET',
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        success: function(){
            employeeList();
        }
    });
};
