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
}