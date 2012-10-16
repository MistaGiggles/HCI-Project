/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Matthew Shepherd <s0935850> and Robert Evans <s0949775>
 */
public class Test extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void amain(String[] args) throws Exception {
        // TODO code application loic here
        Test t = new Test();
    }

    public Test() throws Exception {
        Form1 main = new Form1();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                //here we exit the program (maybe we should ask if the user really wants to do it?)
                //maybe we also want to store the polygons somewhere? and read them next time
                System.out.println("Bye bye!");
                System.exit(0);
            }
        });

        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);


        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);


        this.setSize(1000, 700);
        this.setContentPane(main);
        this.setVisible(true);
    }
}
