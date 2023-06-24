const serverURL = "http://localhost:8080/"

function createTable() {
    const monthsForRevenue = JSON.parse(sessionStorage.getItem("monthsForRevenue")); 
    const revenuePerMonths = JSON.parse(sessionStorage.getItem("revenuePerMonths"));
    const monthsForBrokenVehicle = JSON.parse(sessionStorage.getItem("monthsForBrokenVehicle"));
    const brokenVehiclePerMonths = JSON.parse(sessionStorage.getItem("brokenVehiclePerMonths"));
    // console.log(monthsForRevenue);
    // console.log(revenuePerMonths);
    const optionSetting = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
            display: false,
            }
        },
        interaction: {
            intersect: false,
            mode: 'index',
        },
        scales: {
            y: {
            grid: {
                drawBorder: false,
                display: true,
                drawOnChartArea: true,
                drawTicks: false,
                borderDash: [5, 5],
                color: 'rgba(255, 255, 255, .2)'
            },
            ticks: {
                suggestedMin: 0,
                suggestedMax: 500,
                beginAtZero: true,
                padding: 10,
                font: {
                    size: 14,
                    weight: 300,
                    family: "Roboto",
                    style: 'normal',
                    lineHeight: 2
                },
                color: "#fff"
            },
            },
            x: {
            grid: {
                drawBorder: false,
                display: true,
                drawOnChartArea: true,
                drawTicks: false,
                borderDash: [5, 5],
                color: 'rgba(255, 255, 255, .2)'
            },
            ticks: {
                display: true,
                color: '#f8f9fa',
                padding: 10,
                font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
                },
            }
            },
        },
    }

    var ctx = document.getElementById("revenue-chart-id").getContext("2d");
    var ctx2 = document.getElementById("monthly-available-chart-id").getContext("2d");
    
    new Chart(ctx, {
        type: "bar",
        data: {
        labels: monthsForRevenue,
        datasets: [{
            label: "Sales",
            tension: 0.4,
            borderWidth: 0,
            borderRadius: 4,
            borderSkipped: false,
            backgroundColor: "rgba(255, 255, 255, .8)",
            data: revenuePerMonths,
            maxBarThickness: 6
        }, ],
        },
        options : optionSetting
    });
    new Chart(ctx2, {
        type: "bar",
        data: {
        labels: monthsForBrokenVehicle,
        datasets: [{
            label: "Sales",
            tension: 0.4,
            borderWidth: 0,
            borderRadius: 4,
            borderSkipped: false,
            backgroundColor: "rgba(255, 255, 255, .8)",
            data: brokenVehiclePerMonths,
            maxBarThickness: 6
        }, ],
        },
        options : optionSetting
    });
}


const validateEmail = (email) => {
    // Regex that validate a email address
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };

function managerSignIn(){
    // Customer sign in function
    $("#sign-in-btn-manager-id").click(function(){
        const email = $("#sign-in-email-id").val();
        const pass = $("#sign-in-password-id").val();
        const json = JSON.stringify({"email": email, "password": pass});

        if (!validateEmail(email)) {
            alert("Invalid email");
            $("#sign-in-email-id").val("");
            $("#sign-in-password-id").val("");
            return;
        }

        const settings = {
            "crossDomain": true,
            "url": serverURL + "manager/signin",
            "method": "POST",
            "type" : "jsonp",
            "data" : json,
            contentType:"application/json; charset=utf-8",
            success: function (body, status, xhr) {
                if (body["status"] == 200) {
                    if (body["status"] == 200) {
                        data = body["data"];
                        sessionStorage.setItem("managerID", data["id"]);
                        window.location.href = "manager.html";
                    } else if (body["status"] == 500) {
                        alert(body["msg"]);
                    } else {
                        alert("Unexpected Error");
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
        $.ajax(settings).done(function (response) {});
    });
}

function managerReport() {
    const settingsRevenue = {
        "crossDomain": true,
        "url": serverURL + "manager/revenuePreviousTenMonths",
        "method": "GET",
        "type" : "JSON",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            var monthsForRevenue = [];
            var revenuePerMonths = [];
            for (const key in body) {
                monthsForRevenue.push(key);
                revenuePerMonths.push(body[key]);
            }
            sessionStorage.setItem("monthsForRevenue", JSON.stringify(monthsForRevenue));
            sessionStorage.setItem("revenuePerMonths", JSON.stringify(revenuePerMonths));
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(textStatus);
        }
    };

    const settingsBrokenVehicle = {
        "crossDomain": true,
        "url": serverURL + "/manager/numOfBrokenVehiclesPreviousTenMonths",
        "method": "GET",
        "type" : "JSON",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            var monthsForBrokenVehicle = [];
            var brokenVehiclePerMonths = [];
            for (const key in body) {
                monthsForBrokenVehicle.push(key);
                brokenVehiclePerMonths.push(body[key]);
            }
            sessionStorage.setItem("monthsForBrokenVehicle", JSON.stringify(monthsForBrokenVehicle));
            sessionStorage.setItem("brokenVehiclePerMonths", JSON.stringify(brokenVehiclePerMonths));
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(textStatus);
        }
    };

    $.ajax(settingsRevenue).done(function (response) {
        $.ajax(settingsBrokenVehicle).done(function (response) {
            createTable();
        });
    });
}

function operatorPerformance() {
    const settings = {
        "crossDomain": true,
        "url": serverURL + "/manager/operatorFixAndMove",
        "method": "GET",
        "type" : "JSON",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            for (const key in body) {
                $("#operator-performance-id").append(`
                <tr>
                <td>
                  <div class="px-2 py-1">
                    <div class="flex-column justify-content-center lign-middle text-center">
                      <p class="mb-0 text-m">` + key + `</p>
                    </div>
                  </div>
                </td>
                <td class="align-middle text-center text-sm">
                  <h5 class="font-weight-bold">` + body[key] +`</h5>
                </td>
                </tr>
                `)
            }
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(textStatus);
        }
    };

    $.ajax(settings).done(function (response) {});
}

function availableVehicleCounter() {
    const settingsUnavailable = {
        "crossDomain": true,
        "url": serverURL + "/manager/currentNumUnavailableVehicle",
        "method": "GET",
        "type" : "JSON",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            sessionStorage.setItem("availableVehicle", body);
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(textStatus);
        }
    };

    const settingsAvailable = {
        "crossDomain": true,
        "url": serverURL + "/manager/currentNumAvailableVehicle",
        "method": "GET",
        "type" : "JSON",
        contentType:"application/json; charset=utf-8",
        success: function (body, status, xhr) {
            sessionStorage.setItem("unavailableVehicle", body);
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(textStatus);
        }
    };

    $.ajax(settingsUnavailable).done(function (response) {
        $.ajax(settingsAvailable).done(function (response) {
            const unavailableVehicle = parseInt(sessionStorage.getItem("unavailableVehicle"));
            const availableVehicle = parseInt(sessionStorage.getItem("availableVehicle"));
            var ratio = Math.floor(((unavailableVehicle) / (unavailableVehicle + availableVehicle)) * 100)
            $("#percentage-available-vehicle").text(ratio + "%");
            $("#progress-bar-vehicle").attr("aria-valuenow", ratio);
            $("#progress-bar-vehicle").attr("class", "progress-bar bg-gradient-info w-"+ 30);
        });
    });
}