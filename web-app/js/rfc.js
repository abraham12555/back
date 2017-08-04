function generarHomoclave(nombre, apellidoPaterno, apellidoMaterno, rfcParcial) {

    var alfabeto = {'A': '11', 'B': '12', 'C': '13', 'D': '14', 'E': '15', 'F': '16', 'G': '17', 'H': '18', 'I': '19', 'J': '21', 'K': '22', 'L': '23', 'M': '24', 'N': '25', 'O': '26', 'P': '27', 'Q': '28', 'R': '29', 'S': '32', 'T': '33', 'U': '34', 'V': '35', 'W': '36', 'X': '37', 'Y': '38', 'Z': '39', 'Ñ': '40', '&': '10', ' ': '00', '0': '00', '1': '01', '2': '02', '3': '03', '4': '04', '5': '05', '6': '06', '7': '07', '8': '08', '9': '09'};
    var alfabetoDigitoVerificador = {'0': '00', '1': '01', '2': '02', '3': '03', '4': '04', '5': '05', '6': '06', '7': '07', '8': '08', '9': '09', 'A': '10', 'B': '11', 'C': '12', 'D': '13', 'E': '14', 'F': '15', 'G': '16', 'H': '17', 'I': '18', 'J': '19', 'K': '20', 'L': '21', 'M': '22', 'N': '23', '&': '24', 'O': '25', 'P': '26', 'Q': '27', 'R': '28', 'S': '29', 'T': '30', 'U': '31', 'V': '32', 'W': '33', 'X': '34', 'Y': '35', 'Z': '36', ' ': '37', 'Ñ': '38'};
    var alfanumerico = ['1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    var nombreConvertido = '0';
    var homoclave;
    var acum = '';
    var sum = 0;
    var coeficiente;
    var residuo;
    var name = normalizaString(nombre.toUpperCase()).trim();
    var apPaterno = normalizaString(apellidoPaterno.toUpperCase()).trim();
    var apMaterno = normalizaString(apellidoMaterno.toUpperCase()).trim();
    var nombreCompleto = [apPaterno, apMaterno, name].join(' ');
    
    for (var i = 0; i < nombreCompleto.length; i++) {
        nombreConvertido += alfabeto[nombreCompleto[i]];
    }

    for (var i = 0; (i < nombreConvertido.length - 1); i++) {
        var valor1 = nombreConvertido.substring(i, (i+2));
        var valor2 = nombreConvertido[i + 1];
        sum += parseInt(valor1) * parseInt(valor2);
    }

    coeficiente = (sum % 1000)
    residuo = (coeficiente % 34)
    coeficiente = (coeficiente - residuo) / 34;
    console.log("Coeficiente: " + coeficiente);
    console.log("Residuo: " + residuo);
    rfcParcial += alfanumerico[coeficiente];
    rfcParcial += alfanumerico[residuo];
    sum = 0;
    for (var i = 0; i < rfcParcial.length; i++) {
        sum += parseInt(alfabetoDigitoVerificador[rfcParcial[i]]) * ((rfcParcial.length + 1) - i);
    }

    coeficiente = Math.floor(sum / 11);
    residuo = (sum % 11);
    if (residuo === 0) {
        rfcParcial += 0;
    } else { 
        residuo = (11 - residuo);
        if (residuo === 10) {
            rfcParcial += 'A';
        } else {
            rfcParcial += residuo;
        }
    }
    return rfcParcial;
}

function ajustaCompuesto(str) {
    var compuestos = [/\bDA\b/, /\bDAS\b/, /\bDE\b/, /\bDEL\b/, /\bDER\b/, /\bDI\b/,
        /\bDIE\b/, /\bDD\b/, /\bEL\b/, /\bLA\b/, /\bLOS\b/, /\bLAS\b/, /\bLE\b/,
        /\bLES\b/, /\bMAC\b/, /\bMC\b/, /\bVAN\b/, /\bVON\b/, /\bY\b/];
    compuestos.forEach(function (compuesto) {
        if (compuesto.test(str)) {
            str = str.replace(compuesto, '');
        }
    });
    return str;
}

function normalizaString(str) {
    var origen, destino, salida;
    origen = ['Ã', 'À', 'Á', 'Ä', 'Â', 'È', 'É', 'Ë', 'Ê', 'Ì', 'Í', 'Ï', 'Î',
        'Ò', 'Ó', 'Ö', 'Ô', 'Ù', 'Ú', 'Ü', 'Û', 'ã', 'à', 'á', 'ä', 'â',
        'è', 'é', 'ë', 'ê', 'ì', 'í', 'ï', 'î', 'ò', 'ó', 'ö', 'ô', 'ù',
        'ú', 'ü', 'û', 'Ç', 'ç'];
    destino = ['A', 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'E', 'I', 'I', 'I', 'I',
        'O', 'O', 'O', 'O', 'U', 'U', 'U', 'U', 'a', 'a', 'a', 'a', 'a',
        'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'o', 'o', 'o', 'o', 'u',
        'u', 'u', 'u', 'c', 'c'];
    str = str.split('');
    salida = str.map(function (char) {
        var pos = origen.indexOf(char);
        return (pos > -1) ? destino[pos] : char;
    });
    return salida.join('');
}