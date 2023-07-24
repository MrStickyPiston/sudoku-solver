import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  GetSudoku() {
    let grid = Array.from(new Array(9), (_) => Array(9).fill(0));

    for (let i = 0; i < 9; i++) {
      for (let j = 0; j < 9; j++) {
        grid[i][j] = Number(
          (<HTMLInputElement>(
            document.getElementById(
              `sudoku-input-[${i.toString()}-${j.toString()}]`
            )
          ))?.value
        );
      }
    }
    return grid;
  }

  FillSudoku(grid: Array<Array<Number>>) {
    for (let i = 0; i < 9; i++) {
      for (let j = 0; j < 9; j++) {
        (<HTMLInputElement>(
          document.getElementById(
            `sudoku-input-[${i.toString()}-${j.toString()}]`
          )
        ))!.value = grid[i][j].toString();
      }
    }
  }

  ResetSudoku() {
    console.log('Reset sudoku');
    this.FillSudoku(Array.from(new Array(9), (_) => Array(9).fill('')));
  }

  async SolveSudoku() {
    let grid = this.GetSudoku();
    console.log('Solving ' + JSON.stringify(grid));

    let solved = await fetch(`/api/solve?grid=${JSON.stringify(grid)}`, {
      method: 'POST',
    });
    let solvedGrid = await solved.json();
    
    try {
      this.FillSudoku(JSON.parse(solvedGrid["grid"]));

    } catch (SyntaxError) {
      alert("Invalid API response")

    }
  }

  constructor() {}

  ngOnInit() {}
}
