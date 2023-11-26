<?php
require_once "conf.php";
require_once "session.php";
$error = '';

if ($_SERVER["Request_Method"] == "post" && isset($_POST['submit'])){
    $email = trim($_POST['email']);
    $password = trim($_POST['password']);

    if(empty($email)){
        $error .= '<p class="error">Enter the email.</p>';
    }
    if(empty($password)){
        $error .='<p class="error">Enter the password.</p>';
    }
    if(empty($error)){
        if($query = $db->prepare("SELECT * FROM users WHERE email = ?"))
    }
}