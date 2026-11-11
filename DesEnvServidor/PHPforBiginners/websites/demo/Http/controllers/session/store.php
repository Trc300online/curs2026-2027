<?php

use Core\Authenticator;
use Http\Forms\LoginForm;


$form = LoginForm::validate($attributes = [
    'email' => $_POST['email'],
    'password' => $_POST['password']
]);

$singnedIn = (new Authenticator)->attempt($attributes['email'], $attributes['password']);

if (!$singnedIn) {
    $form->error('email', 'No matching account found for that email address and password.') -> throw();
}

redirect('/');
