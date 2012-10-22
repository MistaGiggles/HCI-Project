/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 *
 * @author Matthew Shepherd <s0935850> and Robert Evans <s0949775>
 */
public class ImageFileView extends FileView {
    ImageIcon jpgIcon = Utils.createImageIcon(System.getProperty("user.dir")+"/src/test/Icons/jpgIcon.gif");
    ImageIcon gifIcon = Utils.createImageIcon("/icons/gifIcon.gif");
    ImageIcon tiffIcon = Utils.createImageIcon("/icons/tiffIcon.gif");
    ImageIcon pngIcon = Utils.createImageIcon("/icons/pngIcon.png");
 
    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }
 
    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }
 
    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }
 
    public String getTypeDescription(File f) {
        String extension = Utils.getExtension(f);
        String type = null;
 
        if (extension != null) {
            if (extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg)) {
                type = "JPEG Image";
            } else if (extension.equals(Utils.gif)){
                type = "GIF Image";
            } else if (extension.equals(Utils.tiff) ||
                       extension.equals(Utils.tif)) {
                type = "TIFF Image";
            } else if (extension.equals(Utils.png)){
                type = "PNG Image";
            }
        }
        return type;
    }
 
    public Icon getIcon(File f) {
        String extension = Utils.getExtension(f);
        Icon icon = null;
 
        if (extension != null) {
            if (extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg)) {
                icon = jpgIcon;
            } else if (extension.equals(Utils.gif)) {
                icon = gifIcon;
            } else if (extension.equals(Utils.tiff) ||
                       extension.equals(Utils.tif)) {
                icon = tiffIcon;
            } else if (extension.equals(Utils.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
