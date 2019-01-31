/**
 * 
 */

function myMap() {
	myCenter = new google.maps.LatLng(41.878114, -87.629798);
	var mapOptions = {
		center : myCenter,
		zoom : 12,
		scrollwheel : false,
		draggable : false,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("googleMap"),
			mapOptions);

	var marker = new google.maps.Marker({
		position : myCenter,
	});
	marker.setMap(map);

}

function openMenu(evt, menuName) {
	var i, x, tablinks, y;
	x = document.getElementsByClassName("menu");
	for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablink");
	for (i = 0; i < x.length; i++) {
		tablinks[i].classList.remove(" greyy");
	}
	if (menuName == "Eat") {
		var element = document.getElementById("hhh");
		element.classList.add(" greyy");

	} else {

		var element = document.getElementById("myLink");
		element.classList.add(" greyy");
	}
	document.getElementById(menuName).style.display = "block";
}
document.getElementById("myLink").click();