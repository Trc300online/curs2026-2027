<?php

/*$name = "Laracasts";
$cost = 15;*/

$business = [
        'name' => 'Laracasts',
        'cost' => 15,
        'categories' => ["Testing", "PHP", "JavaScript"]
];

function register($user) {
    // Create the user record in the db
    // Log them in.
    // Send a welcome email
    // Redirect to their new dashboard
}

/*
foreach ($business['categories'] as $category) {
    echo $category . <br>;
}
*/

/*
if ($business['cost'] > 99) {
    echo "Not interested.";
}*/

// access to data --> $business['name']; <-- Laracasts

require "index.view.php";