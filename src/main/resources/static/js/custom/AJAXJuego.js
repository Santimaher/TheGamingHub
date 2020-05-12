var x;
    if (window.XMLHttpRequest) {
      x = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
      x = new ActiveXObject("Microsoft.XMLHTTP");
    }

function buscar(){
    var conte = document.getElementsByName('expositor')[0].innerHTML="";
      x.open("GET","plataformas.txt",true);
      x.send();
      x.onreadystatechange=tratar;

    }
    function tratar(){
      if (x.readyState==4 && x.status==200) {
        var crudo = x.responseText;
        var objs = JSON.parse(crudo);
        var idem2 = document.getElementsByName("selector")[0].value;
        for(obj of objs.plataformas){
          if (idem2== obj.familia) {
            var conte = document.getElementsByName('expositor')[0];
            var boton = document.createElement("button");
            boton.class="btn-floating btn-large waves-effect waves-light purple";
            boton.appendChild(document.createTextNode(obj.nombre));
            //var texto = document.createTextNode(obj.id+"/"+obj.familia+"/"+obj.img+"/"+obj.nombre);
            conte.appendChild(boton);
          }
        }
      }
    }