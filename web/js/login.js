$(function(){
    $("input[value='']:not(:submit):first").focus();

    //Remover espaços em branco de cada input
    $("input:not(:submit)").blur(function(){
        $(this).val($.trim($(this).val()));
    });

    //Arredondar bordas
    $(":submit").addClass("corner");
    $("#wrapper").addClass("corner");

    //Aviso
    var pos = Math.round(($("body").innerWidth()-$("#warning").innerWidth())/2);
    $("#warning").css("right", pos).addClass("corner-bottom");
    setTimeout(function(){$("#warning").fadeOut('slow');}, 5000);
});