package app;/*
 * @(#)ClosestPoint.java    2014/04/11
 *
 */

import app.models.MultidimensionalPoint;

/**
 * app.ClosestPointSolver class calculates the minimum distance between two
 * n-dimensional points.
 * Provides the minimum distance, the two Multidimensional points and their
 * indexes.
 *
 * @author Bota Laszlo<bota.laszlo.dev@outlook.com>
 * @see MultidimensionalPoint
 * @version 0.5
 */
public class ClosestPointSolver implements DistanceMeasure {

    /**
     * Represents the dimension number of points.
     */
    private final int N;

    /**
     * Copied array of the given multidimensional points.
     * @see MultidimensionalPoint
     */
    private final MultidimensionalPoint[] points;

    /**
     * Represents the closest points to each other.
     * @see MultidimensionalPoint
     */
    private MultidimensionalPoint pointA, pointB;

    /**
     * Index of either point in the given points array.
     */
    private int indexA;

    /**
     * Index of other point in the given points array.
     */
    private int indexB;

    private double minimumDistance = Double.POSITIVE_INFINITY;

    /**
     * Constructor.
     *
     * @param points of multidimensional points.
     * @throws IllegalArgumentException if the given array is empty.
     * @see MultidimensionalPoint
     */
    public ClosestPointSolver(MultidimensionalPoint[] points) {
        if (points.length == 0) {
            throw new IllegalArgumentException("The points array is empty.");
        }

        this.N = points.length;
        this.points = new MultidimensionalPoint[N];
        System.arraycopy(points, 0, this.points, 0, N);

        this.findMinimumDistance();
    }

    /**
     * @return dimension of points.
     */
    public int dimension() {
        return N;
    }

    /**
     * @return either point of closest pair points.
     */
    public MultidimensionalPoint either() {
        return pointA;
    }

    /**
     * @return other point of closest pair points.
     */
    public MultidimensionalPoint other() {
        return pointB;
    }

    /**
     * @return index of either point of closest pair points.
     */
    public int indexOfEither() {
        return indexA;
    }

    /**
     * @return index of other point of closest pair points.
     */
    public int indexOfOther() {
        return indexB;
    }

    /**
     * Find the minimum distance in the given point list.
     */
    private void findMinimumDistance() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                final double distance = euclideanDistance(
                        points[i].getCoordinates(), points[j].getCoordinates());

                if (distance < minimumDistance) {
                    minimumDistance = distance;
                    setPointData(i, j);
                }
            }
        }
    }

    /**
     * Set the point data. The data is the either and other points and their
     * indexes in the given list.
     *
     * @param i index of either point.
     * @param j index of other point.
     */
    private void setPointData(int i, int j) {
        indexA = i;
        indexB = j;
        pointA = points[i];
        pointB = points[j];
    }
}
