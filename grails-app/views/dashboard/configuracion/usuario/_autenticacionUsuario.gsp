<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<div class="idLightbox hide" id="loginUsuario">
    <div class="overlay"></div>
    <div class="loginContainer creditLb">
        <div class="dashBordBox">
            <form action="/dashboard/autenticarUsuario" method="POST" class="loginForm gray font14" id="formUserAuthentication">
                <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">ASIGNACIÓN DE ROLES</p>
                <div class="solicitudTitle autoMargin">
                    <p class="center font14 lightGray">Para modificar el rol del usuario ingresa tu contraseña</p>
                </div>
                <div class="formContainer">
                    <div id="authPassword-control" class="marginRight25 letterspacing1"></div>
                    <input class="inputs marginBottom30 lightGray letterspacing1 font14" name="password" type="password" required placeholder="Contraseña" id="authPassword"/>
                    <button type="submit" class="loginButton blueButton letterspacing2 font14 pointer" id="autenticarUsuario-btn">ACEPTAR</button>
                    <button type="button" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);" id="cancelUserAuthentication-btn">CANCELAR</button>
                </div>
            </form>
        </div>
        <div class="loginShadow"></div>
    </div>
</div>
