/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Matthew Shepherd <s0935850> 
 *
 *	This class is used to store a a set of coordinates (x and y) for plotting a polygon
 *
 *
 */
public class Point{
        
        int x = 0;
        int y = 0;
        
        /**
         *	Creates a new points with coordinates x & y
         * @param _x The X coordinate
         * @param _y The Y coordinate
         *
         */
        public Point(int _x, int _y) {
            x = _x;
            y = _y;
            
        }
        
        /**
         * Returns the euclidian distance between two points
         * @param A The first point
         * @param B the second point
         * @return The distance between the two points
         *
         */ 
        public static double dist(Point A, Point B) {
            if(A == null || B == null) return 100; // if either point is null then return dummy value
            return Math.sqrt(Math.pow(A.x-B.x, 2) + Math.pow(A.y - B.y, 2));
        }
        
        
    }
