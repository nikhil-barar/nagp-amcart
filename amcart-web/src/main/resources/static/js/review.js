(function($) {
"use strict";

$(document).ready(function () {
	$('.dislike > a, .like > a').click(function () {
		var count = parseInt($(this).next().text());
		$(this).next().text(count+1);
	});

});


})(jQuery);