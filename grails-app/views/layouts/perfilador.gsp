<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>${(configuracion?.htmlTitle ? (configuracion?.htmlTitle + " - " )  : "" )}Perfilador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
        <meta charset="UTF-8">
        <g:external dir="css" file="bootstrap.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="formulario.css" title="text/css" rel="stylesheet" />     
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="select2.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="slick.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="slick-theme.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="spectrum.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="bootstrap-tour-standalone.min.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.webui-popover.css" title="text/css" rel="stylesheet" />
        <g:if test="${session.configuracion?.rutaCss}">
            <link rel="stylesheet" href="${session.configuracion?.rutaCss}">
        </g:if>
        <g:external dir="css" file="default.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="default.date.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="jquery.mloading.css" title="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
                    <!-- Include Editor style. -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.6.0/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.6.0/css/froala_style.min.css" rel="stylesheet" type="text/css" />
        <g:external dir="css" file="perfilador.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="vanilla-notify.css" title="text/css" rel="stylesheet" />
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="jasny-bootstrap.min.js" />
        <g:external dir="js" file="dropzone.js" />
        <g:external dir="js" file="spectrum.js" />
        <g:external dir="js" file="dateFormat.min.js" />
        <g:external dir="js" file="dashboard.js" />
        <g:external dir="js" file="curp.js" />
        <g:external dir="js" file="rfc.js" />
        <g:external dir="js" file="select2.min.js" />
        <g:external dir="js" file="slick.js" />
        <g:external dir="js" file="bootstrap-tour-standalone.min.js" />
        <g:external dir="js" file="jquery.webui-popover.min.js" />

        <g:external dir="css" file="jquery-ui-kosmos.css" />
        <g:external dir="js" file="jquery-ui-kosmos.js" />
        <g:external dir="js" file="googleanalytics.js" />
        <g:external dir="js" file="perfilador.js" />
        <g:external dir="js" file="picker.js" />
        <g:external dir="js" file="picker.date.js" />
        <g:external dir="js" file="jquery.mloading.js" />
        <g:external dir="js" file="vanilla-notify.min.js" />
        <g:external dir="js" file="autoNumeric.min.js" />
        <script src="https://cdn.polyfill.io/v2/polyfill.min.js"></script>
        <script src="http://harvesthq.github.io/chosen/chosen.jquery.js"></script>
           <!-- Include Editor JS files. -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.6.0/js/froala_editor.pkgd.min.js"></script>
        <g:layoutHead/>
    </head>
    <body>
        <header class="appHeader" style="border-top: solid 5px ${session.configuracion?.colorBordeSuperior}; background-color: ${session.configuracion?.colorEncabezado};">
            <div class="container clearFix">
                <img class="logo dashboard floatLeft desktop" src="${resource(dir:'images', file: session.configuracion?.rutaLogotipo )}" alt="Logo" title="Logo" />
                <img class="logoMobile dashboard floatLeft mobile tablet" src="${resource(dir:'images', file:'kosmos-mobile-logo.png')}" alt="Logo" title="Logo" />
                <div class="floatingHeader floatRight clearFix">
                    <div class="floatRight clearFix">
                        <div class="clearFix floatLeft ">
                            <img class="userPicture dashboard floatLeft" src="${resource(dir:'images', file:'profile.png')}"/>
                        </div>
                        <p class="userName dashboard floatLeft">Hola ${session.usuarioNombre} </p>
                        <div class="dropBox floatLeft marginTop20 marginLeft5 desktop">
                            <i class="fa fa-angle-down paddingTop4 paddingRight5 paddingLeft5 dropbtn" aria-hidden="true" onclick="mostrarOpciones();"></i>
                            <div id="opcionesUsuario" class="dropdown-content">
                                <a href="/dashboard/editarPerfil">Editar Perfil</a>
                                <a href="/logout">Salir</a>
                            </div>
                        </div>
                        <span class="mobile tablet floatLeft mobMenu dashboard dropbtn"><i class="fa fa-bars" aria-hidden="true" onclick="w3_open();"></i></span>
                    </div>
                    <div class="floatRight width337 borderGrayRight paddingBottom10 paddingTop10 desktop">
                        <div class="searchBox autoMargin clearFix">
                            <input class="letterspacing0.8 font16 gray" placeholder="Buscar"/>
                            <i class="fa fa-search" aria-hidden="true"></i>
                        </div>
                    </div>
                    <div class="floatRight notificationBox dashboardHeader marginTop15 paddingTop5 paddingRight5 paddingLeft5">
                        <img class="floatLeft paddingLeft10 paddingTop5" src="${resource(dir:'images', file:'bell.png')}" alt="bell" title="bell">
                        <p class="floatLeft colorWhite font16 paddingLeft10">${(session.solicitudesPendientes ?: 0)}</p>
                    </div>
                </div>
            </div>
        </header>
        <ul id="mySidenav" class="side-nav right-aligned" style="transform: translateX(0px); -ms-transform: translateX(0px);-webkit-transform: translateX(0px);">
            <li>
                <a href="${createLink(controller:'dashboard', action:'index')}">
                    <img class="menuIcon" src="${resource(dir:'images', file:'dashboard.png')}" alt="dashboard" title="dashboard"> DASHBOARD
                </a>
            </li>
            <li>
            <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                <a href="${createLink(controller:'dashboard', action:'solicitudes')}">
                    <img class="menuIcon" src="${resource(dir:'images', file:'solicitudes.png')}" alt="solicitudes" title="solicitudes"> SOLICTUDES
                </a>
            </sec:ifAnyGranted>
        </li>
        <li>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
            <a href="${createLink(controller:'dashboard', action:'perfilarCliente')}">
                <img class="menuIcon" src="${resource(dir:'images', file:'perfilador.png')}" alt="perfilador" title="perfilador"> PERFILADOR
            </a>
        </sec:ifAnyGranted>
    </li>
    <li>
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
        <a href="${createLink(controller:'dashboard', action:'analiticas')}">
            <img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="analiticas" title="analiticas"> ANALITICAS
        </a>
    </sec:ifAnyGranted>
</li>
<li>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_VERIFICADOR'>
    <a href="${createLink(controller:'dashboard', action:'verificaciones')}">
        <img class="menuIcon" src="${resource(dir:'images', file:'verification.png')}" alt="verificacion" title="verificaciones"> VERIFICACIONES
    </a>
</sec:ifAnyGranted>
</li>
<li>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR, ROLE_RIESGOS, ROLE_DISENO'>
    <a href="${createLink(controller:'dashboard', action:'configuracion')}">
        <img class="menuIcon" src="${resource(dir:'images', file:'configration.png')}" alt="configration" title="configration"> CONFIGURACIÓN
    </a>
</sec:ifAnyGranted>
</li>
<li>
<sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <a href="${createLink(controller:'dashboard', action:'administracion')}">
        <img class="menuIcon" src="${resource(dir:'images', file:'key.png')}" alt="administracion" title="administracion"> ADMINISTRACIÓN
    </a>
</sec:ifAnyGranted>
</li>
</ul>
<nav id="dashboardMenu" class="container menuBox marginBottom28">
    <ul class="clearFix">
        <li class="floatLeft">
            <a id="principalOpc1" href="${createLink(controller:'dashboard', action:'index')}" class="elementoMenuPrincipal font12">
                <span><img class="menuIcon" src="${resource(dir:'images', file:'dashboard.png')}" alt="dashboard" title="dashboard"></span>
                <span>DASHBOARD</span>
            </a>
        </li>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
            <li class="floatLeft">
                <a id="principalOpc2" href="${createLink(controller:'dashboard', action:'solicitudes')}" class="elementoMenuPrincipal font12 ">
                    <span><img class="menuIcon" src="${resource(dir:'images', file:'solicitudes.png')}" alt="solicitudes" title="solicitudes"></span>
                    <span>SOLICITUDES</span>
                </a>
            </li>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ANALISTA, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
            <li class="floatLeft">
                <a id="principalOpc3" href="${createLink(controller:'dashboard', action:'perfilarCliente')}" class="elementoMenuPrincipal font12 ">
                    <span><img class="menuIcon" src="${resource(dir:'images', file:'perfilador.png')}" alt="perfilador" title="perfilador"></span>
                    <span>PERFILADOR</span>
                </a>
            </li>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
            <li class="floatLeft">
                <a id="principalOpc4" href="${createLink(controller:'dashboard', action:'analiticas')}" class="elementoMenuPrincipal font12">
                    <span><img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="analiticas" title="analiticas"></span>
                    <span>ANALITICAS</span>
                </a>
            </li>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_VERIFICADOR'>
            <li class="floatLeft">
                <a id="principalOpc5" href="${createLink(controller:'dashboard', action:'verificaciones')}" class="elementoMenuPrincipal font12">
                    <span><img class="menuIcon" src="${resource(dir:'images', file:'verification.png')}" alt="verificacion" title="verificaciones"></span>
                    <span>VERIFICACIONES</span>
                </a>
            </li>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR, ROLE_RIESGOS, ROLE_DISENO'>
            <li class="floatLeft">
                <a id="principalOpc6" href="${createLink(controller:'dashboard', action:'configuracion')}" class="elementoMenuPrincipal font12">
                    <span><img class="menuIcon" src="${resource(dir:'images', file:'configration.png')}" alt="configration" title="configration"></span>
                    <span>CONFIGURACIÓN</span>
                </a>
            </li>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
            <li class="floatLeft">
                <a id="principalOpc7" href="${createLink(controller:'dashboard', action:'administracion')}" class="elementoMenuPrincipal font12">
                    <span><img class="menuIcon" src="${resource(dir:'images', file:'key.png')}" alt="administracion" title="administracion"></span>
                    <span>ADMINISTRACIÓN</span>
                </a>
            </li>
        </sec:ifAnyGranted>  
    </ul>
</nav>

<g:layoutBody/>

</body>
</html>
