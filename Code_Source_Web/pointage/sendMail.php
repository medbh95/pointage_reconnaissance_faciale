<?php
 use PHPMailer\PHPMailer\PHPMailer;
 use PHPMailer\PHPMailer\Exception;
 
 require 'src/Exception.php';
 require 'src/PHPMailer.php';
 require 'src/SMTP.php';

if(isset($_GET['email'])&&isset($_GET['name'])&&isset($_GET['object'])&&isset($_GET['message'])) {
$email=$_GET['email'];
$name=$_GET['name'];
$obj=$_GET['object'];
$msg=$_GET['message'];

  // en sachant qu'on a fais une condition dans le code insertion de user pour ne pas inserer un user avec email deja existant dans la base ;)
  //envoie d'email avec user name et  mot de passe ******************************************************************************
 $mail = new PHPMailer;
$mail->isSMTP(); 
$mail->SMTPDebug = 2; // 0 = off (for production use) - 1 = client messages - 2 = client and server messages
$mail->Host = "smtp.gmail.com"; // use $mail->Host = gethostbyname('smtp.gmail.com'); // if your network does not support SMTP over IPv6
$mail->Port = 587; // TLS only
$mail->SMTPSecure = 'tls'; // ssl is depracated
$mail->SMTPAuth = true;
$mail->Username = "infomedbh@gmail.com";
$mail->Password = "Azerty1995";
$mail->setFrom("infomedbh@gmail.com", "Application de Pointage");
$mail->addAddress($email, "Username");
$mail->Subject = $obj;
$mail->msgHTML($msg); //$mail->msgHTML(file_get_contents('contents.html'), __DIR__); //Read an HTML message body from an external file, convert referenced images to embedded,
$mail->AltBody = 'HTML messaging not supported';
// $mail->addAttachment('/pointage.png'); //Attach an image file

if(!$mail->send()){
    echo "Mailer Error: " . $mail->ErrorInfo;
}else{
    echo "Message sent!";
}
 
}

else{

echo 'parametre manquant';

}
?>