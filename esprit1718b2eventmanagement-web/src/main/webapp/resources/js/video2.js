/**
 * 
 */
(function(){

	var img = document.querySelector('img');

	var url = "ws://localhost:18080/esprit1718b2eventmanagement-web/wsServer";
	
	var socket = new WebSocket(url);
	
	socket.onopen=onOpen;
	function onOpen(event){
		img.src=window.URL.createObjectURL(event.data);
	}

	
	
	
	})();