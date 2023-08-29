function aficherLivres(){
	
	let wsUrl = "./api-livres/livre";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let livresJs = JSON.parse(responseJson);
		console.log("livresJs="+livresJs);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let livre of livresJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = livre.Reference;
			(row.insertCell(1)).innerHTML = livre.Auteur;
			(row.insertCell(2)).innerHTML = livre.Editeur;
			(row.insertCell(3)).innerHTML = livre.Titre;
		}
	});
	
}