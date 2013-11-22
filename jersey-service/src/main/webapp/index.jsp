<html>

<head>
	<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
	
	<script type="text/javascript">
		function mycallback(user) {
			alert(user.id + " - " + user.lastname.toUpperCase() + ", " + user.firstname);
		}
		
		function calljsonp() {
			$.getJSON("webapi/users/jsonp/1?jsonp=?", mycallback);
		}
	</script>
</head>

<body>
    <h2>Sample Jersey App !</h2>
    
    <p><a href="webapi/application.wadl">WADL</a></p>
    
    <h3>JSON</h3>
    
    <p><a href="webapi/users/1">GET user with id 1</a></p>
    <p><a href="webapi/users">GET all users</a></p>
    
    <h3>JSONP</h3>
    
    <p><a href="javascript:calljsonp();">GET javascript for user 1</a></p>
</body>
</html>
