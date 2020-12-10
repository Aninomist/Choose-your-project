function processChoice(result, time) {
    console.log("result:" + result);
    var js = JSON.parse(result);
    var choices = js["choices"];
    var length = choices.length;
    var newChoice;

    const textarea = document.getElementById('ListOfChoices');
    textarea.value = "Choice# " + "ChoiceID                               " + "dateCreated               " + "completed?   description\n";
    if (length == 0){
        textarea.value = "There is currently no Choice created at all!";
        if (time !== -1) {
            const deleteResult = document.getElementById('result');
            deleteResult.value = "Delete complete, remaining Choices will be shown above";
        }
        return;
    }
    for (var i = 0; i < length; i++){
        newChoice = "";
        newChoice += i.toString() + "       ";
        newChoice += choices[i]["choiceID"] + "   ";
        newChoice += choices[i]["dateCreated"] + "   ";
        newChoice += choices[i]["completed"] + "        ";
        newChoice += choices[i]["description"] + "\n\n";
        textarea.value += newChoice;
    }
    if (time !== -1) {
        const deleteResult = document.getElementById('result');
        deleteResult.value = "Delete complete, remaining Choices will be shown above";
    }
}

function getChoice(time){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", getChoice_url, true);

    var data = {};
    data["daysToDelete"] = parseFloat(time);
    var js = JSON.stringify(data);
    if (Number.isNaN(data["daysToDelete"])){
        alert("Plz put integer or double in that field");
        return
    }

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
                processChoice(xhr.responseText, time);

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