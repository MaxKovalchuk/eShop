function minus(myId) {
	var elem = document.getElementById('q' + myId);
	if (+elem.innerHTML > 1) {
		elem.innerHTML = +elem.innerHTML - 1;
	}
}
function plus(myId) {
	var elem = document.getElementById('q' + myId);
	elem.innerHTML = +elem.innerHTML + 1;
}
function buy() {
	var qnt = document.getElementById('q' + myId).innerHTML;
	$.ajax({
		type : "GET",
		url : "./cart",
		data : "bought=1",
	});
}