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

		if(!isset($_POST['club_id_fk']) || !isset($_POST['id_fk'])){
			$response['error'] = true;
			$response['message'] = "Data Missing!";

			echo json_encode($response);
			exit();

		}

		// get data from url

		$club_id_fk = $dbcon->real_escape_string($_POST['club_id_fk']);
		$id_fk = $dbcon->real_escape_string($_POST['id_fk']);
    $insert = new Insert($dbcon);
    $result = $insert->insert_subscriptions($club_id_fk,$id_fk);

		echo json_encode($response);

	?>
