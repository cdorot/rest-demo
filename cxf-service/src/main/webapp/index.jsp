<html>

<head>
	<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
	
	<script type="text/javascript">
		function mycallback(user) {
			alert(user.id + " - " + user.lastname.toUpperCase() + ", " + user.firstname);
		}
		
		function calljsonp() {
			$.getJSON("webapi/user/jsonp/1?jsonp=?", mycallback);
		}
	</script>
</head>

<body>
    <h2>Sample CXF App !</h2>
    
    <p><a href="webapi/services">WADL</a></p>
    <p><a href="webapi/user/1">GET User with id 1</a></p>
    
    <p><a href="javascript:calljsonp();">JSONP</a></p>
</body>
</html>
