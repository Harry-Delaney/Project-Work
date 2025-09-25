<?php
// Start the session and ensure the session cookie expires when the browser is closed
ini_set('session.cookie_lifetime', 0);  // Set the session cookie to expire when the browser closes
session_start();

// Check if the user is logged in
$isLoggedIn = isset($_SESSION['username']);
?>

<?php include('header.php'); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.phptutorial.net/app/css/style.css">
    <link rel="stylesheet" href="test.css">
    <title>Home</title>
    <style>
        .center-text {
            text-align: center;
            margin: 10px 0;
        }
    </style>
</head>
<body>
<main>
    <h1>Welcome to the Book Search Website</h1>

    <?php if ($isLoggedIn): ?>
        <p>Hello, <?php echo htmlspecialchars($_SESSION['username']); ?>! You are logged in.</p>
        <p>On this website you can search for and resver books, please follow the buttons below!</p>
        
        <!-- Button to navigate to the search page -->
        <form action="search.php">
            <button type="submit" class="gotosearchbtn">Go to Book Search</button>
        </form>

        <!-- Button to navigate to reservations page -->
        <form action="reserve.php">
            <button type="submit" class="gotoreservebtn">Go to Reservations</button>
        </form>

        <!-- Logout button -->
        <form action="logout1.php" method="POST">
            <button type="submit" class="logoutbtn">Logout</button>
        </form>*
    
    <?php else: ?>
        <p>You are not logged in. Please log in to access the book search and reservations.</p>
        
        <!-- Button to navigate to the login page -->
        <form action="login.php">
            <button type="submit" class="loginbtn">Go to Login</button>
        </form>

        <!-- Centered text -->
        <p class="center-text">Don't have an account, register here!</p>
        
        <!-- Button to navigate to the registration page -->
        <form action="register.php">
            <button type="submit" class="registerbtn">Register an Account</button>
        </form>
    <?php endif; ?>

</main>

</body>
</html>

<?php include('footer.php'); ?>
