/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author s0935850
 */
public class XMLReader {
    String fname;
    private Document dom;
    
    public XMLReader(String file) {
        fname = file;
        
    }
    
    
    public void openXML(ObjectManager toReplace) {
        ObjectManager MAN = new ObjectManager(toReplace.lb);
        toReplace.transfer(MAN);
        toReplace = MAN;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            try {

                    //Using factory get an instance of document builder
                    DocumentBuilder db = dbf.newDocumentBuilder();

                    //parse using builder to get DOM representation of the XML file
                    dom = db.parse(fname);


            }catch(ParserConfigurationException pce) {
                    pce.printStackTrace();
            }catch(SAXException se) {
                    se.printStackTrace();
            }catch(IOException ioe) {
                    ioe.printStackTrace();
            }

            System.out.println("LOADED");


            Element docEle = dom.getDocumentElement();

            //get a nodelist of  elements
            NodeList nl = docEle.getElementsByTagName("object");
            if(nl != null && nl.getLength() > 0) {
                    for(int i = 0 ; i < nl.getLength();i++) {
                            
                        
                           //System.out.println(((Element)nl.item(i)).getTextContent());
                            
                            //get the employee element
                            Element el = (Element)nl.item(i);
                            
                            String name = getTextValue(el,"name");
                            
                            System.out.println(name);

                            //get the Employee object
                            //Employee e = getEmployee(el);

                            //add it to list
                            //myEmpls.add(e);
                    }
            }
                
                
                
    }
    
    private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
    
    
    
}
