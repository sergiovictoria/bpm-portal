package br.com.seta.processo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
/**
 * Representa a entidade GE_EMPRESA da base de dados da CONSINCO
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(name="GeEmpresaEntity.empresasPorNroempresa", query="SELECT * FROM GE_EMPRESA WHERE STATUS = 'A' AND NROEMPRESA = ?1 ORDER BY NROEMPRESA ASC", resultClass=GeEmpresaEntity.class),
	@NamedNativeQuery(name="GeEmpresaEntity.empresasAtivas", query="SELECT * FROM GE_EMPRESA WHERE STATUS = 'A' ORDER BY NROEMPRESA ASC", resultClass=GeEmpresaEntity.class),
	@NamedNativeQuery(name="GeEmpresaEntity.empresasAtivasPorNomeReduzido", query="SELECT * FROM GE_EMPRESA WHERE NOMEREDUZIDO LIKE ?1 ORDER BY NROEMPRESA ASC", resultClass=GeEmpresaEntity.class),
})
@Entity
@Table(name = "GE_EMPRESA", uniqueConstraints = @UniqueConstraint(columnNames = "NOMEREDUZIDO"))
public class GeEmpresaEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_EMPRESAS_ATIVAS = "GeEmpresaEntity.empresasAtivas";
	public static final String QUERY_EMPRESAS_POR_NROEMPRESA = "GeEmpresaEntity.empresasPorNroempresa";
	public static final String QUERY_EMPRESAS_ATIVAS_POR_NOMEREDUZIDO = "GeEmpresaEntity.empresasAtivasPorNomeReduzido";
	
	private Integer nroempresa;
	private String fantasia;
	private String razaosocial;
	private String nomereduzido;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String pais;
	private Long nrocgc;
	private Byte digcgc;
	private String ddd;
	private Long fonenro;
	private String faxddd;
	private Integer faxnro;
	private String inscrestadual;
	private String inscrmunicipal;
	private String outrainscricao;
	private Integer matriz;
	private Date dtaalteracao;
	private String usualteracao;
	private Short empseguranca;
	private String endereconro;
	private String email;
	private String homepage;
	private String nrocontrolec5;
	private Short nroempseguranca;
	private String cmpltologradouro;
	private Integer seqcidade;
	private Integer seqbairro;
	private Integer seqlogradouro;
	private String nroinscinss;
	private String nroinscpispasepcisus;
	private String nroinscsuframa;
	private Integer seqpessoa;
	private String status;
	private String arqlogo;
	private String nomeusuariosmtp;
	private String emailsmtp;
	private String servidorsmtp;
	private Integer nroportasmtp;
	private String indautenticasmtp;
	private String nomecontasmtp;
	private String senhacontasmtp;
	private long nrobaseexportacao;
	private Integer maxqtdlicenca;
	private String indcmdautenticacao;
	private String emailvalidacaotnsrv;
	private String indusacertificado;
	private String caminhocertificado;
	private String senhacertificado;
	private String tipocertificado;	

	public GeEmpresaEntity() {
	}

	public GeEmpresaEntity(int nroempresa, String fantasia, String razaosocial, String nomereduzido, String status, long nrobaseexportacao) {
		this.nroempresa = nroempresa;
		this.fantasia = fantasia;
		this.razaosocial = razaosocial;
		this.nomereduzido = nomereduzido;
		this.status = status;
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public GeEmpresaEntity(int nroempresa, String fantasia, String razaosocial, String nomereduzido, String endereco, String bairro,
			String cep, String cidade, String estado, String pais, Long nrocgc, Byte digcgc, String ddd, Long fonenro, String faxddd,
			Integer faxnro, String inscrestadual, String inscrmunicipal, String outrainscricao, Integer matriz, Date dtaalteracao,
			String usualteracao, Short empseguranca, String endereconro, String email, String homepage, String nrocontrolec5,
			Short nroempseguranca, String cmpltologradouro, Integer seqcidade, Integer seqbairro, Integer seqlogradouro,
			String nroinscinss, String nroinscpispasepcisus, String nroinscsuframa, Integer seqpessoa, String status, String arqlogo,
			String nomeusuariosmtp, String emailsmtp, String servidorsmtp, Integer nroportasmtp, String indautenticasmtp,
			String nomecontasmtp, String senhacontasmtp, long nrobaseexportacao, Integer maxqtdlicenca, String indcmdautenticacao,
			String emailvalidacaotnsrv, String indusacertificado, String caminhocertificado, String senhacertificado, String tipocertificado) {
		this.nroempresa = nroempresa;
		this.fantasia = fantasia;
		this.razaosocial = razaosocial;
		this.nomereduzido = nomereduzido;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.nrocgc = nrocgc;
		this.digcgc = digcgc;
		this.ddd = ddd;
		this.fonenro = fonenro;
		this.faxddd = faxddd;
		this.faxnro = faxnro;
		this.inscrestadual = inscrestadual;
		this.inscrmunicipal = inscrmunicipal;
		this.outrainscricao = outrainscricao;
		this.matriz = matriz;
		this.dtaalteracao = dtaalteracao;
		this.usualteracao = usualteracao;
		this.empseguranca = empseguranca;
		this.endereconro = endereconro;
		this.email = email;
		this.homepage = homepage;
		this.nrocontrolec5 = nrocontrolec5;
		this.nroempseguranca = nroempseguranca;
		this.cmpltologradouro = cmpltologradouro;
		this.seqcidade = seqcidade;
		this.seqbairro = seqbairro;
		this.seqlogradouro = seqlogradouro;
		this.nroinscinss = nroinscinss;
		this.nroinscpispasepcisus = nroinscpispasepcisus;
		this.nroinscsuframa = nroinscsuframa;
		this.seqpessoa = seqpessoa;
		this.status = status;
		this.arqlogo = arqlogo;
		this.nomeusuariosmtp = nomeusuariosmtp;
		this.emailsmtp = emailsmtp;
		this.servidorsmtp = servidorsmtp;
		this.nroportasmtp = nroportasmtp;
		this.indautenticasmtp = indautenticasmtp;
		this.nomecontasmtp = nomecontasmtp;
		this.senhacontasmtp = senhacontasmtp;
		this.nrobaseexportacao = nrobaseexportacao;
		this.maxqtdlicenca = maxqtdlicenca;
		this.indcmdautenticacao = indcmdautenticacao;
		this.emailvalidacaotnsrv = emailvalidacaotnsrv;
		this.indusacertificado = indusacertificado;
		this.caminhocertificado = caminhocertificado;
		this.senhacertificado = senhacertificado;
		this.tipocertificado = tipocertificado;
	}

	@Id
	@Column(name = "NROEMPRESA", unique = true, nullable = false, precision = 3, scale = 0)
	public int getNroempresa() {
		return this.nroempresa;
	}

	public void setNroempresa(int nroempresa) {
		this.nroempresa = nroempresa;
	}

	@Column(name = "FANTASIA", nullable = false, length = 30)
	public String getFantasia() {
		return this.fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	@Column(name = "RAZAOSOCIAL", nullable = false, length = 40)
	public String getRazaosocial() {
		return this.razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	@Column(name = "NOMEREDUZIDO", unique = true, nullable = false, length = 12)
	public String getNomereduzido() {
		return this.nomereduzido;
	}

	public void setNomereduzido(String nomereduzido) {
		this.nomereduzido = nomereduzido;
	}

	@Column(name = "ENDERECO", length = 40)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "BAIRRO", length = 30)
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "CEP", length = 12)
	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "CIDADE", length = 30)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "ESTADO", length = 2)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "PAIS", length = 25)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(name = "NROCGC", precision = 16, scale = 0)
	public Long getNrocgc() {
		return this.nrocgc;
	}

	public void setNrocgc(Long nrocgc) {
		this.nrocgc = nrocgc;
	}

	@Column(name = "DIGCGC", precision = 2, scale = 0)
	public Byte getDigcgc() {
		return this.digcgc;
	}

	public void setDigcgc(Byte digcgc) {
		this.digcgc = digcgc;
	}

	@Column(name = "DDD", length = 5)
	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	@Column(name = "FONENRO", precision = 12, scale = 0)
	public Long getFonenro() {
		return this.fonenro;
	}

	public void setFonenro(Long fonenro) {
		this.fonenro = fonenro;
	}

	@Column(name = "FAXDDD", length = 5)
	public String getFaxddd() {
		return this.faxddd;
	}

	public void setFaxddd(String faxddd) {
		this.faxddd = faxddd;
	}

	@Column(name = "FAXNRO", precision = 8, scale = 0)
	public Integer getFaxnro() {
		return this.faxnro;
	}

	public void setFaxnro(Integer faxnro) {
		this.faxnro = faxnro;
	}

	@Column(name = "INSCRESTADUAL", length = 20)
	public String getInscrestadual() {
		return this.inscrestadual;
	}

	public void setInscrestadual(String inscrestadual) {
		this.inscrestadual = inscrestadual;
	}

	@Column(name = "INSCRMUNICIPAL", length = 20)
	public String getInscrmunicipal() {
		return this.inscrmunicipal;
	}

	public void setInscrmunicipal(String inscrmunicipal) {
		this.inscrmunicipal = inscrmunicipal;
	}

	@Column(name = "OUTRAINSCRICAO", length = 20)
	public String getOutrainscricao() {
		return this.outrainscricao;
	}

	public void setOutrainscricao(String outrainscricao) {
		this.outrainscricao = outrainscricao;
	}

	@Column(name = "MATRIZ", precision = 3, scale = 0)
	public int getMatriz() {
		return this.matriz;
	}

	public void setMatriz(int matriz) {
		this.matriz = matriz;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAALTERACAO", length = 7)
	public Date getDtaalteracao() {
		return this.dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	@Column(name = "USUALTERACAO", length = 12)
	public String getUsualteracao() {
		return this.usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	@Column(name = "EMPSEGURANCA", precision = 3, scale = 0)
	public Short getEmpseguranca() {
		return this.empseguranca;
	}

	public void setEmpseguranca(Short empseguranca) {
		this.empseguranca = empseguranca;
	}

	@Column(name = "ENDERECONRO", length = 10)
	public String getEndereconro() {
		return this.endereconro;
	}

	public void setEndereconro(String endereconro) {
		this.endereconro = endereconro;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "HOMEPAGE", length = 50)
	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Column(name = "NROCONTROLEC5", length = 2000)
	public String getNrocontrolec5() {
		return this.nrocontrolec5;
	}

	public void setNrocontrolec5(String nrocontrolec5) {
		this.nrocontrolec5 = nrocontrolec5;
	}

	@Column(name = "NROEMPSEGURANCA", precision = 3, scale = 0)
	public Short getNroempseguranca() {
		return this.nroempseguranca;
	}

	public void setNroempseguranca(Short nroempseguranca) {
		this.nroempseguranca = nroempseguranca;
	}

	@Column(name = "CMPLTOLOGRADOURO", length = 10)
	public String getCmpltologradouro() {
		return this.cmpltologradouro;
	}

	public void setCmpltologradouro(String cmpltologradouro) {
		this.cmpltologradouro = cmpltologradouro;
	}

	@Column(name = "SEQCIDADE", precision = 6, scale = 0)
	public Integer getSeqcidade() {
		return this.seqcidade;
	}

	public void setSeqcidade(Integer seqcidade) {
		this.seqcidade = seqcidade;
	}

	@Column(name = "SEQBAIRRO", precision = 7, scale = 0)
	public Integer getSeqbairro() {
		return this.seqbairro;
	}

	public void setSeqbairro(Integer seqbairro) {
		this.seqbairro = seqbairro;
	}

	@Column(name = "SEQLOGRADOURO", precision = 6, scale = 0)
	public Integer getSeqlogradouro() {
		return this.seqlogradouro;
	}

	public void setSeqlogradouro(Integer seqlogradouro) {
		this.seqlogradouro = seqlogradouro;
	}

	@Column(name = "NROINSCINSS", length = 20)
	public String getNroinscinss() {
		return this.nroinscinss;
	}

	public void setNroinscinss(String nroinscinss) {
		this.nroinscinss = nroinscinss;
	}

	@Column(name = "NROINSCPISPASEPCISUS", length = 20)
	public String getNroinscpispasepcisus() {
		return this.nroinscpispasepcisus;
	}

	public void setNroinscpispasepcisus(String nroinscpispasepcisus) {
		this.nroinscpispasepcisus = nroinscpispasepcisus;
	}

	@Column(name = "NROINSCSUFRAMA", length = 20)
	public String getNroinscsuframa() {
		return this.nroinscsuframa;
	}

	public void setNroinscsuframa(String nroinscsuframa) {
		this.nroinscsuframa = nroinscsuframa;
	}

	@Column(name = "SEQPESSOA", precision = 8, scale = 0)
	public Integer getSeqpessoa() {
		return this.seqpessoa;
	}

	public void setSeqpessoa(Integer seqpessoa) {
		this.seqpessoa = seqpessoa;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ARQLOGO", length = 40)
	public String getArqlogo() {
		return this.arqlogo;
	}

	public void setArqlogo(String arqlogo) {
		this.arqlogo = arqlogo;
	}

	@Column(name = "NOMEUSUARIOSMTP", length = 40)
	public String getNomeusuariosmtp() {
		return this.nomeusuariosmtp;
	}

	public void setNomeusuariosmtp(String nomeusuariosmtp) {
		this.nomeusuariosmtp = nomeusuariosmtp;
	}

	@Column(name = "EMAILSMTP", length = 40)
	public String getEmailsmtp() {
		return this.emailsmtp;
	}

	public void setEmailsmtp(String emailsmtp) {
		this.emailsmtp = emailsmtp;
	}

	@Column(name = "SERVIDORSMTP", length = 40)
	public String getServidorsmtp() {
		return this.servidorsmtp;
	}

	public void setServidorsmtp(String servidorsmtp) {
		this.servidorsmtp = servidorsmtp;
	}

	@Column(name = "NROPORTASMTP", precision = 8, scale = 0)
	public Integer getNroportasmtp() {
		return this.nroportasmtp;
	}

	public void setNroportasmtp(Integer nroportasmtp) {
		this.nroportasmtp = nroportasmtp;
	}

	@Column(name = "INDAUTENTICASMTP", length = 1)
	public String getIndautenticasmtp() {
		return this.indautenticasmtp;
	}

	public void setIndautenticasmtp(String indautenticasmtp) {
		this.indautenticasmtp = indautenticasmtp;
	}

	@Column(name = "NOMECONTASMTP", length = 40)
	public String getNomecontasmtp() {
		return this.nomecontasmtp;
	}

	public void setNomecontasmtp(String nomecontasmtp) {
		this.nomecontasmtp = nomecontasmtp;
	}

	@Column(name = "SENHACONTASMTP", length = 180)
	public String getSenhacontasmtp() {
		return this.senhacontasmtp;
	}

	public void setSenhacontasmtp(String senhacontasmtp) {
		this.senhacontasmtp = senhacontasmtp;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "MAXQTDLICENCA", precision = 6, scale = 0)
	public Integer getMaxqtdlicenca() {
		return this.maxqtdlicenca;
	}

	public void setMaxqtdlicenca(Integer maxqtdlicenca) {
		this.maxqtdlicenca = maxqtdlicenca;
	}

	@Column(name = "INDCMDAUTENTICACAO", length = 1)
	public String getIndcmdautenticacao() {
		return this.indcmdautenticacao;
	}

	public void setIndcmdautenticacao(String indcmdautenticacao) {
		this.indcmdautenticacao = indcmdautenticacao;
	}

	@Column(name = "EMAILVALIDACAOTNSRV", length = 50)
	public String getEmailvalidacaotnsrv() {
		return this.emailvalidacaotnsrv;
	}

	public void setEmailvalidacaotnsrv(String emailvalidacaotnsrv) {
		this.emailvalidacaotnsrv = emailvalidacaotnsrv;
	}

	@Column(name = "INDUSACERTIFICADO", length = 1)
	public String getIndusacertificado() {
		return this.indusacertificado;
	}

	public void setIndusacertificado(String indusacertificado) {
		this.indusacertificado = indusacertificado;
	}

	@Column(name = "CAMINHOCERTIFICADO", length = 200)
	public String getCaminhocertificado() {
		return this.caminhocertificado;
	}

	public void setCaminhocertificado(String caminhocertificado) {
		this.caminhocertificado = caminhocertificado;
	}

	@Column(name = "SENHACERTIFICADO", length = 200)
	public String getSenhacertificado() {
		return this.senhacertificado;
	}

	public void setSenhacertificado(String senhacertificado) {
		this.senhacertificado = senhacertificado;
	}

	@Column(name = "TIPOCERTIFICADO", length = 1)
	public String getTipocertificado() {
		return this.tipocertificado;
	}

	public void setTipocertificado(String tipocertificado) {
		this.tipocertificado = tipocertificado;
	}	

	public void setNroempresa(Integer nroempresa) {
		this.nroempresa = nroempresa;
	}

	public void setMatriz(Integer matriz) {
		this.matriz = matriz;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arqlogo == null) ? 0 : arqlogo.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime
				* result
				+ ((caminhocertificado == null) ? 0 : caminhocertificado
						.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime
				* result
				+ ((cmpltologradouro == null) ? 0 : cmpltologradouro.hashCode());
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((digcgc == null) ? 0 : digcgc.hashCode());
		result = prime * result
				+ ((dtaalteracao == null) ? 0 : dtaalteracao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailsmtp == null) ? 0 : emailsmtp.hashCode());
		result = prime
				* result
				+ ((emailvalidacaotnsrv == null) ? 0 : emailvalidacaotnsrv
						.hashCode());
		result = prime * result
				+ ((empseguranca == null) ? 0 : empseguranca.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((endereconro == null) ? 0 : endereconro.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((fantasia == null) ? 0 : fantasia.hashCode());
		result = prime * result + ((faxddd == null) ? 0 : faxddd.hashCode());
		result = prime * result + ((faxnro == null) ? 0 : faxnro.hashCode());
		result = prime * result + ((fonenro == null) ? 0 : fonenro.hashCode());
		result = prime * result
				+ ((homepage == null) ? 0 : homepage.hashCode());
		result = prime
				* result
				+ ((indautenticasmtp == null) ? 0 : indautenticasmtp.hashCode());
		result = prime
				* result
				+ ((indcmdautenticacao == null) ? 0 : indcmdautenticacao
						.hashCode());
		result = prime
				* result
				+ ((indusacertificado == null) ? 0 : indusacertificado
						.hashCode());
		result = prime * result
				+ ((inscrestadual == null) ? 0 : inscrestadual.hashCode());
		result = prime * result
				+ ((inscrmunicipal == null) ? 0 : inscrmunicipal.hashCode());
		result = prime * result + ((matriz == null) ? 0 : matriz.hashCode());
		result = prime * result
				+ ((maxqtdlicenca == null) ? 0 : maxqtdlicenca.hashCode());
		result = prime * result
				+ ((nomecontasmtp == null) ? 0 : nomecontasmtp.hashCode());
		result = prime * result
				+ ((nomereduzido == null) ? 0 : nomereduzido.hashCode());
		result = prime * result
				+ ((nomeusuariosmtp == null) ? 0 : nomeusuariosmtp.hashCode());
		result = prime * result
				+ (int) (nrobaseexportacao ^ (nrobaseexportacao >>> 32));
		result = prime * result + ((nrocgc == null) ? 0 : nrocgc.hashCode());
		result = prime * result
				+ ((nrocontrolec5 == null) ? 0 : nrocontrolec5.hashCode());
		result = prime * result
				+ ((nroempresa == null) ? 0 : nroempresa.hashCode());
		result = prime * result
				+ ((nroempseguranca == null) ? 0 : nroempseguranca.hashCode());
		result = prime * result
				+ ((nroinscinss == null) ? 0 : nroinscinss.hashCode());
		result = prime
				* result
				+ ((nroinscpispasepcisus == null) ? 0 : nroinscpispasepcisus
						.hashCode());
		result = prime * result
				+ ((nroinscsuframa == null) ? 0 : nroinscsuframa.hashCode());
		result = prime * result
				+ ((nroportasmtp == null) ? 0 : nroportasmtp.hashCode());
		result = prime * result
				+ ((outrainscricao == null) ? 0 : outrainscricao.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((razaosocial == null) ? 0 : razaosocial.hashCode());
		result = prime
				* result
				+ ((senhacertificado == null) ? 0 : senhacertificado.hashCode());
		result = prime * result
				+ ((senhacontasmtp == null) ? 0 : senhacontasmtp.hashCode());
		result = prime * result
				+ ((seqbairro == null) ? 0 : seqbairro.hashCode());
		result = prime * result
				+ ((seqcidade == null) ? 0 : seqcidade.hashCode());
		result = prime * result
				+ ((seqlogradouro == null) ? 0 : seqlogradouro.hashCode());
		result = prime * result
				+ ((seqpessoa == null) ? 0 : seqpessoa.hashCode());
		result = prime * result
				+ ((servidorsmtp == null) ? 0 : servidorsmtp.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((tipocertificado == null) ? 0 : tipocertificado.hashCode());
		result = prime * result
				+ ((usualteracao == null) ? 0 : usualteracao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeEmpresaEntity other = (GeEmpresaEntity) obj;
		if (arqlogo == null) {
			if (other.arqlogo != null)
				return false;
		} else if (!arqlogo.equals(other.arqlogo))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (caminhocertificado == null) {
			if (other.caminhocertificado != null)
				return false;
		} else if (!caminhocertificado.equals(other.caminhocertificado))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cmpltologradouro == null) {
			if (other.cmpltologradouro != null)
				return false;
		} else if (!cmpltologradouro.equals(other.cmpltologradouro))
			return false;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (digcgc == null) {
			if (other.digcgc != null)
				return false;
		} else if (!digcgc.equals(other.digcgc))
			return false;
		if (dtaalteracao == null) {
			if (other.dtaalteracao != null)
				return false;
		} else if (!dtaalteracao.equals(other.dtaalteracao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailsmtp == null) {
			if (other.emailsmtp != null)
				return false;
		} else if (!emailsmtp.equals(other.emailsmtp))
			return false;
		if (emailvalidacaotnsrv == null) {
			if (other.emailvalidacaotnsrv != null)
				return false;
		} else if (!emailvalidacaotnsrv.equals(other.emailvalidacaotnsrv))
			return false;
		if (empseguranca == null) {
			if (other.empseguranca != null)
				return false;
		} else if (!empseguranca.equals(other.empseguranca))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (endereconro == null) {
			if (other.endereconro != null)
				return false;
		} else if (!endereconro.equals(other.endereconro))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fantasia == null) {
			if (other.fantasia != null)
				return false;
		} else if (!fantasia.equals(other.fantasia))
			return false;
		if (faxddd == null) {
			if (other.faxddd != null)
				return false;
		} else if (!faxddd.equals(other.faxddd))
			return false;
		if (faxnro == null) {
			if (other.faxnro != null)
				return false;
		} else if (!faxnro.equals(other.faxnro))
			return false;
		if (fonenro == null) {
			if (other.fonenro != null)
				return false;
		} else if (!fonenro.equals(other.fonenro))
			return false;
		if (homepage == null) {
			if (other.homepage != null)
				return false;
		} else if (!homepage.equals(other.homepage))
			return false;
		if (indautenticasmtp == null) {
			if (other.indautenticasmtp != null)
				return false;
		} else if (!indautenticasmtp.equals(other.indautenticasmtp))
			return false;
		if (indcmdautenticacao == null) {
			if (other.indcmdautenticacao != null)
				return false;
		} else if (!indcmdautenticacao.equals(other.indcmdautenticacao))
			return false;
		if (indusacertificado == null) {
			if (other.indusacertificado != null)
				return false;
		} else if (!indusacertificado.equals(other.indusacertificado))
			return false;
		if (inscrestadual == null) {
			if (other.inscrestadual != null)
				return false;
		} else if (!inscrestadual.equals(other.inscrestadual))
			return false;
		if (inscrmunicipal == null) {
			if (other.inscrmunicipal != null)
				return false;
		} else if (!inscrmunicipal.equals(other.inscrmunicipal))
			return false;
		if (matriz == null) {
			if (other.matriz != null)
				return false;
		} else if (!matriz.equals(other.matriz))
			return false;
		if (maxqtdlicenca == null) {
			if (other.maxqtdlicenca != null)
				return false;
		} else if (!maxqtdlicenca.equals(other.maxqtdlicenca))
			return false;
		if (nomecontasmtp == null) {
			if (other.nomecontasmtp != null)
				return false;
		} else if (!nomecontasmtp.equals(other.nomecontasmtp))
			return false;
		if (nomereduzido == null) {
			if (other.nomereduzido != null)
				return false;
		} else if (!nomereduzido.equals(other.nomereduzido))
			return false;
		if (nomeusuariosmtp == null) {
			if (other.nomeusuariosmtp != null)
				return false;
		} else if (!nomeusuariosmtp.equals(other.nomeusuariosmtp))
			return false;
		if (nrobaseexportacao != other.nrobaseexportacao)
			return false;
		if (nrocgc == null) {
			if (other.nrocgc != null)
				return false;
		} else if (!nrocgc.equals(other.nrocgc))
			return false;
		if (nrocontrolec5 == null) {
			if (other.nrocontrolec5 != null)
				return false;
		} else if (!nrocontrolec5.equals(other.nrocontrolec5))
			return false;
		if (nroempresa == null) {
			if (other.nroempresa != null)
				return false;
		} else if (!nroempresa.equals(other.nroempresa))
			return false;
		if (nroempseguranca == null) {
			if (other.nroempseguranca != null)
				return false;
		} else if (!nroempseguranca.equals(other.nroempseguranca))
			return false;
		if (nroinscinss == null) {
			if (other.nroinscinss != null)
				return false;
		} else if (!nroinscinss.equals(other.nroinscinss))
			return false;
		if (nroinscpispasepcisus == null) {
			if (other.nroinscpispasepcisus != null)
				return false;
		} else if (!nroinscpispasepcisus.equals(other.nroinscpispasepcisus))
			return false;
		if (nroinscsuframa == null) {
			if (other.nroinscsuframa != null)
				return false;
		} else if (!nroinscsuframa.equals(other.nroinscsuframa))
			return false;
		if (nroportasmtp == null) {
			if (other.nroportasmtp != null)
				return false;
		} else if (!nroportasmtp.equals(other.nroportasmtp))
			return false;
		if (outrainscricao == null) {
			if (other.outrainscricao != null)
				return false;
		} else if (!outrainscricao.equals(other.outrainscricao))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (razaosocial == null) {
			if (other.razaosocial != null)
				return false;
		} else if (!razaosocial.equals(other.razaosocial))
			return false;
		if (senhacertificado == null) {
			if (other.senhacertificado != null)
				return false;
		} else if (!senhacertificado.equals(other.senhacertificado))
			return false;
		if (senhacontasmtp == null) {
			if (other.senhacontasmtp != null)
				return false;
		} else if (!senhacontasmtp.equals(other.senhacontasmtp))
			return false;
		if (seqbairro == null) {
			if (other.seqbairro != null)
				return false;
		} else if (!seqbairro.equals(other.seqbairro))
			return false;
		if (seqcidade == null) {
			if (other.seqcidade != null)
				return false;
		} else if (!seqcidade.equals(other.seqcidade))
			return false;
		if (seqlogradouro == null) {
			if (other.seqlogradouro != null)
				return false;
		} else if (!seqlogradouro.equals(other.seqlogradouro))
			return false;
		if (seqpessoa == null) {
			if (other.seqpessoa != null)
				return false;
		} else if (!seqpessoa.equals(other.seqpessoa))
			return false;
		if (servidorsmtp == null) {
			if (other.servidorsmtp != null)
				return false;
		} else if (!servidorsmtp.equals(other.servidorsmtp))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipocertificado == null) {
			if (other.tipocertificado != null)
				return false;
		} else if (!tipocertificado.equals(other.tipocertificado))
			return false;
		if (usualteracao == null) {
			if (other.usualteracao != null)
				return false;
		} else if (!usualteracao.equals(other.usualteracao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GeEmpresaEntity [nroempresa=" + nroempresa + ", fantasia="
				+ fantasia + ", razaosocial=" + razaosocial + ", nomereduzido="
				+ nomereduzido + ", endereco=" + endereco + ", bairro="
				+ bairro + ", cep=" + cep + ", cidade=" + cidade + ", estado="
				+ estado + ", pais=" + pais + ", nrocgc=" + nrocgc
				+ ", digcgc=" + digcgc + ", ddd=" + ddd + ", fonenro="
				+ fonenro + ", faxddd=" + faxddd + ", faxnro=" + faxnro
				+ ", inscrestadual=" + inscrestadual + ", inscrmunicipal="
				+ inscrmunicipal + ", outrainscricao=" + outrainscricao
				+ ", matriz=" + matriz + ", dtaalteracao=" + dtaalteracao
				+ ", usualteracao=" + usualteracao + ", empseguranca="
				+ empseguranca + ", endereconro=" + endereconro + ", email="
				+ email + ", homepage=" + homepage + ", nrocontrolec5="
				+ nrocontrolec5 + ", nroempseguranca=" + nroempseguranca
				+ ", cmpltologradouro=" + cmpltologradouro + ", seqcidade="
				+ seqcidade + ", seqbairro=" + seqbairro + ", seqlogradouro="
				+ seqlogradouro + ", nroinscinss=" + nroinscinss
				+ ", nroinscpispasepcisus=" + nroinscpispasepcisus
				+ ", nroinscsuframa=" + nroinscsuframa + ", seqpessoa="
				+ seqpessoa + ", status=" + status + ", arqlogo=" + arqlogo
				+ ", nomeusuariosmtp=" + nomeusuariosmtp + ", emailsmtp="
				+ emailsmtp + ", servidorsmtp=" + servidorsmtp
				+ ", nroportasmtp=" + nroportasmtp + ", indautenticasmtp="
				+ indautenticasmtp + ", nomecontasmtp=" + nomecontasmtp
				+ ", senhacontasmtp=" + senhacontasmtp + ", nrobaseexportacao="
				+ nrobaseexportacao + ", maxqtdlicenca=" + maxqtdlicenca
				+ ", indcmdautenticacao=" + indcmdautenticacao
				+ ", emailvalidacaotnsrv=" + emailvalidacaotnsrv
				+ ", indusacertificado=" + indusacertificado
				+ ", caminhocertificado=" + caminhocertificado
				+ ", senhacertificado=" + senhacertificado
				+ ", tipocertificado=" + tipocertificado + "]";
	}
}
