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

// ///////////participante c u
 function validarN_Partic() {
 var valido;
 var n=document.partForm.nombre.value;
 var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
 var t=patt.test(n);
 if(t){
 valido=true;
 }else{
 alert("El formato del nombre no es correcto.");
 document.partForm.nombre.style.backgroundColor='red';
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
 document.partForm.apellido.style.backgroundColor='red';
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
 M.toast({html: 'Debes seleccionar una categoría.'});
 valido=false;
 }
 return valido;
 }

 function vfP(){
 if(validarN_Partic() && validarA_Partic()&& validarC_Partic()){
 M.toast({html: 'Funciona.'});
 return true;
 }else{
 alert("El formato introducido en los campo de texto no es el correcto.");
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
										alert("El formato introducido en los campo de texto no es el correcto.");
										return false;
									}
								}
								
// /////////// usuario c uUser
							
								
								function validarN_User(){
									var valido;
										 var n=document.userform.nombre.value;
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
									
									function validarE_User(){
										var valido;
										        var pa=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
										        var e=document.userform.email.value;
										        var t=pa.test(e);
														if(t){
															valido=true;
														}else{
															alert("El formato del correo electrónico no es correcto.");
															valido=false;
														}
									
										       return valido;
										      }
									
									function validarN_Usuario()	{
										var valido;
										 var n=document.userform.pass.value;
									    var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ0-9 ][4,30}$");
									    var t=patt.test(n);
											if(t){
												valido=true;
											}else{
												alert("El mín de caracteres para la contraseña son 4 y el máximo 30.");
												valido=false;
											}
									    return valido;
									    }
										    function vfU(){
												if(validarN_User() && validarE_User() && validarN_Usuario()){
															return true;
												}else{
													alert("El formato introducido en los campo de texto no es el correcto.");
													return false;
												}
											}
											
										  // //////////////////////////////

											function validarNameupdate_User(){
												var valido;
													 var n=document.userform.nombre.value;
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
												
												function validarEmailupdate_User(){
													var valido;
													        var pa=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
													        var e=document.userform.email.value;
													        var t=pa.test(e);
																	if(t){
																		valido=true;
																	}else{
																		alert("El formato del correo electrónico no es correcto.");
																		valido=false;
																	}
												
													       return valido;
													      }
												
												
												    return valido;
												    }
													    function vformupdateUser(){
															if(validarN_User() && validarE_User()){
																		return true;
															}else{
																alert("El formato introducido en los campo de texto no es el correcto.");
																return false;
															}
														}				    
							         // ///////////////////////////////////////////////
													    
