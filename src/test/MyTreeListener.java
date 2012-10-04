/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

/**
 *
 * @author s0935850
 */
public class MyTreeListener implements TreeSelectionListener {
    
    ObjectManager manager;
    
    public MyTreeListener(ObjectManager mngr) {
        manager = mngr;
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        //throw new UnsupportedOperationException("Not supported yet.");
        // Get all nodes whose selection status has changed
		TreePath[] paths = tse.getPaths();
 
		// Iterate through all affected nodes
		for (int i=0; i<paths.length; i++) {
			if (tse.isAddedPath(i)) {
				// This node has been selected
				//System.out.println(paths[i].getLastPathComponent().toString());
                                
				break;
			} else {
				// This node has been deselected
				break;
			}
		}
    }
    
}
