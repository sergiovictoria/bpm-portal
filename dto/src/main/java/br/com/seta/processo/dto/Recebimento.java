package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.validacoes.ValidaDivergencias;
import br.com.seta.processo.validacoes.ValidarOutroComoTipoDeDocumento;
import br.com.seta.processo.validacoesCheck.GuiaCegaCheck;
import br.com.seta.processo.validacoesCheck.TipoDocumentoCheck;

@ValidaDivergencias(
		message="Para os casos de existência de divergências, é necessário selecionar a categoria e adicionar um motivo/justificativa", 
		groups={GuiaCegaCheck.class})
@ValidarOutroComoTipoDeDocumento(message="Favor descrever o tipo do documento", groups={TipoDocumentoCheck.class})
public class Recebimento implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	private String box;
	@NotBlank(message="O número da nota é obrigatório", groups={GuiaCegaCheck.class})
	private String nroNota;
	@NotBlank(message="Informar os conferentes", groups={GuiaCegaCheck.class})
	private String conferentes;
	private String descarregadores;
	private String nfSerie;
	@NotBlank(message="O tipo do documento é obrigatório", groups={TipoDocumentoCheck.class})
	private String tipoDocumento;
	private String descTipoDocumento;
	private String atividade;
	private String IsDivergencia = "NÃO";
	private String IsImposto;
	private String IsRecebimento;
	private String Ismedicacao;
	private String IsProduto;
	private String IsPrestacaoServico;	
	private String tipoDivergencia;
	private String IsAprovarComDivergencia;
	private String motivoOuJustificativaDivergencia;
	private String necessidadeAjusteImpostos;
	private String IsStatus;
	private List<Comentario> comentariosAprovRecebimentoDivergente = new ArrayList<Comentario>();
	
	/**
	 * Adiciona um novo comentário. O comentário será inserido na primeira posição da lista de comentários
	 * 
	 * @param usuario Usuário que fez o comentário
	 * @param texto Conteúdo do comentário
	 */
	public void adicionaComentarioAprovRecebDivergente(Usuario usuario, String texto){
		adicionaComentarioAprovRecebDivergente(new Comentario(usuario, texto));
	}
	
	/**
	 * Adiciona um novo comentário. O comentário será inserido na primeira posição da lista de comentários
	 * 
	 * @param comentario O comentário que será inserido
	 */
	public void adicionaComentarioAprovRecebDivergente(Comentario comentario) {
		this.comentariosAprovRecebimentoDivergente.add(0, comentario);
	}
	
	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public String getNroNota() {
		return nroNota;
	}

	public void setNroNota(String nroNota) {
		this.nroNota = nroNota;
	}

	public String getConferentes() {
		return conferentes;
	}

	public void setConferentes(String conferentes) {
		this.conferentes = conferentes;
	}

	public String getDescarregadores() {
		return descarregadores;
	}

	public void setDescarregadores(String descarregadores) {
		this.descarregadores = descarregadores;
	}

	public String getNfSerie() {
		return nfSerie;
	}

	public void setNfSerie(String nfSerie) {
		this.nfSerie = nfSerie;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getIsDivergencia() {
		return IsDivergencia;
	}

	public void setIsDivergencia(String isDivergencia) {
		IsDivergencia = isDivergencia;
	}

	public String getIsImposto() {
		return IsImposto;
	}

	public void setIsImposto(String isImposto) {
		IsImposto = isImposto;
	}

	public String getIsRecebimento() {
		return IsRecebimento;
	}

	public void setIsRecebimento(String isRecebimento) {
		IsRecebimento = isRecebimento;
	}

	public String getIsmedicacao() {
		return Ismedicacao;
	}

	public void setIsmedicacao(String ismedicacao) {
		Ismedicacao = ismedicacao;
	}

	public String getIsProduto() {
		return IsProduto;
	}

	public void setIsProduto(String isProduto) {
		IsProduto = isProduto;
	}

	public String getIsPrestacaoServico() {
		return IsPrestacaoServico;
	}

	public void setIsPrestacaoServico(String isPrestacaoServico) {
		IsPrestacaoServico = isPrestacaoServico;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}

	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}

	
	
	public String getTipoDivergencia() {
		return tipoDivergencia;
	}

	public void setTipoDivergencia(String tipoDivergencia) {
		this.tipoDivergencia = tipoDivergencia;
	}

	public String getMotivoOuJustificativaDivergencia() {
		return motivoOuJustificativaDivergencia;
	}

	public void setMotivoOuJustificativaDivergencia(
			String motivoOuJustificativaDivergencia) {
		this.motivoOuJustificativaDivergencia = motivoOuJustificativaDivergencia;
	}	
	
	public String getIsAprovarComDivergencia() {
		return IsAprovarComDivergencia;
	}

	public void setIsAprovarComDivergencia(String isAprovarComDivergencia) {
		IsAprovarComDivergencia = isAprovarComDivergencia;
	}

	
	public String getNecessidadeAjusteImpostos() {
		return necessidadeAjusteImpostos;
	}

	public void setNecessidadeAjusteImpostos(String necessidadeAjusteImpostos) {
		this.necessidadeAjusteImpostos = necessidadeAjusteImpostos;
	}

	public String getIsStatus() {
		return IsStatus;
	}

	public void setIsStatus(String isStatus) {
		IsStatus = isStatus;
	}

	public List<Comentario> getComentariosAprovRecebimentoDivergente() {
		return comentariosAprovRecebimentoDivergente;
	}

	public void setComentariosAprovRecebimentoDivergente(
			List<Comentario> comentariosAprovRecebimentoDivergente) {
		this.comentariosAprovRecebimentoDivergente = comentariosAprovRecebimentoDivergente;
	}

	@Override
	public String toString() {
		return "Recebimento [box=" + box + ", nroNota=" + nroNota
				+ ", conferentes=" + conferentes + ", descarregadores="
				+ descarregadores + ", nfSerie=" + nfSerie + ", tipoDocumento="
				+ tipoDocumento + ", descTipoDocumento=" + descTipoDocumento
				+ ", atividade=" + atividade + ", IsDivergencia="
				+ IsDivergencia + ", IsImposto=" + IsImposto
				+ ", IsRecebimento=" + IsRecebimento + ", Ismedicacao="
				+ Ismedicacao + ", IsProduto=" + IsProduto
				+ ", IsPrestacaoServico=" + IsPrestacaoServico
				+ ", tipoDivergencia=" + tipoDivergencia
				+ ", IsAprovarComDivergencia=" + IsAprovarComDivergencia
				+ ", motivoOuJustificativaDivergencia="
				+ motivoOuJustificativaDivergencia
				+ ", necessidadeAjusteImpostos=" + necessidadeAjusteImpostos
				+ ", IsStatus=" + IsStatus
				+ ", comentariosAprovRecebimentoDivergente="
				+ comentariosAprovRecebimentoDivergente + "]";
	}	
	
}
