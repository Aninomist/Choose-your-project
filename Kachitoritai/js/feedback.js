const createFeedBack_url = "https://bxden5df79.execute-api.us-east-2.amazonaws.com/delta/addFeedback";


function processFeedback(result) {
    console.log("result:" + result);

    var js = JSON.parse(result);
    var alternatives = js["alt"];
    var length = alternative.length;

    for (var j = 0; j < length; j++) {
        if (alternatives[j]["noAlter"] == 1) {
            var alt1 = alternatives[j];
            feedBacks = alt1["feedBacks"];
            const textarea = document.getElementById('feedbackList1');

            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                var temp;

                for (let i = 0; i < feedBacks.length; i++) {
                    temp = "";
                    temp += "Feedback #" + i.toString() + ": ";
                    temp += "FeedbackID: " + feedbacks[i]["feedbackID"] + "; ";
                    temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                    temp += "Owner: " + feedbacks[i]["username"] + "; ";
                    temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                    temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                    textarea.value += temp;
                }
            }
        }
        if (alternatives[j]["noAlter"] == 2) {
            const textarea = document.getElementById('feedbackList2');
            var alt2 = alternatives[j];
            feedBacks = alt2["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                var temp;

                for (var i = 0; i < feedBacks.length; i++) {
                    temp = "";
                    temp += "Feedback #" + i.toString() + ": ";
                    temp += "FeedbackID: " + feedbacks[i]["feedbackID"] + "; ";
                    temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                    temp += "Owner: " + feedbacks[i]["username"] + "; ";
                    temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                    temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                    textarea.value += temp;
                }
            }
        }
        if (alternatives[j]["noAlter"] == 3) {
            const textarea = document.getElementById('feedbackList3');
            var alt3 = alternatives[j];
            feedBacks = alt3["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                var temp;

                for (var i = 0; i < feedBacks.length; i++) {
                    temp = "";
                    temp += "Feedback #" + i.toString() + ": ";
                    temp += "FeedbackID: " + feedbacks[i]["feedbackID"] + "; ";
                    temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                    temp += "Owner: " + feedbacks[i]["username"] + "; ";
                    temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                    temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                    textarea.value += temp;
                }
            }
        }
        if (alternatives[j]["noAlter"] == 4) {
            const textarea = document.getElementById('feedbackList4');
            var alt4 = alternatives[j];
            feedBacks = alt4["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                var temp;

                for (var i = 0; i < feedBacks.length; i++) {
                    temp = "";
                    temp += "Feedback #" + i.toString() + ": ";
                    temp += "FeedbackID: " + feedbacks[i]["feedbackID"] + "; ";
                    temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                    temp += "Owner: " + feedbacks[i]["username"] + "; ";
                    temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                    temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                    textarea.value += temp;
                }
            }
        }
        if (alternatives[j]["noAlter"] == 5) {
            const textarea = document.getElementById('feedbackList5');
            var alt5 = alternatives[j];
            feedBacks = alt5["feedBacks"];
            if (length === 0) {
                textarea.value = "No feedback";
            } else {
                var temp;

                for (var i = 0; i < feedBacks.length; i++) {
                    temp = "";
                    temp += "Feedback #" + i.toString() + ": ";
                    temp += "FeedbackID: " + feedbacks[i]["feedbackID"] + "; ";
                    temp += "Timestamp: " + feedbacks[i]["dateCreated"] + "; ";
                    temp += "Owner: " + feedbacks[i]["username"] + "; ";
                    temp += "Alternative ID: " + feedbacks[i]["altID"] + "; ";
                    temp += "Description: " + feedbacks[i]["description"] + "\n\n";
                    textarea.value += temp;
                }
            }
        }
    }
   

}

function handleFeedbackClick(username, numAlt, feedback) {

    var data = {};
    data["username"] = username;
    data["altID"] = numAlt;
    data["description"] = feedback;
    data["dateCreated"] = Date.now();

    var js = JSON.stringify(data);
    console.log("JS:" + js);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", createFeedBack_url, true);

    // send the collected data as JSON
    xhr.send(js);

    xhr.onloadend = function () {

        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {

            var responseJs = JSON.parse(xhr.responseText);
            var reponseCode = responseJs.httpCode;

            if (reponseCode === 200) {

                xhr.open("POST", getAlternative_url, true);

                var data = {};
                var js = JSON.stringify(data);

                xhr.send(js);

                xhr.onloadend = function () {
                    console.log(xhr);
                    console.log(xhr.request);
                    if (xhr.readyState == XMLHttpRequest.DONE) {

                        responseJs = JSON.parse(xhr.responseText);
                        reponseCode = responseJs.httpCode;

                        if (reponseCode === 200) {

                            console.log("XHR:" + xhr.responseText);
                            processFeedback(xhr.responseText);

                        } else {
                            alert("unable to process request1");
                        }
                    } else {
                        //processResponse("N/A");
                    }
                };
            } else {
                alert("unable to process request2");
            }
        }
    };
}
