function timer() {
	var $s = $(".second"),
        $min = $(".minute"),
        $h = $(".hour"),
        $d = $(".day"),
        $m = $(".month"),
		today = new Date(),
    	time_from_html = new Date($('.timer-wrapper time').attr('datetime')),
    	last_sec = (time_from_html - today)/1000,
        m = Math.floor(last_sec/(60*60*24*30)),
        d = Math.floor((last_sec - (m*60*60*24*30))/(60*60*24)),
        h = Math.floor((last_sec - (m*60*60*24*30) - (d*60*60*24))/(60*60)),
        min = Math.floor((last_sec - (m*60*60*24*30) - (d*60*60*24) - (h*60*60))/60),
        s = Math.floor(last_sec - (m*60*60*24*30) - (d*60*60*24) - (h*60*60) - (min*60));

    $s.val(s).trigger("change");
    $min.val(min).trigger("change");
    $h.val(h).trigger("change");
    $d.val(d).trigger("change");
    $m.val(m).trigger("change");
    setTimeout("timer()", 1000);
}


$(document).ready(function () {

	$('.timer input').knob({
		'min':0,
		'max':60,
		width : 154,
		readOnly: true,
		thickness : 0.04,
		font : "50px Arial",
		inputColor: "#fff",
		bgColor: "rgba(0,0,0,0.3)",
		fgColor: "#dccc99"
	});
	$('.day').trigger(
		'configure',{
	        "max":30
        }
    );
	$('.month').trigger(
		'configure',{
			"max":12
		}
    );

	timer();
});


