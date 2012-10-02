/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author s0935850
 */
public class ObjectManager {
    ArrayList<PolygonObject> objects;
    DefaultMutableTreeNode tree;
    JTree jt;
    
    public ObjectManager() {
        objects = new ArrayList<PolygonObject>();
    }
    
    public void setWorkingTree(DefaultMutableTreeNode t) {
        tree = t;
    }
    
    public void addObject(PolygonObject po) {
        
        objects.add(po);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(po.getName());
        //node.setUserObject(po);
        
        tree.add(node);
        
        
        update();
    }
    
    public void update() {
        jt.updateUI();
        jt.expandRow(0);
        
    }
}
