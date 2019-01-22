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

            var select = document.getElementsByName("selectCatalog");

            for(var j=0; j<select.length;++j) {

                for (var i = 0; i < catalogJson.length; ++i) {

                    var option = document.createElement("option");

                    option.text = catalogJson[i].nomeCatalogo;

                    option.id = catalogJson[i].id_catalogo;

                    option.value = catalogJson[i].id_catalogo;

                    console.log(catalogJson);
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
    var currentCatalog= object.options[object.selectedIndex].text;
    var n_catalog= document.getElementById("mod_namecatalog");
    n_catalog.setAttribute("value",""+currentCatalog);
    console.log(object.options[object.selectedIndex].title);

}