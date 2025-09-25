<?php
    $conn       = mysqli_connect('localhost', 'root', '', 'mydb');
    $username   = $_POST['username'];
    $password   = $_POST['password'];
    $query      = "select * from register where username = '$username'";
    $resultSet  = mysqli_query($conn, $query); //Syntax error: mysqli_query(connection,query);
    if(mysqli_num_rows($resultSet) > 0){
        $row    = mysqli_fetch_assoc($resultSet);
        if($row['password'] == $password){ // if you are using encryption like md5 or anything else then you have to add in this line accordingly
            echo "Good, Logged In!";
        }else{
            echo "Oh No, password not correct!";
        }
    }else{
        echo "Please enter a valid username!";
    }