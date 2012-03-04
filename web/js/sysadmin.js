jQuery(function($){
    //Settings
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
        porta  : {mask: '99999'},
        numero : {mask: '99999999999'},
        valor  : {mask: '99,999.999.999.9', type: 'reverse', defaultValue: '000'}
    });

    //Inicializar máscaras
    $(":text").setMask();

    //jQuery UI
    $("button, :button, :submit").button();
    $(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
    $(".delete").button({icons: {primary: "ui-icon-trash"}, text: false});
    $(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
    $(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: true});
    $(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

    //Reconfigurar os botões assim que uma requisição AJAX for realmente finalizada.
    $("#main > div.content").ajaxStop(function() {
        $(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
        $(".delete").button({icons: {primary: "ui-icon-trash"}, text: false});
        $(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
        $(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: true});
        $(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});
    });

    //Calendário
    $("[alt='data']").datepicker({
        showOn: "button",
        buttonImage: URLBASE + "/images/calendar.gif",
        buttonImageOnly: true/*,
        beforeShowDay: $.datepicker.noWeekends*/
    });

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
                alert("CEP inválido.");
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

    /** iframe - code **/
    $("#doAll").live("click", function() {
        var id = $(this).attr("name").split("-");

        //URL
        var fullurl = URLBASE +"/"+ id[0] +"/"+ id[1] +"/"+ id[2];
        var refresh = URLBASE +"/"+ id[0] +"/"+ id[1] +"/refresh";

        $.ajax({
            type: "POST",
            url: fullurl,
            data: $("form").serialize(),
            //async: false,
            dataType: "json",
            success: function(response) {
                if (response.toString() == "OK") {
                    parent.$("#main > div.content").load(refresh, function(r, s) {


                        //Atualizar botões
                        /*parent.$(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
                        parent.$(".delete").button({icons: {primary: "ui-icon-trash"}, text: false});
                        parent.$(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                        parent.$(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: true});
                        parent.$(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});
                        body.find(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
                        body.find(".delete").button({icons: {primary: "ui-icon-trash"}, text: false});
                        body.find(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                        body.find(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: true});
                        body.find(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});*/

                        if (s == "error")
                            alert("Ocorreu um erro ao carregar a lista.");
                    });

                    //Fechar iframe
                    parent.window.hs.getExpander().close();

                    /*var body = parent.$("body");
                    body.find(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
                    body.find(".delete").button({icons: {primary: "ui-icon-trash"}, text: false});
                    body.find(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                    body.find(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: true});
                    body.find(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});*/
                } else {
                    var errors = new Array();

                    $.each(response, function(k, i) {errors.push(i.message);});

                    alert(errors.join("\n"));
                }
            }
        });
    });
    /** iframe - code **/

    /** Delete - code **/
    $(".delete").live("click", function() {
        //URL
        var fullurl = $(this).attr("rel");
        var refresh = URLBASE +"/"+ fullurl.split("/")[2] +"/"+ fullurl.split("/")[3] +"/refresh";

        if (window.confirm("Deseja realmente excluir?")) {
            $.ajax({
                type: "POST",
                url: fullurl,
                //data: $("form").serialize(),
                dataType: "json",
                success: function(response) {
                    if (response.toString() == "OK") {
                        parent.$("#main > div.content").load(refresh, function(r, s) {

                            //Atualizar botões
                            parent.$(".pdf").button({icons: {primary: "ui-icon-print"}, text: true});
                            parent.$(".delete").button({icons: {primary: "ui-icon-trash"}, text: false});
                            parent.$(".preview").button({icons: {primary: "ui-icon-search"}, text: false});
                            parent.$(".add-form").button({icons: {primary: "ui-icon-plusthick"}, text: true});
                            parent.$(".edit-form").button({icons: {primary: "ui-icon-pencil"}, text: false});

                            if (s == "error")
                                alert("Ocorreu um erro ao carregar a lista.");
                        });

                        //Fechar iframe
                        parent.window.hs.getExpander().close();
                    } else {
                        var errors = new Array();

                        $.each(response, function(k, i) {errors.push(i.message);});

                        alert(errors.join("\n"));
                    }
                }
            });
        }

        return false;
    });
    /** Delete - code **/

    /** Combobox de setores **/
    $("#unidade").live("change", function() {
        $("#loading").show();

        //Carregar combobox
        $("#setor").load(URLBASE +"/admin/unidade/filterSectorsPerUnit/"+ (($.trim($(this).val()) === "") ? 0 : $(this).val()), function(r, s) {
            $("#loading").hide();

            if (s == "error")
                alert("Ocorreu um erro ao carregar a lista de setores.");
        });
    });
    /** Combobox de setores **/

    /** Combobox de perfis **/
    $("#perfil").live("change", function() {
        var perfil = $(this).val();
        var login  = $("#login");
        var senha  = $("#senha");
        var check  = $("#checkPass");

        if (perfil == 'REPRESENTANTE') {
            login.attr("disabled", "disabled");
            senha.attr("disabled", "disabled");
            check.attr("disabled", "disabled");
        } else {
            login.removeAttr("disabled");
            senha.removeAttr("disabled");
            check.removeAttr("disabled");
        }
    });
    /** Combobox de perfis **/
});