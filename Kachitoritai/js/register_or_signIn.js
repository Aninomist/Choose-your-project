alternativeURL = "https://kachitoridaics3733groupproject.s3.us-east-2.amazonaws.com/html/Alternative.html"
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
                //processResponse(xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var alternatives = js["alternatives"];
                const altNum = alternatives.length;
                var LOD = [];
                for (var i = 0; i < altNum; i++){
                    LOD[i] = alternatives[i]["description"];
                }
                var LOD_s = JSON.stringify(LOD)
                alternativeURL = alternativeURL + "?choiceID="  + choiceID + "&choiceDes=" + urlParams.get('choiceDes') + "&username=" + js["response"] + "&LOD=" + LOD_s;
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
