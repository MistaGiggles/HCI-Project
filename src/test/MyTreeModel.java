/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Matthew Shepherd <s0935850> and Robert Evans <s0949775>
 */
class MyTreeModel extends DefaultTreeModel {

    public MyTreeModel(TreeNode node) {
        super(node);
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent(); //THE GOOD THING IS THAT THE UNTOUCHED OBJECT IS STILL AVAILABLE HERE
        String value = (String) newValue;
        PolygonObject myUserObject = (PolygonObject) node.getUserObject();

        nodeChanged(node); //THIS LINE IS VERY IMPORTANT, OTHERWISE treeNodesChanged WILL NOT BE CALLED AFTER A NODE IS EDITED
    }
}