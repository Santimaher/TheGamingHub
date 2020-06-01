function confirmarBorrado(x) {
	return confirm("¿Estas seguro de que desea borrar este elemento : "+x+"?");
}
function validarN_Partic() {
	 var valido=false;
	 var n=document.partForm.nombre.value;
	 var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
	 var t=patt.test(n);
	 if(t){
	 valido=true;
	 }else{
		 
	 M.toast({html: 'El formato del nombre no es correcto.'}); 
	 document.partForm.nombre.style.color='red';
	 
	 }
	 return valido;
	 }

	 function validarA_Partic(){
	 var valido=false;
	 var pa=/^[A-Z]{1}[a-zA-ZñÑçÇáéíóú ]{2,40}$/;
	 var a=document.partForm.apellido.value;
	 var t=pa.test(a);
	 if(t){
	 valido=true;
	 }else{
		M.toast({html: 'El formato de los apellidos no es correcto.'});
	 document.partForm.apellido.style.color='red';
	 }

	 return valido;
	 }

	 function validarC_Partic(){
	 var valido=false;
	 var c=document.partForm.idCat.value;
	 if(c!=null){
	 valido=true;
	 }else{
	 M.toast({html: 'Debes seleccionar una categoría.'});
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
// ///////////////////////
function validarN_ParticU()	{
					var valido=false;
					 var n=document.partFormu.nombre.value;
				   		var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
				  		 var t=patt.test(n);
						if(t){
							valido=true;
						}else{
							 M.toast({html: 'El formato del nombre no es correcto.'});
							 document.partFormu.nombre.style.color='red';
							
						}
				   return valido;
				   }

					function validarA_ParticU(){
						var valido=false;
					        var pa=/^[A-Z]{1}[a-zA-ZñÑçÇáéíóú ]{2,40}$/;
					        var a=document.partFormu.apellido.value;
					        var t=pa.test(a);
									if(t){
										valido=true;
									}else{
										M.toast({html: 'El formato de los apellidos no es correcto'});
										document.partFormu.apellido.style.color='red';
									
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
								
// /////////// usuario c uUser
							
								
								function validarN_User(){
									var valido=false;
										 var n=document.userform.nombre.value;
									    var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
									    var t=patt.test(n);
											if(t){
												valido=true;
											}else{
												M.toast({ html: 'El formato del nombre no es correcto.'});
												document.userform.nombre.style.color='red';
																							}
									    return valido;
									    }
									
									function validarE_User(){
										var valido=false;
										   var pa=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
										   var e=document.userform.email.value;
										   var t=pa.test(e);
												if(t){
													valido=true;
														}else{
															document.userform.email.style.color='red';
															M.toast({html:'El formato del correo electrónico no es correcto.'});
														}														
									
										       return valido;
										      }
									
									function validarPwd_Usuario()	{
										var valido=false;
										 var pass=document.userform.pass.value;
										 var patt1=/^[a-zA-Z0-9]{8,20}$/;
									      var patt2=/[A-Z]{1,20}/
									      var patt3=/[0-9]{1,20}/
									      console.log(patt1.test(pass)&&patt2.test(pass)&&patt3.test(pass));
										
									    var t=(patt1.test(pass)&&patt2.test(pass)&&patt3.test(pass));
											if(t){
												valido=true;
											}else{
												M.toast({html:'El mínimo de carácteres para la contraseña son 8 y el máximo 20. Además es obligatorio el uso de mayúsculas y números.'});
												document.userform.pass.style.color='red';
											
											}
									    return valido;
									    }
										    function vfU(){
												if(validarN_User() && validarE_User() && validarPwd_Usuario()){
															return true;
												}else{
													return false;
												}
											}
											
										  // //////////////////////////////

											function validarNameupdate_User(){
												var valido=false;
													 var n=document.userform.nombre.value;
												    var patt = new RegExp("^[a-zA-ZáéíóúñÑçÇ ]{2,30}$");
												    var t=patt.test(n);
														if(t){
															valido=true;
														}else{
															M.toast({html:'El formato del nombre no es correcto.'});
															document.userform.nombre.style.color='red';
																		}
												    return valido;
												    }
												
												function validarEmailupdate_User(){
													var valido=false;
													        var pa=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
													        var e=document.userform.email.value;
													        var t=pa.test(e);
																	if(t){
																		valido=true;
																	}else{
																		M.toast({html:'El formato del correo electrónico no es correcto.'});
																		document.userform.nombre.email.color='red';
																	
																	}
												
													       return valido;
													      }
												
									
													    function vformupdateUser(){
															if(validarN_User() && validarE_User()){
																		return true;
															}else{
																return false;
															}
														}				    
							         // ///////////////////////////////////////////////
									function validarUpdate_Pwd(){
										 var pass=document.passForm.passwordNu.value;
										
										 var patt1=/^[a-zA-Z0-9]{8,20}$/;
									      var patt2=/[A-Z]{1,20}/
									      var patt3=/[0-9]{1,20}/
									      console.log(patt1.test(pass)&&patt2.test(pass)&&patt3.test(pass));
									    var t=(patt1.test(pass)&&patt2.test(pass)&&patt3.test(pass));
									    
											if(t){
											    return true;
											}else{
									     		M.toast({html:'El mínimo de carácteres para la contraseña son 8 y el máximo 20. Además es obligatorio el uso de mayúsculas y números.'});
												document.passForm.passwordNu.style.color='red';
																return false;
																}
														
														    }    
