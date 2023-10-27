var riceamount;
var people;
var dal;
var curry;
var rcup;
var water;
function auth(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if (email === "mihir.motukuri@Gmail.com" && password === "1234"){
        window.location.replace("ricepage.html");

    }
    else {
        alert("Invalid. Please try again");
        return;
    }

}