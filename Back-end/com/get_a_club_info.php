<?php
$response = array();
$p=array();

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

	if(!isset($_POST['club_id'])){
		$response['error'] = true;
		$response['message'] = "Data Missing!";

		echo json_encode($response);
		exit();
	}

	// get data from url

	$club_id = $dbcon->real_escape_string($_POST['club_id']);

	$select = new Select($dbcon);

	$result = $select->getmyclubinfo($club_id);

	$success = $result->num_rows==1;



	if($success){
		$p[]=$result->fetch_array(MYSQLI_ASSOC);
		$response['clubinfo'] = $p;
	}
else {
	$response['error'] = !$success;
	//$response['message'] = $success?"Found!":"Not Found!";
}
	echo json_encode($response);

?>
