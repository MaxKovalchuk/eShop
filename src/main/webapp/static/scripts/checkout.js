function minus(myId) {
	var elem = document.getElementById('q' + myId);
	var oldQnt = elem.innerHTML;
	if (+elem.innerHTML > 1) {
		elem.innerHTML = +elem.innerHTML - 1;
		buy(myId, oldQnt, minus);
	}
}
function plus(myId) {
	var elem = document.getElementById('q' + myId);
	var oldQnt = elem.innerHTML;
	elem.innerHTML = +elem.innerHTML + 1;
	buy(myId, oldQnt, plus);
}
function buy(myId, oldQnt, pm) {
	var qnt = +document.getElementById('q' + myId).innerHTML - +oldQnt;
	$.ajax({
		type : "GET",
		url : "./cart",
		data : "productID=" + (myId + ":" + qnt),
		success : function(response) {
			var cartSize = +document.getElementById('cart').innerHTML.substr(6)
					+ +qnt;
			document.getElementById('cart').innerHTML = " Cart: " + cartSize;

			var info = document.getElementById('p' + myId).innerHTML;
			var infoPrice = info.slice(info.indexOf(':') + 1,
					info.indexOf('*') - 1);
			var infoAmount = document.getElementById('q' + myId).innerHTML;
			var infoTotal = +infoPrice * +infoAmount;
			document.getElementById('p' + myId).innerHTML = "Price: "
					+ infoPrice + " * " + infoAmount + " = " + infoTotal
					+ " UAH";

			var ttl = document.getElementById("ttlCost").innerHTML;
			var ttlprice = ttl.slice(ttl.indexOf(':') + 1, ttl.indexOf('U'));
			console.log(ttlprice);
			console.log(pm);
			if (pm == plus) {
				ttlprice = +ttlprice + +infoPrice;
			} else {
				ttlprice = +ttlprice - +infoPrice;
			}
			document.getElementById("ttlCost").innerHTML = "Totalcost : " + ttlprice + " UAH";
		}
	});
}