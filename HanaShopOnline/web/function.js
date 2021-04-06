//Get the button
var mybutton = document.getElementById("myBtn");

// When the user scrolls  show the button
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        // mybutton.style.display = "block";
        // mybutton[0].classList.add('animate');
        mybutton.style.animation = "fadeUp 1s ease-out forwards";
    } else {
        // mybutton.style.display = "none";
        // mybutton[0].classList.remove('animate');
        mybutton.style.animation = "fadeDown 1s ease-in-out forwards";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

//check quantity 
function validateQuantity() {
    var x1 = document.forms["formUpdate"]["txtQuantity"].value;
    var x2 = document.forms["formUpdate"]["txtQuantityReal"].value;
    if (x1 === "") {
        alert("Quantity is empty");
        return false;
    }
    if (Number(x1) > Number(x2)) {
        alert("Quantity out of stock");
        return false;
    } else {
        return confirm('Do you want to save?');

    }
}
//check validate for update product
function validateForm() {
    var x = document.forms["myForm"]["txtName"].value;
    var x2 = document.forms["myForm"]["txtDescription"].value;
    var x3 = document.forms["myForm"]["txtPrice"].value;
    var x4 = document.forms["myForm"]["txtQuantity"].value;
    var x5 = document.forms["myForm"]["slCategory"].value;
    var t1 = /^[0-9]{5}$/;
    if (x === "") {
        alert("Name must be filled out");
        return false;
    }
    if (x2 === "") {
        alert("Description must be filled out");
        return false;
    }
   
    if (x3 === "") {
        alert("Price must be filled out");
        return false;
    }
    if (!x3.match(t1)) {
        alert("Price must be 5 number type integer");
        return false;
    }
    
    if (x4 === "") {
        alert("Quantity must be filled out");
        return false;
    }
    if (isNaN(x4)) {
        alert("Quantity must be type integer");
        return false;
    }
    if (x5 === "") {
        alert("Category must be filled out");
        return false;
    } else {
        return confirm('Do you want to Update?');
    }
}
//check validate for insert product

function validateFormInsert() {
    var x = document.forms["myForm"]["txtName"].value;
    var x1 = document.forms["myForm"]["txtImage"].value;
    var x2 = document.forms["myForm"]["txtDescription"].value;
    var x3 = document.forms["myForm"]["txtPrice"].value;
    var x4 = document.forms["myForm"]["txtQuantity"].value;
    var x5 = document.forms["myForm"]["slCategory"].value;
    var t1 = /^[0-9]{5}$/;

    if (x === "") {
        alert("Name must be filled out");
        return false;
    }
    
    if (x1 === "") {
        alert("Image must be filled out");
        return false;
    }
   
    if (x2 === "") {
        alert("Description must be filled out");
        return false;
    }
    
    if (x3 === "") {
        alert("Price must be filled out");
        return false;
    }
    if (!x3.match(t1)) {
        alert("Price must be 5 number type integer");
        return false;
    }
    if (x4 === "") {
        alert("Quantity must be filled out");
        return false;
    }
    if (isNaN(x4)) {
        alert("Quantity must be an integer number");
        return false;
    } 
}
