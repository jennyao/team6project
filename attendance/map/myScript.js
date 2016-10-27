<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
$(document).ready(function() {
    if (navigator.geolocation)
            navigator.geolocation.getCurrentPosition(success);
    else
           $("p").html("HTML5 Not Supported");    
});
    
function success(position) {
	   $("p").html("Latitude: "+position.coords.latitude+
	         "<br />Longitude: "+position.coords.longitude+
		     "<br />Accuracy: "+position.coords.accuracy);
}
