<?php
$response = array();

	if($_SERVER['REQUEST_METHOD'] != 'POST'){
		$response['error'] = true;
		$response['message'] = "Error";

		echo json_encode($response);
		exit();
	}

	require_once (dirname(dirname(__FILE__)) . "/db/Database.php");
	include_once(dirname(dirname(__FILE__)) . "/operations/Insert.php");

	$db = new Database();
	$dbcon=$db->db_connect();

	if(!$dbcon){
		$response['error'] = true;
		$response['message'] = "Database Connection Error";

		echo json_encode($response);
		exit();
	}

	//begina

		if(!isset($_POST['username']) || !isset($_POST['email']) || !isset($_POST['password'])
		|| !isset($_POST['Institution'])){
			$response['error'] = true;
			$response['message'] = "Data Missing!";

			echo json_encode($response);
			exit();

		}

		// get data from url

		$username = $dbcon->real_escape_string($_POST['username']);
		$email = $dbcon->real_escape_string($_POST['email']);
		$password = $dbcon->real_escape_string($_POST['password']);
		$Institution = $dbcon->real_escape_string($_POST['Institution']);



		$insert = new Insert($dbcon);

		$result = $insert->insert_registration($username,$email,$password,$Institution);

		$response['error']= !$result;
		$response['message']=$result?"Registration Successful":"Registration Failed";

		echo json_encode($response);

	?>
