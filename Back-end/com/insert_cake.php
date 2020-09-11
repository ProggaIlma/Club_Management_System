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


	if(!isset($_POST['cakedescription']) || !isset($_POST['cakeprice']) || !isset($_POST['stock'])
|| !isset($_POST['shopno'])){
		$response['error'] = true;
		$response['message'] = "Data Missing!";

		echo json_encode($response);
		exit();
	}

	// get data from url

	$shopname = $dbcon->real_escape_string($_POST['cakedescription']);
	$address = $dbcon->real_escape_string($_POST['cakeprice']);
  $contact = $dbcon->real_escape_string($_POST['stock']);
  $contact = $dbcon->real_escape_string($_POST['shopno']);

	$insert = new Insert($dbcon);

	$result = $insert->insert_cake($cakedescription,$cakeprice,$stock,$shopno);

	echo json_encode($response);

?>
