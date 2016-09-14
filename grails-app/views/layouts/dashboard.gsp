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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <g:external dir="js" file="main.js" />


        <g:layoutHead/>
    </head>
    <body>
    <header class="appHeader">
      <div class="container clearFix">
        <img class="logo dashboard floatLeft desktop" src="images/kosmos-logo.png" alt="Logo" title="Logo" />
        <img class="logoMobile dashboard floatLeft mobile tablet" src="images/kosmos-mobile-logo.png" alt="Logo" title="Logo" />
        <div class="floatingHeader floatRight clearFix">
          <div class="floatRight clearFix">
            <div class="userPicture dashboard floatLeft "></div>
            <p class="userName dashboard floatLeft">Hola Joseph</p>
            <div class="dropBox floatLeft marginTop20 marginLeft5 desktop">
              <i class="fa fa-angle-down paddingTop2 paddingRight2 paddingLeft5" aria-hidden="true"></i>
            </div>
            <span class="mobile tablet floatLeft mobMenu dashboard"><i class="fa fa-bars" aria-hidden="true"></i></span>
          </div>
          <div class="floatRight width337 borderGrayRight paddingBottom10 paddingTop10 desktop">
            <div class="searchBox autoMargin clearFix">
              <input class="letterspacing0.8 font16 gray" placeholder="Buscar"/>
              <i class="fa fa-search" aria-hidden="true"></i>
            </div>
          </div>
          <div class="floatRight notificationBox dashboardHeader marginTop15 paddingTop5 paddingRight5 paddingLeft5">
            <img class="floatLeft paddingLeft10 paddingTop5" src="images/bell.png" alt="bell" title="bell">
            <p class="floatLeft colorWhite font16 paddingLeft10">99</p>
          </div>
        </div>
      </div>
    </header>
    
        <nav class="container menuBox marginBottom28">
      <ul class="clearFix">
        <li class="floatLeft">
          <a href="${createLink(controller:'dashboard', action:'index')}" class="font12 selected">
            <span><img class="menuIcon" src="images/dashboard.png" alt="dashboard" title="dashboard"></span>
            <span>DASHBOARD</span>
          </a>
        </li>
        <li class="floatLeft">
          <a href="${createLink(controller:'dashboard', action:'solicitudes')}" class="blueButton font12 ">
            <span><img class="menuIcon" src="images/solicitudes.png" alt="solicitudes" title="solicitudes"></span>
            <span>SOLICTUDES</span>
          </a>
        </li>
        <li class="floatLeft">
          <a href="${createLink(controller:'dashboard', action:'analiticas')}" class="font12">
            <span><img class="menuIcon" src="images/analiticas.png" alt="analiticas" title="analiticas"></span>
            <span>ANALITICAS</span>
          </a>
        </li>
        <li class="floatLeft">
          <a href="${createLink(controller:'dashboard', action:'verificaciones')}" class="font12">
            <span><img class="menuIcon" src="images/verification.png" alt="analiticas" title="analiticas"></span>
            <span>VERIFICATIONES</span>
          </a>
        </li>
        <li class="floatLeft">
          <a href="${createLink(controller:'dashboard', action:'configuracion')}" class="font12">
            <span><img class="menuIcon" src="images/configration.png" alt="configration" title="configration"></span>
            <span>CONFIGURATION</span>
          </a>
        </li>
      </ul>
    </nav>
    
            <g:layoutBody/>
        
    </body>
</html>
