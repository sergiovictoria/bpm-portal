var iniciarCamposData = function(){
	$("input.date").datepicker({
	    format: "dd/mm/yyyy",
	    language: "pt-BR"
	});
}

var limparCampoComentarios = function(){
	$(".comentarios").val("");
}

$(function(){
	iniciarCamposData();
});