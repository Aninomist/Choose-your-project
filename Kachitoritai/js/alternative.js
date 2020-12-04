function processAlternative(ListOfAlt) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);
    var alternatives = js["alternatives"];
    var length = alternatives.length;
    var alt1  = alternatives[0];
    var alt2  = alternatives[1];
    if (length == 3) {
        var alt3 = alternatives[2];
    }
    if (length == 4) {
        var alt3 = alternatives[2];
        var alt4  = alternatives[3];
    }
    if (length == 5) {
        var alt3 = alternatives[2];
        var alt4  = alternatives[3];
        var alt5  = alternatives[4];
    }



    document.alternatives.alt1.value = alt1["description"];
    document.alternatives.alt2.value = alt2["description"];

    if(length>=3) {
        document.alternatives.alt3.value = alt3["description"];
    }
    if(length>=4) {
        document.alternatives.alt4.value = alt4["description"];
    }
    if(length>=5) {
        document.alternatives.alt5.value = alt5["description"];
    }
    //document.alternatives.alt4.value = alt4["description"];
    //document.alternatives.alt5.value = alt5["description"];
    // Update computation result
    //document.createChoice.result.value = computation;
}