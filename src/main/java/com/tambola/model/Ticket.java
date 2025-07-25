package com.tambola.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private final int[][] grid;

    public Ticket(int[][] grid) {
        this.grid = grid;
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
}
