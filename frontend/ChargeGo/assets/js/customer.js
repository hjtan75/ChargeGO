const serverURL = "http://localhost:8080/"
// Mocked server
// const serverURL = "https://7145e527-1e33-4919-add8-d03ba9e035c5.mock.pstmn.io/"

const validateEmail = (email) => {
    // Regex that validate a email address
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };

function customerSignIn(){
    // Customer sign in function
    $("#sign-in-btn-customer-id").click(function(){
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
            "url": serverURL + "signin",
            "method": "POST",
            "type" : "jsonp",
            "data" : json,
            contentType:"application/json; charset=utf-8",
            success: function (body, status, xhr) {
                if (body["status"] == 200) {
                    data = body["data"];
                    sessionStorage.setItem("customerID", data["id"]);
                    sessionStorage.setItem("firstname", data["firstname"]);
                    sessionStorage.setItem("AuthenticationToken", data["token"]);
                    window.location.href = "user-stations-select.html";
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

function customerStationSelect(){
    // Function that store the customer's station selection
    function stationsButtonClicked(event) {
        console.log( "ID: " + event.data.stationID + " Name: " + event.data.stationName);
        sessionStorage.setItem("stationID", event.data.stationID);
        sessionStorage.setItem("stationName", event.data.stationName);
        window.location.href = "user-vehicle-select.html"
    }

    var stationsArr;
    const settings = {
        "crossDomain": true,
        "url": serverURL + "stations",
        "method": "GET",
        "type" : "jsonp",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            if (body["status"] == 200) {
                stationsArr = body["data"];
                console.log(stationsArr);
                sessionStorage.setItem("stationsArray", JSON.stringify(stationsArr));
                for (var i = 0; i < stationsArr.length; i++) {
                    $("#station-menu-id").append(`
                    <li class=\"nav-item\">
                    <a id=station-button-id-` + stationsArr[i].id + ` class=\"nav-link text-white\">
                      <span class="nav-link-text ms-1">` + stationsArr[i].name + `</span>
                    </a>
                    </li>`);
                }
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
    $.ajax(settings).done(function (response) {
        for (var i = 0; i < stationsArr.length; i++){
            $("#station-button-id-" + stationsArr[i].id).click({stationID: stationsArr[i].id, stationName: stationsArr[i].name}, stationsButtonClicked);
        }
    });
 }

 function customerVehicleSelect(){
    function vehicleSelectButtonClicked(event) {
        sessionStorage.setItem("vehicleID", event.data.vehicleID);
        sessionStorage.setItem("vehicleRemainingBattery", event.data.vehicleRemainingBattery);
        window.location.href = "user-vehicle-selected.html"
    }

    const stationID = sessionStorage.getItem("stationID");
    const stationName = sessionStorage.getItem("stationName");
    var vehicleArr;

    const settings = {
        "crossDomain": true,
        "url": serverURL + "vehicles?stationId=" + stationID,
        "method": "GET",
        "type" : "jsonp",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            if (body["status"] == 200) {
               var vehicleMenuID = "";
               var vehicleImgFilename = "";
               
               vehicleArr = body["data"];
               for (var i = 0; i < vehicleArr.length; i++) {
                const vehicle = vehicleArr[i];
                if (vehicle.vehicleType == 1){
                    vehicleMenuID = "#bike-menu-id";
                    vehicleImgFilename = "../assets/img/user/bike.jpg"
                } else if (vehicle.vehicleType == 2) {
                    vehicleMenuID = "#scooter-menu-id";
                    vehicleImgFilename = "../assets/img/user/scooter.png"
                }
                $(vehicleMenuID).append(`
                    <tr>
                        <td>
                        <div class="d-flex px-2 py-1">
                            <div>
                            <img src=` + vehicleImgFilename + ` class="avatar avatar-sm me-3 border-radius-lg" alt="user1">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                            <h6 class="mb-0 text-sm">` + vehicle.id + `</h6>
                            </div>
                        </div>
                        </td>
                        <td class="align-middle text-center text-sm">
                        <span class="badge badge-sm bg-gradient-success">` + vehicle.remainingBattery + `</span>
                        </td>
                        <td class="align-middle text-center">
                        <a id=vehicle-button-id-` + vehicle.id + ` class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
                            Select
                        </a>
                        </td>
                    </tr>`);
        }
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

    $.ajax(settings).done(function (response) {
        for (var i = 0; i < vehicleArr.length; i++){
            $("#vehicle-button-id-" + vehicleArr[i].id).click({vehicleID: vehicleArr[i].id, vehicleRemainingBattery: vehicleArr[i].remainingBattery}, vehicleSelectButtonClicked);
        }
    });

 }


function customerVehicleSelected(){
    const customerID = sessionStorage.getItem("customerID");
    const vehicleID = sessionStorage.getItem("vehicleID");
    const stationID = sessionStorage.getItem("stationID");
    const vehicleRemainingBattery = sessionStorage.getItem("vehicleRemainingBattery");
    const json = JSON.stringify({"customerId": customerID, "vehicleId": vehicleID, "location": stationID});

    $("#display-selected-vehicle-id").text(vehicleID.toUpperCase());
    $("#display-selected-vehicle-remaining-battery").text("Battery " + vehicleRemainingBattery + "%");
    $("#selected-vehicle-unlock-button").click(function () {
    const settings = {
        "crossDomain": true,
        "url": serverURL + "orders?customerId=" + customerID + "&vehicleId=" + vehicleID + "&location=" + stationID,
        "method": "POST",
        "type" : "jsonp",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            if (body["status"] == 200) {
                sessionStorage.setItem("orderState", JSON.stringify(body["data"]));
                window.location.href = "user-renting.html";
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
    $.ajax(settings).done(function (response) {})});
}

function customerRenting(){
    const vehicleID = sessionStorage.getItem("vehicleID");
    const vehicleRemainingBattery = sessionStorage.getItem("vehicleRemainingBattery");
    const stationArr = JSON.parse(sessionStorage.getItem("stationsArray"));
    const stationID = sessionStorage.getItem("stationID");
    var orderState = JSON.parse(sessionStorage.getItem("orderState"));
    sessionStorage.removeItem("orderState");
    sessionStorage.removeItem("vehicleRemainingBattery");
   
    $("#display-selected-vehicle-id").text(vehicleID);
    $("#display-selected-vehicle-remaining-battery").text("Battery " + vehicleRemainingBattery + "%");
    $("#return-btn-customer-id").click(function () {
        var endStationIdx = Math.floor(Math.random() * stationArr.length);
        while (stationArr[endStationIdx].id == stationID) {
            endStationIdx = Math.floor(Math.random() * stationArr.length);
        }
        orderState.endLocation = stationArr[endStationIdx].id;
        orderState.status = 2;

        const settings = {
            "crossDomain": true,
            "url": serverURL + "order/" + orderState.id,
            "method": "PUT",
            "type" : "jsonp",
            "data" : JSON.stringify(orderState), 
            contentType:"application/json; charset=utf-8",
            success: function (body, status, xhr) {
                if (body["status"] == 200) {
                    sessionStorage.setItem("paymentState", JSON.stringify(body["data"]["payment"]));
                    window.location.href = "user-pay.html";
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
}

function customerPay() {
    const paymentState = JSON.parse(sessionStorage.getItem("paymentState"));
    // console.log(paymentState);
    $("#display-payment-id").text("Payment ID: " + paymentState.id.toUpperCase());
    $("#display-payment-cost-id").text("Cost: " + paymentState.totalPrice);
    $("#pay-btn-customer-id").click(function () {
        const settings = {
            "crossDomain": true,
            "url": serverURL + "payment/" + paymentState.id,
            "method": "POST",
            "type" : "jsonp", 
            contentType:"application/json; charset=utf-8",
            success: function (body, status, xhr) {
                if (body["status"] == 200) {
                    console.log(body);
                    window.location.href = "user-stations-select.html";
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
    })
}


// function customerVehicleList(){
//    console.log(sessionStorage.getItem("customerID"))
//    console.log(sessionStorage.getItem("SelectedVehicle"))
// }