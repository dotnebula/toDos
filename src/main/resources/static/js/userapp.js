$(document).ready(function(){
  
    $(".pupup").on("mouseenter", function() {
    $(this).css("color", "red");
	});
	
	$(".pupup").on("mouseleave", function() {
    $(this).css("color", "black");
	});
	
    $('.pupup').on('click', function () {
        $(this).siblings('.org').toggle();
	    });
	    
	$("input.sub").on("mouseenter", function() {
    $("input.sub").css("background-color", "green");
	});
	
	$(".sub").on("mouseleave", function() {
    $(".sub").css("background-color", "rgb(239, 239, 239)");
	});
	
	$("#toDelete").on("mouseenter", function() {
    $("#toDelete").css("background-color", "rgb(255, 0, 0)");
	});
	
	$("#toDelete").on("mouseleave", function() {
    $("#toDelete").css("background-color", "rgb(239, 239, 239)");
	});
	    
});