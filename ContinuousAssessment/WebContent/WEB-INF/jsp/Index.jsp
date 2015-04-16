<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!--tag lib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Continuous Assessment</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<link href="${pageContext.request.contextPath}/c3.css" rel="stylesheet" type="text/css">

<!-- Load d3.js and c3.js -->
<script src="${pageContext.request.contextPath}/d3.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/c3.min.js"></script>

<script type="text/javascript">

function getProjectInfo(projectName){
	var selectedProject = projectName.options[document.getElementById('projOptions').selectedIndex].text;
	
	$.ajax({
        type: "GET",
        url: "AllProjectTasks",
        data: { projectName: selectedProject},
        timeout: 10000,
        async: true,
        dataType: "text",
        cache: false,               
        headers: { "cache-control": "no-cache" },
        success: function(data){
        //	alert(data);
        	console.log(data);
        	
        	/* var dataSplit = data.split(",");
        	alert(dataSplit);
        	console.log(dataSplit); */
        	data = JSON
        	.parse(data);
        	//alert(data);
        	
        	//alert(data[0]);
        	//alert(data[1]);
        	//alert(data[2]);
        	
         	var chart = c3.generate({
        		bindto: '#chart1',
        	    data: {
        	    	x : 'Date',
        	        columns: [
						data[0],			//['data1', -30, 200, 200, 400, -150, 250],
        	            data[1],
        	            data[2],
        	            data[3],
        	            data[4]
        	           
        	        ],
        	        type: 'area',
        	        colors:{
            	    	'To do':'#08298A',
            	    	'Done': '#A4C639',
            	    	'In Progress' :'#FFBF00',
            	    	'To Test':'#E32636'
    				},
        	        groups: [
        	            ['To do', 'In Progress' , 'To Test', 'Done'    ]
        	        ],
        	        order:null
        	    },
        	    
        	    grid: {
        	        y: {
        	            lines: [{value:0}]
        	        }
        	    },
        	    
        	    axis: {
        	        x: {
        	        	 type: 'categorized'
        	            
        	        }
        	        
        	    }
        	}); 
         	
         	var chart2 = c3.generate({
        		bindto: '#chart_2',
        	    data: {
        	    	x : 'Date',
        	        columns: [
						data[0],
						data[6],			//['data1', -30, 200, 200, 400, -150, 250],
        	            data[7]
        	           
        	        ],
        	        type: 'area',
        	        groups: [
        	            ['Remaining hours for To Test tasks', 'Remaining hours for In Progress tasks' ]
        	        ],
        	        
        	        
        	    },
        	    grid: {
        	        y: {
        	            lines: [{value:0}]
        	        }
        	    },
        	    axis: {
        	        x: {
        	        	 type: 'categorized'
        	            
        	        }
        	        
        	    }
        	}); 
        	
        	
        	
        	
        }
	});
}

</script>


</head>
<body>
<p> Select a team from the drop down to retrieve info </p> 
<form >
<select id="projOptions" name="name" onchange="getProjectInfo(this)">
<option value=" " disabled="disabled" selected="selected">Please select a project</option>
<c:forEach items="${projects }" var="projectNames">
	<option>${projectNames}</option>
</c:forEach>




</select>


</br>
</br>
</br>
<div id="chart1"></div>
</br>
<div id="chart_2"></div>
</form>
</body>

</html>