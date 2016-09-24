
<html>
<head>
<style>

.center {
    text-align: center;
 	height: 100px;
 	line-height: 100px;
}
</style>
</head>

<body>
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
