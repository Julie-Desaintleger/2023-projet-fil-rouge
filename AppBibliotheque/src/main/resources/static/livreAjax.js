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
			link.href = "formulaireReservation.html?id=" + exemp.idExemp + "&disponibilite=" + (exemp.isDisponibilite ? 'succes' : 'echec' );
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



function livreWithButtunAdd() {

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

			let link = document.createElement("a");
			link.href = "formulaireAjouterExemplaire.html?idLivre=" + livre.idLivre;
			let ajouterButton = document.createElement("button");
			ajouterButton.textContent = "Ajouter";
			link.appendChild(ajouterButton);
			row.insertCell(6).appendChild(link);
		}
	}
	// Send a request
	xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre")
	xmlhttp.send()

}

function exemplaireAdmin() {

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

			// Créer un conteneur (div) pour les boutons "Modifier" et "Supprimer"
			let actionContainer = document.createElement("div");

			let linkModifier = document.createElement("a");
			linkModifier.href = "formulaireModifierExemplaire.html?id=" + exemp.idExemp + "&etat="+ exemp.etat + "&disponibilite=" + exemp.isDisponibilite  +  "&label=" + exemp.label  + "&idLivre="+ exemp.livre.idLivre ; 
			let modifierButton = document.createElement("button"); 
			modifierButton.textContent = "Modifier";
			linkModifier.appendChild(modifierButton);

			let linkSupprimer = document.createElement("a");
			let supprimerButton = document.createElement("button");
			supprimerButton.textContent = "Supprimer";
			supprimerButton.addEventListener("click", function() {
			Supprimerexemplaire(exemp.idExemp,exemp.etat,(exemp.isDisponibilite ? 'succes' : 'echec' ));
			});
			linkSupprimer.appendChild(supprimerButton);

			// Ajouter les liens "Modjeifier" et "Supprimer" au conteneur
			actionContainer.appendChild(linkModifier);
			actionContainer.appendChild(linkSupprimer);

			// Insérer le conteneur d'action dans la cellule "Action"
			let celluleAction = row.insertCell(4);
			celluleAction.appendChild(actionContainer);
		}

		}
	// Send a request
	xmlhttp.open("GET", "http://localhost:8080/AppBibliotheque/api-livres/livre/exemp")
	xmlhttp.send()

}




function Supprimerexemplaire(idExemp,etat,isDisponibilite) {
	
	if(etat == "HORS_SERVICE" || isDisponibilite == "succes" ){
	
		var data = "";	
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;	
		xhr.addEventListener("readystatechange", function() {
		  if(this.readyState === 4) {
		    console.log(this.responseText);
		  }
		});
		
		xhr.open("DELETE", "http://localhost:8080/AppBibliotheque/api-livres/livre/exemplaire/" + idExemp);
		xhr.send(data);
		alert("L'exemplaire a été supprimé avec succès");
		setTimeout(function(){ window.location = 'exemplaireAjaxAdmin.html'; }, 0000);
		
	}
	else{
		alert("Impossible de supprimer cet exemplaire car il est actuellement emprunté par un lecteur.");
		setTimeout(function(){ window.location = 'exemplaireAjaxAdmin.html'; }, 0000);
	}

	
	}
	



