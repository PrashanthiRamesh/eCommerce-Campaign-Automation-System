$(function() {

    $('#login-form-link').click(function(e) {
    	location.href="/login";
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		location.href="/register";
		e.preventDefault();
	});

});
