"use strict";

function validatePassword(){
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm").value;
    console.log(password);
    console.log(confirmPassword);

    if(password !== confirmPassword){
        alert("Passwords do not match.")
        document.getElementById("submit").setAttribute("disabled", "disabled")
    }
}