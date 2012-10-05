/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author s0935850
 */
public class ObjectManager {
    ArrayList<PolygonObject> objects;
    DefaultMutableTreeNode tree;
    JTree jt;
    int newID;
    LABELTEST lb;
    MyTreeModel model;
    
    public void transfer(ObjectManager to) {
        to.tree = tree;
        to.jt = jt;
        to.lb = lb;
        to.model = model;
              
    }
    
    
    public ObjectManager(LABELTEST _lb) {
        objects = new ArrayList<PolygonObject>();
        newID = 1;
        lb = _lb;
    }
    
    public void setWorkingTree(DefaultMutableTreeNode t) {
        tree = t;
    }
    
    public void addObject(PolygonObject po) {
        
        String name =  JOptionPane.showInputDialog ( "Enter object name:" ); 
        po.setName(name);
        
        objects.add(po);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(po);
        po.node = node;
        //node.setUserObject(po);
        po.setID(newID);
        tree.add(node);
        lb.setUnsavedChanges(true);
        jt.setSelectionPath(new TreePath(model.getPathToRoot(node)));
        update();
    }
    
    public void select(PolygonObject p) {
        deselect();
        
        for(PolygonObject O : objects) {
            if(O.equals(p)) {
                p.select();
            }
        }
    }
    
    public void deselect() {
        for(PolygonObject O : objects) {
            O.deSelect();
        }
        
    }
    
    public void unhighlight() {
        jt.setSelectionPath(new TreePath(model.getPathToRoot(tree.getRoot())));
    }
    
    public PolygonObject getSelected() {
        for(PolygonObject O :objects) {
            if(O.isSelected()) {
                return O;
            }
        }
        return null;
    }
    
    public PolygonObject get(int hash) {
        for(PolygonObject O : objects) {
            if(O.hashCode() == hash) {
                return O;
            }
        }
        return null;
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
