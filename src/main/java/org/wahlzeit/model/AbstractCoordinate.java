package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	protected static final double DISTANCE_EQUALITY = 10E-12;
	
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();
	
	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		checkCoordinate(coordinate);
		CartesianCoordinate self, other;
		double dx, dy, dz;
		self = this.asCartesianCoordinate();
		other = coordinate.asCartesianCoordinate();
		dx = Math.pow(self.getX() - other.getX(), 2);
		dy = Math.pow(self.getY() - other.getY(), 2);
		dz = Math.pow(self.getZ() - other.getZ(), 2);
		return Math.sqrt(dx + dy + dz);
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getCentralAngle(Coordinate coordinate) {
		checkCoordinate(coordinate);
		SphericCoordinate self, other;
		self = this.asSphericCoordinate();
		other = coordinate.asSphericCoordinate();
		if (self.getRadius() != other.getRadius()) {
			throw new IllegalArgumentException("The coordinates must be on the same sphere.");
		}
		double latitudeRad1, latitudeRad2, longitudeRad1, longitudeRad2;
		latitudeRad1 = self.getPolar().convertPolarToLatitudeRad();
		latitudeRad2 = other.getPolar().convertPolarToLatitudeRad();
		longitudeRad1 = self.getAzimut().convertAzimutToLongitudeRad();
		longitudeRad2 = other.getAzimut().convertAzimutToLongitudeRad();
		return Math.acos(
				(Math.sin(latitudeRad1)*Math.sin(latitudeRad2))
				+ (Math.cos(latitudeRad1)*Math.cos(longitudeRad2)
				* Math.cos(Math.abs(longitudeRad1-longitudeRad2)))
		);
	}

	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean isEqual(Coordinate coordinate) {
		checkCoordinate(coordinate);
		if (this.getCartesianDistance(coordinate) < DISTANCE_EQUALITY) {
			return true;
		}
		return false;
	}

	/**
	 * @methodtype assert
	 */
	protected static void checkCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new NullPointerException("Coordinate must not be null");
		}
	}
	
	/**
	 * @methodtype assert
	 */
	protected static void checkDouble(double x, String msg) {
		if ((Double.isNaN(x)) || (Double.isInfinite(x))) {
			throw new IllegalArgumentException("Value '" + msg + "' must be a valid double value.");
		}
	}
	
	
}
