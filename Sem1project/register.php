<?php

require_once "config.php";
require_once "session.php";

if ($_SERVER["request_method"] == "post" && isset($_POST['submit'])){

    $fname = trim($_POST['name']);
    $password = trim($_POST['email']);
    $hash = hash($password,PASSWORD_BCRYPT);
    $confirm = trim($_POST["confirm"]);
    $email = trim($_POST['email']);


    if($query = $db->prepare("SELECT * FROM users WHERE mail =?")){
        $error = '';
    }
    $query->bind_param('s', $email);
    $query->execute();
    $query->store_result();
    if($query->num_rows > 0){
        $error .= '<p class="error"> The email address is already registered! Please try again</p>';
    }
    else {
        if(strlen($password) < 10){
            $error .='<p class="error"> The password needs to be more than 10 characters. Please try again</p>';
        }
        if(empty($confirm)){
            $error .='<p class="error"> Type the password in again for confirmation.</p>';
        }
        else {
            if(empty($error) && ($password != $confirm)){
                $error .= '<p class="error"> Password failed. Try again.</p>';
            }
        }
        if(empty($error) ){
            $insertQuery = $db ->prepare("INSERT INTO users (name,email,password) Values(?,?,?); ");
            $insertQuery->bind_param("sss", $fname, $email, $hash);
            $result =  $insertQuery ->execute();

            if($result){
                $error .='<p class="nice job"> You did it. You are officially a genius!</p>';
            }
            else{
                $error .='<p class="error"> You failed. Try again</p>';
            }
        }
    }

    $query->close();
    $insertQuery->die();
    mysqli_close($db);

}
?>