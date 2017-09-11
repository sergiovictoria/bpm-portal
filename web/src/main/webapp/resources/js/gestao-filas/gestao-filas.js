/**
Trata o comportamento de marcar/desmarcar todos dos checkboxs
*/
function CheckBoxHandler() {

	this.marcarDesmarcarTodos = function() {
		var cbs = $("input[type='checkbox'].marcar");

		if (todosCheckBoxesMarcados()) {
			cbs.each(function() {
				this.checked = false;

			});
			habilitaDesabilitaBotoesAtribuicao(false);
			alteraBtnSelecaoParaSelecionarTodos();
		} else {
			cbs.each(function() {
				this.checked = true;
			});
			habilitaDesabilitaBotoesAtribuicao(true);
			alteraBtnSelecaoParaDesmarcarTodos();
		}
	}
}

var habilitaDesabilitaBotoesAtribuicaoNoEventoDeCheck = function() {
	$("input[type='checkbox'].marcar").click(function() {
		var algunCheckHabilitado = existeCheckBoxMarcado();
		habilitaDesabilitaBotoesAtribuicao(algunCheckHabilitado);

		if(todosCheckBoxesMarcados()){
			alteraBtnSelecaoParaDesmarcarTodos();
		}else{
			alteraBtnSelecaoParaSelecionarTodos();
		}
	})
}

var todosCheckBoxesMarcados = function() {
		var checkboxes = $("input[type='checkbox'].marcar");

		for (var i = 0; i < checkboxes.length; i++) {
			if (!checkboxes[i].checked)
				return false;
		}

		return true;
	}

var alteraBtnSelecaoParaSelecionarTodos = function(){
	alteraNomeBotaoSelecaoCheckboxes("Selecionar Todos");
}

var alteraBtnSelecaoParaDesmarcarTodos = function(){
	alteraNomeBotaoSelecaoCheckboxes("Desmarcar Todos");
}

var alteraNomeBotaoSelecaoCheckboxes = function(nomeBotao){
	$("#lblBotaoSelecao").text(nomeBotao);
}

var habilitaDesabilitaBotoesAtribuicao = function(habilitado) {
	$("#btnAtribuir, #buscaAtividadeBtn").prop("disabled", !habilitado);
}

var existeCheckBoxMarcado = function() {
	var checkboxes = $("input[type='checkbox'].marcar");
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked)
			return true
	}

	return false;
}

/**
	Define os estilos dos painéis de atividade quando estes são selecionados
	*/
var setEstiloAtividades = function() {
	$(".atividadePanel").first(".atividaePanel").addClass("itemSelecionado");

	/*$(".atividadePanel").click(function(){
		$(".atividadePanel").removeClass("itemSelecionado");
		$(this).addClass("itemSelecionado");
	});*/
}

function removerSelecionados(item) {
	$(".atividadePanel").removeClass("itemSelecionado");
	$(item).addClass("itemSelecionado");
}

/**
Oculta as seções de atividades e exibe uma mensagem de alerta, caso não tenhamos atividades
*/
var existeAtividades = function() {
	if ($(".atividadePanel").length === 0) {
		$(".atividadesTabela").hide();
		$(".navigator-container").hide();
		$(".secaoTimeline").hide();
		$(".alertaContainer").show();
	} else {
		$(".alertaContainer").hide();
		$(".atividadesTabela").show();
		$(".navigator-container").show();
		$(".secaoTimeline").show();
		$(".atividadesContainer").show();
	}
}