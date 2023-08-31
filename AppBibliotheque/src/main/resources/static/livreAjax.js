

// Create an XMLHttpRequest object
var xmlhttp = new XMLHttpRequest()
// Define a callback function
xmlhttp.onload = function() {
let data = JSON.parse(this.responseText)
//	console.log(data)
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
			for(let livre of data){
				let row = bodyElt.insertRow(-1);
				(row.insertCell(0)).innerHTML = livre.idLivre;
				(row.insertCell(1)).innerHTML = livre.auteur;
				(row.insertCell(2)).innerHTML = livre.editeur;
				(row.insertCell(3)).innerHTML = livre.titre;
				(row.insertCell(3)).innerHTML = livre.titre;
				(row.insertCell(3)).innerHTML = livre.titre;
				(row.insertCell(3)).innerHTML = livre.titre;
				(row.insertCell(3)).innerHTML = livre.titre;
			}
}
// Send a request
xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre")
xmlhttp.send()



