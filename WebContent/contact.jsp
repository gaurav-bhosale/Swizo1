<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact</title>
    <style>
         	.fixed-footer{
		width: 100%;
        position: fixed;        
        background: #333;
        padding: 10px 0;
        color: #fff;
    text-align: center;
        bottom: 0;
    } 
    ul {
  
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
  border-right:1px solid #bbb;
}

li:last-child {
  border-right: none;
}

li a {
  display: block;
  color: rgba(255, 255, 255, 0.822);
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: rgb(122, 120, 120);
}
.footer {
    position: static;
    left: 0;
    bottom: 0;
    color: white;
    text-align: center;
  background-color: #998c8c;
  padding: 10px;
  text-align: center;
}
body {font-family: Arial, Helvetica, sans-serif;}
   * {
        box-sizing:border-box;
        margin: 0;

    }

   
.column {
  float: left;
  width: 33.3%;
  margin-bottom: 16px;
  padding: 0 8px;
}


@media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}


.container {
  padding: 0 16px;
}


.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}


.button:hover {
  background-color: #555;
}
.fixed-footer{
		width: 100%;
        position: fixed;        
        background: rgb(161, 160, 160);
        padding: 10px 0;
        color: #fff;
    text-align: center;
        bottom: 0;
    } 
    * {box-sizing: border-box}
body {font-family: Verdana, sans-serif; margin:0}
.mySlides {display: none}
img {vertical-align: middle;}


.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}


.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  padding: 16px;
  margin-top: -22px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}


.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}


.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}


.text {
  color: #333;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}


.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}


.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active, .dot:hover {
  background-color: #717171;
}


.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@media only screen and (max-width: 300px) {
  .prev, .next,.text {font-size: 11px}
}
input[type=text],select,textarea{
    width: 50%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px ;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
    margin-left:25%;
    resize: vertical;
}
input[type=submit]{
    background-color: #4caf50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px ;
    cursor: pointer;
}
input[type=submit]:hover{
    background-color: #45a049;

}
.container{
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: px;
}
    </style>
</head>

<body style="background-color: #fff4f4;">
    <ul style="position: sticky;">
        <li><a class="active" href="index.jsp" target="">SWIZO</a></li>
        <li style="float: right;"><a href="login.html">UserLogin</a></li>
         <li style="float:right"><a href="register.html">SignUp</a></li>
        <li ><a href="About.jsp">About</a></li>
        <li><a href="offer.jsp">Offers</a></li>
      </ul>
<header style="margin-top: 1%; margin-left: 30%; margin-bottom: 4%;">
  <b>
    Contact form
  </b> 
</header>
<form action="index.jsp" method="POST">

<input type="text" id="fname" name="firstname" placeholder="yourname">

<input type="text" id="lname" name="lastname" placeholder="your last name">

<select name="state" id="state">
    <option value="Maharastra">Maharastra</option>
    <option value="Goa">Goa</option>
    <option value="Telangana">Telangana</option>
</select>

<textarea name="subject" id="subject"  placeholder=" Write here" style="height: 200px;"></textarea>
<input type="submit" value="Submit" onclick="fun()">
</form>
</div>

    <div class="fixed-footer">
	<div class="container">Copyright &copy; 2020 SWIZO</div>        
</div>
<script>
  function  fun(){
        var x=document.getElementById("fname").value;
        var y=document.getElementById("lname").value;
        var z=document.getElementById("state").value;
        alert("we have recieved "+x+y+"response");

        
    }
</script>
</body>
</html>