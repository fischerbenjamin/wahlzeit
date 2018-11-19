package org.wahlzeit.model;

public interface Coordinate {

	final double EPSILON = 10E-9;
	
	CartesianCoordinate asCartesianCoordinate();
	
	double getCartesianDistance(Coordinate coordinate);
	
	SphericCoordinate asSphericCoordinate();
	
	double getCentralAngle(Coordinate coordinate);
	
	boolean isEqual(Coordinate coordinate);
	
}
