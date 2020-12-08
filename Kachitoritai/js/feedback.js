function handleFeedbackClick(username, numAlt, feedback) {
    var data = {};
    data["username"] = username;
    data["altID"] = numAlt;
    data["description"] = feedback;

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", createFeedBack_url, true);

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
                getAlternative(document.choiceID.value);
            } else if (responseCode == 405) {
                alert("Choice already Concluded, can't vote anymore");
                //hide buttons
            }
            else {
                alert("unable to process request");
            }
        } else {
            //processResponse("N/A");
        }
    };
               
}