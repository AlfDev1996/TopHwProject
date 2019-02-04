function validatePass() {


    var reg = /^[a-zA-Z0-9!@#$%^&*]{5,16}$/;
    if (document.getElementById("password").value.match(reg)) {

        return true;
    }else {
        alert("La Password non rispetta il formato. \n La lunghezza deve essere compresa tra 5 e 16 caratteri");
        return false;

    }

}


function validateName() {


    var reg = /^[a-zA-Z ]{3,30}$/;
    if (document.getElementById("name").value.match(reg)) {

        return true;
    }else {
        alert("Il nome non rispetta il formato. \n La lunghezza deve essere compresa tra 3 e 30 caratteri");
        return false;

    }

}


function validateEmail() {


    var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (document.getElementById("email").value.match(reg)) {

        return true;
    }else {
        alert("e-mail non valida");
        return false;

    }

}

function validateAddress(){

}


