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

                option.id=marcheJson[i].id_categoria;

                select.add(option);





            }

        }

    }

    xh.open("GET","ServletBrandFindAll",true);

    xh.send();





}