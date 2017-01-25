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
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <g:external dir="css" file="sweetalert.css" title="text/css" rel="stylesheet" />
        <g:external dir="css" file="spectrum.css" title="text/css" rel="stylesheet" />
        <g:if test="${session.configuracion?.rutaCss}">
            <link rel="stylesheet" href="${session.configuracion?.rutaCss}">
        </g:if>
        <g:external dir="js" file="jquery-3.0.0.min.js" />
        <g:external dir="js" file="sweetalert.min.js" />
        <g:external dir="js" file="dropzone.js" />
        <g:external dir="js" file="spectrum.js" />
        <g:external dir="js" file="dateFormat.min.js" />
        <g:external dir="js" file="dashboard.js" />
        <!--<g:external dir="css" file="jquery-ui-kosmos.css" />-->
        <g:external dir="js" file="jquery-ui-kosmos.js" />
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
                        <p class="userName dashboard floatLeft">Hola Joseph</p>
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
        <ul id="mySidenav" class="side-nav right-aligned" style="transform: translateX(0px); -ms-transform: translateX(0px);
    -webkit-transform: translateX(0px);">
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
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_EJECUTIVO, ROLE_SUCURSAL'>
                    <li class="floatLeft">
                        <a id="principalOpc3" href="${createLink(controller:'dashboard', action:'analiticas')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'analiticas.png')}" alt="analiticas" title="analiticas"></span>
                            <span>ANALITICAS</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_DIRECTOR, ROLE_VERIFICADOR'>
                    <li class="floatLeft">
                        <a id="principalOpc4" href="${createLink(controller:'dashboard', action:'verificaciones')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'verification.png')}" alt="verificacion" title="verificaciones"></span>
                            <span>VERIFICACIONES</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR, ROLE_RIESGOS, ROLE_DISENO'>
                    <li class="floatLeft">
                        <a id="principalOpc5" href="${createLink(controller:'dashboard', action:'configuracion')}" class="elementoMenuPrincipal font12">
                            <span><img class="menuIcon" src="${resource(dir:'images', file:'configration.png')}" alt="configration" title="configration"></span>
                            <span>CONFIGURACIÓN</span>
                        </a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
                    <li class="floatLeft">
                        <a id="principalOpc6" href="${createLink(controller:'dashboard', action:'administracion')}" class="elementoMenuPrincipal font12">
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
