
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarLojaResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarLojaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lojas" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="loja" type="{http://webservice.portal.seta.com.br/}lojaDTO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarLojaResponse", propOrder = {
    "lojas"
})
public class ListarLojaResponse {

    protected ListarLojaResponse.Lojas lojas;

    /**
     * Obtém o valor da propriedade lojas.
     * 
     * @return
     *     possible object is
     *     {@link ListarLojaResponse.Lojas }
     *     
     */
    public ListarLojaResponse.Lojas getLojas() {
        return lojas;
    }

    /**
     * Define o valor da propriedade lojas.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarLojaResponse.Lojas }
     *     
     */
    public void setLojas(ListarLojaResponse.Lojas value) {
        this.lojas = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="loja" type="{http://webservice.portal.seta.com.br/}lojaDTO" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "loja"
    })
    public static class Lojas {

        protected List<LojaDTO> loja;

        /**
         * Gets the value of the loja property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the loja property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLoja().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LojaDTO }
         * 
         * 
         */
        public List<LojaDTO> getLoja() {
            if (loja == null) {
                loja = new ArrayList<LojaDTO>();
            }
            return this.loja;
        }

    }

}
