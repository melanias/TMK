$(function(){
    $(":text").setMask();
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

    //Validar login
    /*$("form").submit(function(){
        //Campos do form
        var cpf   = $("#cpf");
        var senha = $("#senha");

        //Erros
        var er = new Array();

        if (($.trim(cpf.val()) == "") && ($.trim(senha.val()) == "")) {
            er.push("Informe o CPF e a senha.");
            cpf.focus();
        } else {
            if ($.trim(cpf.val()) == "") {
                er.push("Faltou informar o CPF.");
                cpf.focus();
            }

            if ($.trim(senha.val()) == "") {
                er.push("Faltou informar a senha.");
                senha.focus();
            }
        }

        //Exibir os erros que ocorreram e cancelar o envio do form
        if (er.length > 0) {
            alert(er.join("\n"));
            return false;
        }
    });*/
});