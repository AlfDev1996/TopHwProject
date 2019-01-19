function enableMod(btn){
    btn.setAttribute("style","display:none;");
    var save= document.getElementById("save");
    save.style.display="inline";
    var inputs = document.getElementsByTagName('input');

    for(var i = 0; i < inputs.length; i++) {
        if(inputs[i].type.toLowerCase() == 'text'|| inputs[i].type.toLowerCase() == 'password') {
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
    console.log(jsonObj);

    var xh= new XMLHttpRequest;
    xh.onreadystatechange=function(){

        if(xh.readyState==4 && xh.status==200){

            location.reload(true);

        }
    }
    var x  = JSON.stringify(jsonObj);

    xh.open("GET","ServletUpdateUser?utenteJs="+encodeURIComponent(x),true);
    xh.send();


}