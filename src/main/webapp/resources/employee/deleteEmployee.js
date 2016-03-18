function deleteEmployee(id){
    $.ajax({
        url: '/deleteEmployee?employeeId='+id,
        method :'POST',
        dataType : 'json',
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        success: function(){
            employeeList();
        }
    });
};
