<?php

/* @var $this yii\web\View */

$this->title = 'SOFTDEV';
?>
<div class="site-index">

    <!--<div class="jumbotron">
        <img src="uploads/logo.png" style="width:200px;height:200px;"></a>
        <br>
        <br>        
        <center><img src="uploads/d.png" style="width:400px;height:200px;"></center></a>
        <br>
        <p><a class="btn btn-lg btn-success" href="http://localhost/softdev/frontend/web/index.php?r=site%2Fdirections">Show Directions</a></p>
    </div>

    </div>-->
</div>

<!DOCTYPE html>
<html>
  <head>
    <title>Geolocation</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <!--<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
       #map {
        height: 1000px;  /* The height is 400 pixels */
        width: 150%;  /* The width is the width of the web page */
       }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 90%;
        margin: 0;
        padding: 0;
      }
    </style>-->
  </head>
  <body>
            <div id="googlemaps"></div>
    <div id="jumbotron">
        <img src="uploads/logo.png" style="width:200px;height:200px;"></a>
                <br>
                <br>        
                <center><img src="uploads/d.png" style="width:400px;height:200px;"></center></a>
                <br>
                <p><a class="btn btn-lg btn-success" href="http://localhost/softdev/frontend/web/index.php?r=site%2Fdirections">Show Directions</a></p>
        </div>
    

            <script src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

<script type="text/javascript">

// The latitude and longitude of your business / place
var position = [14.556586, 121.023415];

function showGoogleMaps() {

    var latLng = new google.maps.LatLng(position[0], position[1]);

    var mapOptions = {
        zoom: 16, // initialize zoom level - the max value is 21
        streetViewControl: false, // hide the yellow Street View pegman
        scaleControl: true, // allow users to zoom the Google Map
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: latLng
    };

    map = new google.maps.Map(document.getElementById('googlemaps'),
        mapOptions);

    // Show the default red marker at the location
    marker = new google.maps.Marker({
        position: latLng,
        map: map,
        draggable: false,
        animation: google.maps.Animation.DROP
    });
}

google.maps.event.addDomListener(window, 'load', showGoogleMaps);
</script>
</html>