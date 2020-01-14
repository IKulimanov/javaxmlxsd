import com.login.app.*;
import com.login.app.ObjectFactory;
import javax.xml.bind.*;

public class main {
    public static void main(String[] args) {
        try {
            // create a JAXBContext capable of handling classes generated into package
            javax.xml.bind.JAXBContext jaxbContext = javax.xml.bind.JAXBContext.newInstance(Galtype.class);
            // create an object to marshal
            Galtype objectToMarshal = new Galtype();

            objectToMarshal.setNamegalaxy("mygalaxy");

            // create a Marshaller and do marshal
            javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(objectToMarshal, new java.io.FileOutputStream("mygalaxy.xml"));
        } catch (javax.xml.bind.JAXBException je) {
            je.printStackTrace();
        } catch (java.io.FileNotFoundException io) {
            io.printStackTrace();
        }

        try {
            // create a JAXBContext capable of handling classes generated into package
            javax.xml.bind.JAXBContext jaxbContext = javax.xml.bind.JAXBContext.newInstance(Galtype.class);
            // create an Unmarshaller
            javax.xml.bind.Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // unmarshal an instance document into a tree of Java content
            // objects composed of classes from the package.
            Galtype unmarshalObject = (Galtype) unmarshaller.unmarshal(new java.io.FileInputStream("mygalaxy.xml"));
            System.out.print(unmarshalObject.getNamegalaxy());
        } catch (javax.xml.bind.JAXBException je) {
            je.printStackTrace();
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }

    }
    
}
