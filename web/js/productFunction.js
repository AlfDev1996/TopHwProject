function loadMarche(){


    var xh= new XMLHttpRequest;

    xh.onreadystatechange=function(){



        if(xh.readyState==4 && xh.status==200){

            var response=xh.responseText;

            var marcheJson= JSON.parse(response);

            var select = document.getElementById("selectMarca");



            for(var i=0; i<marcheJson.length; ++i)

            {

                var option = document.createElement("option");

                option.text = marcheJson[i].nome;

                option.value= marcheJson[i].id_marca;

                option.id=marcheJson[i].id_marca;

                select.add(option);


            }

        }

    }

    xh.open("GET","ServletBrandFindAll",true);

    xh.send();


}



function loadCatalog(){


    var xh= new XMLHttpRequest;

    xh.onreadystatechange=function(){



        if(xh.readyState==4 && xh.status==200){

            var response=xh.responseText;

            var catalogJson= JSON.parse(response);
            console.log(catalogJson);
            var select = document.getElementsByName("selectCatalog");



            for(var j=0; j<select.length;++j) {

                for (var i = 0; i < catalogJson.length; ++i) {

                    var option = document.createElement("option");

                    option.text = catalogJson[i].nomeCatalogo;

                    option.id = catalogJson[i].id_catalogo;

                    option.value = catalogJson[i].id_catalogo;


                    option.setAttribute("title", catalogJson[i].sconto );
                    select[j].add(option);


                }
            }
        }

    }

    xh.open("GET","ServletCatalogFindAll",true);

    xh.send();


}


function updateField(object){
    var idCatalogo= object.options[object.selectedIndex].id;
    var n_catalog= document.getElementById("mod_namecatalog");
    var desc_catalogo = document.getElementById("mod_descrizionecatalogo");
    var sconto_catalogo= document.getElementById("modscontocatalogo");
    var nomeCatalogo ="";
    var descCatalog="";
    var sconto="";

    var xh= new XMLHttpRequest;

    xh.onreadystatechange=function(){



        if(xh.readyState==4 && xh.status==200){

            var response=xh.responseText;

            var catalogJson= JSON.parse(response);






                for (var i = 0; i < catalogJson.length; ++i) {

                    if(idCatalogo== catalogJson[i].id_catalogo){
                            if(catalogJson[i].nomeCatalogo != null && catalogJson[i].nomeCatalogo != undefined )
                            nomeCatalogo=catalogJson[i].nomeCatalogo;
                            console.log( catalogJson[i].descrizioneCatalogo);


                        if(catalogJson[i].descrizioneCatalogo != null && catalogJson[i].descrizioneCatalogo != undefined )
                            descCatalog= catalogJson[i].descrizioneCatalogo;
                        if(catalogJson[i].sconto != null && catalogJson[i].sconto != undefined )
                            sconto = catalogJson[i].sconto;
                            n_catalog.setAttribute("value",""+nomeCatalogo);
                            desc_catalogo.value= descCatalog;
                            sconto_catalogo.setAttribute("value", sconto);
                    }




                }

        }

    }

    xh.open("GET","ServletCatalogFindAll",true);

    xh.send();



}