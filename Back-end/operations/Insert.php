<?php

class Insert {

	private  $dbcon=null;

	public function __construct($dbcon){
		$this->dbcon=$dbcon;
	}

	public function insert_subscriptions($id_fk,$club_id_fk){

			$sql = "INSERT INTO subscriptions (id_fk,club_id_fk) VALUES (?,?)";

			$stmt = $this->dbcon->prepare($sql);
			$stmt->bind_param("ii", $id_fk,$club_id_fk);
			$stmt->execute();

			return $stmt->affected_rows==1;
	}

	public function insert_club($division,$district,$contact,$clubname,,$location,$id_fk){

			$sql = "INSERT INTO Club (division, district, clubname,contacts,location,id_fk) VALUES (?,?,?,?,?,?)";

			$stmt = $this->dbcon->prepare($sql);
			$stmt->bind_param("ssssss", $division,$district,$contact,$clubname,$location,$id_fk);
			$stmt->execute();

			return $stmt->affected_rows==1;
	}

	public function insert_event($club_id_fk,$event_name,$event_location,$event_date){

			$sql = "INSERT INTO event (club_id_fk,event_name,event_location,event_date) VALUES (?,?,?,?)";

			$stmt = $this->dbcon->prepare($sql);
			$stmt->bind_param("isss",$club_id_fk, $event_name,$event_location,$event_date);
			$stmt->execute();

			return $stmt->affected_rows==1;
	}

	public function insert_registration($username,$email,$password,$contact,$address){

			$sql = "INSERT INTO user (username,email,password,contact,address) VALUES (?,?,?,?,?)";

			$stmt = $this->dbcon->prepare($sql);
			$stmt->bind_param("sssss", $username,$email,$password,$contact,$address);
			$stmt->execute();

			return $stmt->affected_rows==1;
	}



	public function insert_cake($cakedescription,$cakeprice,$stock,$shopno){

			$sql = "INSERT INTO cake (cakedescription,cakeprice,stock,shopno) VALUES (?,?,?,?,?)";

			$stmt = $this->dbcon->prepare($sql);
			$stmt->bind_param("ssss", $cakedescription,$cakeprice,$stock,$shopno);
			$stmt->execute();

			return $stmt->affected_rows==1;
	}

}

  ?>
