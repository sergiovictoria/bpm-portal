var iniciarCamposData = function(){
	$("input.date").datepicker({
	    format: "dd/mm/yyyy",
	    language: "pt-BR"
	});
}

var iniciarCamposMonetarios = function(){
	var _mask = "#.##0,00";
	
	$(".monetario").mask(_mask, 
			{
				reverse: true
			}
	);
}

var iniciarCamposNumericos = function(){
	$(".numerico").on("keyup", function(event){
		var valor = this.value;
		if(jQuery.isNumeric(valor) === false){
			this.value = this.value.slice(0, -1);
		}
	});
}

var deMonetarioParaNumero = function(stringMoney){
	var stringNro = stringMoney.replace(/\./g, "").replace(/\,/g, ".");
	return Number(stringNro);
}

/**
* Formata uma String que representa um valor númerico como Moeda
* Referência/Fonte: http://codigosprontos.blogspot.com.br/2010/06/o-codigo-function-moedavalor-casas.html
* 
* @Params
* valor Obrigatório. String que representa um número (a parte decimal deve ser separada da parte inteira por vírgula)
* casas Obrigatório. Quantidade de casas decimais
* separdor_decimal  Obrigatório. Separador de decimais, em geral é utilizado ","
* separador_milhar  Obrigatório. Separado de milhar, em gerla é utlizado "."
* moeda Opcional. Abreviatura da moeda. Por exemplo: para Real, utilizar R$
* 
* @Return
* Valor formatado como valor monetário
* 
* @Ex
* var resultado = moeda("1000.5", 2, ',', '.', 'R$');
* resultado será igual a "R$ 1.000,50"
* 
* */
function moeda(valor, casas, separdor_decimal, separador_milhar, moeda){ 
	 
    var valor_total = parseInt(valor * (Math.pow(10,casas)));
    var inteiros =  parseInt(parseInt(valor * (Math.pow(10,casas))) / parseFloat(Math.pow(10,casas)));
    var centavos = parseInt(parseInt(valor * (Math.pow(10,casas))) % parseFloat(Math.pow(10,casas)));


    if(centavos%10 == 0 && centavos+"".length<2 ){
        centavos = centavos+"0";
    }else if(centavos<10){
        centavos = "0"+centavos;
    }

    var milhares = parseInt(inteiros/1000);
    inteiros = inteiros % 1000; 

    var retorno = "";

    if(milhares>0){
            retorno = milhares+""+separador_milhar+""+retorno
        if(inteiros == 0){
            inteiros = "000";
        } else if(inteiros < 10){
            inteiros = "00"+inteiros; 
        } else if(inteiros < 100){
            inteiros = "0"+inteiros; 
        }
    }
    retorno += inteiros+""+separdor_decimal+""+centavos;

    if(moeda){
        retorno = moeda+ " "+ retorno
    }

    return retorno;
}

var calcularValorTotal = function(){
	var valorTotal = 0;

	$(".valorUnitario").each(function(index){
		valorTotal += deMonetarioParaNumero(this.value);
	});
	
	$("#vlrTotalContabil")[0].value = moeda(valorTotal.toFixed(2), 2, ",", ".", null);	
}

var exibirMensagemSucesso = function(titulo, mensagem){
	_criaNotificacao(titulo, mensagem, "success", "fa fa-check");
}

var exibirMensagemInformacao = function(titulo, mensagem){
	_criaNotificacao(titulo, mensagem, "info", "fa fa-info");	
}

var exibirMensagemAdvertencia = function(titulo, mensagem){
	_criaNotificacao(titulo, mensagem, "error", "fa fa-times");	
}

var _criaNotificacao = function(titulo, mensagem, tipo, icone){
	new PNotify({
		title: titulo,
		text: mensagem,
		delay: 3000,
		type: tipo,
		icon: icone		
	});
}

var limparCampoComentarios = function(){
	$(".comentarios").val("");
}

/**
 * Ativa um par de aba e secao
 * @params
 * idAba id html da aba. Não usar o prefixo #
 * idSecao id html da seção (tab-pane). Não usar o prefixo #
 */
var ativarAbaSecao = function(idAba, idSecao){
	var ACTIVE_CLASS = "active";
	idAba  = "#" + idAba;
	idSecao = "#" + idSecao; 

	$(".completed.active, .tab-pane.active ").removeClass(ACTIVE_CLASS);
	$(idAba + "," + idSecao).addClass("active");
}

var exibeCarregamento = function(){
	$('#carregamentoModal').modal('show');
}

var ocultaCarregamento = function(){
	$('#carregamentoModal').modal('hide');
}

$(function(){
	iniciarCamposData();
	iniciarCamposMonetarios();
	iniciarCamposNumericos();
	//$("#desdobramento").mask("00#/##");
});