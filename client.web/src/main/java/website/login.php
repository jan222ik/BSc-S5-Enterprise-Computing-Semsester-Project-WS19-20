<?php
session_start(); // Right at the top of your script
?>

if($count==1)
  {
    $_SESSION['logged']=true;
    $_SESSION['username']=$myusername;
    echo "Login worked";
    exit();
  }
else
  {
     $_SESSION['logged']=false;
     echo "Login failed";
     exit();
  }
