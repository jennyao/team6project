<html>
<head>
<style>
.header {
    width: 100%;
    height: 40px;
}
#center {
    text-align: center;
 	height: 100px;
 	line-height: 100px;
}
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}
/* Dropdown button on hover & focus */
.dropbtn:hover, .dropbtn:focus {
    background-color: #3e8e41;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
    position: relative;
    display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
    display: none;
    /*position: absolute; */
    background-color: #f9f9f9;
    min-width: 600px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.nameC{
	border-width: thin 10px thin 10px;
	max-width: 200px;
}

.timeC{
	max-width: 500px;
}
/* Links inside the dropdown */
.dropdown-content a {
    color: black;
    
    text-decoration: none;
    display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f9f9f9}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {display:block;}

</style>
</head>

<body>
<script>
	function myFunction() {
    	document.getElementById("myDropdown").classList.toggle("show");
	}
	
</script>

	<div class="header">
			<form action="https://docs.google.com/spreadsheets/d/1uOa1xu8k47jSzVswPsb_KMhH1vQ2-YWGCKyoDKBccLk/edit#gid=1900717557">
				<input type="submit" value="Edit Existing Classes" />
			</form>
			
			<br>
			<!--
			<form action="AdminL">
				Input Random Key Size Between 1-9 <input type="password" name="keyLength">
			</form>
			-->
			<form>
				<input type="button" name="rndBtn" value="Generate Random Key"  >
			</form>
				<p id="demo"></p>
			<br>
			
			<div class="dropdown">
				<button onclick="myFunction()" class="dropbtn">Create New Class</button>
  				<div id="myDropdown" class="dropdown-content">
	  				<form action="AdminL" method="post">
						<div class="nameC">
							<!-- Typing in class name -->
							<p>Class Name:</p> <input type="text" name="className"> 
						</div>
						
						<div class="timeC">
						<!-- Selecting Class Start and End Times -->
							<p>Class Start Time:</p> <input type="time" name="classStart">
							<p>End Time:</p> <input type="time" name="classEnd">
						</div>
						<!-- Selecting days when class is in session with checkboxes -->
						<p>Class Session Days</p> 
						Monday<input type="checkbox" name="Monday" value="Monday">
						Tuesday<input type="checkbox" name="Tuesday" value="Tuesday">
						Wednesday<input type="checkbox" name="Wednesday" value="Wednesday"><br>
						Thursday<input type="checkbox" name="Thursday" value="Thursday">
						Friday<input type="checkbox" name="Friday" value="Friday">
						Saturday<input type="checkbox" name="Saturday" value="Saturday">
						Sunday<input type="checkbox" name="Sunday" value="Sunday"><br>
						 <input type="submit" value="submit">
					 
					</form>
  				</div>
			</div>
		
	</div>	<!-- header -->
<!--  <input type="text" name="studentId"> Key: <input type="text" name="key"> <input type="submit" value="submit">-->
	


</body>
</html>