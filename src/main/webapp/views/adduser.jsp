<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MIB's Spring App</title>
<style>
.dropbtn {
  background-color: #04AA6D;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>
</head>
<body>
	<h2 style="text-align: center;">MIB's Spring App</h2>
	<div class="dropdown">
		<button class="dropbtn">Menu</button>
		<div class="dropdown-content">
			<a href="allusers">Display All Users</a> <a href="searchbyid">Search
				By ID</a> <a href="searchbyname">Search By Name</a> <a href="adduser">Add
				a User</a> <a href="deleteuser">Delete a User</a>
		</div>
	</div>
	<h3>Add a User</h3>
	<form action="addUser" method="post">
		<label for="id">Enter the id:</label><br> <input type="text"
			name="id" id="id"><br>
		<br> <label for="name">Enter the name:</label><br> <input
			type="text" name="name" id="name"><br>
		<br> <input type="submit" value="Submit">
	</form>
	<hr>
	<a href="/">Home</a>
	<br>
	<br>
	<h3>Search Results</h3>
	${result}

</body>
</html>