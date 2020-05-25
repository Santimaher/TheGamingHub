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
function validarJC(){
	
	
}
/////////////participante c u
function validarN_Partic()	{
	var valido;
	 var n=document.partForm.nombre.value;
    var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
    var t=patt.test(n);
		if(t){
			valido=true;
		}else{
			alert("El formato del nombre no es correcto.");
			valido=false;
		}
    return valido;
    }

function validarA_Partic(){
	var valido;
	        var pa=/^[A-Z]{1}[a-zA-ZñÑçÇáéíóú ]{2,40}$/;
	        var a=document.partForm.apellido.value;
	        var t=pa.test(a);
					if(t){
						valido=true;
					}else{
						alert("El formato de los apellidos no es correcto.");
						valido=false;
					}

	       return valido;
	      }

				function validarC_Partic(){
					var valido;
					var c=document.partForm.idCat.value;
				     if(c==null){
				    	 valido=true;
				     }else{
							 alert("Debes elegir una categoría.");
				    	 valido=false;
				     }
				     return valido;
				     }

				function vfP(){
					if(validarN_Partic() && validarA_Partic()&& validarC_Partic()){
								return true;
					}else{
						return false;
					}
				}
				
				function validarN_ParticU()	{
					var valido;
					 var n=document.partFormu.nombre.value;
				   		var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
				  		 var t=patt.test(n);
						if(t){
							valido=true;
						}else{
							alert("El formato del nombre no es correcto.");
							valido=false;
						}
				   return valido;
				   }

					function validarA_ParticU(){
						var valido;
					        var pa=/^[A-Z]{1}[a-zA-ZñÑçÇáéíóú ]{2,40}$/;
					        var a=document.partFormu.apellido.value;
					        var t=pa.test(a);
									if(t){
										valido=true;
									}else{
										alert("El formato de los apellidos no es correcto.");
										valido=false;
									}

					       return valido;
					      }


								function vfPu(){
									if(validarN_ParticU() && validarA_ParticU()){
												return true;
									}else{
										return false;
									}
								}
