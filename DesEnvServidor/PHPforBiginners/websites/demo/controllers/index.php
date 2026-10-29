<?php

$_SESSION['name'] = 'Toni';

view("index.view.php", [
    'heading' => 'Home',
]);