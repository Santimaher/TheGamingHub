
function getJSON() {
	  var resp = [];
	 $.ajax({
	  url: '/js/custom/plataformas.txt',
	  type: 'GET',
	  dataType: 'json',
	  async: false,
	  success : function(data) {
	    console.log(data);
	    resp.push(data);
	    for ( var consola in data.plataformas) {
			$("#expositor").append("<button>"+consola.nombre+"</button>");
		}
	  }, error : function(req, err) {
	      console.log(err);
	  }
	})
	console.log(resp);
	return resp;
	}

getJSON();