"use strict";

function submitForm(event){
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm").value;

    if(!password.match(confirmPassword)){
        event.preventDefault();
        alert("Passwords do not match");
    }
}