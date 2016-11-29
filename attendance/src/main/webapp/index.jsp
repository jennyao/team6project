
<html>
<head>
<style>
.center {
    text-align: center;
 	height: 100px;
 	line-height: 100px;
}

.header{
	width: 100%;
	height: 40px;
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
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

/* Links inside the dropdown */
.dropdown-content a {
    color: black;
    padding: 12px 16px;
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

<!-- Admin Dropdown Toggle. --> 
<script>
	function myFunction() {
    	document.getElementById("myDropdown").classList.toggle("show");
	}
</script>

<!-- Login info inside the dropdown -->
<div class="header">
	<div class="dropdown">
  		<button onclick="myFunction()" class="dropbtn">Admin Login</button>
	  	<div id="myDropdown" class="dropdown-content">
	  		<button> New Admin</button>
			<form action="CheckIn" method="get">
				<!-- switched name from studentId to AdminId -->
				User ID: <input type="text" name="AdminId"> 
				<br>
				<!-- switched name from Key to AdminKey -->
				Key: <input type="text" name="AdminKey"> <input type="submit" value="submit">
			</form>
	  	</div>
	</div>

</div>
	<div class="center">
		<img src="/attendance/images/csus.png">
		<form action="CheckIn" method="get">
			Student ID: <input type="text" name="studentId"> Key: <input type="text" name="key"> <input type="submit" value="submit">
		</form>
		
<%  
	String error = (String)request.getAttribute("error");
	if (error != null)
		out.print(error);
	
%>



	</div>

</body>
</html>
