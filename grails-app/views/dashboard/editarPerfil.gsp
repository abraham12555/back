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
    </head>
    <body>
        <section class="container clearFix">
            <div class="width990 solicitudBox autoMargin clearFix">
                <div class="clearFix">
                    <ul class="clearFix">
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="MI PERFIL" class="displayInline font24 fontWeight700 darkBluetitle paddingTop15 paddingBottom15 paddingLeft20 paddingRight20">MI PERFIL</a>
                        </li>
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="Editar" class="displayInline font20 fontWeight500 darkBluetitle padding20">Editar</a>
                        </li>
                        <li class="floatLeft paddingLeft5 paddingRight5">
                            <a href="#" title="Guardar Cambios" class="displayInline font20 fontWeight500 darkBluetitle padding20">Guardar Cambios</a>
                        </li>
                        <li class="floatRight paddingLeft5 paddingRight5">
                            <a href="#" title="Guardar Cambios" class="displayInline font20 fontWeight500 colorRed padding20">Eliminar</a>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <section class="container marginTop100 marginBottom50">

            <div class="width400 autoMargin">
                <div class="clearFix">
                    <p class="floatRight font14 fontWeight500 letterspacing1 lightGray pointer paddingBottom10">CAMBIAR FOTO</p>
                </div>
                <div class="profileBox positionR padding30">
                    <div>
                        <img class="profileImage" src="${resource(dir:'images', file:'profile.png')}"/>
                    </div>
                    <div class="paddingTop50 paddingAside20">
                        <p class="font14 gray fontWeight500 paddingTop14 letterspacing1">NOMBRE DE USUARIO</p>
                        <div class="paddingTop15 fullInputs">
                            <input class="whiteBox font14 lightGray paddingTop10 paddingBottom10 paddingLeft15" type="text" name="name" value="" placeholder="Joseph_s">
                        </div>
                        <p class="font14 gray fontWeight500 paddingTop14 letterspacing1">NOMBRE</p>
                        <div class="paddingTop15 fullInputs">
                            <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="name" value="" placeholder="Joseph Sasson">
                        </div>
                        <p class="font14 gray fontWeight500 paddingTop14 letterspacing1">EMPRESA</p>
                        <div class="paddingTop15 fullInputs">
                            <input class=" font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="name" value="" placeholder="Kosmos">
                        </div>
                        <p class="font14 gray fontWeight500 paddingTop14 letterspacing1">CORREO</p>
                        <div class="paddingTop15 fullInputs">
                            <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="name" value="" placeholder="joseph@kosmos.io">
                        </div>
                        <p class="font14 gray fontWeight500 letterspacing1 paddingTop14">CONTRASEÑA</p>
                        <div class="paddingTop15 fullInputs">
                            <input class="font14 lightGray whiteBox paddingTop10 paddingBottom10 paddingLeft15" type="text" name="name" value="" placeholder="·········">
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
