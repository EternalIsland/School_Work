var pat_form_mod = (function() {

	function init_submit(evt) {
		var f1 = document.querySelector("#f1");
		f1
				.addEventListener(
						'submit',
						function(event) {
							// prevent the form submit
							event.preventDefault();

							// get the input fields
							var firstname = document
									.querySelector('#f1 input[id="student-firstname"]');
							var lastname = document
									.querySelector('#f1 input[id="student-lastname"]');
							var studentno = document
									.querySelector('#f1 input[id="student-number"]');
							var email = document
									.querySelector('#f1 input[id="student-email"]');
							var mailing = document
									.querySelector('#f1 input[id="student-mail"]');

							// construct a student object
							// field names must match student.java
							var s = {
								firstname : firstname.value,
								lastname : lastname.value,
								studentno : studentno.value,
								email : email.value,
								mailing : mailing.value
							};
							console.log(s);
							var json = JSON.stringify(s);

							// send the update to the server
							local_ajax_mod.ajax_request({
								method : "POST",
								link : window.location.pathname,
								doc : json,
								mime : 'application/json',
								ok_fn : function(req) {
									try {
										var pReply = JSON
												.parse(req.responseText);
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
