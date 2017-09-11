
package br.com.seta.processo.service.gestor.acesso.ws.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listaPermissaoDTORequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listaPermissaoDTORequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permissao" type="{http://webservice.portal.seta.com.br/}permissaoTagDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaPermissaoDTORequest", propOrder = {
    "permissao"
})
public class ListaPermissaoDTORequest {

    protected List<PermissaoTagDTO> permissao;

    /**
     * Gets the value of the permissao property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissao property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissao().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PermissaoTagDTO }
     * 
     * 
     */
    public List<PermissaoTagDTO> getPermissao() {
        if (permissao == null) {
            permissao = new ArrayList<PermissaoTagDTO>();
        }
        return this.permissao;
    }

}
