<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!--tag lib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
  <title>Radar chart</title>
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="RadarChart.js"></script>
	<style>
		body {
		  overflow: hidden;
		  margin: 0;
		  font-size: 14px;
		  font-family: "Helvetica Neue", Helvetica;
		}

		#chart {
		  position: absolute;
		  top: 50px;
		  left: 100px;
		}	
	</style>
<script>
var w = 500,
h = 500;

var colorscale = d3.scale.category10(); 

//Legend titles
var LegendOptions = ['Individual','Team', 'Class'];

function getAllInfo(StudentName)
{
var selectedStudent = StudentName.options[document.getElementById('student').selectedIndex].text;
	
	$.ajax({
        type: "GET",
        url: "SpiderFetchVal",
        data: { name: selectedStudent},
        timeout: 10000,
        async: true,
        dataType: "text",
        cache: false,               
        headers: { "cache-control": "no-cache" },
        success: function(data)
        {
        	console.log(data);
        	src_val = JSON.parse(data);
        	//alert(data);	
		
        var data = [
          		  [
       			{axis:"Code Activity",value: src_val[0]	},
       			{axis: "Project Activity", value: src_val[1]}, 
                {axis: "Module Activity", value: src_val[2]},  
                {axis: "Team Activity", value: src_val[3]}
                  			
                    ],
                    
                    [
         			{axis:"Code Activity",value: src_val[4]	},
       			{axis: "Project Activity", value: src_val[5]}, 
                {axis: "Module Activity", value: src_val[6]},  
                {axis: "Activity", value: src_val[7]}
                   
                  			],
                  			 [
                   			{axis:"Code Activity",value: src_val[8]	},
                 			{axis: "Project Activity", value: src_val[9]}, 
                          {axis: "Module Activity", value: src_val[10]},  
                          {axis: "Activity", value: src_val[11]}
                             
                            			]
                    ];
                  
        var mycfg = {
        		  w: w,
        		  h: h,
        		  maxValue: 0.6,
        		  levels: 6,
        		  ExtraWidthX: 300
        		}
        RadarChart.draw("#chart", data, mycfg);
        
        var svg = d3.select('#body')
    	.selectAll('svg')
    	.append('svg')
    	.attr("width", w+300)
    	.attr("height", h)

    //Create the title for the legend
    var text = svg.append("text")
    	.attr("class", "title")
    	.attr('transform', 'translate(90,0)') 
    	.attr("x", w - 70)
    	.attr("y", 10)
    	.attr("font-size", "12px")
    	.attr("fill", "#404040")
    	.text("Code, Project and Module activity comparator");
    		
    //Initiate Legend	
    var legend = svg.append("g")
    	.attr("class", "legend")
    	.attr("height", 100)
    	.attr("width", 200)
    	.attr('transform', 'translate(90,20)') 
    	;
    	//Create colour squares
    	legend.selectAll('rect')
    	  .data(LegendOptions)
    	  .enter()
    	  .append("rect")
    	  .attr("x", w - 65)
    	  .attr("y", function(d, i){ return i * 20;})
    	  .attr("width", 10)
    	  .attr("height", 10)
    	  .style("fill", function(d, i){ return colorscale(i);})
    	  ;
    	//Create text next to squares
    	legend.selectAll('text')
    	  .data(LegendOptions)
    	  .enter()
    	  .append("text")
    	  .attr("x", w - 52)
    	  .attr("y", function(data, i){ return i * 20 + 9;})
    	  .attr("font-size", "11px")
    	  .attr("fill", "#737373")
    	  .text(function(data) { return data; })
    	  ;	
        
        }
			});
}



</script>


     


</head>

<body>

<p> Select a student to retrieve info</p> 
<form>
<select id="student" name="students_names" onchange="getAllInfo(this)">
<option value=" " disabled="disabled" selected="selected">Please select a name</option>
<c:forEach items="${students }" var="studentNames">
	<option>${studentNames}</option>
</c:forEach>
</select>
</br>
</br>
</br>
<div id="body">
<div id="chart"></div>
</form>
</body>
</html>