/**
 * Classe responsável pelo gerenciamento da abertura das Modals
 */
function GerenciadorModals() {
	var idModal = "";

	this.abrirModal = function(idModal) {
		$(this.idModal).modal("show");
	}

	this.setModalSelecionado = function(idModal) {
		this.idModal = idModal;
	}
}