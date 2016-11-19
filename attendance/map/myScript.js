$(document).ready(function()
{
	//Declares variable optn, enables retrieval of user's latitude and longitude
	var optn = {
		enableHighAccuracy: true,
		//Timeout, continues to return user position until closed. If set for a specific time (in miliseconds)
		//It ends, and leaves the marker at the last updated position
		//As it is no longer retrieving user coordinates
		timeout: Infinity,
		maximumAge: 0	
	};
	//Checks to see if it can retrieve the user's latitude and longitude
	if( navigator.geolocation )
		//Checks the user's longitude and latitude, and returns
		navigator.geolocation.watchPosition(success, fail, optn);
	//If it cannot, function is not supported, returns error
	else
		$("p").html("HTML5 Not Supported");
});
function addMarker(map, googleLatLng, title, content){
	//Allows creation of tracking marker
	var marker = {
		map: map,
		//Sets position to retrieved user coordinates
		position: googleLatLng,
		title: title,
	};
	//Allows for info windows, just meant to display information when clicking on the marker
	//Not used, as there are only two markers and their functions are apparent
 	var infoWindow = new google.maps.InfoWindow({ content: content, position: googleLatLng});
	google.maps.event.addListener(marker, "click", function()
	{
		infoWindow.open(map);
	});												   
}
function success(position)
{
	//Finds the user's latitude and longitude, and assigns them to googleLatLng
	var googleLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
	//Creates new variable centered on user position
	var mapOtn = {
		zoom: 10,
		center: googleLatLng,
		//Enables display of default google road map view
		mapTypeId:google.maps.MapTypeId.ROAD
	};
	//Retrieves the map ID for use
 	var Pmap = document.getElementById("map");
 	var map = new google.maps.Map(Pmap, mapOtn);
	//Calls addMarker function, sends parameters for use
	addMarker(map, googleLatLng, "User", "User Location");
}
function fail(error)
{
	//Error codes if it fails to retrieve user position
	//They may or may not work, as either there was never an error and it functioned perfectly, or the error codes never displayed
	var errorType={
	0: "Unknown Error",
	1: "Permission denied by the user",
	2: "Position of the user not available",
	3: "Request timed out"
	};
 	var errMsg = errorType[error.code];
 	if(error.code == 0 || error.code == 2)
	{
		errMsg = errMsg + " - " + error.message;
	}
	$("p").html(errMsg);
}
