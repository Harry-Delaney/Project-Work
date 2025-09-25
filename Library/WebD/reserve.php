<?php

include('header.php');
// Enable error reporting to help with debugging
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Start the session to maintain login status
session_start();

// Include database connection
require_once "database.php";

// Check if the user is logged in, if not, show an error or redirect to login page
if (!isset($_SESSION['username'])) {
    // Redirect to login page if the user is not logged in
    header("Location: login.php"); 
    exit();  // Make sure to exit after redirect to prevent further code execution
}

// Check the connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Fetch reserved books for the logged-in user
$username = $_SESSION['username'];
$reservedBooksSql = "SELECT r.ISBN, b.BookTitle, b.Author, r.ReservedDate 
                     FROM reservations r 
                     JOIN books b ON r.ISBN = b.ISBN 
                     WHERE r.Username = '$username' AND b.Reserved = 'Y'";

$reservedBooksResult = $conn->query($reservedBooksSql);

// Handle unreserve action
if (isset($_GET['unreserve'])) {
    $isbnToUnreserve = $conn->real_escape_string($_GET['unreserve']);  // Get the ISBN of the book to unreserve

    // Update the books table to mark the book as not reserved
    $updateReservedSql = "UPDATE books SET Reserved = 'N' WHERE ISBN = '$isbnToUnreserve'";

    // Delete the reservation record from the reservations table
    $deleteReservationSql = "DELETE FROM reservations WHERE ISBN = '$isbnToUnreserve' AND Username = '$username'";

    if ($conn->query($updateReservedSql) === TRUE && $conn->query($deleteReservationSql) === TRUE) {
        echo "Book unreserved successfully.";
    } else {
        echo "Error unreserving the book: " . $conn->error;
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.phptutorial.net/app/css/style.css">
    <link rel="stylesheet" href="test.css">
    <title>Reserved Books</title>
</head>
<body>

<main>
    <h1>Your Reserved Books</h1>

    <div class="container">
        <?php if ($reservedBooksResult->num_rows > 0): ?>
            <ul>
                <?php while ($row = $reservedBooksResult->fetch_assoc()): ?>
                    <li>
                        <strong>Book Title:</strong> <?php echo htmlspecialchars($row['BookTitle']); ?><br>
                        <strong>Author:</strong> <?php echo htmlspecialchars($row['Author']); ?><br>
                        <strong>Reserved Date:</strong> <?php echo htmlspecialchars($row['ReservedDate']); ?><br>
                        <a href="reserve.php?unreserve=<?php echo urlencode($row['ISBN']); ?>">Unreserve</a>
                    </li>
                <?php endwhile; ?>
            </ul>
        <?php else: ?>
            <p>You have no reserved books at the moment.</p>
        <?php endif; ?>
    </div>

    <p>If you're not logged in, you'll be redirected to the login page.</p>

</main>

</body>
</html>

<?php
// Close the database connection
$conn->close();
include('footer.php');
?>
