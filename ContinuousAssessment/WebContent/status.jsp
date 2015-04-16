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

/* /* var task1d1 = ${task1d1};
var task1d2 = ${task1d2};
var task1d3 = ${task1d3};

var task2d1 = ${task2d1};
var task2d2 = ${task2d2};
var task2d3 = ${task1d3};

var task3d1 = ${task3d1};
var task3d2 = ${task3d2};
var task3d3 = ${task1d3}; 


var testArray1 = ['Resolve issue with placing sprint retrospective questions on Moodle project page',0, 0.5, 0.9];
var testArray2 = ['Create Moodle-based individual Sprint reflection activity',0, 0.6, 1];
var testArray3 = ['Install and verify Jenkins plugins for Android, C, and PHP',0, 0.8, 0.8]; 

/* var chart = c3.generate({
    bindto: '#chart',
    data: {
      columns: [
        ['data1', 30, 200, 100, 400, 150, 250],
        ['data2', 50, 20, 10, 40, 15, 25]
      ]
    }
});
 

var chart = c3.generate({
    bindto: '#chart',
    data: {
      columns: [
        testArray1,		//['data1', 30, 200, 100, 400, 150, 250],
        testArray2,
        testArray3		//['data2', 50, 20, 10, 40, 15, 25]
      ]
    }
}); */

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