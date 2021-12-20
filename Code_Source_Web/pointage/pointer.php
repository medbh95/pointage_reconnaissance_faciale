<?php
include "config.php";

if(isset($_GET['username']) && isset($_GET['type'])){

    $uname = $_GET['username'];
    $type = $_GET['type'];
    
   

        $sql_query = "INSERT INTO `pointer` (`date`, `nom`, `type`) VALUES (current_timestamp(), '".$uname."', '".$type."')";
        $result = mysqli_query($con,$sql_query);

        if($result){
            echo "OK";
        } else{
            echo "Erreur";
        }
 
}
?>