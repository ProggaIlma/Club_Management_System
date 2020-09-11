<?php

//echo "Hello";

$conn = mysqli_connect("localhost", "root", "sakifa", "cakeshop");

if($conn){

	$username = $_GET['email'];
	$password= $_GET['password'];

	//echo $username . ' ' . $password;

	$sql = "SELECT * FROM user WHERE  password='$password'";

	//echo $sql;

	$result = $conn->query($sql);
	if($result->num_rows>0){
		while($row =  $result->fetch_array(MYSQLI_ASSOC)){
			echo $row['username'].'<br>';
		}


	}
	else{
		echo "NO USER FOUND!";
	}
}else{
	echo "Error";
}

?>
