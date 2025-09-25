
<!-- register.php (PHP Registration Script) -->
<?php

include('header.php');

// database.php should be included if necessary, but for simplicity, we'll include the database connection here

// Replace with your actual database connection details
require "database.php";

// Check connection

// Check the database connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if form data is submitted via POST method
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Get the input values from the form
    $username = $_POST['username'];
    $password = $_POST['password'];
    $confirm_password = $_POST['confirm_password'];
    $firstname = $_POST['firstname'];
    $surname = $_POST['surname'];
    $addressline1 = $_POST['addressline1'];
    $addressline2 = $_POST['addressline2'];
    $city = $_POST['city'];
    $telephone = $_POST['telephone'];
    $mobilenumber = $_POST['mobilenumber'];

    // Sanitize inputs to prevent SQL injection
    $username = mysqli_real_escape_string($conn, $username);
    $password = mysqli_real_escape_string($conn, $password);
    $firstname = mysqli_real_escape_string($conn, $firstname);
    $surname = mysqli_real_escape_string($conn, $surname);
    $addressline1 = mysqli_real_escape_string($conn, $addressline1);
    $addressline2 = mysqli_real_escape_string($conn, $addressline2);
    $city = mysqli_real_escape_string($conn, $city);
    $telephone = mysqli_real_escape_string($conn, $telephone);
    $mobilenumber = mysqli_real_escape_string($conn, $mobilenumber);

    // Check if password length is exactly 6 characters
    if (strlen($password) != 6) {
        echo "Invalid Input, Password must be six characters";
    } else if ($password !== $confirm_password) {
        echo "Password and Confirm Password do not match";
    } else {
        // Prepare the SQL statement using a prepared statement to prevent SQL injection
        $stmt = $conn->prepare("INSERT INTO users (Username, Password, FirstName, Surname, AddressLine1, AddressLine2, City, Telephone, Mobile) 
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        // Bind the parameters to the prepared statement
        $stmt->bind_param("sssssssss", $username, $password, $firstname, $surname, $addressline1, $addressline2, $city, $telephone, $mobilenumber);

        // Execute the query and check if it was successful
        if ($stmt->execute()) {
            echo "Registered Successfully!";
            header("location: login.php"); 
        } else {
            echo "Error: " . $stmt->error;
        }
        include('footer.php');
        // Close the statement and connection
        $stmt->close();
        $conn->close();
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
    <title>Register</title>
</head>
<body>
<main>
<form action="register.php" method="POST">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <label for="username"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" id="username" required>

            <label for="psw"><b>Password (6 Characters)</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password" required>

            <label for="psw-confirm"><b>Confirm Password</b></label>
            <input type="password" placeholder="Confirm Password" name="confirm_password" id="confirm_password" required>

            <label for="fName"><b>First Name</b></label>
            <input type="text" placeholder="Enter First Name" name="firstname" id="firstname" required>

            <label for="Surname"><b>Surname</b></label>
            <input type="text" placeholder="Enter Surname" name="surname" id="surname" required>

            <label for="line1"><b>Address Line 1</b></label>
            <input type="text" placeholder="Enter Address Line 1" name="addressline1" id="addressline1" required>

            <label for="line2"><b>Address Line 2</b></label>
            <input type="text" placeholder="Enter Address Line 2" name="addressline2" id="addressline2" required>

            <label for="city"><b>City</b></label>
            <input type="text" placeholder="Enter City" name="city" id="city" required>

            <label for="telephone"><b>Telephone</b></label>
            <input type="text" placeholder="Enter Telephone" name="telephone" id="telephone" required pattern="^[0-9]+$" title="Please enter a valid telephone number">

            <label for="mobile"><b>Mobile Number</b></label>
            <input type="text" placeholder="Enter Mobile Number" name="mobilenumber" id="mobilenumber" required pattern="^[0-9]+$" title="Please enter a valid mobile number">
            <hr>

            <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
            <button type="submit" class="registerbtn" name="register">Register</button>
        </div>

        <div class="container signin">
            <p>Already have an account? <a href="login.php">Login</a>.</p>
        </div>
    </form>
</main>
</body>
</html>
<?php
include('footer.php');
?>