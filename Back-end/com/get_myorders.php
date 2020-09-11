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

	if(!isset($_POST['orderno']) || !isset($_POST['userno'])){
		$response['error'] = true;
		$response['message'] = "Data Missing!";

		echo json_encode($response);
		exit();
	}

	// get data from url

	$orderno = $dbcon->real_escape_string($_POST['orderno']);
	$userno = $dbcon->real_escape_string($_POST['userno']);

	$select = new Select($dbcon);

	$result = $select->myorders_info($orderno, $userno);

	$success = $result->num_rows==1;

	$response['error'] = !$success;
	$response['message'] = $success?"Found!":"Not Found!";

	if($success){
		$response['myorderinfo'] = $result->fetch_array(MYSQLI_ASSOC);
	}

	echo json_encode($response);

?>
