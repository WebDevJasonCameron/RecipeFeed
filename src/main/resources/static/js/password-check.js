"use strict";

function submitForm(event){
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm").value;

    if(!password.match(confirmPassword)){
        event.preventDefault();
        alert("Passwords do not match");
    }

    if(password.length !== 6){
        event.preventDefault();
        alert("Password must be longer than 6 characters");
    }
}

function deleteCheck(event){
    const deleteButton = document.getElementById("delete");
    const message = confirm("Are you sure you want to delete your account?");

        if(message === false){
            event.preventDefault();
        }

}