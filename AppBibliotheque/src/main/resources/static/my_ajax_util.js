//subfunction with errCallback as optional callback :
function registerCallbacks(xhr, callback, errCallback) {
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if ((xhr.status == 200 || xhr.status == 0)) {
				callback(xhr.responseText);
			}
			else {
				if (errCallback)
					errCallback(xhr.responseText);
			}
		}
	};
}
function makeAjaxGetRequest(url, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("GET", url, true); xhr.send(null);
}
function makeAjaxDeleteRequest(url, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("DELETE", url, true); xhr.send(null);
}
function makeAjaxPostRequest(url, jsonData, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonData);
}
function makeAjaxPutRequest(url, jsonData, callback, errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr, callback, errCallback);
	xhr.open("PUT", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(jsonData);
}


function differenceEntreDeuxDates(date1, date2) {
	// Convertir les dates en objets Date si elles ne le sont pas déjà
	if (!(date1 instanceof Date)) {
		date1 = new Date(date1);
	}
	if (!(date2 instanceof Date)) {
		date2 = new Date(date2);
	}
	//const differenceEnMillisecondes = Math.abs(date1 - date2);
	const differenceEnMillisecondes = date2 - date1;
	const differenceEnJours = differenceEnMillisecondes / (1000 * 60 * 60 * 24);
	return differenceEnJours;
}