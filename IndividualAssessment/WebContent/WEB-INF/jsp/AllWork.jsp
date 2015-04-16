<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<link href="${pageContext.request.contextPath}/c3.css" rel="stylesheet" type="text/css">

<!-- Load d3.js and c3.js -->
<script src="${pageContext.request.contextPath}/d3.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/c3.min.js"></script>

<script type="text/javascript">
function getStudentInfo(studentName){
	var selectedStudent = studentName.options[document.getElementById('student').selectedIndex].text;
	
	$.ajax({
        type: "GET",
        url: "GetInfo",
        data: { 'studentName': selectedStudent},
        timeout: 10000,
        async: true,
        dataType: "text",
        cache: false,               
        headers: { "cache-control": "no-cache" },
        success: function(data){
        	//alert(data);
        	console.log(data);
        	
        	/* var dataSplit = data.split(",");
        	alert(dataSplit);
        	console.log(dataSplit); */
        	data = JSON.parse(data);
        	//alert(data);
        	
        	//alert(data[0]);
        	//alert(data[1]);
        	//alert(data[2]);
        	
        	
         	var chart = c3.generate({
        		bindto: '#chart1',
        	    data: {
        	    	x : 'Date',
        	        columns:[ data[0],
        	                  data[1],
        	                  data[2],
        	                  data[3]
        	                 ],
									//['data1', -30, 200, 200, 400, -150, 250],
        	        type: 'bar', 
        	        axes: {'Commits': 'y2',
    	        		'Lines of code added':'y',
    	        		'Lines of code deleted':'y'
    	        			}
        	    		},
        	    
         	    axis: {
        	        x: {
        	        	 type: 'categorized'
        	            
        	        } ,
        	        
        	            y2: {
        	                show: true
        	            }
        	        
        	       
        	        
        	    }, 
        	    
        	  /*   bar: {
        	        width: {
        	            ratio: 0.5 // this makes bar width 50% of length between ticks
        	        		}        	    
        	    	} */
        	    
        	   
        	    					}); 
        },
	
        error: function(error) {
        	  console.log(error);
        	  alert(error);
        	}
       
	  });
	}
</script>
</head>

<body>
<p> Select a student to retrieve info</p> 
<form>
<select id="student" name="students_names" onchange="getStudentInfo(this)">
<option value=" " disabled="disabled" selected="selected">Please select a name</option>
<c:forEach items="${students }" var="studentNames">
	<option>${studentNames}</option>
</c:forEach>
</select>
</br>
</br>
</br>
<div id="chart1"></div>
</form>
</body>
</html>