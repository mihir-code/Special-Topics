<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $email = $_POST["email"];
    $psw = $_POST["psw"];

    $validemail ="mihir.motukuri@gmail.com";
    $validpass ="1234";

    if ($email === $validemail && $psw === $validpass) {
        header("Location: ricepage.php");
        exit();
    }
    else{
        header("Location: firstpage.html");
        exit();
    }


}

?>