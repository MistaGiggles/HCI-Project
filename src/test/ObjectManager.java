
package test;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Matthwew Shepherd <s0935850>
 * Used to store a list of defined polygon objects.
 * Also handles which polygon has been selected & displaying them on the tree
 */
public class ObjectManager {
    ArrayList<PolygonObject> objects;
    DefaultMutableTreeNode tree;
    JTree jt;
    int newID;
    LABELTEST lb;
    MyTreeModel model;
    
    
    /**
     * Used to transfer required references to a new ObjectManager
     * @param to The new ObjectManager to transfer the references to.
     */
    public void transfer(ObjectManager to) {
        to.tree = tree;
        to.jt = jt;
        to.lb = lb;
        to.model = model;
              
    }
    
    /**
     * Constructor
     * @param _lb Reference to the swing form, used to manipulate the JTree among other things
     */
    public ObjectManager(LABELTEST _lb) {
        objects = new ArrayList<PolygonObject>();
        newID = 1;
        lb = _lb;
    }
    
    
    /**
     * Used to set a reference to the root node
     * @param t The root node
     */
    public void setWorkingTree(DefaultMutableTreeNode t) {
        tree = t;
    }
    
    /**
     * Adds a new polygon to the list, adds it to the tree and selects it
     * @param po The PolygonObject to add
     */
    public void addObject(PolygonObject po) {
        
        
        System.out.println("ADDING: " + po.getName());
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
    
    /*
     * Selects a polygon to be highlighted on the tree and to be made editable
     * @param p Polygon to select
     */
    public void select(PolygonObject p) {
        deselect();
        
        for(PolygonObject O : objects) {
            if(O.equals(p)) {
                p.select();
            }
        }
    }
    
    /*
     * Deselects all polygons (used before a new polygon is selected to ensure only one is ever selected
     */
    public void deselect() {
        for(PolygonObject O : objects) {
            O.deSelect();
        }
        
    }
    
    /*
     * Selects the root node in the tree, used to make sure no polygon has been highlighted/selected on the tree
     */
    public void unhighlight() {
        jt.setSelectionPath(new TreePath(model.getPathToRoot(tree.getRoot())));
    }
    
    /*
     * Returns reference to the selected polygon
     */
    public PolygonObject getSelected() {
        for(PolygonObject O :objects) {
            if(O.isSelected()) {
                return O;
            }
        }
        return null;
    }
    
    
    /*
     * returns PolygonObject that matches the given hashcode
     * @param hash Hashcode of polygon to fetch
     */
    public PolygonObject get(int hash) {
        for(PolygonObject O : objects) {
            if(O.hashCode() == hash) {
                return O;
            }
        }
        return null;
    }
    
    
    /*
     * returns a polygon if the coordinates are touching one of its lines
     * @param mx X coordinate
     * @param my Y coordinate
     */
    public PolygonObject isTouch(int mx, int my) {
        for(PolygonObject po : objects) {
            if(po.isTouching(mx, my)) {
                return po;
            }
        }
        return null;
    }
    
    /*
     * Updates the tree
     */
    public void update() {
        
        jt.updateUI();
        jt.expandRow(0);
        
    }
}
