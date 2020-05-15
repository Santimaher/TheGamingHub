function busca() {
	clear();
	var aguja = document.getElementById('busca').value;
	var res = getJSON();
	var arr = res[0].plataformas;
	for (let consola of arr) {
		if(aguja == consola.familia){
			// document.getElementById('qwe').innerHTML += '<button
			// onclick="alert(this.value+"/"+this.id)" id="'+consola.id+'">' +
			// consola.nombre+ "</button>";
			var boton = document.createElement("button");
			boton.id = consola.id;
			var texto=document.createTextNode(consola.nombre);
			boton.append(texto);
			boton.setAttribute("onclick",'anadir("'+consola.nombre+'",this.id)');
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
	// document.getElementById('123').innerHTML = '<div class="chip"
	// name="idPlataforma[]" value="'+id+'">'nombre'<i class="close
	// material-icons">close</i></div>'
	var divo = document.createElement("div");	
	divo.classList.add("chip");
	var texto2 = document.createTextNode(nombre);
	divo.append(texto2);
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