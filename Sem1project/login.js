const loginform = document.getElementById("loginform");
const loginbutton = document.getElementById("loginsubmit");
const loginerror = document.getElementById("loginer");



loginbutton.addEventListener("click", (e) =>
{
    e.preventDefault();
    const username = loginform.username.value;
    const password = loginform.password.value;

    if(username === "Mihir" && password === "1234"){
        alert("Nice job, you've logged in");
        location.reload;
    }
    else {
        loginerror.style.opacity = 1;
    }
})
