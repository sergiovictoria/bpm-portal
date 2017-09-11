
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acesso" type="{http://webservice.portal.seta.com.br/}listaPermissaoDTOResponseV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarResponse", propOrder = {
    "acesso"
})
public class ListarResponse {

    protected ListaPermissaoDTOResponseV2 acesso;

    /**
     * Obtém o valor da propriedade acesso.
     * 
     * @return
     *     possible object is
     *     {@link ListaPermissaoDTOResponseV2 }
     *     
     */
    public ListaPermissaoDTOResponseV2 getAcesso() {
        return acesso;
    }

    /**
     * Define o valor da propriedade acesso.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPermissaoDTOResponseV2 }
     *     
     */
    public void setAcesso(ListaPermissaoDTOResponseV2 value) {
        this.acesso = value;
    }

}
