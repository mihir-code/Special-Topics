<?php
session_start();
if (isset($_SESSION["userid"]) && $_SESSIOn["userid"] === true) {
    header("location: welcome.php");
    exit;
}