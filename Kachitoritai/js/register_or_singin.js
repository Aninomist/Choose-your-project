//signInURL = "https://kachitoridaics3733groupproject.s3.us-east-2.amazonaws.com/html/RegisterOrSingIn.html"

function processResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var computation = js["result"];

    // Update computation result
    //document.createChoice.result.value = computation;
    console.log(computation);
}

function handleRegisterClick(e) {
    //document.getElementById("result").value = "This is";
    //document.createChoice.result.value = "success";
    var username = document.registerOrSignIn.username.value;
    var password = document.registerOrSignIn.password.value;
    var data = {};
    data["ID"] = choiceID;
    data["username"] = username;
    data["password"] = password;

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", participateChoice_url, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {

            var responseJs = JSON.parse(xhr.responseText);
            //choiceID = responseJs.response;
            reponseCode = responseJs.httpCode;

            if (reponseCode == 200) {

                console.log ("XHR:" + xhr.responseText);
                processResponse(xhr.responseText);

                //signInHTML = signInURL + "?choiceID="  + choiceID;
                //console.log(signInHTML)
                //window.location.replace(signInHTML);


            } else if (reponseCode == 400) {
                alert ("unable to process request");
            } else if (reponseCode == 406) {
                alert ("not accepted, alternertaive need to be between 2 and 5");
            }
        } else {
            processResponse("N/A");
        }
    };
}

