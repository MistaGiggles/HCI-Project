/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author s0935850
 */
public class Point{
        
        int x = 0;
        int y = 0;
        
        public Point(int _x, int _y) {
            x = _x;
            y = _y;
            
        }
        
        public static double dist(Point A, Point B) {
            if(A == null || B == null) return 100;
            return Math.sqrt(Math.pow(A.x-B.x, 2) + Math.pow(A.y - B.y, 2));
        }
        
        
    }