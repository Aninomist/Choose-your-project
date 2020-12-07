function handleVoteClick(number, flag, username){

    console.log("alt#:" + number);
    console.log("flag:" + flag);

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
                data["isUpVote"] = flag;
                js = JSON.stringify(data);

                xhr = new XMLHttpRequest();
                xhr.open("POST", vote_url, true);
                xhr.send(js);

                xhr.onloadend = function () {
                    console.log(xhr);
                    console.log(xhr.request);
                    if (xhr.readyState == XMLHttpRequest.DONE) {

                        var responseJs = JSON.parse(xhr.responseText);
                        //choiceID = responseJs.response;
                        reponseCode = responseJs.httpCode;

                        if (reponseCode == 200) {
                            getAlternative(choiceID);
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
