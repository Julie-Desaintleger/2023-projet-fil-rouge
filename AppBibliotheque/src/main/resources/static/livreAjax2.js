

// Create an XMLHttpRequest object
var xmlhttp = new XMLHttpRequest()
// Define a callback function
xmlhttp.onload = function() {
let data = JSON.parse(this.responseText)
//	console.log(data)
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
			for(let x of data){
				let row = bodyElt.insertRow(-1);
				(row.insertCell(0)).innerHTML = x.idLivre;
				(row.insertCell(1)).innerHTML = x.auteur;
				(row.insertCell(2)).innerHTML = x.editeur;
				(row.insertCell(3)).innerHTML = x.titre;
			}
}
// Send a request
xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre")
xmlhttp.send()



