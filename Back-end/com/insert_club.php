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

	// begina


	if(!isset($_POST['division']) || !isset($_POST['district']) || !isset($_POST['contacts'])||
	 !isset($_POST['clubname']) || !isset($_POST['location'])|| !isset($_POST['id_fk'])){
		$response['error'] = true;
		$response['message'] = "Data Missing!";

		echo json_encode($response);
		exit();
	}
echo "1";
	// get data from url

	$division = $dbcon->real_escape_string($_POST['division']);
	$district = $dbcon->real_escape_string($_POST['district']);
  $contact = $dbcon->real_escape_string($_POST['contacts']);
	$clubname = $dbcon->real_escape_string($_POST['clubname']);
	$location = $dbcon->real_escape_string($_POST['location']);
  $id_fk = $dbcon->real_escape_string($_POST['id_fk']);
	echo "2";

	$insert = new Insert($dbcon);
	echo "3";

	$result = $insert->insert_club($division,$district,$contact,$clubname,$location,$id_fk);
echo "4";
	echo json_encode($response);

?>
