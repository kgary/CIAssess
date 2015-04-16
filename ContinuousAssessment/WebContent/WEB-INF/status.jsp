<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="C:/Users/Suhas Xavier/Desktop/C3js/c3-0.4.9/c3-0.4.9/c3.css" rel="stylesheet" type="text/css">

<!-- Load d3.js and c3.js -->
<script src="C:/Users/Suhas Xavier/Desktop/C3js/d3/d3.min.js" charset="utf-8"></script>
<script src="C:/Users/Suhas Xavier/Desktop/C3js/c3-0.4.9/c3-0.4.9/c3.min.js"></script>

</head>
<body>


<div id="chart"></div>

<script type="text/javascript">
var chart = c3.generate({
    bindto: '#chart',
    data: {
      columns: [
        ['data1', 30, 200, 100, 400, 150, 250],
        ['data2', 50, 20, 10, 40, 15, 25]
      ]
    }
});

</script>




</body>
</html>