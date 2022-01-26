$(document).ready(function(){

   $( "#datepicker" ).datepicker({
		dateFormat: "mm-dd-yy",
		minDate : 0
	  });
	  
	$( "#datepicker_e" ).datepicker({
		dateFormat: "mm-dd-yy",
		minDate : 0
	});

	$("#datepicker").datepicker("setDate", new Date());

});