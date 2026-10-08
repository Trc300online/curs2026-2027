<?php

require 'functions.php';
//require 'router.php';
require 'Database.php';
//. Database Tables and Indexes vid done
$config = require('config.php');

$db = new Database($config['database']);
$id = $_GET['id'];
$query = "select * from posts where id = :id";

$post = $db->query($query, ['id' => $id])->fetchAll();//fetch(PDO::FETCH_ASSOC);

dd($post);
/*foreach ($posts as $post) {
    echo "<li>" . $post["title"] . "</li>";
}*/
