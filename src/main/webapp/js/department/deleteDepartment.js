function deleteDepartment(id){
    $.ajax({
        url: '/deleteDepartment.html?departmentID='+id,
        method :'GET',
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        success: function(){
            departmentList();
        }
    });
};
