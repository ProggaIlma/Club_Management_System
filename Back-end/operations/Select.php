<?php
    //session_start();
    //include_once(dirname(__FILE__)."/Utility.php");

    class Select{
      private  $dbcon;

      public function __construct($dbcon){
        //$this->_validateUser();
        $this->dbcon=$dbcon;

      }

      public function verify_user($email, $password){

        $sql = "SELECT * FROM student WHERE email=? AND password=?";

        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("ss", $email, $password);
        $stmt->execute();

        $result = $stmt->get_result();

        return $result;

      }

      public function get_districts($district){

        $sql = "SELECT district FROM divi WHERE division=?";

        $stmt = $this->dbcon->prepare($sql);
            $stmt->bind_param("s", $district);
        $stmt->execute();

        $databaserowarray = $stmt->get_result();

        $phparray = array();

        while($row=$databaserowarray->fetch_array(MYSQLI_ASSOC)){
          $phparray[] = $row;
        }

        return $phparray;

      }
      public function clubs($district){

        $sql = "SELECT clubname,id_fk FROM Club WHERE district=?";

        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("s", $district);
        $stmt->execute();

        $databaserowarray = $stmt->get_result();

        /*$phparray = array();

        while($row=$databaserowarray->fetch_array(MYSQLI_ASSOC)){
          $phparray[] = $row;
        }
        */

        return $databaserowarray;

      }
      public function getmyclubs($id_fk){

        $sql = "SELECT clubname,club_id FROM Club WHERE id_fk=?";
        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("i", $id_fk);
        $stmt->execute();
        $databaserowarray = $stmt->get_result();
        return $databaserowarray;

      }

      public function getmyclubinfo($club_id){

        $sql = "SELECT * FROM Club WHERE club_id=?";
        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("i", $club_id);
        $stmt->execute();
        $databaserowarray = $stmt->get_result();
        return $databaserowarray;

      }
      public function get_events($club_id_fk){

        $sql = "SELECT event_name,event_id FROM event WHERE club_id_fk=?";

        $stmt = $this->dbcon->prepare($sql);
    $stmt->bind_param("i", $club_id_fk);
        $stmt->execute();

        $databaserowarray = $stmt->get_result();



        return $databaserowarray;

      }

      public function event_info($event_id){

        $sql = "SELECT * FROM event WHERE event_id=?";

        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("i", $event_id);
        $stmt->execute();

        $result = $stmt->get_result();

        return $result;

      }
      public function student_info($userno){

        $sql = "SELECT * FROM student WHERE Id=?";

        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("i", $userno);
        $stmt->execute();

        $result = $stmt->get_result();

        return $result;

      }

      public function myshops_info($userno){

        $sql = "SELECT * FROM shop WHERE userno=?";

        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("i", $userno);
        $stmt->execute();

        $result = $stmt->get_result();

        return $result;

      }

      public function myorders_info($orderno, $userno){

        $sql = "SELECT * FROM cakeorder WHERE orderno=? AND cakeno IN
        (SELECT cakeno FROM cake WHERE shopno IN (SELECT shopno FROM shop
          WHERE userno=? ))";

        $stmt = $this->dbcon->prepare($sql);
        $stmt->bind_param("ii",$orderno, $userno);
        $stmt->execute();

        $result = $stmt->get_result();

        return $result;

      }

    }

?>
