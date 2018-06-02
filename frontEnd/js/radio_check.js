window.onload = function () {
    var dom_a1 = document.getElementById('a1');
    var dom_a2 = document.getElementById('a2');
    var dom_a3 = document.getElementById('a3');
    var dom_a4 = document.getElementById('a4');
    var dom_a5 = document.getElementById('a5');
    var dom_a6 = document.getElementById('a6');

    dom_a1.onclick(function () {
       return  "Classical";
    });
    dom_a2.onclick(function () {
        return  "Hipop";
    });
    dom_a3.onclick(function () {
        return  "Jass";
    });
    dom_a4.onclick(function () {
        return  "Metal";
    });
    dom_a5.onclick(function () {
        return  "Pop";
    });
    dom_a6.onclick(function () {
        return  "Rock";
    });
}
