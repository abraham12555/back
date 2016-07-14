/*
* Funciones Login Google+
*/
var googleUser = {};
var initGoogle = function() {
	gapi.load('auth2', function(){
		// Retrieve the singleton for the GoogleAuth library and set up the client.
		auth2 = gapi.auth2.init({
			client_id: '327700176153-eekh64q51h9aagjv9lin4qaq53hbnm6n.apps.googleusercontent.com',
			cookiepolicy: 'single_host_origin',
			// Request scopes in addition to 'profile' and 'email'
			//scope: 'additional_scope'
		});
		attachSignin(document.getElementById('customBtn'));
	});
   };
function attachSignin(element) {
	console.log(element.id);
    auth2.attachClickHandler(element, {},
		function(googleUser) {
			//document.getElementById('name').innerText = "Signed in: " + googleUser.getBasicProfile().getName();
			$("#datos_google").val(JSON.stringify(googleUser.getBasicProfile()));
			$("#form_paso1").submit();
			}, 
		function(error) {
			alert(JSON.stringify(error, undefined, 2));
		}
	);
}
