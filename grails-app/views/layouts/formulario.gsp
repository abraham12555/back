<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="utf-8">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario2.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="progressbar.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery-ui.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.modal.min.css" title="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="jquery-ui.min.js" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="jquery.modal.min.js" />
        <g:external dir="js" file="formulario.js" />
        <g:external dir="js" file="photobooth_min.js" />
    </head>
    <body>
        <header class="formularioHeader clearFix">
            <div class="container clearFix">
                <img class="logo floatLeft" src="${resource(dir:'images', file:'kosmos-logo.png')}" alt="Logo" title="Logo" />
                <div class="floatingHeader floatRight clearFix">
                    <div class="floatRight clearFix">
                        <div class="userPicture floatLeft "></div>
                        <p class="userName marginTop28 paddingRight5 marginBottom27 floatLeft">Hola Joseph</p>
                        <div class="dropBox floatLeft marginTop28 marginLeft5">
                            <i class="fa fa-angle-down paddingTop2 paddingRight2 paddingLeft5" aria-hidden="true"></i>
                        </div>
                    </div>
                    <div class="floatRight width337 borderGrayRight borderGrayLeft paddingBottom18 paddingTop19">
                        <div class="urlBox autoMargin">
                            <p class="letterspacing0.8 font14 gray paddingTop10 paddingRight36 paddingBottom10 paddingLeft25 center ">TU URL: KSM.os/ER45</p>
                        </div>
                    </div>
                    <div class="salvadoConatiner floatRight">
                        <p class="salvadoTitle floatLeft paddingTop20 paddingBottom15">salvado automatico</p>
                        <img class="floatLeft paddingTop20 paddingLeft16 paddingRight10 paddingBottom15" src="${resource(dir:'images', file:'cloud.png')}" alt="cloud" title="cloud"/>
                    </div>
                </div>
            </div>
        </header>
        <g:layoutBody/>
    </body>
</html>