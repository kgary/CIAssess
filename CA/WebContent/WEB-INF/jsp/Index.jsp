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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="RadarChart.js"></script>

<!-- //overflow: hidden; --> 
<style>
		body {
		  margin: 0;
		  font-size: 16px;
		  font-family: "Helvetica Neue", Helvetica;
		}

		#chart1 {
		margin-left:100
		  position: relative;
		  top: 30px;
		  left: 100px;
		}	
</style>

<script type="text/javascript">
var LegendOptions = ['Individual','Team', 'Class'];

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
        	//alert(data);
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
        		bindto: '#chart_1',
        	    data: {
        	    	x : 'Date',
        	        columns: [
						data[0],			//['data1', -30, 200, 200, 400, -150, 250],
        	            data[1],
        	            data[2],
        	            data[3],
        	            data[4]
        	           
        	        ],
        	        type: 'area-spline',
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
         	
/*          	var chart2 = c3.generate({
        		bindto: '#chart_2',
        	    data: {
        	    	x : 'Date',
        	        columns: [
						data[0],
						data[5],			//['data1', -30, 200, 200, 400, -150, 250],
        	            data[6]
        	           
        	        ],
        	        type: 'area',
        	        groups: [
        	            ['Remaining hours for In Progress tasks' ,'Remaining hours for To Test tasks']
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
        	});  */
        	
        	
        	
        	
        }
	});
}


function getIndividualData(){

var w = 500,
h = 500;
0
var colorscale = d3.scale.category10(); 

//Legend titles


var posting_id = document.getElementById("posting_id").value;
//alert('Posting Id entered = ' + posting_id);
	
	$.ajax({
        type: "GET",
        url: "Intermediate",
        data: { id: posting_id},
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
              RadarChart.draw("#chart1", data, mycfg);
              
              var svg = d3.select('#chart1')
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
          	  .text(function(data) { return data; });
        	
         	var chart = c3.generate({
        		bindto: '#chart2',
        	    data: {
        	    	x : 'Date',
        	        columns:[ src_val[12],
        	                  src_val[13],
        	                  src_val[14],
        	                  src_val[15]
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
        	    
        	    bar: {
        	        width: {
        	            ratio: 0.5 // this makes bar width 50% of length between ticks
        	        		}        	    
        	    	} 
        	    
        	   
        	    					}); 
    
            
            }
    			}); 
		
       
    	



}

</script>
<!--  -->


</head>
<body>
<p> Select a team from the drop down to retrieve info </p> 
<form  method="post"> <!-- action="IndividualData" -->
<select id="projOptions" name="name" onchange="getProjectInfo(this)">
<option value=" " disabled="disabled" selected="selected">Please select a project</option>
<c:forEach items="${projects }" var="projectNames">
	<option>${projectNames}</option>
</c:forEach>
</select>

<br />
<p> Enter Posting Id to retrieve individual details </p>
<input id = "posting_id" type="text" name="posting_id">
<input type="button" value="Submit" onclick="getIndividualData()">
</form>

<div id="chart_1"></div>
<br>
<br>
<div id="chart1" style="margin-left:300px"></div>
<div id="chart2"></div>

</body>

</html>