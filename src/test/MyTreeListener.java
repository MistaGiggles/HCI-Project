/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author s0935850
 */
public class MyTreeListener implements TreeSelectionListener {
    
    ObjectManager manager;
    JTree tree;
    
    public MyTreeListener(ObjectManager mngr, JTree _tree) {
        manager = mngr;
        tree = _tree;
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent tse) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
         

    /* if nothing is selected */ 
        if (node == null) return;

    /* retrieve the node that was selected */ 
        Object nodeInfo = node.getUserObject();
        
        manager.select(manager.get(nodeInfo.hashCode()));
    }
    
}
