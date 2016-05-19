package app.models;
/*
 * @(#)app.models.MultidimensionalPoint.java    2014/04/11
 *
 */

/**
 * app.models.MultidimensionalPoint represents an n-dimensional point in the space.
 *
 * @author Bota Laszlo<bota.laszlo.dev@outlook.com>
 * @version 0.5
 */
public class MultidimensionalPoint {

    /**
     * Dimension of point.
     */
    private final int dimension;

    /**
     * Coordinates of the point.
     * The number of coordinates equals to dimension of point.
     */
    private final double[] coordinates;

    /**
     * Constructor.
     * The value of coordinates is copied to avoid the reference errors.
     *
     * @param coordinates of the point.
     */
    public MultidimensionalPoint(double[] coordinates) {
        this.dimension = coordinates.length;
        this.coordinates = new double[dimension];
        System.arraycopy(coordinates, 0, this.coordinates, 0, dimension);
    }

    /**
     * Get dimension of point.
     * @return dimension of point.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Get coordinates of point.
     * @return coordinates of point.
     */
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * @return dimension of point.
     */
    public int dimension() {
        return getDimension();
    }

    /**
     * @return coordinates of point.
     */
    public double[] coordinates() {
        return getCoordinates();
    }
}
