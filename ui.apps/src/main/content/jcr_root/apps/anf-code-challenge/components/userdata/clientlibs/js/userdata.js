$(document).ready(function() {

    $("form#userForm").submit(function(e) {
        var fname = $('#fname').val();
        var lname = $('#lname').val();
        var age = $('#age').val();
        var country = $("input[name=country]").val();

        $.ajax({
            type: "GET",
            url: '/bin/saveUserDetails',
            data: {
                "firstName": fname,
                "lastName": lname,
                "age": age,
                "country": country
            },
            success: function(data, textStatus, jqXHR) {
                $("#success-message").show();

            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //write your logic that you need to perform on error
                $("#error-message").text(data.responseJSON.message);

            }
        });
    });
});