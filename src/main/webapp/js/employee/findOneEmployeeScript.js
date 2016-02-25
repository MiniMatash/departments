
jQuery(document).on('ready', function(){
    $(".searchButton").on("click",  function() {        // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        $('#updateEmployeeForm').ajaxSubmit({
            dataType: 'json',
            success: function(data){
                $('#ajaxResponseEmployee').append($("<table><tr><td>First name</td><td>Last name</td>" +
                "<td>Date of birth</td><td>Validity of contract</td><td>Department ID</td></tr><tr>")                    // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
                    .append($("<td>").text(data.firstName))       // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                    .append($("<td>").text(data.lastName))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
                    .append($("<td>").text(data.dateOfBirth))
                    .append($("<td>").text(data.validityOfContract))
                    .append($("<td>").text(data.depId))
                    .append($("</tr></table>")));// Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
                //});
            }
        });
    });
});

