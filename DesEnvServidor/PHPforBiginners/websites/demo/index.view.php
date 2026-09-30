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

<ul>
    <?php /*foreach ($books as $book) : */?><!--
            <li><?php /*= $book */?></li>
        --><?php /*endforeach; */?>

    <?php foreach ($filteredBooks as $book) : ?>
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