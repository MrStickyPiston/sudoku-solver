import { Component } from '@angular/core';

@Component({
  selector: 'sudoku-grid',
  templateUrl: './sudoku.component.html',
  styleUrls: ['./sudoku.component.css']
})

export class SudokuComponent {
  grid = Array.from(new Array(9), _ => Array(9).fill(0));
  
  PostInput() {
    
  }
}