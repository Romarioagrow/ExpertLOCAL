$(document).ready(function(){
    $('button[type="submit"][name="addToOrder"]').on('click', addToOrder);
});

function addToOrder(e) {
    e.preventDefault();

    const productID = ($(this).attr("value"));
    console.log(productID);

    $.ajax({
        url: 'http://localhost:8080/order',
        type: 'POST',
        dataType: 'json',
        data: productID,
        processData: false,
        headers: {'Content-Type': 'application/json'},
        complete: function(products) {
            alert("Add product with ID " + productID);
        }
    });
}