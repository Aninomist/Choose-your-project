alternativeURL = "https://kachitoridaics3733groupproject.s3.us-east-2.amazonaws.com/html/alternative.html"

function processResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);
    var alternatives = js["alternatives"];
    var length = alternatives.length;
    var alt1  = alternatives[0];
    var alt2  = alternatives[1];
    if (length == 3) {
        var alt3 = alternatives[2];
    }
    if (length == 4) {
        var alt3 = alternatives[2];
        var alt4  = alternatives[3];
    }
    if (length == 5) {
        var alt3 = alternatives[2];
        var alt4  = alternatives[3];
        var alt5  = alternatives[4];
    }



    document.alternatives.alt1.value = alt1["description"];
    document.alternatives.alt2.value = alt2["description"];

    if(length>=3) {
        document.alternatives.alt3.value = alt3["description"];
    }
    if(length>=4) {
        document.alternatives.alt4.value = alt4["description"];
    }
    if(length>=5) {
        document.alternatives.alt5.value = alt5["description"];
    }
    //document.alternatives.alt4.value = alt4["description"];
    //document.alternatives.alt5.value = alt5["description"];
    // Update computation result
    //document.createChoice.result.value = computation;
}

function handleRegisterClick(e) {
    //document.getElementById("result").value = "This is";
    //document.createChoice.result.value = "success";
    var username = document.registerOrSignIn.username.value;
    var password = document.registerOrSignIn.password.value;
    var data = {};
    data["choiceID"] = choiceID;
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
                var js = JSON.parse(xhr.responseText);
                var alternatives = js["alternatives"];
                alternativeURL = alternativeURL + "?choiceID="  + choiceID + "&username="  + js["response"] + "&altNum=" + alternatives.length;
                console.log(alternativeURL);
                window.location.replace(alternativeURL);


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
