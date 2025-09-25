<?php
// Start the session
session_start();

// Destroy the session and clear all session data
session_unset();
session_destroy();

// Clear the session cookie by setting the expiration date to the past
setcookie(session_name(), '', time() - 3600, '/');

// Redirect the user to the login page
header("Location: login.php");
exit();
?>
