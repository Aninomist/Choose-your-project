signInURL = "https://kachitoridaics3733groupproject.s3.us-east-2.amazonaws.com/html/RegisterOrSingIn.html"
adminHTML = "https://kachitoridaics3733groupproject.s3.us-east-2.amazonaws.com/html/admin.html";
function processResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var computation = js["result"];

    // Update computation result
    document.createChoice.result.value = computation;
	console.log(computation);
}

function handleCreateClick(e) {
    //document.getElementById("result").value = "This is";
    //document.createChoice.result.value = "success";
    var choiceID = "newChoice"
    var numAlt = document.createChoice.numAlt.value;
    var numMember = document.createChoice.numMember.value;
    var description = document.createChoice.description.value;

	var alternative  = [];
	alternative.push("alt descirption 1");
	alternative.push("alt descirption 2");
	alternative.push("alt descirption 3");
	alternative.push("alt descirption 4");
    
var data = {};

    data["choiceID"] = choiceID;
    data["limitMember"] = numMember;
    data["numAlt"] = numAlt;
    data["description"] = description;

	data["alternative"] = alternative;
	

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();

}

