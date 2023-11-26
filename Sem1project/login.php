<?php
require_once "conf.php";
require_once "session.php";
$error = '';

if ($_SERVER["Request_Method"] == "post" && isset($_POST['submit'])){
}
    $email = trim($_POST['email']);
    $password = trim($_POST['password']);

    if(empty($email)){
        $error .= '<p class="error">Enter the email.</p>';
    }
    if(empty($password)){
        $error .='<p class="error">Enter the password.</p>';
    }
    if(empty($error)){
        if($query = $db->prepare("SELECT * FROM users WHERE email = ?")){
            $query->bind_param("s", $email);
            $query->execute();
            $row =$query->get_result();
            if($row){
                if(password_verify($password, $row->password)){ 
                    $_SESSION["userid"] = $row->id;
                    $_SESSION["user"] = $row;
                    header("location: welcome.php");
                    exit;
            }
            else{
                $error .= '<p class="error">Password is not valid.</p>';
            }

        }
        else{
            $error .= '<p class="error">Email Address is not tied to any user.</p>';
        }
        
    }
    quer
}