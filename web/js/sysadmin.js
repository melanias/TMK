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

    //Configurar máscaras
    $.mask.masks = $.extend($.mask.masks, {
        //peso      : {mask: '99,999.999', type: 'reverse', defaultValue: '000'},
        //valor     : {mask: '99,999.999.99', type: 'reverse', defaultValue: '000'},
        rg        : {mask: '99999999999'},
        numero    : {mask: '99999999999999999999'}/*,
        matricula : {mask: '9999999-9'}*/
    });

    //Inicializar máscaras
    $(":text").setMask();
});