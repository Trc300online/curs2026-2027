<?php

require 'functions.php';

//require 'router.php';

//DONE create a MySQL Databas vid

// connct to our MySQL databas:

/*class Person {
    public $name;
    public $age;

    public function breathe() {
        echo $this->name . ' is breathing!';
    }
}

$person = new Person();

$person->name = "John Doe";
$person->age = 25;

$person->breathe();*/

$dsn = "mysql:host=127.0.0.1;port=3306;dbname=php_videos;charset=utf8mb4";
$pdo = new PDO($dsn, "root", "1234");

$statement = $pdo->prepare("select * from posts");
$statement-> execute();

$posts = $statement->fetchAll(PDO::FETCH_ASSOC);

foreach ($posts as $post) {
    echo "<li>" . $post["title"] . "</li>";
}