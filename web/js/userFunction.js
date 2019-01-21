function enableMod(btn){
    btn.setAttribute("style","display:none;");
    var save= document.getElementsByName("saveButton");
    for(var i = 0; i < save.length; i++) {
        save[i].style.display = "inline";
    }
    var inputs = document.getElementsByTagName('input');

    for(var i = 0; i < inputs.length; i++) {
        if(inputs[i].type.toLowerCase() == 'text'|| inputs[i].type.toLowerCase() == 'password' || inputs[i].type.toLowerCase() == 'number') {
            inputs[i].removeAttribute("readonly");
        }

    }
}


function save(salva){

    salva.setAttribute("style","display:none;");
    var mod= document.getElementById("mod");
    mod.setAttribute("style","display:inline;");

    var jsonObj= {
        "nome" : "",
        "cognome" : "",
        "email" : "",
        "password" : ""
    };

    var inputs = document.getElementsByTagName('input');

    for(var i = 0; i < inputs.length; i++) {
        if(inputs[i].type.toLowerCase() == 'text' || inputs[i].type.toLowerCase() == 'password')
        {

            if(inputs[i].id=="modnome")
                jsonObj.nome=inputs[i].value;
            if(inputs[i].id=="modcognome")
                jsonObj.cognome=inputs[i].value;
            if(inputs[i].id=="modemail")
                jsonObj.email=inputs[i].value;
            if(inputs[i].id=="modpassword")
                jsonObj.password=inputs[i].value;


        }




    }


    var xh= new XMLHttpRequest;
    xh.onreadystatechange=function(){

        if(xh.readyState==4 && xh.status==200){

            location.reload(true);

        }
    }
    var x  = JSON.stringify(jsonObj);

    xh.open("GET","ServletUpdateUser?utenteJs="+encodeURIComponent(x),true);
    xh.send();
    console.log(jsonObj);
    for(var i = 0; i < inputs.length; i++) {
        if(inputs[i].type.toLowerCase() == 'text'|| inputs[i].type.toLowerCase() == 'password' || inputs[i].type.toLowerCase() == 'number') {
            inputs[i].setAttribute("readonly","readonly");
        }

    }
}


function saveAddress(salva){

    salva.setAttribute("style","display:none;");
    var mod= document.getElementById("modIndirizzo");

        mod.setAttribute("style", "display:inline;");

    var jsonObj= {
        "via" : "",
        "civico" : 0,
        "comune" : "",
        "provincia" : "",
        "cap": 0,
        "nazione" : "",
        "id_utente":0
    };

    var inputs = document.getElementsByTagName('input');

    for(var i = 0; i < inputs.length; i++) {
        if(inputs[i].type.toLowerCase() == 'text' || inputs[i].type.toLowerCase() == 'password'  || inputs[i].type.toLowerCase() == 'hidden')
        {

            if(inputs[i].id=="modvia")
                jsonObj.via=inputs[i].value;
            if(inputs[i].id=="modcivico")
                jsonObj.civico=inputs[i].value;
            if(inputs[i].id=="modcomune")
                jsonObj.comune=inputs[i].value;
            if(inputs[i].id=="modprovincia")
                jsonObj.provincia=inputs[i].value;
            if(inputs[i].id=="modcap")
                jsonObj.cap=inputs[i].value;
            if(inputs[i].id=="modnazione")
                jsonObj.nazione=inputs[i].value;
            if(inputs[i].id=="id_utente")
                jsonObj.id_utente=inputs[i].value;

        }

    }
    console.log(jsonObj);

    var xh= new XMLHttpRequest;
    xh.onreadystatechange=function(){

        if(xh.readyState==4 && xh.status==200){

            location.reload(true);

        }
    }
    var x  = JSON.stringify(jsonObj);

    xh.open("GET","ServletUpdateAddressUser?addressJs="+encodeURIComponent(x),true);
    xh.send();

    for(var i = 0; i < inputs.length; i++) {
        if(inputs[i].type.toLowerCase() == 'text'|| inputs[i].type.toLowerCase() == 'password' || inputs[i].type.toLowerCase() == 'number') {
            inputs[i].setAttribute("readonly","readonly");
        }

    }

}