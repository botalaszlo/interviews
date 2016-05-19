package app;
/*
 * @(#)app.DistanceMeasure.java    2014/04/11
 *
 */

import app.models.MultidimensionalPoint;

/**
 * app.DistanceMeasure interface provides the Euclidean distance algorithms.
 * This interface behaves as a trait object in order to use advantage of
 * java 8.
 *
 * @author Bota Laszlo<bota.laszlo.dev@outlook.com>
 * @version 0.5
 */
public interface DistanceMeasure {

    /**
     * Euclidean distance algorithms to calculate the minium distance. This
     * algorithms operate on two double array.
     *
     * @param a coordinates of the either point.
     * @param b coordinates of the other point.
     * @return distance of the two given coordinates.
     * @see MultidimensionalPoint
     */
    default double euclideanDistance(double[] a, double[] b) {
        checkLengthOfArrays(a, b);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            final double d = a[i] - b[i];
            sum += d * d;
        }
        return Math.sqrt(sum);
    }

    /**
     * Euclidean distance algorithms to calculate the minium distance by the
     * absolute methods which used to calculate the difference of two
     * coordinate. This algorithms operate on two double array.
     *
     * @param a coordinates of the either point.
     * @param b coordinates of the other point.
     * @return distance of the two given coordinates.
     * @see MultidimensionalPoint
     */
    default double euclideanDistanceAbs(double[] a, double[] b) {
        checkLengthOfArrays(a, b);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            final double d = Math.abs(a[i] - b[i]);
            sum += d * d;
        }
        return Math.sqrt(sum);
    }

    /**
     * Check the two given double array has the same length.
     *
     * @param a given array of coordinates.
     * @param b given array of coordinates.
     * @throws IllegalArgumentException
     */
    default void checkLengthOfArrays(double[] a, double[] b) throws IllegalArgumentException {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Dimensions are not the same.");
        }
    }
}
