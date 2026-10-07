<?php

require 'functions.php';
//require 'router.php';
require 'Database.php';

$config = require('config.php');

$db = new Database($config['database']);

$post = $db->query("select * from posts")->fetch(PDO::FETCH_ASSOC);

dd($post["title"]);
/*foreach ($posts as $post) {
    echo "<li>" . $post["title"] . "</li>";
}*/
