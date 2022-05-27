<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MIB's Spring App</title>
</head>
<body>
	<h2 style="text-align:center;">MIB's Spring App</h2>
	<h3>Display All Users</h3>
	<form action="getUsers" method="get">
		<input type="submit" value="Submit">
	</form>
	<hr>
	<h3>Search By ID</h3>
	<form action="getUser" method="get">
		<label for="id">Enter the id:</label><br>
		<input type="text" name="id" id="id"><br><br>
		<input type="submit" value="Submit">
	</form>
	<hr>
	<h3>Search By Name</h3>
	<form action="getUserByName" method="get">
		<label for="name">Enter the name:</label><br>
		<input type="text" name="name" id="name"><br><br>
		<input type="submit" value="Submit">
	</form>
	<hr>
	<h3>Add a User</h3>
	<form action="addUser" method="post">
		<label for="id">Enter the id:</label><br>
		<input type="text" name="id" id="id"><br><br>
		<label for="name">Enter the name:</label><br>
		<input type="text" name="name" id="name"><br><br>
		<input type="submit" value="Submit">
	</form>
	<hr>
	<h3>Delete a User</h3>
	<form action="deleteUser" method="post">
		<label for="id">Enter the id:</label><br>
		<input type="text" name="id" id="id"><br><br>
		<input type="submit" value="Submit">
	</form>
	<hr>
	
	<a href="test">Test</a>
	
	<br>
	<br>
	<h3>Search Results</h3>
	${result}

</body>
</html>