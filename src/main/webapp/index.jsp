<html>
<head>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<link rel="stylesheet"  href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css' rel='stylesheet' type='text/css'>

  <title>Hello RMIT Guild</title>
<style>
   #content {color: red; font-weight: bold;}
   body {margin:20px;}
</style>
</head>
<body>
<h2>Hello RMIT Guild!</h2>
<script>
//curl call: curl -X GET --header 'Accept: application/json' 'http://localhost:8889/rmit_guild_rest/services/v1/students'
function getGuilders() {
	$("#content").html('');
  var settings = {
		"async": true,
		"crossDomain": true,
		"dataType": "json",
		"url": "http://localhost:8889/rmit_guild_rest/services/v1/students",
		"method": "GET",
		"headers": {
			"accept": "application/json"
		}
	}

  $.ajax(settings)
  .done(function (response) {
    console.log(response);
	var myArray = response.students;
	var arrayLength = myArray.length;
	var myStudents = '';
	for (var i = 0; i < arrayLength; i++) {
		myStudents = myStudents + i + ') - ' + myArray[i].id + ':' + myArray[i].name + '<br/>';
	}
	console.log(myStudents);
	$("#content").append(myStudents);
  });
  settings = '';
}
</script>

<button type="button" onclick="getGuilders()" class="btn btn-danger weatherbutton">Get Guilders!</button>

<div id="content"></div>

</body>
</html>
