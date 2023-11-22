<?php
define('Dbserver', 'localhost');
define('Dbusername','root');
define('Dbpassword','?NXQP5A$?jhcA@M4');
define('Dbname','registration page');

$db = mysqli_connect(Dbserver, Dbusername, Dbpassword, Dbname);

if($db === false){
    die("Connection error " . mysqli_connect);
}
