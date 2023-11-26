<?php
session_start();
if(!isset($_SESSION["userid"]) || $_SESSION["userID"] !== true){
    header("location: login.php");
    exit;
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>
            Rice App!
        </title>
        <link rel="stylesheet" href="./firstpage.css">
    </head>
    <img src ="ricecooker.png" alt="RICE!" class="center">
    <h1>
        <span class="firstcharacter">U</span>
        sing sophisiticated calculus, we'll be able to provide an answer to a question asked by billions all over the world.
    </h1>
    <br></br>

    <p>
        We take inputs like the number of people, consistency of rice, cups of water, etc to provide you, the Customer, 
        with the exact amount of rice necessary. According to a global report <b>900 million tonnes of food</b> is thrown away every single year.
    </p>
        <pre><br>That's insane.</br> </pre>
    <p2>   
        <article>And rice is a big part of it. Just in the Phillipines, <b> 308,000 tons</b> is wasted each
        year. The food waste problem also affects the world's battle against climate change. The wasted rice in Asia alone emits <b>610.5 million tonnes</b>
        of CO<sub>2</sub> a year! </article>

    <br> While that's insane, I unfortunately cannot do much to stop that. However, I can start the change with my family. 
    We love rice and eat it around 3-4 times a week. I've recently started to measure the rice myself and notice that a lot of the food gets wasted based on 
bad math. 
    </p2>
    <pre>To solve this problem, I've come up with the innovative solution, RICER, to solve my family's problem indefinitely.</pre></br>
    <p3>If you are interested in doing the right thing, please scroll down and create an account!</p3>
    <head>
        <link rel="stylesheet" href="test1.css">
        <script defer src="login.js"></script>
    </head>

</html>