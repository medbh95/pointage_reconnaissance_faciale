<?php
require ("DateBase.php");
$db = new DataBase();
if (isset($_GET['email']) && isset($_GET['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("user", $_GET['email'], $_GET['password'])) {
            echo "Connexion effectue avec success";
        } else echo "Nom d'utilisateur ou mot de passe incorrect";
    } else echo "Erreur de connexion";
} else echo "Vous devez remplir tous les champs";
?>
