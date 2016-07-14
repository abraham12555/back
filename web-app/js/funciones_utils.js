/*UTILS*/

		$(document).ready(function() {
		  setPaso('1');
		  if("${tipo_login}" == 'FB'){
		    fillFB("${datos_login.encodeAsJSON()}");
		  }else if("${tipo_login}" == 'Google'){
		    fillGoogle("${datos_login.encodeAsJSON()}");
		  }
		});

		 function validar1_1(){

            if(validar_form('form_solicitud_1_1','paso1_1') == true){
                $('#paso1_1').hide(500);
                $('#confirmar_datos1_1').hide(500);
                $('#paso1_2').show(500);
            }else{
                console.log("Verificar Datos 1_1");
            }
         }
         function validar1_2(){
             if(validar_form('form_solicitud_1_2','paso1_2') == true){
                document.getElementById("alinkIr2").setAttribute("style", "");
                $('#button_ir2').addClass('Rectangle-irA');
                $('#button_ir2Texto').removeClass('footerTextColor');
             }else{
                console.log("Verificar Datos 1_2");
             }
         }

          function validar2_1(){
                if(validar_form('form_solicitud_2_1','paso2_1') == true){
                setPaso('3');
                document.getElementById("alinkIr4").setAttribute("style", "");
                $('#button_ir4').addClass('Rectangle-irA');
                $('#button_ir4Texto').removeClass('footerTextColor');
                console.log("Validando");
               }else{
                console.log("Verificar Datos 3_1");
               }
          }

         function validar3_1(){
              if(validar_form('form_solicitud_3_1','paso3_1') == true){
                 setPaso('4');
		 //avanzarPaso(4);
                 window.location.href = "/kosmos-app/solicitud/formulario";
                 console.log("Avanzar Paso");
                 document.getElementById("alinkIr4").setAttribute("style", "");
                 $('#button_ir4').addClass('Rectangle-irA');
                 $('#button_ir4Texto').removeClass('footerTextColor');
                 console.log("Validando");
               }else{
                 console.log("Verificar Datos 3_1");
               }
         }

        function fillFB(data){
            var json = JSON.parse(data);
            $("#USUARIO").html(json["name"]);
            $("#NOMBRE_USUARIO").val(json["first_name"] + " "+ json["last_name"]);
            $("#IMAGEN_USUARIO").html("<img src="+json["picture"].data.url+"/>");
            $("#MES").val(json["birthday"].substring(0,2));
            $("#DIA").val(json["birthday"].substring(3,5));
            $("#ANIO").val(json["birthday"].substring(6,12));
            $("#ESTADO_CIVIL").val(json["relationship_status"]);
        }

        function fillGoogle(data){
            var json = JSON.parse(data);
        }

        function setPaso(paso){
            if (paso == '1') {
                 //ENCABEZADO
                 $('#progreso_barra').addClass('width24');
                 $('#progreso_barra').removeClass('width59');
                 $('#progreso_barra').removeClass('width145');
                 $("#progreso_numero").html('<p>5%</p>');
                 $("#pasoNumero").html('<p>Paso 1</p>');
                 $("#pasoTitulo").html('<p>DATOS GENERALES</p>');

                 $("#paso1_barra").show();
                 $("#paso2_barra").hide();
                 $("#paso3_barra").hide();

                 //CUERPO
                 $("#paso1_1").show();
                 $("#paso1_2").hide();
                 $("#paso2_1").hide();
                 $("#paso3_1").hide();

                 //PIE
                 document.getElementById("alinkIr1").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir1').addClass('blueCircle');
                 $('#button_ir1').removeClass('grayCircle');


                 document.getElementById("alinkIr2").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir2').addClass('rectangle250');
                 $('#button_ir2Texto').addClass('footerTextColor');
                 $('#button_ir2').removeClass('grayCircle');
                 $('#button_ir2').removeClass('blueCircle');

                 document.getElementById("alinkIr3").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir3').addClass('grayCircle');
                 $('#button_ir3').removeClass('rectangle250');
                 $('#button_ir3').removeClass('blueCircle');
                 $('#button_ir3Texto').addClass('footerTextColor');

                 document.getElementById("alinkIr4").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir4').addClass('grayCircle');
                 $('#button_ir4').removeClass('rectangle250');
                 $('#button_ir4').removeClass('blueCircle');
                 $('#button_ir4Texto').addClass('footerTextColor');


            }
            if (paso == '2'){
                  //ENCABEZADO
                 $('#progreso_barra').addClass('width59');
                 $('#progreso_barra').removeClass('width145');
                 $('#progreso_barra').removeClass('width24');
                 $("#progreso_numero").html('<p>25%</p>');

                 $("#pasoNumero").html('<p>Paso 2</p>');
                 $("#pasoTitulo").html('<p>VIVIENDA</p>');

                 //CUERPO
                 $('#paso1_1').hide();
                 $('#paso1_2').hide();
                 $('#paso2_1').show();
                 $('#paso3_1').hide();

                 //PIE
                 document.getElementById("alinkIr1").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir1').addClass('grayCircle');
                 $('#button_ir1').removeClass('blueCircle');
                 $('#button_ir1Texto').addClass('footerTextColor');

                 document.getElementById("alinkIr2").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir2').addClass('blueCircle');
                 $('#button_ir2').removeClass('grayCircle');
                 $('#button_ir2').removeClass('rectangle250');
                 $('#button_ir2').removeClass('Rectangle-irA');
                 $('#button_ir2Texto').removeClass('footerTextColor');
                 document.getElementById("button_ir2Texto").setAttribute("style", "color:white;");
                 $("#button_ir2Texto").html('<p>2</p>');

                 document.getElementById("alinkIr3").setAttribute("style", "");
                 $('#button_ir3').addClass('Rectangle-irA');
                 $('#button_ir3').removeClass('grayCircle');
                 $('#button_ir3Texto').removeClass('footerTextColor');
                 $("#button_ir3Texto").html('<p>IR AL PASO 3</p>');

                 document.getElementById("alinkIr4").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir4').addClass('grayCircle');
                 $('#button_ir4').removeClass('rectangle250');
                 $('#button_ir4').removeClass('blueCircle');
                 $('#button_ir4Texto').addClass('footerTextColor');

            }
            if (paso == '3'){
                  //ENCABEZADO

                 $('#progreso_barra').addClass('width145');
                 $('#progreso_barra').removeClass('width59');
                 $('#progreso_barra').removeClass('width24');
                 $("#progreso_numero").html('<p>45%</p>');



                 $("#pasoNumero").html('<p>paso 3</p>');
                 $("#pasoTitulo").html('<p>EMPLEO</p>');


                 //CUERPO
                 $('#paso1_1').hide();
                 $('#paso1_2').hide();
                 $('#paso2_1').hide();
                 $('#paso3_1').show();

                 //PIE
                 document.getElementById("alinkIr1").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir1').addClass('grayCircle');
                 $('#button_ir1').removeClass('blueCircle');
                 $('#button_ir1Texto').addClass('footerTextColor');

                 document.getElementById("alinkIr2").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir2').addClass('grayCircle');
                 $('#button_ir2').removeClass('blueCircle');
                 $('#button_ir2').removeClass('rectangle250');
                 $('#button_ir2Texto').addClass('footerTextColor');
                 document.getElementById("button_ir2Texto").setAttribute("style", "pointer-events:none;cursor:default");

                 document.getElementById("alinkIr3").setAttribute("style", "pointer-events:none;cursor:default");
                 $('#button_ir3').addClass('blueCircle');
                 $('#button_ir3').removeClass('rectangle250');
                 $('#button_ir3').removeClass('grayCircle');
                 $('#button_ir3').removeClass('Rectangle-irA');
                 $('#button_ir3Texto').removeClass('footerTextColor');
                 document.getElementById("button_ir3Texto").setAttribute("style", "color:white;");
                 $("#button_ir3Texto").html('<p>3</p>');


                 document.getElementById("alinkIr4").setAttribute("style", "");
                 $('#button_ir4').addClass('Rectangle-irA');
                 $('#button_ir4').removeClass('blueCircle');
                 $('#button_ir4').removeClass('grayCircle');
                 $('#button_ir4Texto').removeClass('footerTextColor');
                 document.getElementById("button_ir4Texto").setAttribute("style", "color:white;");
                 $("#button_ir4Texto").html('<p>IR AL PASO 4</p>');
            }

        }
		
		
	    function avanzarPaso(paso) {
		$.ajax({
			type: 'POST',
			data: 'siguientePaso=' + paso,
			url: '/kosmos-app/solicitud/cambiarPaso',
			success: function (data, textStatus) {
				$('#paso1').hide();
				$('#paso1').html(data);
				$('#paso1').fadeIn();
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				sweetAlert("Oops...", "Algo salió mal, intenta nuevamente en unos minutos.", "error");
			}
		});
}
		

        function validar_form(form,metodo){
           var result=true;
           if( $('#'+form)[0].checkValidity()== true){
            //var validator = $( "#"+form ).validate();
            //validator.form();
            $.ajax({
               type: "POST",
               url: metodo,
               data: $("#"+form).serialize(),
               success: function(data)
               {
                console.log(data);
               },
               fail: function(data)
               {
                result=false;
               }
            });
           }else{
              result=false;
           }
           return result;
        }