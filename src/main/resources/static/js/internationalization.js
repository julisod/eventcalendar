$(document).ready(function() {
	const firstPath = window.location.pathname.split('/')[1];
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace(firstPath + '?lang=' + selectedOption);
        }
    });
});