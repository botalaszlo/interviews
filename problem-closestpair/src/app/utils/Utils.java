package app.utils;

import app.ClosestPointSolver;
import app.models.MultidimensionalPoint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility functions.
 *
 * @author Bota Laszlo<bota.laszlo.dev@outlook.com>
 * @version 0.5
 */
public class Utils {
    
    private static final String CHARSET_UTF_8 = "UTF-8";
    
    private static final String TXT_FILE_EXTENSION = "txt";
    
    private static final String TAB_SEPERATOR = "\t";

    /**
     * Get multidimensional points from file.
     *
     * @param fileName of file.
     * @return array of multidimensional points.
     */
    public static MultidimensionalPoint[] getMultidimensionalPoints(String fileName) {
        List<MultidimensionalPoint> points = new ArrayList<>();
        BufferedReader TSVFile = null;
        String line = "";
        
        try {
            TSVFile = new BufferedReader(new FileReader(fileName));
            while ((line = TSVFile.readLine()) != null) {
                String[] rawNumbers = line.trim().split(TAB_SEPERATOR);
                double[] numbers = new double[rawNumbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = Double.parseDouble(rawNumbers[i]);
                }
                points.add(new MultidimensionalPoint(numbers));
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println("[Error] The file not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("[Error] Input/Output error: " + ex.getMessage());
        } finally {
            try {
                if (TSVFile != null) {
                    TSVFile.close();
                }
            } catch (IOException ex) {
                System.err.println("[Error] Input/Output error: " + ex.getMessage());
            }
        }
        
        return convertToMultidimensionalPointArray(points);
    }

    /**
     * Export the result into a file.
     *
     * @param closestPoint object
     * @param inputFileName name of input file.
     */
    public static void exportResult(ClosestPointSolver closestPoint, String inputFileName) {
        List<String> lines = Arrays.asList(
                formatResult(closestPoint.indexOfEither() + 1, closestPoint.either()),
                formatResult(closestPoint.indexOfOther()+ 1, closestPoint.other())
        );
        String outputFileName = inputFileName.substring(0, inputFileName.length() - 3) + TXT_FILE_EXTENSION;
        outputFileName = outputFileName.replace("input", "output");
        Path filePath = Paths.get(outputFileName);
        try {
            Files.write(filePath, lines, Charset.forName(CHARSET_UTF_8));
            System.out.println("Result exported into: " + outputFileName);
        } catch (IOException ex) {
            System.err.println("[Error] File could not created: " + ex.getMessage());
        }
    }

    /**
     * Convert list of multidimensional points to array of multidimensional
     * points.
     *
     * @param points list of multidimensional points.
     * @return array of multidimensional points.
     */
    private static MultidimensionalPoint[] convertToMultidimensionalPointArray(List<MultidimensionalPoint> points) {
        MultidimensionalPoint[] result = new MultidimensionalPoint[points.size()];
        for (int i = 0; i < points.size(); i++) {
            result[i] = points.get(i);
        }
        return result;
    }
    
    /**
     * Format result data for the file.
     * 
     * @param lineNumber line number of given point in the input file.
     * @param point given point.
     * @return 
     */
    private static String formatResult(int lineNumber, MultidimensionalPoint point) {
        return lineNumber + ":" + formatCoordinates(point.coordinates());
    }
    
    /**
     * Format the coordinates.
     * 
     * @param coordinates of point.
     * @return formated coordinates.
     */
    private static String formatCoordinates(double[] coordinates) {
        StringBuilder sb = new StringBuilder();
        sb.append((int) Math.round(coordinates[0]));
        for (int i = 1; i < coordinates.length; i++) {
            sb.append(TAB_SEPERATOR).append(formatNumber(coordinates[i]));
        }
        return sb.toString();
    }

    /**
     * Format number.
     * If number is double and the second decimal is zero then write out
     * with one decimal part else round it and convert to integer.
     * 
     * @param coordinate of point.
     * @return number as string type.
     */
    private static String formatNumber(double coordinate) {
        String number;
        double fractionalPart = coordinate % 1;
        
        if (fractionalPart != 0) {
            if (Math.round(fractionalPart * 100) % 10 == 0) {
                number = String.format("%.1f", coordinate);
            } else {
                number = String.valueOf((int) Math.round(coordinate));
            }
        } else {
            number = String.valueOf((int) coordinate);
        }        
        return number;
    }
}
