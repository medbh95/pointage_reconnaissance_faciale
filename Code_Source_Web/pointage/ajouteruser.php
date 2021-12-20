<?php
require ("DateBase.php");
$db = new DataBase();
$con=$db->dbConnect();
if(isset($_GET['id']) && isset($_GET['username']) && isset($_GET['password']) && isset($_GET['email']))
{
 $id= $_GET['id'];   
 $username= $_GET['username'];
 $password= $_GET['password'];
 $mail= $_GET['email'];
 
    $Sql_Query = "INSERT INTO `users`(`id`,`username`,`password`,`email`)
    values ('".$id."','".$username."','".$password."','".$mail."')";
   
    if(mysqli_query($con,$Sql_Query)){
    
    echo 'Ajoute avec succes';
    
    }
    else{
    
    echo 'Reessayer';
    
    }
     
 }

else{

echo 'parametre manquant';

}

?>