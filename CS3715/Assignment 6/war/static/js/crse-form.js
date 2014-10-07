var pat_form_mod = (function() {

	function init_submit(evt) {
		var f1 = document.querySelector("#f1");
		f1.addEventListener('submit', function(event) {
			// prevent the form submit
			event.preventDefault();

			// get the input fields
			var coursesubj = document
					.querySelector('#f1 input[id="course-subject"]');
			var coursenum = document
					.querySelector('#f1 input[id="course-number"]');

			// construct a course object
			// field names must match course.java
			var c = {
				coursesubj : coursesubj.value,
				coursenum : coursenum.value,
			};
			console.log(c);
			var json = JSON.stringify(c);

			// send the update to the server
			local_ajax_mod.ajax_request({
				method : "POST",
				link : window.location.pathname,
				doc : json,
				mime : 'application/json',
				ok_fn : function(req) {
					try {
						var pReply = JSON.parse(req.responseText);
						console.log(pReply);
					} catch (e) {
						console.log(e);
					}
				}
			});
		});
	}

	window.addEventListener('load', init_submit);

}());
