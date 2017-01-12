package main.java;

/**
 * Created by gabe.barcelos on 1/12/17.
 */
public class Blob {

    private long cellsRead;

    public int[] getBoundaries(int[][] matrix) {

        cellsRead = 0;
        int[] boundaries = new int[] { -1, -1, -1, -1 };
        int[] coordinates = getPointOfStartOfBlob(matrix);

        long accessCountBackup = getCellsRead();
        if (coordinates[0] == -1 || getNextDirection(matrix, Direction.right, coordinates[0], coordinates[1]) == Direction.left) {
            return boundaries;
        }
        cellsRead = accessCountBackup;

        boundaries[Boundary.top.getIndex()] = coordinates[0];
        boundaries[Boundary.bottom.getIndex()] = coordinates[0];
        boundaries[Boundary.left.getIndex()] = coordinates[1];
        boundaries[Boundary.right.getIndex()] = coordinates[1];

        getBoundaries(matrix, coordinates[0], coordinates[1], boundaries);
        return boundaries;
    }

    private void getBoundaries(final int[][] matrix, final int startRow, final int startColumn, final int[] result) {
        Direction currentDirection = Direction.right;
        int row = startRow;
        int column = startColumn;

        do {
            currentDirection = getNextDirection(matrix, currentDirection, row, column);
            row += currentDirection.getRow();
            column += currentDirection.getColumn();
            updateBoundaries(result, row, column);
        } while (!(row == startRow && column == startColumn));
    }

    private Direction getNextDirection(final int[][] matrix, final Direction previousDirection, final int row, final int column) {
        switch (previousDirection) {
        case right: {
            if (isEdge(matrix, row + Direction.up.getRow(), column + Direction.up.getColumn())) {
                return Direction.up;
            } else if (isEdge(matrix, row + Direction.right.getRow(), column + Direction.right.getColumn())) {
                return Direction.right;
            } else if (isEdge(matrix, row + Direction.down.getRow(), column + Direction.down.getColumn())) {
                return Direction.down;
            } else {
                return Direction.left;
            }
        }
        case left: {
            if (isEdge(matrix, row + Direction.down.getRow(), column + Direction.down.getColumn())) {
                return Direction.down;
            } else if (isEdge(matrix, row + Direction.left.getRow(), column + Direction.left.getColumn())) {
                return Direction.left;
            } else if (isEdge(matrix, row + Direction.up.getRow(), column + Direction.up.getColumn())) {
                return Direction.up;
            } else {
                return Direction.right;
            }
        }
        case up: {
            if (isEdge(matrix, row + Direction.left.getRow(), column + Direction.left.getColumn())) {
                return Direction.left;
            } else if (isEdge(matrix, row + Direction.up.getRow(), column + Direction.up.getColumn())) {
                return Direction.up;
            } else if (isEdge(matrix, row + Direction.right.getRow(), column + Direction.right.getColumn())) {
                return Direction.right;
            } else {
                return Direction.down;
            }
        }
        case down: {
            if (isEdge(matrix, row + Direction.right.getRow(), column + Direction.right.getColumn())) {
                return Direction.right;
            } else if (isEdge(matrix, row + Direction.down.getRow(), column + Direction.down.getColumn())) {
                return Direction.down;
            } else if (isEdge(matrix, row + Direction.left.getRow(), column + Direction.left.getColumn())) {
                return Direction.left;
            } else {
                return Direction.up;
            }
        }
        default:
            throw new IllegalArgumentException("previousDirection");
        }
    }

    private void updateBoundaries(int[] result, int row, int col) {
        result[Boundary.top.getIndex()] = Math.min(result[Boundary.top.getIndex()], row);
        result[Boundary.bottom.getIndex()] = Math.max(result[Boundary.bottom.getIndex()], row);
        result[Boundary.left.getIndex()] = Math.min(result[Boundary.left.getIndex()], col);
        result[Boundary.right.getIndex()] = Math.max(result[Boundary.right.getIndex()], col);
    }

    private int[] getPointOfStartOfBlob(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (isEdge(matrix, row, column)) {
                    return new int[] { row, column };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    private boolean isEdge(int[][] matrix, int row, int column) {
        if (column < 0 || row < 0 || column >= matrix[0].length  || row >= matrix.length) {
            return false;
        }
        cellsRead++;
        return matrix[row][column] == 1;
    }

    public long getCellsRead() {
        return cellsRead;
    }

    enum Direction {

        up(-1, 0),
        down(1, 0),
        left(0, -1),
        right(0, 1);

        private final int row;
        private final int column;

        Direction(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

    }

    enum Boundary {

        top(0),
        bottom(1),
        left(2),
        right(3);

        private final int index;

        Boundary(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

}
