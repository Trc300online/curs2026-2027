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
<!--
    <?php
/*        $name = "Dark Matter";
        $read = true;

        if($read) {
            $message = "You have read $name";
        } else {
            $message = "You have NOT read $name";
        }
    */?>

    <h1>

        <?php /*= $message */?>

    </h1>
-->
    <h1>
        Recommended Books
    </h1>

    <?php
        $books = [
            [
                "name" => "Do Androids Dream of Electric Sheep",
                "author" => "Philip K. Dick",
                "releaseYear" => "1968",
                "purchaseUrl" => "https://example.com"
            ],
            [
                "name" => "Project Hail Mary",
                "author" => "Andy Weir",
                "releaseYear" => "2021",
                "purchaseUrl" => "https://example.com"
            ],
            [
                "name" => "The Martian",
                "author" => "Andy Weir",
                "releaseYear" => "2011",
                "purchaseUrl" => "https://example.com"
            ]
        ];

        function filterByAuthor($books, $author) {
            $filteredBooks = [];

            foreach ($books as $book) {
                if ($book["author"] === $author) {
                    $filteredBooks[] = $book;
                }
            }

            return $filteredBooks;
        }
    ?>

    <ul>
        <?php /*foreach ($books as $book) : */?><!--
            <li><?php /*= $book */?></li>
        --><?php /*endforeach; */?>

        <?php foreach (filterByAuthor($books, "Andy Weir") as $book) : ?>
            <li>
                <a href="<?= $book["purchaseUrl"] ?>">
                    <?= $book["name"] ?> (<?= $book["releaseYear"]?>) - By <?= $book["author"] ?>
                </a>
            </li>
        <?php endforeach; ?>

    </ul>

    <!--<p>
        <?php /*= $books[1] */?>
    </p>-->

</body>
</html>

"Do Androids Dream of Electric Sheep",
"The Langoliers",
"Project Hail Mary"