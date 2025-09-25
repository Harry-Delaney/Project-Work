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

// Pagination settings
$booksPerPage = 5;  // Number of books to display per page

// Get the current page number, default is 1
$page = isset($_GET['page']) ? (int)$_GET['page'] : 1;
$offset = ($page - 1) * $booksPerPage;  // Calculate the offset for the SQL query

// Query to fetch categories for the dropdown
$sqlCategories = "SELECT CategoryID, CategoryDescription FROM categories"; // Ensure both CategoryID and CategoryDescription are selected
$categoriesResult = $conn->query($sqlCategories);

// Check if the query was successful
if ($categoriesResult === false) {
    die("Error fetching categories: " . $conn->error . "<br>SQL Query: $sqlCategories");
}

// Handle search query if submitted
$BookTitle = isset($_POST['BookTitle']) ? $conn->real_escape_string($_POST['BookTitle']) : (isset($_GET['BookTitle']) ? $conn->real_escape_string($_GET['BookTitle']) : '');
$Author = isset($_POST['Author']) ? $conn->real_escape_string($_POST['Author']) : (isset($_GET['Author']) ? $conn->real_escape_string($_GET['Author']) : '');
$CategoryID = isset($_POST['CategoryID']) ? $conn->real_escape_string($_POST['CategoryID']) : (isset($_GET['CategoryID']) ? $conn->real_escape_string($_GET['CategoryID']) : '');

// Perform the search query with LIKE for partial matches (only at the beginning)
$sql = "SELECT ISBN, BookTitle, Author, CategoryID, Reserved FROM books WHERE 1=1";  // Make sure we include BookTitle and Author for identification

// Match BookTitle starting with the given value
if (!empty($BookTitle)) {
    $sql .= " AND BookTitle LIKE '$BookTitle%'";  // Match if BookTitle starts with the search term
}

// Match Author starting with the given value
if (!empty($Author)) {
    $sql .= " AND Author LIKE '$Author%'";  // Match if Author starts with the search term
}

// Exact match for CategoryID
if (!empty($CategoryID)) {
    $sql .= " AND CategoryID = '$CategoryID'";  // Exact match for CategoryID
}

// Get total number of books for pagination
$totalResult = $conn->query($sql);
if ($totalResult === false) {
    die("Error fetching total books: " . $conn->error);
}

$totalBooks = $totalResult->num_rows;
$totalPages = ceil($totalBooks / $booksPerPage);  // Calculate total pages

// Add LIMIT and OFFSET to the query for pagination
$sql .= " LIMIT $booksPerPage OFFSET $offset";

// Fetch the search results
$result = $conn->query($sql);

// Handle book reservation
if (isset($_GET['reserve'])) {
    $isbn = $conn->real_escape_string($_GET['reserve']);  // Get the ISBN of the book to reserve
    $username = $_SESSION['username'];  // Get the logged-in username
    $reservedDate = date('Y-m-d H:i:s');  // Current date and time as the reservation date

    // Check if the book is already reserved
    $checkReservedSql = "SELECT Reserved FROM books WHERE ISBN = '$isbn'";
    $checkReservedResult = $conn->query($checkReservedSql);
    $book = $checkReservedResult->fetch_assoc();

    if ($book && $book['Reserved'] === 'N') {
        // Update the books table to mark the book as reserved
        $updateReservedSql = "UPDATE books SET Reserved = 'Y' WHERE ISBN = '$isbn'";

        // Insert a new record into the reservations table
        $reserveSql = "INSERT INTO reservations (ISBN, Username, ReservedDate) VALUES ('$isbn', '$username', '$reservedDate')";

        if ($conn->query($updateReservedSql) === TRUE && $conn->query($reserveSql) === TRUE) {
            echo "Book reserved successfully.";
        } else {
            echo "Error reserving the book: " . $conn->error;
        }
    } else {
        echo "This book is already reserved.";
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
    <title>Book Search</title>
</head>
<body>

<main>
    <h1>Search for Books</h1>

    <div class="container">
    
    <!-- Search form -->
    <form action="search.php" method="POST">
        <label for="BookTitle"><b>Book Title</b></label>
        <input type="text" placeholder="Enter Book Title" name="BookTitle" id="BookTitle" value="<?php echo htmlspecialchars($BookTitle); ?>">
        
        <label for="Author"><b>Author</b></label>
        <input type="text" placeholder="Enter Author" name="Author" id="Author" value="<?php echo htmlspecialchars($Author); ?>">

        <label for="CategoryID"><b>Category</b></label>
        <select name="CategoryID" id="CategoryID">
            <option value="">Select Category</option>
            <?php
            // Check if categories are available
            if ($categoriesResult->num_rows > 0) {
                // Loop through the result and display both CategoryID and CategoryDescription
                while ($category = $categoriesResult->fetch_assoc()) {
                    $selected = ($CategoryID == $category['CategoryID']) ? 'selected' : '';
                    // Display CategoryID and CategoryDescription in the dropdown
                    echo "<option value='" . $category['CategoryID'] . "' $selected>" . htmlspecialchars($category['CategoryID']) . " - " . htmlspecialchars($category['CategoryDescription']) . "</option>";
                }
            } else {
                echo "<option value=''>No categories available</option>";
            }
            ?>
        </select>

        <button type="submit" class="registerbtn">Search</button>
    </form>
    </div>

    <div class="search-results">
        <?php if (!empty($BookTitle) || !empty($Author) || !empty($CategoryID)): ?>
        <form action="search.php" method="GET">
            <?php
            // Check if there are search results
            if ($result->num_rows > 0) {
                echo "<ul>";  // Start unordered list
                while ($row = $result->fetch_assoc()) {
                    echo "<li><strong>Book Title:</strong> " . htmlspecialchars($row['BookTitle']) . "<br><strong>Author:</strong> " . htmlspecialchars($row['Author']) . "<br>";
                    // Check if the book is already reserved
                    if ($row['Reserved'] === 'N') {
                        echo "<a href='search.php?page=$page&BookTitle=" . urlencode($BookTitle) . "&Author=" . urlencode($Author) . "&CategoryID=" . urlencode($CategoryID) . "&reserve=" . urlencode($row['ISBN']) . "'>Reserve this book</a>";  // Using ISBN
                    } else {
                        echo "<span>Reserved</span>";
                    }
                    echo "</li>";
                }
                echo "</ul>";  // End unordered list
            } else {
                echo "No books found matching the search criteria.";
            }

            // Pagination controls
            if ($totalPages > 1) {
                echo "<div class='pagination'>";

                // Previous page link
                if ($page > 1) {
                    echo "<a href='search.php?page=" . ($page - 1) . "&BookTitle=" . urlencode($BookTitle) . "&Author=" . urlencode($Author) . "&CategoryID=" . urlencode($CategoryID) . "'>Previous</a>";
                }

                // Next page link
                if ($page < $totalPages) {
                    echo "<a href='search.php?page=" . ($page + 1) . "&BookTitle=" . urlencode($BookTitle) . "&Author=" . urlencode($Author) . "&CategoryID=" . urlencode($CategoryID) . "' class='next'>Next</a>";
                }
                

                echo "</div>";
            }
            ?>
        </form>
        <?php endif; ?>
    </div>

    <!-- Close the database connection -->
    <?php $conn->close(); ?>

    
</body>
</main>
</html>
