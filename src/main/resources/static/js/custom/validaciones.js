function validarN_Partic() {
	 var valido;
	 var n=document.partForm.nombre.value;
	 var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
	 var t=patt.test(n);
	 if(t){
	 valido=true;
	 }else{
		 
	 M.toast({html: 'El formato del nombre no es correcto.'}); 
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
	 }else
		M.toast({html: 'El formato de los apellidos no es correcto.'});
	 document.partForm.apellido.style.backgroundColor='red';
	 valido=false;
	 }

	 return valido;
	 }

	 function validarC_Partic(){
	 var valido;
	 var c=document.partForm.idCat.value;
	 if(c!=null){
	 valido=true;
	 }else{
	 M.toast({html: 'Debes seleccionar una categoría.'});
	 valido=false;
	 }
	 return valido;
	 }

	 function vfP(){
	 if(validarN_Partic() && validarA_Partic()&& validarC_Partic()){
	
	 return true;
	 }else{
		 M.toast({html: 'El formato introducido en los campo de texto no es el correcto.'});
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
													    
