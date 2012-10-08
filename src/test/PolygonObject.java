/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * @author s0935850
 */
public class PolygonObject {
    
    public ArrayList<Point> points;
    private ArrayList<Point> redos;
    private Polygon poly;
    public Color color;
    private String name;
    public Point temp;
    public boolean isValid;
    private int _id;
    private boolean isSelected;
    public DefaultMutableTreeNode node;
    
    /**
     * Constructor, instantiates arraylist and sets color to white and name to null
     */
    public PolygonObject() {
        
        points = new ArrayList<Point>();
        redos = new ArrayList<Point>();
        color = new Color(255,255,255);
        name = "null";
        poly = null;
        isValid = false;
    }
    
    public int  removeLastPoint() {
        if(points.size() > 0) {
            
        redos.add(points.remove(points.size()-1));
        
        }
        return points.size();
    }
    
    public int redo() {
        if(redos.size() > 0) {
            points.add(redos.remove(redos.size()-1));
            
        }
        return redos.size();
        
    }
    
    
    /* GETTERS AND SETTERS */
    public void setName(String _name) {
        name = _name;
    }
    
    public void edit() {
        poly = null;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public void select() {
        isSelected = true;
    }
    
    public void deSelect() {
        isSelected = false;
    }
    
    public String getName() {
        return name;
    }
    
    public void setID(int i) {
        
        _id = i;
    }
    
    public void setColor(int r, int g, int b) {
        color = new Color(r,g,b);
   
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getID() {
        return _id;
        
    }
    
    public String toString() {
        
        return name;
    }
    
    
    /**
     * 
     * Draws the polygon with Graphics g. Handles the drawing of
     * an uncomplete poly, a complete poly and a selected poly
     * @param g Graphics object to draw with
     * @param shade NOT USED
     * @param scale NOT USED
     */
    public void draw(Graphics g, boolean shade, double scale)
    {
        
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        //g2.scale(scale, scale);
        g2.setStroke(new BasicStroke(7.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f));

        if(points.size() == 1) {
            if(temp != null)
                g2.drawLine(points.get(0) .x,points.get(0).y, temp.x, temp.y);
            return;
        }
        if(poly == null) {
            
            Point prev = null;
            boolean first = true;
            
            if(points.size() > 0)
            g2.drawOval(points.get(0).x, points.get(0).y, 7, 7);
            
            for(Point p : points)
            {
                if(!first) {
                    g2.drawLine(prev.x, prev.y, p.x, p.y);
                }
                first = false;
                prev = p;
            }
            
            if(temp != null && prev != null) {
                g2.drawLine(prev.x, prev.y, temp.x, temp.y);
                
            }
            
        } else {
            g.setColor(color);
            
            g2.setStroke(new BasicStroke(6.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,8.0f));
            
            g2.drawPolygon(poly);
            g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
            
                
            
        }
        
        if(isSelected) {
            if(poly!=null) {
                g2.fillPolygon(poly);
                for(Point p : points) {
                    //g2.scale(scale, scale);
                    g2.setColor(color);
                    g2.fillOval(p.x - 6, p.y -6 , 12, 12);
                    g2.setColor(new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue()));
                    g2.drawOval(p.x -6, p.y -6, 12, 12);
                    //g2.scale(1, 1);


                }
            }
        }
        
    }
    
    /**
     * Designed to test if a point is touching a line of the polygon
     * @param mx X coordinate
     * @param my Y coordinate
     * @return true if point is touching or almost touching
     */
    public boolean isTouching(int mx, int my) {
        Point prev = null;
        for(Point p : points) {
            if(prev!=null) {
                double res = 100;
                //double res = (double)(((double)mx-(double)prev.x)*((double)p.x-(double)prev.x) + ((double)my-(double)prev.y)*((double)p.y-(double)prev.y))/Math.pow(((double)prev.x-(double)p.x)*((double)prev.x-(double)p.x) + ((double)prev.y-(double)p.y)*((double)prev.y-(double)p.y), 2);
                if(res<7) {
                    System.out.println("Highlight + " + res);
                    return true;
                }
            
            }
            prev = p;
        }
        return false;
    }
    
    
    /**
     * Adds a point to the polygon
     * @param _x X coordinate
     * @param _y Y coordinate
     */
    public void addPoint(int _x, int _y) {
        points.add(new Point(_x, _y));
        if(points.size() > 1) {
            isValid = true;
        }
    }
    
    
    /**
     * Generates the Graphics Polygon Object for fast drawing and filling
     */
    public void generatePoly()
    {
        poly = new Polygon();
        for(Point p : points)
        {
            poly.addPoint(p.x, p.y);
            
        }
        
    
    }
    
    
    
    
}
