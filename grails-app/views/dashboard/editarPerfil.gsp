<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="dashboard"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
        <g:external dir="js" file="validaciones.min.js" />
        <g:external dir="js" file="dashboard/perfil/perfil.min.js" />
        <g:external dir="js" file="jquery-2.0.0.min.js" />
        <g:external dir="js" file="jquery.cropit.js" />
        <script type="text/javascript">
            $.allowedContentTypes = "${allowedContentTypes}";
            $.allowedSize = "${allowedSize}";
        </script>
        <g:external dir="js" file="dashboard/perfil/profilePicture.min.js" />
    </head>
    <body>
        <section class="container clearFix">
            <div class="width990 solicitudBox autoMargin clearFix">
                <div class="clearFix">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="MI PERFIL" class="displayInline font24 fontWeight700 darkBluetitle paddingTop5 paddingBottom15 paddingLeft20 paddingRight20">MI PERFIL</a>
                        </li>
                    </ul>
                </div>
                <div id="usuariosSubMenu" class="configuracionSubMenu lightGrayBG">
                    <ul class="clearFix paddingLeft30">
                        <li class="floatLeft">
                            <a title="Cambiar contraseña" class="displayInline font14 gray2 paddingTop10 paddingBottom10  paddingRight20 pointer" id="updatePassword-btn">CAMBIAR CONTRASEÑA</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <section class="container marginTop50 marginBottom50">
            <form id="formProfile" action="/dashboard/saveProfile" method="POST" class="form gray font14">
                <input type="hidden" id="userId" name="id"/>
                <div class="width400 autoMargin">
                    <div class="clearFix">
                        <a title="Cambiar foto" class="floatRight font14 fontWeight500 letterspacing1 lightGray pointer paddingBottom10" id="editProfilePicture-btn">CAMBIAR FOTO</a>
                    </div>
                    <div class="clearFix">
                        <a title="Eliminar foto" class="floatRight font14 fontWeight500 letterspacing1 lightGray pointer paddingBottom10 hide" id="deleteProfilePicture-btn">ELIMINAR FOTO</a>
                    </div>
                    <div class="profileBox positionR padding20">
                        <div>
                            <img class="profileImage" src="${resource(dir:'images', file:'profile.png')}" id="profilePicture"/>
                        </div>
                        <div class="paddingTop50 paddingAside30">
                            <div class="control marginBottom20">
                                <p class="font14 gray fontWeight500 paddingTop14 letterspacing1">NOMBRE DE USUARIO</p>
                                <div class="paddingTop5 fullInputs">
                                    <input class="whiteBox font14 lightGray paddingTop10 paddingBottom10 paddingLeft15" type="text" name="username" placeholder="Nombre de usuario" id="username">
                                </div>
                            </div>
                            <div class="control marginBottom20">
                                <p class="font14 gray fontWeight500 letterspacing1">NOMBRE</p>
                                <div class="paddingTop5 fullInputs">
                                    <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="nombre" placeholder="Nombre" id="name">
                                </div>
                            </div>
                            <div class="control marginBottom20">
                                <p class="font14 gray fontWeight500 letterspacing1">APELLIDO PATERNO</p>
                                <div class="paddingTop5 fullInputs">
                                    <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="apellidoPaterno" placeholder="Apellido Paterno" id="apPaterno">
                                </div>
                            </div>
                            <div class="control marginBottom20">
                                <p class="font14 gray fontWeight500 letterspacing1">APELLIDO MATERNO</p>
                                <div class="paddingTop5 fullInputs">
                                    <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="apellidoMaterno" placeholder="Apellido Materno" id="apMaterno">
                                </div>
                            </div>
                            <div class="control marginBottom20">
                                <p class="font14 gray fontWeight500 letterspacing1">CORREO ELECTRÓNICO</p>
                                <div class="paddingTop5 fullInputs">
                                    <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="email" placeholder="Correo electrónico" id="email">
                                </div>
                            </div>
                            <div class="formContainer">
                                <button type="button" class="loginButton blueButton letterspacing2 font14 pointer marginBottom30" id="editProfile-btn">EDITAR</button>
                                <button type="button" class="loginButton letterspacing2 font14 pointer hide" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);" id="discardChanges-btn">CANCELAR</button>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </form>
        </section>
        <g:render template="updatePassword"/>
        <g:render template="updateProfilePicture"/>
    </body>
</html>
