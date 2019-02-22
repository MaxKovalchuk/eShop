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
function buy(myId) {
	var qnt = document.getElementById('q' + myId).innerHTML;
	$.ajax({
		type : "GET",
		url : "./cart",
		data : "productID=" + (myId + "-" + qnt),
		success : function(response) {
			alert('Product added to cart');
		}
	});
}