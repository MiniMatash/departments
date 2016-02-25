
jQuery(document).on('ready', function(){
    $(".searchButton").on("click",  function() {
        $('#updateEmployeeForm').ajaxSubmit({
            dataType: 'json',
            success: function(data){
                $('#ajaxResponseEmployee').append($("<table><tr><td>Department Name</td></tr><tr>")
                    .append($("<td>").text(data.depName))
                    .append($("</tr></table>")));
            }
        });
    });
});

