<?php
$response = array();

	if($_SERVER['REQUEST_METHOD'] != 'POST'){
		$response['error'] = true;
		$response['message'] = "Error";

		echo json_encode($response);
		exit();
	}

	require_once (dirname(dirname(__FILE__)) . "/db/Database.php");
	include_once(dirname(dirname(__FILE__)) . "/operations/Select.php");

	$db = new Database();
	$dbcon=$db->db_connect();

	if(!$dbcon){
		$response['error'] = true;
		$response['message'] = "Database Connection Error";

		echo json_encode($response);
		exit();
	}

	// begina

	if(!isset($_POST['district'])){
		$response['error'] = true;
		$response['message'] = "Data Missing!";

		echo json_encode($response);
		exit();
	}

	// get data from url

	$district = $dbcon->real_escape_string($_POST['district']);


	$select = new Select($dbcon);

	$result = $select->clubs($district);

	$success = $result->num_rows>=1;


	//$response['message'] = $success?"Found!":"Not Found!";

	if($success){
		$phparray = array();

		while($row=$result->fetch_array(MYSQLI_ASSOC)){
			$phparray[] = $row;
		}
		$response['clubs'] = $phparray;


	echo json_encode($response);}
	else
	{$response['error'] = !$success;
		$response['message'] = $success?"Found!":"Not Found!";

		echo json_encode($response);
	}

?>
