<?php

// Connect to the databse, and execute a query.
class Database {

    public $connection;

    public function __construct() {
        $dsn = "mysql:host=127.0.0.1;port=3306;dbname=php_videos;charset=utf8mb4";

        $this->connection = new PDO($dsn, "root", "1234");
    }
    public function query($query) {

        $statement = $this->connection->prepare($query);
        $statement-> execute();

        return $statement;


    }
}

$db = new Database();

$post = $db->query("select * from posts")->fetch(PDO::FETCH_ASSOC);

dd($post["title"]);
/*foreach ($posts as $post) {
    echo "<li>" . $post["title"] . "</li>";
}*/
