function livre() {

	// Create an XMLHttpRequest object
	var xmlhttp = new XMLHttpRequest()
	// Define a callback function
	xmlhttp.onload = function() {
		let data = JSON.parse(this.responseText)
		//	console.log(data)
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML = "";//vider le tableau avant de le re-remplir
		for (let livre of data) {
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
function exemplaire() {

	// Create an XMLHttpRequest object
	var xmlhttp = new XMLHttpRequest()
	// Define a callback function
	xmlhttp.onload = function() {
		let data = JSON.parse(this.responseText)
		console.log(data)
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML = "";//vider le tableau avant de le re-remplir
		for (let exemp of data) {
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = exemp.idExemp;
			(row.insertCell(1)).innerHTML = exemp.livre.titre;
			(row.insertCell(2)).innerHTML = exemp.etat;
			(row.insertCell(3)).innerHTML = exemp.isDisponibilite;
			let link = document.createElement("a");
			link.href = "formulaireReservation.html?id=" + exemp.idExemp + "&disponibilite=" + exemp.isDisponibilite;
			let reserverButton = document.createElement("button");
			reserverButton.textContent = "Reserver";
			link.appendChild(reserverButton);
			row.insertCell(4).appendChild(link);
		}
	}
	// Send a request
	xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre/exemp")
	xmlhttp.send()

}

/*

function reserver() {

	const id = null;
	var idExemp = parseInt(document.getElementById("idexemp").value);
	var datedebut = document.getElementById("datedebut").value;
	var datefin = document.getElementById("datefin").value;
	var idlecteur = parseInt(document.getElementById("idlecteur").value);
	var reservationType = document.getElementById("reservationType").value;
	
	console.log("////////////////////" + isDisponibilite);
	var formData = JSON.stringify({
			"id": id,
			"dateDebut": datedebut,
			"dateFin": datefin,
			"type": reservationType,
			"idExemp": idExemp,
			"idLecteur": idlecteur,
		});
	console.log(formData);
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.withCredentials = true;
	xmlhttp.addEventListener("readystatechange", function() {
		if (this.readyState === 4) {
			console.log(this.responseText);
		}
	});
	xmlhttp.open("POST", "http://localhost:8080/AppBibliotheque/api-emprunts/emprunt");
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send(formData);
}


*/



