package main.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gabe.barcelos on 1/11/17.
 */
public class BlobBoundary {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.err.println("Please supply document path as an argument");
            System.exit(0);
        }

        Blob blob = new Blob();

        int[][] cells = getBlobMatrix(args[0]);
        if (cells == null) {
            System.err.println("Document could not be read.");
            System.exit(0);
        }

        int[] boundaries = blob.getBoundaries(cells);

        System.out.printf("Cell Reads: %d%n", blob.getCellsRead());
        System.out.printf("Top: %d%n", boundaries[Blob.Boundary.top.getIndex()]);
        System.out.printf("Left: %d%n", boundaries[Blob.Boundary.left.getIndex()]);
        System.out.printf("Bottom: %d%n", boundaries[Blob.Boundary.bottom.getIndex()]);
        System.out.printf("Right: %d%n", boundaries[Blob.Boundary.right.getIndex()]);
    }

    private static int[][] getBlobMatrix(String filePath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        List<String> rows = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            rows.add(scanner.nextLine());
        }
        scanner.close();

        if (rows.size() == 0) {
            System.out.println("Document empty, try a different file");
            System.exit(0);
        }

        int columnCount = rows.get(0).split("").length;
        int[][] matrix = new int[rows.size()][columnCount];

        for (int rowNumber = 0; rowNumber < matrix.length; rowNumber++) {
            String[] elements = rows.get(rowNumber).split("");
            for (int columnNumber = 0; columnNumber < matrix[0].length; columnNumber++) {
                if (elements[columnNumber].equals("0")) {
                    matrix[rowNumber][columnNumber] = 0;
                } else {
                    matrix[rowNumber][columnNumber] = 1;
                }
            }
        }

        return matrix;
    }
}
