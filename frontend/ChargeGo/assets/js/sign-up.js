const serverURL = "http://localhost:8080/"

const validateEmail = (email) => {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };

$(document).ready(function(){
    $("#sign-up-btn-id").click(function(){
        const firstName = $("#sign-up-first-name-input-id").val();
        const lastName  = $("#sign-up-last-name-input-id").val();
        const email = $("#sign-up-email-input-id").val();
        const phoneNum = $("#sign-up-tel-input-id").val();
        const password = $("#sign-up-password-input-id").val();
        const json = JSON.stringify({
            "firstname": firstName,
            "lastname": lastName,
            "email": email,
            "phone": phoneNum, 
            "password": password 
        });

        if (!validateEmail(email)) {
            // Check is the entered email is invalid
            alert("Invalid email");
            $("#sign-up-email-input-id").val("");
            $("#sign-in-password-id").val("");
            return;
        }

        console.log(json);
        const settings = {
            "crossDomain": true,
            "url": "http://localhost:8080/signup",
            "method": "POST",
            "type" : "jsonp",
            "data" : json,
            contentType:"application/json; charset=utf-8",
            success: function (body, status, xhr) {
                console.log(body);
                if (body["status"] == 200) {
                    window.location.href = "sign-in-customer.html";
                } else if (body["status"] == 500) {
                    alert(body["msg"]);
                } else {
                    alert("Unexpected Error");
                }
            },
            error: function (jqXhr, textStatus, errorMessage) {
                console.log(textStatus);
            }
        };
    
        $.ajax(settings).done(function (response) {});

    });
});