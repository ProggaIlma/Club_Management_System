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

	if(!$dbcon){
		$response['error'] = true;
		$response['message'] = "Database Connection Error";

		echo json_encode($response);
		exit();
	}
	//begin
	if(!isset($_POST['club_id_fk'])){
		$response['error'] = true;
		$response['message'] = "Data Missing!";

		echo json_encode($response);
		exit();
	}
	$club_id_fk = $dbcon->real_escape_string($_POST['club_id_fk']);

	$select = new Select($dbcon);

	$result = $select->get_events($club_id_fk);

  //$response['events'] = $result;
	if(true){
		$phparray = array();

		while($row=$result->fetch_array(MYSQLI_ASSOC)){
			$phparray[] = $row;
		}
		$response['events'] = $phparray;


	echo json_encode($response);}
	else
	{$response['error'] = !$success;
		$response['message'] = $success?"Found!":"Not Found!";

		echo json_encode($response);
	}


?>
