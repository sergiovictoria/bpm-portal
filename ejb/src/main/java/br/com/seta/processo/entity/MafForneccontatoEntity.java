package br.com.seta.processo.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "MAF_FORNECCONTATO")

public class MafForneccontatoEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal seqforneccontato;
	private BigDecimal seqfornecedor;
	private Long nrocgccpf;
	private Long digcgccpf;
	private String nomerazao;
	private String fone;
	private String celular;
	private String fax;
	private String email;
	private String nomecontatoemailped;
	private String emailpedcompra;
	private String indtipocontato;
	private String nomecontatoemailage;
	private String emailagefornec;
	private String indprincipal;
	private String nomecontatoemaildev;
	private String emaildevfornec;
	private String cargo;
	private String rg;
	private String nomecontatoemailacordo;
	private String emailacordo;
	private String nomecontatotitulo;
	private String emailtitulo;
	private String nomecontatolancpromo;
	private String emaillancpromo;
	private String emailregdevfornec;
	private String nomecontatoemailregdev;

	public MafForneccontatoEntity() {
	}

	public MafForneccontatoEntity(BigDecimal seqforneccontato, BigDecimal seqfornecedor, String nomerazao) {
		this.seqforneccontato = seqforneccontato;
		this.seqfornecedor = seqfornecedor;
		this.nomerazao = nomerazao;
	}

	public MafForneccontatoEntity(BigDecimal seqforneccontato, BigDecimal seqfornecedor, Long nrocgccpf, Long digcgccpf, String nomerazao,
			String fone, String celular, String fax, String email, String nomecontatoemailped, String emailpedcompra,
			String indtipocontato, String nomecontatoemailage, String emailagefornec, String indprincipal, String nomecontatoemaildev,
			String emaildevfornec, String cargo, String rg, String nomecontatoemailacordo, String emailacordo, String nomecontatotitulo,
			String emailtitulo, String nomecontatolancpromo, String emaillancpromo, String emailregdevfornec, String nomecontatoemailregdev) {
		this.seqforneccontato = seqforneccontato;
		this.seqfornecedor = seqfornecedor;
		this.nrocgccpf = nrocgccpf;
		this.digcgccpf = digcgccpf;
		this.nomerazao = nomerazao;
		this.fone = fone;
		this.celular = celular;
		this.fax = fax;
		this.email = email;
		this.nomecontatoemailped = nomecontatoemailped;
		this.emailpedcompra = emailpedcompra;
		this.indtipocontato = indtipocontato;
		this.nomecontatoemailage = nomecontatoemailage;
		this.emailagefornec = emailagefornec;
		this.indprincipal = indprincipal;
		this.nomecontatoemaildev = nomecontatoemaildev;
		this.emaildevfornec = emaildevfornec;
		this.cargo = cargo;
		this.rg = rg;
		this.nomecontatoemailacordo = nomecontatoemailacordo;
		this.emailacordo = emailacordo;
		this.nomecontatotitulo = nomecontatotitulo;
		this.emailtitulo = emailtitulo;
		this.nomecontatolancpromo = nomecontatolancpromo;
		this.emaillancpromo = emaillancpromo;
		this.emailregdevfornec = emailregdevfornec;
		this.nomecontatoemailregdev = nomecontatoemailregdev;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQFORNECCONTATO")
    @SequenceGenerator(name="SEQFORNECCONTATO", sequenceName="S_MAD_FORNECCONTATO", allocationSize=1)
	@Column(name = "SEQFORNECCONTATO", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqforneccontato() {
		return this.seqforneccontato;
	}

	public void setSeqforneccontato(BigDecimal seqforneccontato) {
		this.seqforneccontato = seqforneccontato;
	}

	@Column(name = "SEQFORNECEDOR", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSeqfornecedor() {
		return this.seqfornecedor;
	}

	public void setSeqfornecedor(BigDecimal seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}

	@Column(name = "NROCGCCPF", precision = 13, scale = 0)
	public Long getNrocgccpf() {
		return this.nrocgccpf;
	}

	public void setNrocgccpf(Long nrocgccpf) {
		this.nrocgccpf = nrocgccpf;
	}

	@Column(name = "DIGCGCCPF", precision = 2, scale = 0)
	public Long getDigcgccpf() {
		return this.digcgccpf;
	}

	public void setDigcgccpf(Long digcgccpf) {
		this.digcgccpf = digcgccpf;
	}

	@Column(name = "NOMERAZAO", nullable = false, length = 40)
	public String getNomerazao() {
		return this.nomerazao;
	}

	public void setNomerazao(String nomerazao) {
		this.nomerazao = nomerazao;
	}

	@Column(name = "FONE", length = 40)
	public String getFone() {
		return this.fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	@Column(name = "CELULAR", length = 40)
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "FAX", length = 40)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "EMAIL", length = 250)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NOMECONTATOEMAILPED", length = 40)
	public String getNomecontatoemailped() {
		return this.nomecontatoemailped;
	}

	public void setNomecontatoemailped(String nomecontatoemailped) {
		this.nomecontatoemailped = nomecontatoemailped;
	}

	@Column(name = "EMAILPEDCOMPRA", length = 250)
	public String getEmailpedcompra() {
		return this.emailpedcompra;
	}

	public void setEmailpedcompra(String emailpedcompra) {
		this.emailpedcompra = emailpedcompra;
	}

	@Column(name = "INDTIPOCONTATO", length = 1)
	public String getIndtipocontato() {
		return this.indtipocontato;
	}

	public void setIndtipocontato(String indtipocontato) {
		this.indtipocontato = indtipocontato;
	}

	@Column(name = "NOMECONTATOEMAILAGE", length = 40)
	public String getNomecontatoemailage() {
		return this.nomecontatoemailage;
	}

	public void setNomecontatoemailage(String nomecontatoemailage) {
		this.nomecontatoemailage = nomecontatoemailage;
	}

	@Column(name = "EMAILAGEFORNEC", length = 250)
	public String getEmailagefornec() {
		return this.emailagefornec;
	}

	public void setEmailagefornec(String emailagefornec) {
		this.emailagefornec = emailagefornec;
	}

	@Column(name = "INDPRINCIPAL", length = 1)
	public String getIndprincipal() {
		return this.indprincipal;
	}

	public void setIndprincipal(String indprincipal) {
		this.indprincipal = indprincipal;
	}

	@Column(name = "NOMECONTATOEMAILDEV", length = 40)
	public String getNomecontatoemaildev() {
		return this.nomecontatoemaildev;
	}

	public void setNomecontatoemaildev(String nomecontatoemaildev) {
		this.nomecontatoemaildev = nomecontatoemaildev;
	}

	@Column(name = "EMAILDEVFORNEC", length = 250)
	public String getEmaildevfornec() {
		return this.emaildevfornec;
	}

	public void setEmaildevfornec(String emaildevfornec) {
		this.emaildevfornec = emaildevfornec;
	}

	@Column(name = "CARGO", length = 40)
	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Column(name = "RG", length = 20)
	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "NOMECONTATOEMAILACORDO", length = 40)
	public String getNomecontatoemailacordo() {
		return this.nomecontatoemailacordo;
	}

	public void setNomecontatoemailacordo(String nomecontatoemailacordo) {
		this.nomecontatoemailacordo = nomecontatoemailacordo;
	}

	@Column(name = "EMAILACORDO", length = 250)
	public String getEmailacordo() {
		return this.emailacordo;
	}

	public void setEmailacordo(String emailacordo) {
		this.emailacordo = emailacordo;
	}

	@Column(name = "NOMECONTATOTITULO", length = 40)
	public String getNomecontatotitulo() {
		return this.nomecontatotitulo;
	}

	public void setNomecontatotitulo(String nomecontatotitulo) {
		this.nomecontatotitulo = nomecontatotitulo;
	}

	@Column(name = "EMAILTITULO", length = 250)
	public String getEmailtitulo() {
		return this.emailtitulo;
	}

	public void setEmailtitulo(String emailtitulo) {
		this.emailtitulo = emailtitulo;
	}

	@Column(name = "NOMECONTATOLANCPROMO", length = 40)
	public String getNomecontatolancpromo() {
		return this.nomecontatolancpromo;
	}

	public void setNomecontatolancpromo(String nomecontatolancpromo) {
		this.nomecontatolancpromo = nomecontatolancpromo;
	}

	@Column(name = "EMAILLANCPROMO", length = 250)
	public String getEmaillancpromo() {
		return this.emaillancpromo;
	}

	public void setEmaillancpromo(String emaillancpromo) {
		this.emaillancpromo = emaillancpromo;
	}

	@Column(name = "EMAILREGDEVFORNEC", length = 250)
	public String getEmailregdevfornec() {
		return this.emailregdevfornec;
	}

	public void setEmailregdevfornec(String emailregdevfornec) {
		this.emailregdevfornec = emailregdevfornec;
	}

	@Column(name = "NOMECONTATOEMAILREGDEV", length = 40)
	public String getNomecontatoemailregdev() {
		return this.nomecontatoemailregdev;
	}

	public void setNomecontatoemailregdev(String nomecontatoemailregdev) {
		this.nomecontatoemailregdev = nomecontatoemailregdev;
	}

}
