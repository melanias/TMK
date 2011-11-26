jQuery(function($){
    //Settings
    var URLBASE = '/TMK';
    $(":text[value='']:first").focus();
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
        rg     : {mask: '99999999999999999999'},
        data   : {mask: '39/19/2999'},
        numero : {mask: '99999999999'},
        valor  : {mask: '99,999.999.999.9', type: 'reverse', defaultValue: '000'}
    });

    //Inicializar máscaras
    $(":text").setMask();

    //Botão para fechar os erros de validação
    $('<a href="#" title="Fechar">X</a>').insertBefore("blockquote > p:first");
    $("blockquote > a").click(function(){$(this).parent().remove();});

    //jQuery UI
    $("button, :button, :submit").button();

    //CEP
    $("#cep").live("focusout", function(){
        var cep = $(this).val();

        if (cep.length == 9) {
            $("#busy").show();

            $.get(
                //URL
                "http://cep.republicavirtual.com.br/web_cep.php",

                //Data
                {"cep": cep, "formato": "javascript"},

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

    /** setup - code **/
    $("#setup").live("click", function() {
        //Limpar erros
        $("form").find("span").remove();
        $('input').removeClass("error");

        $.ajax({
            type: "POST",
            url: URLBASE + "/setup",
            data: $("form").serialize(),
            dataType: "json",
            success: function(response) {
                if (response.toString() == "OK") {
                    location.href = URLBASE;
                } else {
                    $.each(response, function(k, i) {
                        var id = i.category;
                        var msg = i.message;

                        (id === 'mail' || id === 'nome') ? $("#"+ id).addClass("error").after('<span class="nl">'+ msg +'</span>')
                                                         : $("#"+ id).addClass("error").after('<span>'+ msg +'</span>');
                    });
                }
            }
        });
    });
    /** setup - code **/
});