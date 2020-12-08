//const createFeedBack_url = "https://bxden5df79.execute-api.us-east-2.amazonaws.com/delta/addFeedback";


function processFeedback(result) {
    console.log("result:" + result);

    var js = JSON.parse(result);
    var alternatives = js["alt"];
    var length = alternatives.length;
    var temp;

    var textarea = document.getElementById('feedbackList1');
    textarea.value = "";
    textarea = document.getElementById('feedbackList2');
    textarea.value = "";
    textarea = document.getElementById('feedbackList3');
    textarea.value = "";
    textarea = document.getElementById('feedbackList4');
    textarea.value = "";
    textarea = document.getElementById('feedbackList5');
    textarea.value = "";

    for (var j = 0; j < length; j++) {
        if (alternatives[j]["noAlter"] == 1) {
            var alt1 = alternatives[j];
            feedbacks = alt1["feedBacks"];


            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                //var temp;
                if (feedbacks.length > 0) {
                    for (let i = 0; i < feedbacks.length; i++) {
                        temp = "";
                        temp += "Feedback #" + i.toString() + ": ";
                        //temp += "FeedbackID: " + feedbacks[i]["feedBackID"] + "; ";
                        temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                        temp += "Owner: " + feedbacks[i]["username"] + "; ";
                        //temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                        temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                        const textarea = document.getElementById('feedbackList1');
                        textarea.value += temp;
                    }
                }
            }
        }
        if (alternatives[j]["noAlter"] == 2) {
            const textarea = document.getElementById('feedbackList2');
            var alt2 = alternatives[j];
            feedbacks = alt2["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                //var temp;

                if (feedbacks.length > 0) {
                    for (let i = 0; i < feedbacks.length; i++) {
                        temp = "";
                        temp += "Feedback #" + i.toString() + ": ";
                        //temp += "FeedbackID: " + feedbacks[i]["feedBackID"] + "; ";
                        temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                        temp += "Owner: " + feedbacks[i]["username"] + "; ";
                        //temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                        temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                        const textarea = document.getElementById('feedbackList2');
                        textarea.value += temp;
                    }
                }
            }
        }
        if (alternatives[j]["noAlter"] == 3) {
            const textarea = document.getElementById('feedbackList3');
            var alt3 = alternatives[j];
            feedbacks = alt3["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                //var temp;

                if (feedbacks.length > 0) {
                    for (let i = 0; i < feedbacks.length; i++) {
                        temp = "";
                        temp += "Feedback #" + i.toString() + ": ";
                        //temp += "FeedbackID: " + feedbacks[i]["feedBackID"] + "; ";
                        temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                        temp += "Owner: " + feedbacks[i]["username"] + "; ";
                        //temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                        temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                        const textarea = document.getElementById('feedbackList3');
                        textarea.value += temp;
                    }
                }
            }
        }
        if (alternatives[j]["noAlter"] == 4) {
            const textarea = document.getElementById('feedbackList4');
            var alt4 = alternatives[j];
            feedbacks = alt4["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                //var temp;

                if (feedbacks.length > 0) {
                    for (let i = 0; i < feedbacks.length; i++) {
                        temp = "";
                        temp += "Feedback #" + i.toString() + ": ";
                        //temp += "FeedbackID: " + feedbacks[i]["feedBackID"] + "; ";
                        temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                        temp += "Owner: " + feedbacks[i]["username"] + "; ";
                        //temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                        temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                        const textarea = document.getElementById('feedbackList4');
                        textarea.value += temp;
                    }
                }
            }
        }
        if (alternatives[j]["noAlter"] == 5) {
            const textarea = document.getElementById('feedbackList5');
            var alt5 = alternatives[j];
            feedbacks = alt5["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                //var temp;

                if (feedbacks.length > 0) {
                    for (let i = 0; i < feedbacks.length; i++) {
                        temp = "";
                        temp += "Feedback #" + i.toString() + ": ";
                        //temp += "FeedbackID: " + feedbacks[i]["feedBackID"] + "; ";
                        temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                        temp += "Owner: " + feedbacks[i]["username"] + "; ";
                        //temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                        temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                        const textarea = document.getElementById('feedbackList5');
                        textarea.value += temp;
                    }
                }
            }
        }
    }
   

}

function handleFeedbackClick(username, number, feedback) {

    console.log("username:" + username);
    console.log("numAlt:" + number);
    console.log("feedback:" + feedback);

    var xhr = new XMLHttpRequest();
    //xhr.open("POST", getAlternative_url, true);
    xhr.open("POST", getAlternative_url, true);
    var data = {};
    data["choiceID"] = choiceID;
    var js = JSON.stringify(data);
    // send the collected data as JSON
    xhr.send(js);
    var altID = null;
    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {

            var responseJs = JSON.parse(xhr.responseText);
            //choiceID = responseJs.response;
            reponseCode = responseJs.httpCode;

            if (reponseCode == 200) {
                var alternatives = responseJs["alt"];
                var length = alternatives.length;
                for (var i = 0; i < length; i++){
                    if (alternatives[i]["noAlter"] == number){
                        altID = alternatives[i]["altID"];
                    }
                }

                // BIIIIG OOOF begins here
                data = {};
                data["altID"] = altID;
                data["username"] = username;
                data["description"] = feedback;
                data["dateCreated"] = Date.now();
                js = JSON.stringify(data);

                xhr = new XMLHttpRequest();
                xhr.open("POST", createFeedBack_url, true);
                xhr.send(js);

                xhr.onloadend = function () {
                    console.log(xhr);
                    console.log(xhr.request);
                    if (xhr.readyState == XMLHttpRequest.DONE) {

                        var responseJs = JSON.parse(xhr.responseText);
                        //choiceID = responseJs.response;
                        reponseCode = responseJs.httpCode;

                        if (reponseCode == 200) {
                            getAlternativeFB(choiceID);
                            processFeedback(responseJs);
                            getAlternative(choiceID)
                        } else if( responseCode == 405){
                            alert ("Choice already Concluded, can't vote anymore");
                            //hide buttons
                        }
                        else
                        {
                            alert("unable to process request");
                        }
                    } else {
                        //processResponse("N/A");
                    }
                };
                // BIIIIG OOOF ends here
            } else {
                alert ("unable to process request");
            }
        } else {
            //processResponse("N/A");
        }
    };
}

function getAlternativeFB(choiceID){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", getAlternative_url, true);

    var data = {};
    data["choiceID"] = choiceID;
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
                processFeedback(xhr.responseText);

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