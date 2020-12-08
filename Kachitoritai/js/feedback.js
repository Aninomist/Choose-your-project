const createFeedBack_url = "https://bxden5df79.execute-api.us-east-2.amazonaws.com/delta/addFeedback";
const getFeedback_url = "";

function processFeedback(result) {
    console.log("result:" + result);

    var js = JSON.parse(result);
    var feedbacks = js["feedbacks"];
    var length = feedbacks.length;

    const textarea = document.getElementById('feedbackList1');

    if (length === 0) {
        textarea.value = "No feedback";
    } else {
        let temp;

        for (let i = 0; i < length; i++) {
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

            let responseJs = JSON.parse(xhr.responseText);
            let reponseCode = responseJs.httpCode;

            if (reponseCode === 200) {

                xhr.open("POST", getFeedback_url, true);

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
