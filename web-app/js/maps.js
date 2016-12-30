var map;
var direccionOrigen;
var directionsDisplay;
var directionsService;

function initMap() {
    directionsDisplay = new google.maps.DirectionsRenderer;
    directionsService = new google.maps.DirectionsService;
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 10
    });
    var infoWindow = new google.maps.InfoWindow({map: map});

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            getAddressFromLatLang(position.coords.latitude, position.coords.longitude);
            infoWindow.setPosition(pos);
            infoWindow.setContent('¡Te encuentras aquí! (lat: ' + pos.lat + ', lng: ' + pos.lng + ')');
            map.setCenter(pos);
            directionsDisplay.setMap(map);
            directionsDisplay.setPanel(document.getElementById('listaDeInstrucciones'));
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}

function agregarMarcador(coordenadas, direccion, folio, cliente) {
    var marker = new google.maps.Marker({
        position: coordenadas,
        map: map
    });

    var content = '<div id="iw-container">' +
            '<div class="iw-title">' + cliente + '</div>' +
            '<div class="iw-content">' +
            '<div class="iw-subTitle"> Folio </div>' +
            '<p>' + folio + '</p>' +
            '<div class="iw-subTitle">Dirección</div>' +
            '<p>' + direccion + '</p>' +
            '<div class="iw-subTitle center"><button type="button" onclick="calculateAndDisplayRoute(\'' + direccion+ '\');" class="goToClient AutoMargin blueButton padding10">Calcular Ruta</button></div>' +
            '</div>' +
            '<div class="iw-bottom-gradient"></div>' +
            '</div>';

    var infowindow = new google.maps.InfoWindow({
        content: content,
        maxWidth: 350
    });

    google.maps.event.addListener(marker, 'click', function () {
        map.setCenter(marker.getPosition());
        map.setZoom(18);
        infowindow.open(map, marker);
    });

    google.maps.event.addListener(infowindow, 'domready', function () {
        var iwOuter = $('.gm-style-iw');
        var iwBackground = iwOuter.prev();
        iwBackground.children(':nth-child(2)').css({'display': 'none'});
        iwBackground.children(':nth-child(4)').css({'display': 'none'});
        iwOuter.parent().parent().css({left: '115px'});
        iwBackground.children(':nth-child(1)').attr('style', function (i, s) {
            return s + 'left: 76px !important;'
        });
        iwBackground.children(':nth-child(3)').attr('style', function (i, s) {
            return s + 'left: 76px !important;'
        });
        iwBackground.children(':nth-child(3)').find('div').children().css({'box-shadow': 'rgba(72, 181, 233, 0.6) 0px 1px 6px', 'z-index': '1'});
        var iwCloseBtn = iwOuter.next();
        iwCloseBtn.css({opacity: '1', right: '38px', top: '3px', border: '7px solid #48b5e9', 'border-radius': '13px', 'box-shadow': '0 0 5px #3990B9'});
        if ($('.iw-content').height() < 140) {
            $('.iw-bottom-gradient').css({display: 'none'});
        }
        iwCloseBtn.mouseout(function () {
            $(this).css({opacity: '1'});
        });
    });
}

function getAddressFromLatLang(lat, lng) {
    console.log("Entering getAddressFromLatLang()");
    var geocoder = new google.maps.Geocoder();
    var latLng = new google.maps.LatLng(lat, lng);
    geocoder.geocode({'latLng': latLng}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
                direccionOrigen = results[0].formatted_address;
            }
        } else {
            alert("Geocode was not successful for the following reason: " + status);
        }
    });
}

function mapaGenerado() {
    if (map === null || map === undefined) {
        return false;
    } else {
        return true;
    }
}

function calculateAndDisplayRoute(destino) {
    var start = direccionOrigen;
    var end = destino;
    directionsService.route({
        origin: start,
        destination: end,
        travelMode: google.maps.TravelMode.DRIVING
    }, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
            $('#instruccionesGoogle').fadeIn();
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}