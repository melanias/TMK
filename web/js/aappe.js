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
    $(":text[value='']:first").focus();
    $("a[href='#']").live('click', function(e){e.preventDefault();});

    //Configurar máscaras
    $.mask.masks = $.extend($.mask.masks, {
        rg     : {mask: '99999999999999999999'},
        data   : {mask: '39/19/2999'},
        numero : {mask: '99999999999'},
        valor  : {mask: '99,999.999.999.9', type: 'reverse', defaultValue: '000'}
    });

    //Inicializar máscaras
    $(":text").setMask();

    //Nova Janela
    $("a[rel='externo']").attr({
        title  : "Este link abre uma nova janela",
        target : "_blank"
    });

    //jQuery UI
    $("button, :button, :submit").button();
    $(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
    $(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
    $(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: false});
    $(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

    /*$("[alt='data']").datepicker({
        showOn: "button",
        buttonImage: URLBASE + "/images/calendar.gif",
        buttonImageOnly: true,
        beforeShowDay: $.datepicker.noWeekends
    });*/

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

    /** iframe - code **/
    $("#doAll").live("click", function() {
        var id = $(this).attr("name").split("-");

        //URL
        var fullurl = URLBASE +"/"+ id[1] +"/"+ id[0];
        var refresh = URLBASE +"/"+ id[1] +"/refresh";

        $.ajax({
            type: "POST",
            url: fullurl,
            data: $("form").serialize(),
            dataType: "json",
            success: function(response) {
                if (response.toString() == "OK") {
                    parent.$("#main > div.content").load(refresh, function(r, s) {

                        //Atualizar botões
                        parent.$(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
                        parent.$(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                        parent.$(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: false});
                        parent.$(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

                        if (s == "error")
                            alert("Ocorreu um erro ao carregar a lista.");
                    });

                    parent.window.hs.getExpander().close();
                } else {
                    var errors = new Array();

                    $.each(response, function(k, i) {errors.push(i.message);});

                    alert(errors.join("\n"));
                }
            }
        });
    });
    /** iframe - code **/
});