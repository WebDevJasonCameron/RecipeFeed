"use strict";

function submitForm(event){

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm").value;

    if(!password.match(confirmPassword)){
        event.preventDefault();
        alert("Passwords do not match");
    }

    if(username === ""){
        event.preventDefault();
        alert("Please enter a username")
    }

    if(email === ""){
        event.preventDefault();
        alert("Please enter a email")
    }

    if(password === ""){
        event.preventDefault();
        alert("Please enter a password")
    }
}