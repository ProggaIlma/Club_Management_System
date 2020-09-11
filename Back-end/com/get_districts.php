<?php
$response = array();

	if($_SERVER['REQUEST_METHOD'] != 'POST'){
		$response['error'] = true;
		$response['message'] = "Error";

		echo json_encode($response);
		exit();
	}

	require_once (dirname(dirname(__FILE__)) . "/db/Database.php");

  require_once (dirname(dirname(__FILE__)) . "/operations/Select.php");

	$db = new Database();
	$dbcon = $db->db_connect();

	if(!isset($_POST['division'])){
		$response['error'] = true;
		$response['message'] = "Database Connection Error";

		echo json_encode($response);
		exit();
	}

	$district = $dbcon->real_escape_string($_POST['division']);
	$select = new Select($dbcon);

	$result = $select->get_districts($district);

  $response['district1'] = $result;

	echo json_encode($response);

?>
