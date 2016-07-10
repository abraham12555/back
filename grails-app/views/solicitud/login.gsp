<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="solicitud"/>
		<script src="../js/jquery-3.0.0.min.js"></script>
		<script src = "https://plus.google.com/js/client:platform.js" async defer></script>
	    <script type="text/javascript">
			$(document).ready(function() {
			  $.ajaxSetup({ cache: true });
			  $.getScript('http://connect.facebook.net/en_US/sdk.js', function(){
				FB.init({
				  appId      : '1132024623508026',
				  xfbml      : true,
				  version    : 'v2.6'
				});     
				$('#loginbutton,#feedbutton').removeAttr('disabled');
				FB.getLoginStatus(updateStatusCallback);
			  });
			});
			function updateStatusCallback(){
			   console.log('Status updated!!');
			}
			function fb_login() {
				FB.login( function() {}, { scope: 'email,public_profile' } );
			}
			function google_login() {
				FB.login( function() {}, { scope: 'email,public_profile' } );
			}
		</script>
    </head>
	<body>
	  <header class="formularioHeader clearFix">
		<div class="container">
		  <div class="center">
			<img class="logo" src="images/kosmos-logo.png" alt="Logo" title="Logo" />
		  </div>
		</div>
	  </header>
	  <section class="container">
		<div class="conatinerWhite550 autoMargin marginTop13">
		  <div class="paddingTop35 marginBottom30">
			<h1 class="center font25 fontWeight500 darkBluetitle">CONECTA TU SOLICITUD</h1>
		  </div>
		  <div class="width505 autoMargin clearFix">
			<div class="borderTopResumen clearFix">
			  <div class="col10 autoMargin">
				<div class="paddingTop18 marginBottom16">
				  <p class="center formTitleColor marginBottom30 font18">
					Conecta tus redes sociales y te ayudamos <br/> a pre-llenar tu formulario. Es mas facil y mas rapido.
				  </p>
				  <div class="width350 facebookColor  marginBottom20 autoMargin clearFix">
					<div class="floatLeft width75 paddingTop5">
					  <img class="happines" src="images/fb-icon.svg" alt="FB logo" title="FacebookLogo" />
					</div>		
					
					<a href="#" onclick="fb_login();">
					<div class="fb-login-button floatLeft borderLeft width274">
					  <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
						CONECTATE CON FACEBOOK
					  </p>
					</div>
					</a>
				  </div>
				  
				  <div class="width350 googleColor  marginBottom30 marginTop15 autoMargin clearFix">
					<div class="floatLeft width75 paddingTop18">
					  <img class="happines" src="images/google-icon.svg" alt="Google+ logo" title="GoogleLogo" />
					</div>
					<div id="floatLeft borderLeft width274" class="button">
					  <button class="g-signin"
						  data-scope="email"
						  data-clientid="327700176153-eekh64q51h9aagjv9lin4qaq53hbnm6n.apps.googleusercontent.com"
						  data-callback="onSignInCallback"
						  data-theme="dark"
						  data-cookiepolicy="single_host_origin">
						  <p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
							CONECTATE CON GOOGLE
						  </p>
					  </button>
					  <!-- Textarea for outputting data -->
					  <div id="response" class="hide">
						<textarea id="responseContainer" style="width:100%; height:150px"></textarea>
					  </div>
					</div>
				  </div>
				</div>
			  </div>
			</div>
			<div class="clearFix">
			  <div class="col12 autoMargin">
				<div class="paddingTop18 marginBottom16">
				  <p class="center formTitleColor font18">
					Tomale una foto a tus documentos y pre-llena tu formulario.
				  </p>
				  <p class="center colorblue font18">
					¡Puedes obtener tu crédito hasta en 10 minutos!
				  </p>
				  <div class="width350 boxGreen marginTop20 marginBottom20 marginTop20 autoMargin">
					<p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
					  SUBE TU IDENTIFICACIÓN OFICIAL
					</p>
				  </div>
				  <div class="width350 boxGreen marginBottom30 autoMargin">
					<p class="paddingTop20 paddingBottom15 center font14 colorWhite fontWeight500 letterspacing1">
					  SUBE TU COMPROBANTE DE DOMICILIO
					</p>
				  </div>
				</div>
			  </div>
			</div>
			<div class="borderTopResumen">
			  <div class="width388 blueButton marginTop20 marginBottom20 autoMargin">
				<p class="paddingTop20 paddingBottom15 center font25 colorWhite fontWeight500 letterspacing2">
				  EMPEZAR MI SOLICITUD
				</p>
			  </div>
			</div>
		  </div>
		</div>
	  </section>
	</body>
</html>
