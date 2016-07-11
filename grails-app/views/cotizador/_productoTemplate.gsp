<div class="producto-form0 cotizador-box-container padding20">
    <h1 class="darkBluetitle font20 fontWeight400 letterspacing1 marginBottom20">Elige un Auto</h1>
    <div class="cotizador-p1-buttons clearFix">
        <g:each in="${productos}" var="producto" status="i">
            <div class="col6 floatLeft marginBottom10">
                <p id="productElement${i}" class="productElement width234 marginRight3 center cotizador-box lightGray paddingTop20 paddingBottom20 font18 fontWeight300 letterspacing1">${producto.nombre}</p>
            </div>
        </g:each>
    </div>
    <div class="marker">
        <p>1</p>
    </div>
</div>
<div class="producto-form1 borderGrayTop clearFix elige-un-modelo">
    <div class="col6 col12-mob floatLeft">
        <div class="floatLeft paddingTop18 marginLeft24">
            <p class="formTitleColor font12 paddingBottom12 fontWeight500">1. Elige un auto</p>
            <p class="formTitleColor font19 fontWeight500">NISSAN MAXIMA</p>
        </div>
    </div>
    <div class="cambiar-producto col6 col12-mob floatRight">
        <div class="floatRight paddingTop18 marginBottom16">
            <div class="borderGrayButton marginBottom16">
                <p class="center paddingTop9 paddingBottom9">Cambiar</p>
            </div>
        </div>
    </div>
</div>