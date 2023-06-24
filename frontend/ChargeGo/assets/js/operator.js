const serverURL = "http://localhost:8080/"

const validateEmail = (email) => {
    // Regex that validate a email address
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };

function OperatorSignIn(){
    $("#sign-in-btn-operator-id").click(function(){
        // Take the nessesary variable
        const email = $("#sign-in-email-id").val();
        const pass = $("#sign-in-password-id").val();
        const json = JSON.stringify({"email": email, "password": pass});

        if (!validateEmail(email)) {
            // Check is the entered email is invalid
            alert("Invalid email");
            $("#sign-in-email-id").val("");
            $("#sign-in-password-id").val("");
            return;
        }

        // A post request to the backend server
        // Store customerID, firstname and authentication into sessionStorage
        // Display message when signin not successful.
        const settings = {
            "crossDomain": true,
            "url": serverURL + "operator/signin",
            "method": "POST",
            "type" : "jsonp",
            "data" : json,
            contentType:"application/json; charset=utf-8",
            success: function (body, status, xhr) {
                console.log(body)
                if (body["status"] == 200) {
                    data = body["data"];
                    sessionStorage.setItem("operatorID", data["id"]);
                    sessionStorage.setItem("firstname", data["firstname"]);
                    sessionStorage.setItem("AuthenticationToken", data["token"]);
                    window.location.href = "operator.html";
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
    
        // Perform post request
        $.ajax(settings).done(function (response) {});
    });
}
