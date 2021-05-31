/**
 * @param {String} val the value of the input field
 * @param {*} field the input field (got from jquery)
 * @param {Boolean[]} conditions the input field (got from jquery)
 * @param {(error) => void} cb callback containing an error (true/false)
 */
function checkInputField(val, field, conditions, cb) {
	if (!val || val.trim().length === 0 || conditions.includes(false)) {
		field.removeClass("is-valid");
		field.addClass("is-invalid");
		cb(true);
	} else {
		field.removeClass("is-invalid");
		field.addClass("is-valid");
		cb(false);
	}
}
