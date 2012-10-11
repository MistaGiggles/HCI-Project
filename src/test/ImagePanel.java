/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Matthew Shepherd <s0935850> and Robert Evans <s0949775>
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {
    
    
    public enum Mode {AddPoint, AddPoly, EditPoly, View, Limbo, Limbo2};
    public enum GlobalMode {DrawMode, EditMode};
    
    BufferedImage image;
    PolygonObject po;
    Mode mode;
    GlobalMode global;
    Point first;
    ObjectManager manager;
    PolygonObject highlight;
    PolygonObject selected;
    LABELTEST lb;
    Point grabbed;
    double scale;
    boolean justMade;
    
    int i = 0;
    
    public ImagePanel()
    {
        super();
        justMade=false;
        po = null;
        mode = Mode.AddPoly;
        addMouseListener(this);
        addMouseMotionListener(this);
        final ImagePanel p = this;
        
        ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
                //p.repaint();
                p.updateUI();
                selected = manager.getSelected();
            }
        };

        new Timer(50, taskPerformer).start();
        scale = 1;
        
        
        
    }
    
    public void setLB(LABELTEST _lb) {
        lb = _lb;
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {  
        if(po!=null) {
            
            po.temp = new Point(e.getX(), e.getY());
            
            
        }
        
        // do detection for "selected" object
        highlight = manager.isTouch(e.getX(), e.getY());
        
        
        //this.repaint();
    }
    
    
    
    @Override
    public void mouseDragged(MouseEvent e)
    {  
        
            switch(mode) {
               case EditPoly:

                   if(grabbed != null) {
                       grabbed.x = e.getX();
                       grabbed.y = e.getY();
                       selected.generatePoly();
                   }

                   break;

            }
    
         //this.repaint();
    }
    
  
  
    
    @Override
    public void mouseExited(MouseEvent e)
    {  
    }
    
    @Override
    public void mouseEntered(MouseEvent e)
    {   
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {   
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch(mode) {
                case EditPoly:
                        if(grabbed != null) {
                            grabbed.x = e.getX();
                            grabbed.y = e.getY();
                            if(selected != null) 
                            {
                                selected.generatePoly();
                            }
                            grabbed = null;

                        }
                    break;

            }
        }
        //this.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {  
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch(mode) {
                case EditPoly:
                    System.out.println("ASDASD");
                        if(selected != null) {
                            boolean near = false;
                            //double closest = 20000;
                            for(Point p : selected.points) {
                                //if(Point.dist(new Point(e.getX(), e.getY()), p) < closest) {
                                //    closest = Point.dist(new Point(e.getX(), e.getY()), p);
                                //}
                                if(Point.dist(new Point(e.getX(), e.getY()), p) < 10) {
                                    grabbed = p;
                                    selected.edit();
                                    selected.generatePoly();
                                    near = true;

                                }
                            }

                            if(!near) {
                                mode = Mode.Limbo;
                                manager.deselect();
                                manager.unhighlight();
                            }
                        }
                    break;

                default:


                    break;
            }
        }
        //this.repaint();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("mode: " + mode.toString());
            switch(mode){
                case AddPoly:

                        po = new PolygonObject();
                        Random gen = new Random();
                        po.setColor(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
                        first = new Point(e.getX(), e.getY());
                        po.addPoint(e.getX(), e.getY());
                        mode = Mode.AddPoint;
                        System.out.println("MOUSEI:" + e.getX() + " - " + e.getY());
                        lb.setUndo(true);
                    break;


                case AddPoint:
                        if(po != null) {
                            if(Point.dist(new Point(e.getX(), e.getY()), first) < 10) {
                                po.generatePoly();
                                mode = Mode.View;
                                po.setName("DEFAULT");
                                String name = null;
                                name =  JOptionPane.showInputDialog ( "Enter object name:" );
                                if(name==null) name = "unlabelled";
                                
                                po.setName(name);
                                manager.addObject(po);
                                manager.select(po);
                                selected = manager.getSelected();
                                po = null;
                                mode = Mode.EditPoly;
                                justMade = true;

                            } else {
                                po.addPoint(e.getX(), e.getY());
                                System.out.println("MOUSE:" + e.getX() + " - " + e.getY());

                            }

                        }
                    break;

                case EditPoly:
                        
                            if(justMade) {
                                mode = Mode.AddPoly;
                                justMade = false;
                                selected = null;
                            }


                        


                    break;


                case View:


                    break;

                case Limbo2:
                        mode = Mode.AddPoly;
                        break;  

                case Limbo:
                        mode = Mode.AddPoly;
                        break;




                default:
                    break;

            }
        }
        System.out.println("mode after: " + mode.toString());
        
    }
    
    
    @Override
	public void paint(Graphics g) {
		super.paint(g);
		
                
                
		if (image != null) {
			g.drawImage(
					image, 0, 0, null);
		}
                
                if(manager!=null) {
                    for(PolygonObject O : manager.objects) {
                        if(!O.isSelected()) 
                        O.draw(g, false, scale);
                    }
                }
                
                if(po!=null)
                {
                    po.draw(g, false, scale);
                }
                
                
                if(highlight != null) {
                    highlight.draw(g,true, scale);
                }
                
                if(manager.getSelected()!=null) {
                    manager.getSelected().draw( g, false, scale);
                }
                
                
		
		
	}
    
    public double loadImage(String file) {
        
    image = null;
    try {
        image = ImageIO.read(new File(file));
    } catch (IOException e) {
        System.out.println("Error Opening File: " + e.getMessage());
        
    }
    if(image==null) {return 1;}
    if (image.getWidth() > 800 || image.getHeight() > 600) {
            int newWidth = image.getWidth() > 800 ? 800 : (image.getWidth() * 600)/image.getHeight();
            int newHeight = image.getHeight() > 600 ? 600 : (image.getHeight() * 800)/image.getWidth();
            System.out.println("SCALING TO " + newWidth + "x" + newHeight );
            double scale = (double)newWidth/(double)image.getWidth();
            Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
            image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            image.getGraphics().drawImage(scaledImage, 0, 0, this);
            this.scale= scale;
            return scale;
    }
        return 1;
    }
    
}
