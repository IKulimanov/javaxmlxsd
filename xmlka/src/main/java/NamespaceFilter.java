import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
 class NamespaceFilter extends XMLFilterImpl {

    String requiredNamespace = "namespace";

    public NamespaceFilter(XMLReader parent) {
        super(parent);
    }


}