function busca() {
	clear();
	var aguja = document.getElementById('busca').value;
	var res = getJSON();
	var arr = res[0].plataformas
	for (let consola of arr) {
		if(aguja == consola.familia){
			document.getElementById('qwe').innerHTML += '<button value="'+consola.id+'">' + consola.nombre
			+ "</button>";}
		
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
