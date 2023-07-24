package io.github.mrstickypiston.SudokuSolver;

import javax.sound.midi.Track;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Solver {
    private ObjectMapper mapper = new ObjectMapper();

    public int[][] grid;
    public String message;

    public Solver(String rawGrid){
        try {
            grid = mapper.readValue(rawGrid, new TypeReference<int[][]>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static boolean check(int[][] grid, int row, int col, int num){

        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == num)
                return false;

        for (int x = 0; x <= 8; x++)
            if (grid[x][col] == num)
                return false;

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    private static boolean solveSudoku(int grid[][], int row, int col){
        if (row == 9 - 1 && col ==  9)
            return true;

        if (col ==  9) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {
            if (check(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1)){
                    return true;
                }

            }

            grid[row][col] = 0;
        }

        return false;
    }

    public boolean solve(){
        if (solveSudoku(grid, 0, 0)){
            message = "Solved the sudoku";
            return true;
            
        } else {
            System.out.println("Failed to fill in sudoku");
            message = "No Solution found";

            return false;
        }
    }
}
