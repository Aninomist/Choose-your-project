function processCreateResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var computation = js["result"];

    // Update computation result
    document.createChoice.result.value = computation
}

function handleCreateClick(e) {
    //document.getElementById("result").value = "This is";
    document.createChoice.result.value = "success";
    var choiceID = "NewChoice"
    var numAlt = document.createChoice.numAlt.value;
    var numMember = document.createChoice.numMember.value;
    var description = document.createChoice.description.value;
    var data = {};
    data["choiceID"] = choiceID;
    data["limitMember"] = numMember;
    data["numAlt"] = numAlt;
    data["description"] = description;

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", createChoice_url, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    if (xhr.status == 200) {
    console.log ("XHR:" + xhr.responseText);
    processCreateResponse(xhr.responseText);
} else if (xhr.status == 400) {
    alert ("unable to process request");
}
} else {
    processCreateResponse(arg1, arg2, "N/A");
}
};
}