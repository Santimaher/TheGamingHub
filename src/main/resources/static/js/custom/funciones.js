function buscar() {
	clear();
	var aguja = document.getElementById('busca').value;
	var res = getJSON();
	var arr = res[0].plataformas;
	for (let consola of arr) {
		if(aguja == consola.familia){
			var boton = document.createElement("button");
			boton.id = "btn-"+consola.id;
			var texto=document.createTextNode(consola.nombre);
			boton.append(texto);
			boton.type="button";
			boton.setAttribute("onclick",'anadir("'+consola.nombre+'",'+consola.id+')');
			boton.setAttribute("class",'btn waves-effect waves-light purple');
			document.getElementById('exp').append(boton);
			
		
			}		
	}
	}
function clear(){
	document.getElementById('exp').innerHTML = "";
}		
	
function confirmar(){
	if(confirm('¿Estas seguro de que desea borrar este elemento:?')){
		return true;
	}
	else{
		return false
	}
}


function getJSON() {
	var resp = [];
	$.ajax({
		url : 'https://thegaminghub.herokuapp.com/js/custom/plataformas.txt',
		type : 'GET',
		dataType : 'json',
		async : false,
		success : function(data) {
			resp.push(data);
		},
		error : function(req, err) {
			console.log(err);
		}
	})
	return resp;
}

function anadir(nombre,id){
	if(esta(nombre)){
		alert("La consola: "+nombre+", ya esta seleccionada");
	}
	
	else{
		var divo = document.createElement("div");	
		divo.setAttribute("class","chip purple white-text");
		var contTexto = document.createElement("span");
		contTexto.setAttribute("name","consolas");
		var texto2 = document.createTextNode(nombre);
		contTexto.append(texto2);
		divo.append(contTexto);
		var oculto = document.createElement("input");
		oculto.type="hidden";
		oculto.name="idPlataforma[]";
		oculto.value=id;
		divo.append(oculto);
		var cerrar = document.createElement("i");
		cerrar.classList.add("close");
		cerrar.classList.add("material-icons");	 
		var texto3 = document.createTextNode("close");
		cerrar.append(texto3);
		divo.append(cerrar);
		document.getElementById('selec').append(divo);
	}
	
}
function esta(nombre){
	var check = false;
	var seleccionados = document.getElementsByName("consolas");
	for (let unidad of seleccionados) {
		if (unidad.innerText == nombre) {
			check=true;
		}
	}
	return check
}


	
			