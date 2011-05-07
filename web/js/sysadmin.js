jQuery(function($){
    //Settings
    const URLBASE = '/TMK';
    $("a[href='#']").live('click', function(e){e.preventDefault();});

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

    //Configurar máscaras
    $.mask.masks = $.extend($.mask.masks, {
        //peso      : {mask: '99,999.999', type: 'reverse', defaultValue: '000'},
        //valor     : {mask: '99,999.999.99', type: 'reverse', defaultValue: '000'},
        rg        : {mask: '99999999999'},
        numero    : {mask: '99999999999'}/*,
        matricula : {mask: '9999999-9'}*/
    });

    //Inicializar máscaras
    $(":text").setMask();

    //CEP
    $("#cep").live("focusout", function(){
        var cep = $(this).val();

        if (cep.length == 9) {
            $("#busy").show();

            $.get(
                //URL
                "http://cep.republicavirtual.com.br/web_cep.php",

                //Data
                { "cep": cep, "formato": "javascript" },

                //Callback
                function(){
                    $("#busy").hide();

                    if (resultadoCEP["resultado"] == 1) {
                        $("#uf").val(unescape(resultadoCEP["uf"]));
                        $("#cidade").val(unescape(resultadoCEP["cidade"]));
                        $("#bairro").val(unescape(resultadoCEP["bairro"]));
                        $("#logradouro").val(unescape(resultadoCEP["tipo_logradouro"]) +" "+ unescape(resultadoCEP["logradouro"]));
                    } else {
                        //Limpar campos
                        $("#uf").val("");
                        $("#cidade").val("");
                        $("#bairro").val("");
                        $("#logradouro").val("");

                        //Exibir aviso
                        alert('CEP inválido.');
                    }
                },

                //Callback return type
                "script"
            );
        } else {
            if (cep.length)
                alert("O CEP está incorreto.");
        }
    });
});