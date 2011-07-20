jQuery(function($){
    $("textarea#texto").tinymce({
        script_url : URLBASE + '/js/tinymce/tiny_mce.js',

        theme : "advanced",
        plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

        theme_advanced_buttons1 : "bold,italic,underline,strikethrough,separator,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect,separator,bullist,numlist",

        theme_advanced_buttons2 : "",
        theme_advanced_buttons3 : "",
        theme_advanced_buttons4 : "",
        theme_advanced_resizing : false,
        theme_advanced_toolbar_align : "left",
        theme_advanced_toolbar_location : "top",
        //theme_advanced_statusbar_location : "bottom",

        //Skin
        skin : "o2k7",
        skin_variant : "silver"
    });
});