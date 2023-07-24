package io.github.mrstickypiston.SudokuSolver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SolverApiController {
	private ObjectMapper mapper = new ObjectMapper();

	@PostMapping("/api/solve")
	public SolverApiJson solver(@RequestParam(value = "grid", defaultValue = "[[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0]]") String grid) {
		Solver sudokuSolver = new Solver(grid);
		if (sudokuSolver.solve()){
			try {
				return new SolverApiJson(true, mapper.writeValueAsString(sudokuSolver.grid), sudokuSolver.message);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new SolverApiJson(false, "None", "An error occured");
			}
		} else {
			return new SolverApiJson(false, "None", sudokuSolver.message);
		}

		
	}
}