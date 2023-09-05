

function connecter() {
	var loginForm = document.getElementById("login-form");

	var username = loginForm.elements["utilisateur"].value;
	var password = loginForm.elements["mdp"].value;


	console.log(username);
	console.log(password);

	let loginRequestJs = {
		email: username,
		password: password
	};
	let loginRequestJson = JSON.stringify(loginRequestJs);
	let wsUrl = "./api-personnes/personne/login";
	makeAjaxPostRequest(wsUrl, loginRequestJson, function(responseJson) {
		console.log("responseJson=" + responseJson);
		let loginResponseJs = JSON.parse(responseJson);
		if (loginResponseJs.isConnected) {
			if (loginResponseJs.role == "Lecteur") {				
				window.location.replace("http://localhost:8080/AppBibliotheque/livreAjax.html")
			} else if (loginResponseJs.role == "Administrateur") {
				window.location.replace("http://localhost:8080/AppBibliotheque/livreAjaxAdmin.html")
			}
		} else {

		}
	}, errorFunc());

}

function errorFunc() {
	var loginErrorMsg = document.getElementById("loginErrorMsg");
	var msg = "L'email/username et/ou mot de passe incorrect(s)";
	loginErrorMsg.style.opacity = 1;
	loginErrorMsg.className = "alert alert-danger";
	loginErrorMsg.innerHTML = msg;
}