package com.tambola.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private final int[][] grid;

    public Ticket(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public List<Integer> getTopRow() {
        return extractRow(0);
    }

    public List<Integer> getMiddleRow() {
        return extractRow(1);
    }

    public List<Integer> getBottomRow() {
        return extractRow(2);
    }

    public List<Integer> getCorners() {
        return extractCorners();
    }

    public List<Integer> getAllNumbers() {
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            all.addAll(extractRow(i));
        }
        return all;
    }

    private List<Integer> extractRow(int row) {
        List<Integer> list = new ArrayList<>();
        for (int col = 0; col < 9; col++) {
            int val = grid[row][col];
            if (val != -1) {
                list.add(val);
            }
        }
        return list;
    }

    private List<Integer> extractCorners() {
        List<Integer> list = new ArrayList<>();
        boolean topleft= false, topRight= false,bottomLeft= false, bottomRight= false;
        for (int col = 0; col < 9; col++) {
            int check = grid[0][col];
            if (!topleft && check != -1) {
                topleft = true;
                list.add(grid[0][col]);
            }
            int check2 = grid[2][col];
            if (!bottomLeft && check2 != -1) {
                bottomLeft = true;
                list.add(grid[2][col]);
            }

            if (topleft && bottomLeft) {
                break;
            }

        }

        for (int col = 8; col >= 0; col--) {
            int check = grid[0][col];
            if (!topRight && check != -1) {
                topRight = true;
                list.add(grid[0][col]);
            }
            int check2 = grid[2][col];
            if (!bottomRight && check2 != -1) {
                bottomRight = true;
                list.add(grid[2][col]);
            }

            if (topRight && bottomRight) {
                break;
            }

        }
        return list;
    }

}
