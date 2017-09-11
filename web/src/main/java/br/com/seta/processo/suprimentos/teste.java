//package br.com.seta.processo.suprimentos;
//
//
//
//
//import java.util.List;
//
//import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
//import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
//import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
//import org.apache.wicket.markup.html.basic.Label;
//import org.apache.wicket.markup.html.form.DropDownChoice;
//import org.apache.wicket.markup.html.form.TextField;
//import org.apache.wicket.markup.html.panel.Panel;
//import org.apache.wicket.model.AbstractReadOnlyModel;
//import org.apache.wicket.model.IModel;
//import org.apache.wicket.model.LoadableDetachableModel;
//import org.apache.wicket.model.Model;
//import org.apache.wicket.model.PropertyModel;
//import org.apache.wicket.ajax.AjaxRequestTarget;
//
//import com.sun.mail.iap.Response;
//import com.sun.org.apache.xpath.internal.operations.Mod;
//
//import pqlrd.domain.Ciudad;
//import pqlrd.domain.Estado;
//import pqlrd.domain.Pais;
//import pqlrd.domain.Sector;
//
//public class PanelDireccion extends Panel {
//
//
//	private TextField tbCalle ;
//	private TextField tbNumeroC ;
//	private TextField tbApartamento;
//	private TextField tbNumeroA ;
//	private DropDownChoice cmbSector;
//	private DropDownChoice cmbCiudad;
//	private DropDownChoice cmbEstado;
//
//	private Ciudad cSel;
//	private Estado pSel;
//	private Sector sSel;
//	private Pais paisRD;
//
//
//	public Pais getPaisRD() {
//		return paisRD;
//	}
//
//	public void setPaisRD(Pais paisRD) {
//		this.paisRD = paisRD;
//	}
//
//	public TextField getTbCalle() {
//		return tbCalle;
//	}
//
//	public void setTbCalle(TextField tbCalle) {
//		this.tbCalle = tbCalle;
//	}
//
//	public TextField getTbNumeroC() {
//		return tbNumeroC;
//	}
//
//	public void setTbNumeroC(TextField tbNumeroC) {
//		this.tbNumeroC = tbNumeroC;
//	}
//
//	public TextField getTbApartamento() {
//		return tbApartamento;
//	}
//
//	public void setTbApartamento(TextField tbApartamento) {
//		this.tbApartamento = tbApartamento;
//	}
//
//	public TextField getTbNumeroA() {
//		return tbNumeroA;
//	}
//
//	public void setTbNumeroA(TextField tbNumeroA) {
//		this.tbNumeroA = tbNumeroA;
//	}
//
//	public DropDownChoice getCmbSector() {
//		return cmbSector;
//	}
//
//	public void setCmbSector(DropDownChoice cmbSector) {
//		this.cmbSector = cmbSector;
//	}
//
//	public DropDownChoice getCmbCiudad() {
//		return cmbCiudad;
//	}
//
//	public void setCmbCiudad(DropDownChoice cmbCiudad) {
//		this.cmbCiudad = cmbCiudad;
//	}
//
//	public DropDownChoice getCmbEstado() {
//		return cmbEstado;
//	}
//
//	public void setCmbEstado(DropDownChoice cmbEstado) {
//		this.cmbEstado = cmbEstado;
//	}
//
//	public Sector getsSel() {
//		return sSel;
//	}
//
//	public void setsSel(Sector sSel) {
//		this.sSel = sSel;
//	}
//
//	public List<Estado> getProvincias() {
//		return provincias;
//	}
//
//	public void setProvincias(List<Estado> provincias) {
//		this.provincias = provincias;
//	}
//
//	public HashMap<Estado, List<Ciudad>> getMunicipios() {
//		return municipios;
//	}
//
//	public void setMunicipios(HashMap<Estado, List<Ciudad>> municipios) {
//		this.municipios = municipios;
//	}
//
//	public HashMap<Ciudad, List<Sector>> getSectores() {
//		return sectores;
//	}
//
//	public void setSectores(HashMap<Ciudad, List<Sector>> sectores) {
//		this.sectores = sectores;
//	}
//
//	public Ciudad getcSel() {
//		return cSel;
//	}
//
//	public void setcSel(Ciudad cSel) {
//		this.cSel = cSel;
//	}
//
//	public Estado getpSel() {
//		return pSel;
//	}
//
//	public void setpSel(Estado pSel) {
//		this.pSel = pSel;
//	}
//
//	private List<Estado> provincias;
//	private HashMap<Estado, List<Ciudad>> municipios;
//	private HashMap<Ciudad, List<Sector>> sectores;
//
//
//	public PanelDireccion(String id) {
//		super(id);
//
//	}
//
//
//
//
//		IModel<List<Estado>> mProvincia = new AbstractReadOnlyModel<List<Estado>>(){
//			@Override
//			public List<Estado> getObject() {
//				return  provincias;
//
//			}
//		};
//
//
//		IModel<List<Ciudad>> mMunicipio = new AbstractReadOnlyModel<List<Ciudad>>(){
//
//			@Override
//			public List<Ciudad> getObject() {
//
//				return (List<Ciudad>) ((municipios.get(pSel) == null)? Collections.emptyList() :  municipios.get(pSel));
//
//			}
//		};
//
//		IModel<List<Sector>> mSector = new AbstractReadOnlyModel<List<Sector>>() {
//
//
//			@Override
//			public List<Sector> getObject() {
//
//
//				return (List<Sector>) ((sectores.get(cSel)==null) ? Collections.emptyList() : sectores.get(cSel)); 
//
//			}
//		}; 
//
//
//
//		add(tbCalle= (TextField) new TextField("tbCalle", new Model<String>() ));
//		add(tbNumeroC = (TextField) new TextField("tbNumeroC", new Model<String>() )) ;
//
//		add(tbApartamento = (TextField) new TextField("tbApartamento", new Model<String>() )) ;
//		add(tbNumeroA = new TextField("tbNumeroA", new Model<String>() ) );
//
//		add(cmbSector = new DropDownChoice("cmbSector", new PropertyModel<Sector>(this, "sSel"), mSector ));
//		add(cmbEstado=new DropDownChoice("cmbEstado",new PropertyModel<Estado>(this, "pSel") ,mProvincia)); 
//		add(cmbCiudad=new DropDownChoice("cmbCiudad", new PropertyModel<Ciudad>(this, "cSel") ,mMunicipio));
//
//		cmbCiudad.setOutputMarkupId(true);
//		cmbSector.setOutputMarkupId(true);
//
//
//		//cmbCiudad.setOutputMarkupId(true);
//
//
//		//Pais que voy a traer en este caso solo es RD.
//		paisRD = new Pais();
//
//		try{
//
//			paisRD = (Pais) paisRD.getPaisById(70);
//
//		}
//		catch (Exception e) {
//
//
//
//		}
//
//
//		//La lista de provincias;
//		provincias =  paisRD.getEstados();
//
//		municipios = new HashMap<Estado, List<Ciudad>>();
//		sectores = new HashMap<Ciudad, List<Sector>>();
//
//		//Llenar los municipios de cada provincia
//		for( Estado e : provincias)
//		{
//
//			municipios.put(e, e.getCiudades());
//
//			for(Ciudad c: e.getCiudades())
//				sectores.put(c, c.getSectores());
//
//		}
//
//		cmbEstado.add(new AjaxFormComponentUpdatingBehavior("onchange") {
//
//			@Override
//			protected void onUpdate(AjaxRequestTarget target) {
//				target.add(cmbCiudad);
//
//			}
//		});
//
//
//		cmbCiudad.add(new AjaxFormComponentUpdatingBehavior("onchange") {
//
//			@Override
//			protected void onUpdate(AjaxRequestTarget target) {
//				target.add(cmbSector);
//
//			}
//		});
//
//
//
//	}
//
//}
//
