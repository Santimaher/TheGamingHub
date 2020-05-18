function busca() {
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
			boton.setAttribute("onclick",'anadir("'+consola.nombre+'",'+consola.id+')');
			document.getElementById('qwe').append(boton);
			
			}		
	}
	}
function clear(){
	document.getElementById('qwe').innerHTML = "";
}		
	



function getJSON() {
	var resp = [];
	$.ajax({
		url : '/js/custom/plataformas.txt',
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
		divo.classList.add("chip");
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
		document.getElementById('123').append(divo);
	}
	
}
function esta(nombre){
	//document.getElementsByName("consolas")[0].innerText
	var check = false;
	var seleccionados = document.getElementsByName("consolas");
	for (let unidad of seleccionados) {
		if (unidad.innerText == nombre) {
			check=true;
		}
	}
	return check
}
