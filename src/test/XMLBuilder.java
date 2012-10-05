/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author s0935850
 */
public class XMLBuilder {
     String fname;
     DocumentBuilderFactory docbf;
     DocumentBuilder docb;
     Document doc;
     Element annotation;
    
    public XMLBuilder(String filename) {
        fname = filename;
        docbf = DocumentBuilderFactory.newInstance();
        try {
        docb = docbf.newDocumentBuilder(); } catch (Exception e) {System.out.println("EXCEPTION2");};
        doc = docb.newDocument();
        annotation = doc.createElement("annotation");
        
        
    }
    
    public void buildWrite(ObjectManager manager)  {
        addObjects(annotation, manager);
        doc.appendChild(annotation);
        
        File file = new File(fname);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(file);

        transformer.transform(source, result); } catch (Exception e) {e.printStackTrace();;};
        
    }
    
    public void addPolygon(Element object, PolygonObject po) {
        for(Point p : po.points) {
            Element point = doc.createElement("pt");
            Element x = doc.createElement("x");
                x.setTextContent(Integer.toString(p.x));
            Element y = doc.createElement("y");
                y.setTextContent(Integer.toString(p.y));
                
            point.appendChild(x);
            point.appendChild(y);
            object.appendChild(point);
        }
        
        
    }
    
    public void addObjects(Element root, ObjectManager manager) {
        
        for(PolygonObject po : manager.objects) {
            Element obj = doc.createElement("object");
            Element nm = doc.createElement("name");
                nm.setTextContent(po.getName());
                
            Element dl = doc.createElement("deleted");
                dl.setTextContent("0");
                
            Element vf = doc.createElement("verified");
                vf.setTextContent("0");
            Element dt = doc.createElement("date");
                dt.setTextContent("17-sep-2009 21:48:23");
            Element id = doc.createElement("id");
                id.setTextContent(Integer.toString(po.getID()));
            Element poly = doc.createElement("polygon");
            addPolygon(poly, po);
            
            obj.appendChild(nm);
            obj.appendChild(dl);
            obj.appendChild(vf);
            obj.appendChild(dt);
            obj.appendChild(id);
            obj.appendChild(poly);
            root.appendChild(obj);
            
        }
        
        
        /*
         * <object>
            <name>picture</name>
            <deleted>0</deleted>
            <verified>0</verified>
            <date>17-Sep-2009 21:48:23</date>
            <id>3</id>
            <polygon>
            <username>anonymous</username>
            <pt>
            <x>960</x>
            <y>259</y>
            </pt>
            <pt>
            <x>945</x>
            <y>625</y>
            </pt>
            <pt>
            <x>1190</x>
            <y>646</y>
            </pt>
            <pt>
            <x>1201</x>
            <y>241</y>
            </pt>
            </polygon>
          </object>
         */
        
    }
    
    
    
    /* 
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

    Document document = documentBuilder.newDocument();

    Element rootElement = document.createElement(“root”); // creates a element

    Element element1 = document.createElement(“name”); //create another element

    //creates an Attribute of element1 as type and sets its value to String

    Element1.setAttribute(new String(“type”), “String”);

    rootElement.appendChild(element); // add element1 under rootElement

    document.appendChild(rootElement); // add the rootElement to the document

    */
}
