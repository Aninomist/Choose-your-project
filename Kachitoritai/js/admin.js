function processChoice(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);
    var choices = js["choices"];
    var length = choices.length;
    var newChoice;
    const textarea = document.getElementById('ListOfChoices');
    if (length == 0){
        textarea.value = "There is no Choice created at all!";
        return;
    }
    for (var i = 0; i < length; i++){
        newChoice = "";
        newChoice += "Choice #" + i.toString() + ": ";
        newChoice += "ChoiceID: " + choices[i]["choiceID"] + "; ";
        newChoice += "dateCreated: " + choices[i]["dateCreated"] + "; ";
        newChoice += "completed?: " + choices[i]["completed"] + "\n\n";
        textarea.value += newChoice;
    }

}

function getChoice(time){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", getChoice_url, true);

    var data = {};
    data["choiceID"] = time;
    var js = JSON.stringify(data);
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
                processChoice(xhr.responseText);

                //signInHTML = signInURL + "?choiceID="  + choiceID;
                //console.log(signInHTML)
                //window.location.replace(signInHTML);


            } else {
                alert ("unable to process request");
            }
        } else {
            //processResponse("N/A");
        }
    };
}