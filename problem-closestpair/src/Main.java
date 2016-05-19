/*
 * @(#)Main.java    2014/04/11
 *
 */

import app.ClosestPointSolver;
import app.models.MultidimensionalPoint;
import app.utils.Utils;

/**
 * Main class.
 * The enter point of program.
 *
 * @author Bota Laszlo<bota.laszlo.dev@outlook.com>
 * @version 0.5
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("[Error]: Please add one input file.");
        }
        final String fileName = args[0];
        MultidimensionalPoint[] points = Utils.getMultidimensionalPoints(fileName);
        ClosestPointSolver closestPointSolver = new ClosestPointSolver(points);
        Utils.exportResult(closestPointSolver, fileName);
    } 
}
