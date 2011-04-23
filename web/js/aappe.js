jQuery(function($){
    //Menu
    $("#menu ul li > div").each(function(){
        $(this).find("ul").append('<div style="clear: both;"></div>');
        $(this).parent().hover(function(){
            $(this).find("a:first").addClass("ativo");
            $(this).find("div:first").show();
        }, function(){
            $(this).find("a:first").removeClass("ativo");
            $(this).find("div:first").hide();
        });
    });

    //Settings
    $("a[href='#']").live('click', function(e){e.preventDefault();});

    //Centralização do nome do módulo - REMOVER SE APROVAREM O NOVO POSICIONAMENTO
    /*$("#sysInfo").css("left", Math.round(($("html > body").width()-$("#sysInfo").width())/2));
    $("html").live('mouseenter', function() {
        $("#sysInfo").css("left", Math.round(($("html > body").width()-$("#sysInfo").width())/2));
    });*/
});