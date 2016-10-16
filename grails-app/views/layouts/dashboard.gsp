<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Solicitudes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="UTF-8">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="dashboard.js" />

        <g:layoutHead/>
    </head>
    <body>
        <header class="appHeader">
            <div class="container clearFix">
                <img class="logo dashboard floatLeft desktop" src="${resource(dir:'images', file:'kosmos-logo.png')}" alt="Logo" title="Logo" />
                <img class="logoMobile dashboard floatLeft mobile tablet" src="${resource(dir:'images', file:'kosmos-mobile-logo.png')}" alt="Logo" title="Logo" />
                <div class="floatingHeader floatRight clearFix">
                    <div class="floatRight clearFix">
                        <div class="clearFix floatLeft ">
                            <img  class="userPicture dashboard floatLeft" src="${resource(dir:'images', file:'profile.png')}"/>
                        </div>
                        <p class="userName dashboard floatLeft">Hola Joseph</p>
                        <div class="dropBox floatLeft marginTop20 marginLeft5 desktop">
                            <i class="fa fa-angle-down paddingTop4 paddingRight5 paddingLeft5 dropbtn" aria-hidden="true" onclick="mostrarOpciones();"></i>
                            <div id="opcionesUsuario" class="dropdown-content">
                                <a href="/kosmos-app/dashboard/editarPerfil">Editar Perfil</a>
                                <a href="/kosmos-app/logout">Salir</a>
                            </div>
                        </div>
                        <span class="mobile tablet floatLeft mobMenu dashboard dropbtn"><i class="fa fa-bars" aria-hidden="true" onclick="mostrarOpciones();"></i></span>
                    </div>
                    <div class="floatRight width337 borderGrayRight paddingBottom10 paddingTop10 desktop">
                        <div class="searchBox autoMargin clearFix">
                            <input class="letterspacing0.8 font16 gray" placeholder="Buscar"/>
                            <i class="fa fa-search" aria-hidden="true"></i>
                        </div>
                    </div>
                    <div class="floatRight notificationBox dashboardHeader marginTop15 paddingTop5 paddingRight5 paddingLeft5">
                        <img class="floatLeft paddingLeft10 paddingTop5" src="${resource(dir:'images', file:'bell.png')}" alt="bell" title="bell">
                        <p class="floatLeft colorWhite font16 paddingLeft10">99</p>
                    </div>
                </div>
            </div>
        </header>

        <nav class="container menuBox marginBottom28">
            <ul class="clearFix">
                <li class="floatLeft">
                    <a id="principalOpc1" href="${createLink(controller:'dashboard', action:'index')}" class="elementoMenuPrincipal font12">
                        <span><img class="menuIcon" src="${resource(dir:'images', file:'dashboard.png')}" alt="dashboard" title="dashboard"></span>
                        <span>DASHBOARD</span>
                    </a>
                </li>
                <li class="floatLeft">
                    <a id="principalOpc2" href="${createLink(controller:'dashboard', action:'solicitudes')}" class="elementoMenuPrincipal font12 ">
                        <span><img class="menuIcon" src="${resource(dir:'images', file:'solicitudes.png')}" alt="solicitudes" title="solicitudes"></span>
                        <span>SOLICTUDES</span>
                    </a>
                </li>
                <li class="floatLeft">
                    <a id="principalOpc3" href="${createLink(controller:'dashboard', action:'analiticas')}" class="elementoMenuPrincipal font12">
                        <span><img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="analiticas" title="analiticas"></span>
                        <span>ANALITICAS</span>
                    </a>
                </li>
                <li class="floatLeft">
                    <a id="principalOpc4" href="${createLink(controller:'dashboard', action:'verificaciones')}" class="elementoMenuPrincipal font12">
                        <span><img class="menuIcon" src="${resource(dir:'images', file:'verification.png')}" alt="verificacion" title="verificaciones"></span>
                        <span>VERIFICACIONES</span>
                    </a>
                </li>
                <li class="floatLeft">
                    <a id="principalOpc5" href="${createLink(controller:'dashboard', action:'configuracion')}" class="elementoMenuPrincipal font12">
                        <span><img class="menuIcon" src="${resource(dir:'images', file:'configration.png')}" alt="configration" title="configration"></span>
                        <span>CONFIGURACIÓN</span>
                    </a>
                </li>
            </ul>
        </nav>

        <g:layoutBody/>

    </body>
</html>
