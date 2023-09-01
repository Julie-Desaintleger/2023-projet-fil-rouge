function livre(){

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
				(row.insertCell(4)).innerHTML = livre.domaine.nom;
				(row.insertCell(5)).innerHTML = livre.domaine.description;
				
			}
}
// Send a request
xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre")
xmlhttp.send()

}
function exemplaire(){

// Create an XMLHttpRequest object
var xmlhttp = new XMLHttpRequest()
// Define a callback function
xmlhttp.onload = function() {
let data = JSON.parse(this.responseText)
	console.log(data)
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
			for(let exemp of data){
				let row = bodyElt.insertRow(-1);
				(row.insertCell(0)).innerHTML = exemp.idExemp;
				(row.insertCell(1)).innerHTML = exemp.livre.titre;
				(row.insertCell(2)).innerHTML = exemp.etat;
				(row.insertCell(3)).innerHTML = exemp.isDisponibilite;
				(row.insertCell(4)).innerHTML = "<a href='http://localhost:8080/AppBibliotheque/api-emprunts/emprunt/'"+exemp.idExemp+"><button id='reserver_btn'> Reserver </button></a>";
				
			}
}
// Send a request
xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre/exemp")
xmlhttp.send()

}



function reserver(){

// Create an XMLHttpRequest object
var xmlhttp = new XMLHttpRequest()
// Define a callback function
xmlhttp.onload = function() {
let data = JSON.parse(this.responseText)
	console.log(data)
		
						}
}
// Send a request
xmlhttp.open("POST", "http://localhost:8080/AppBibliotheque/api-emprunts/emprunt")
xmlhttp.send()



