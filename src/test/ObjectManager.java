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
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(po);
        node.setUserObject(po);
        
        tree.add(node);
        
        
        update();
    }
    
    public PolygonObject isTouch(int mx, int my) {
        for(PolygonObject po : objects) {
            if(po.isTouching(mx, my)) {
                return po;
            }
        }
        return null;
    }
    
    public void update() {
        jt.updateUI();
        jt.expandRow(0);
        
    }
}
