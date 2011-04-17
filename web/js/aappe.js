jQuery(function($){
    //Menu
    $("#menu ul li > div").each(function(){
        $(this).parent().hover(function(){
            //$(this).find("a:first").addClass("hover");
            $(this).find("div:first").show();
        }, function(){
            //$(this).find("a:first").removeClass("hover");
            $(this).find("div:first").hide();
        });
    });
});