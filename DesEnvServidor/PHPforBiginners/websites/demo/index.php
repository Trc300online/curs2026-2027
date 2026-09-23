<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demo</title>
    <style>
        body {
            display: grid;
            place-items: center;
            height: 100vh;
            margin: 0;
            font-family: sans-serif;
        }
    </style>
</head>
<body>

    <?php
        $name = "Dark Matter";
        $read = true;
    ?>

    <h1>
        
        You have read "<?php echo $name; ?>."
        <?php
           /* $greeting = "Hello";
            echo "$greeting Everybody!";*/
        ?>
    </h1>

</body>
</html>