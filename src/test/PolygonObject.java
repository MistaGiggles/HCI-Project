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
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author s0935850
 */
public class PolygonObject {
    
    private ArrayList<Point> points;
    private Polygon poly;
    public Color color;
    private String name;
    public Point temp;
    public boolean isValid;
    
    public PolygonObject() {
        
        points = new ArrayList<Point>();
        color = new Color(255,255,255);
        name = "null";
        poly = null;
        isValid = false;
    }
    
    public void setName(String _name) {
        name = _name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setColor(int r, int g, int b) {
        color = new Color(r,g,b);
   
    }
    
    public void draw(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(7.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f));

        if(points.size() == 1) {
            g2.drawLine(points.get(0) .x,points.get(0).y, temp.x, temp.y);
            return;
        }
        if(poly == null) {
            
            Point prev = null;
            boolean first = true;
            
            
            g2.drawOval(points.get(0).x, points.get(0).y, 7, 7);
            
            for(Point p : points)
            {
                if(!first) {
                    g2.drawLine(prev.x, prev.y, p.x, p.y);
                }
                first = false;
                prev = p;
            }
            
            if(temp != null) {
                g2.drawLine(prev.x, prev.y, temp.x, temp.y);
                
            }
            
        } else {
            g.setColor(color);
            
            g2.setStroke(new BasicStroke(9.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f));
            
            g2.drawPolygon(poly);
            g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
            g2.fillPolygon(poly);
            
        }
        
    }
    
    public void addPoint(int _x, int _y) {
        points.add(new Point(_x, _y));
        if(points.size() > 1) {
            isValid = true;
        }
    }
    
    
    
    public void generatePoly()
    {
        poly = new Polygon();
        for(Point p : points)
        {
            poly.addPoint(p.x, p.y);
            
        }
        
    
    }
    
    
    
    
}
