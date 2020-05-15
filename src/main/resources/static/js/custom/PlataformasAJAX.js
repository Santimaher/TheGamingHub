window.arr=null
$(document).ready(function(){
	$('#busca').on("change",function(){
		var aguja = $('#busca').val();
		$.getJSON( "/js/custom/plataformas.json", function( data ) {	
			window.arr = data;
				
			
			
		});
		});
	});	
document.getElementById('expositor').innerHTML = "<p>"+window.arr+"</p>" ;