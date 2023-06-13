package com.ben.wandwars.hitBoxes;

import org.bukkit.util.Vector;

import java.util.List;
//NEEDED METHODS:
//get the bounding box needed for starter hit detection from a list of points
//check if two bounding boxes collide
//check if two point lists collide
//rotate a point list

public class PointUtil {

    //dist = (x1 − x2)^2 + (z1 − z2)^2 + (z1 − z2)^2
    public boolean intersects(HitPoint point1, HitPoint point2) {

        Vector point1Loc = point1.getLocation();
        Vector point2Loc = point2.getLocation();

        //gets the x, y, and z parts of the equation
        double x = Math.pow(point1Loc.getX() - point2Loc.getX(), 2);
        double y = Math.pow(point1Loc.getY() - point2Loc.getY(), 2);
        double z = Math.pow(point1Loc.getZ() - point2Loc.getZ(), 2);

        double neededDist = Math.pow(point1.getRadius() + point2.getRadius(), 2);

        //adds them to get the dist
        return (x + y + z) <= neededDist;
    }

    //needs to be changed
    public double getBoxDist(List<HitPoint> points, Vector startingLoc) {
        double maxDistance = 0;

        for(HitPoint hitPoint : points) {
            Vector loc = hitPoint.getLocation();

            double distY = Math.abs(loc.getY() - startingLoc.getY()) + hitPoint.getRadius();
            if(distY > maxDistance){
                maxDistance = distY;
            }

            double distX = Math.abs(loc.getX() - startingLoc.getX()) + hitPoint.getRadius();
            if(distX > maxDistance){
                maxDistance = distX;
            }

            double distZ = Math.abs(loc.getZ() - startingLoc.getZ()) + hitPoint.getRadius();
            if(distZ > maxDistance){
                maxDistance = distZ;
            }
        }

        return maxDistance;
    }

    public boolean collides(double box1Dist, Vector loc1, double box2Dist, Vector loc2) {
        double xDist = Math.abs(loc1.getX() - loc2.getX());
        double yDist = Math.abs(loc1.getY() - loc2.getY());
        double zDist = Math.abs(loc1.getZ() - loc2.getZ());

        double neededDist = box1Dist + box2Dist;

        return (xDist <= neededDist && yDist <= neededDist && zDist <= neededDist);
    }

//    public List<HitPoint> rotate(List<HitPoint> points, Vector startingLoc, Vector direction) {
//        direction.normalize();
//
//        for(HitPoint hitPoint : points) {
//            Math.acos()
//        }
//    }



}
