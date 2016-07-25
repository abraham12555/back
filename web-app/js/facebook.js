/*
 Funciones Login Facebook
 */
function initFB() {
    $.ajaxSetup({cache: true});
    $.getScript('http://connect.facebook.net/en_US/sdk.js', function () {
        FB.init({
            appId: '1132024623508026',
            xfbml: true,
            version: 'v2.6'
        });
        $('#loginbutton,#feedbutton').removeAttr('disabled');
        FB.getLoginStatus(updateStatusCallback);
        console.log("Inicio de FB ");
    });
}
function updateStatusCallback() {
    console.log('Status updated!!');
}
function  fb_login() {
    //FB.login( function() {}, { scope: 'email,public_profile' } );
    FB.login(function (response) {
        if (response.authResponse) {
            console.log('Login Sccess!!!.... ');
            //Parametros de Busqueda FB
            FB.api('/me?fields=id,name,birthday,education,email,first_name,gender,last_name,middle_name,work,location', function (response) {
                $("#datosFb").val(JSON.stringify(response));
                $("#formRedesSociales").submit();
                console.log('Good to see you, ' + response.name + '.');
            });
        } else {
            console.log('Proceso de Login cancelado.....');
        }
    });
}