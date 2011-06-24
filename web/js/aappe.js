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
        numero    : {mask: '99999999999'}/*,
        matricula : {mask: '9999999-9'}*/
    });

    //Inicializar máscaras
    $(":text").setMask();

    //jQuery UI
    $("button, :button, :submit").button();
    $(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
    $(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: false});
    $(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

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
    $("#add-filial").live("click", function() {
        $.ajax({
            type: "POST",
            url: URLBASE + "filial/add",
            data: $("form").serialize(),
            dataType: "json",
            success: function(response) {
                if (response.toString() == "OK") {
                    parent.$("#main > div.content").load(URLBASE + "filial/refresh", function(r, s) {

                        //Atualizar botões
                        parent.$(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                        parent.$(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: false});
                        parent.$(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

                        if (s == "error")
                            alert("Ocorreu um erro ao carregar a lista de filiais.");
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

    $("#edit-filial").live("click", function() {
        $.ajax({
            type: "POST",
            url: URLBASE + "filial/edit",
            data: $("form").serialize(),
            dataType: "json",
            success: function(response) {
                if (response.toString() == "OK") {
                    parent.$("#main > div.content").load(URLBASE + "filial/refresh", function(r, s) {

                        //Atualizar botões
                        parent.$(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                        parent.$(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: false});
                        parent.$(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

                        if (s == "error")
                            alert("Ocorreu um erro ao carregar a lista de filiais.");
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