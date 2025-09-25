<?php
include('header.php');
session_start();
require "database.php";

if (isset($_POST['username']) && isset($_POST['password'])) {
    $username = $conn->real_escape_string($_POST['username']);
    $password = $conn->real_escape_string($_POST['password']);

    $sql = "SELECT password FROM users WHERE username = '$username'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $dbPassword = $row['password'];

        if ($password == $dbPassword) {
            $_SESSION['username'] = $username;
            header("Location: search.php");
            exit();
        } else {
            echo "Invalid password.";
        }
    } else {
        echo "The username $username does not exist in the database.";
    }

    $result->free();
    include('footer.php');
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.phptutorial.net/app/css/style.css">
    <link rel="stylesheet" href="test.css">
    <title>Login</title>
</head>
<body>
<main>
<form action="login.php" method="POST">
    <div class="container">
        <h1>Login</h1>
        <p>Please enter your details.</p>
        <hr>

        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" id="username" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>

        <button type="submit" class="loginbtn">Login</button>
    </div>
</form>
</main>
</body>
</html>
<?php
include('footer.php');
?>
