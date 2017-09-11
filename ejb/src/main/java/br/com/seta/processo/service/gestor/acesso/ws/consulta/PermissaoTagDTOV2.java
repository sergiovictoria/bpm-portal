
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de permissaoTagDTOV2 complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="permissaoTagDTOV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcionalidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desabilitado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "permissaoTagDTOV2", propOrder = {
    "sistema",
    "modulo",
    "funcionalidade",
    "desabilitado"
})
public class PermissaoTagDTOV2 {

    protected String sistema;
    protected String modulo;
    protected String funcionalidade;
    protected Boolean desabilitado;

    /**
     * Obtém o valor da propriedade sistema.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistema() {
        return sistema;
    }

    /**
     * Define o valor da propriedade sistema.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistema(String value) {
        this.sistema = value;
    }

    /**
     * Obtém o valor da propriedade modulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * Define o valor da propriedade modulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModulo(String value) {
        this.modulo = value;
    }

    /**
     * Obtém o valor da propriedade funcionalidade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionalidade() {
        return funcionalidade;
    }

    /**
     * Define o valor da propriedade funcionalidade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionalidade(String value) {
        this.funcionalidade = value;
    }

    /**
     * Obtém o valor da propriedade desabilitado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDesabilitado() {
        return desabilitado;
    }

    /**
     * Define o valor da propriedade desabilitado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDesabilitado(Boolean value) {
        this.desabilitado = value;
    }

}
