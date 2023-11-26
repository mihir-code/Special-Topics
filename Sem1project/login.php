<?php
require_once "conf.php";
require_once "session.php";
$error = '';

if ($_SERVER["Request_Method"] == "post" && isset($_POST['submit'])){
    $email = trim($_POST["Email"]);
    $password = trim($_POST["Password"]);
    if(empty($email)){
        $error .= '<p class="error"> Please enter in your email.</p>';
    }
    if(empty($password)){
        $error .= '<p class="error"> Please enter in your password.</p>';
    }
    if(empty($error)){
        if($query = $db->prepare("SELECT * FROM users WHERE email =?")){
            $query->bind_param("s",$email);
            $query->execute();
            $row = $query->get_result();
            if($row){
                if(password_verify($password,$row["password"])){
                    $_SESSION["userid"] = $row["id"];
                    $_SESSION["user"] =$row;
                    header("location: welcome.php");
                    exit;
                }
                else{
                    $error .= '<p class="error"> Wrong password. Try again.</p>';
                }
            }
            else{
                $error .= '<p class="error">Unknown email address. Try again.</p>';
            }
        }
        $query->close();
    }
    mysqli_close($db);
}

    // find a way to tie in previous welcome page.

