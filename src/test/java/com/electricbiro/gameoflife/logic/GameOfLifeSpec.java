package com.electricbiro.gameoflife.logic;

import java.util.HashSet;
import java.util.Set;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

/**
 * @author Robert Stevenson-Leggett
 */
@RunWith(OleasterRunner.class)
public class GameOfLifeSpec {

    private GameOfLife gameOfLife = new GameOfLife();

    {
        describe("when a single tick occurs", () -> {
            it("if no cells are alive, no cells come to life", () -> {
                Set<Cell> cells = gameOfLife.evolve(new HashSet<>());
                expect(cells.isEmpty()).toBeTrue();
            });

            it("if a cell has only one neighbour, it dies", () -> {
                Cell cell1 = new Cell(0,0);
                Cell cell2 = new Cell(0,1);

                Set<Cell> cells = gameOfLife.evolve(new HashSet<Cell>() {
                    {
                        add(cell1);
                        add(cell2);
                    }
                });

                expect(cells.isEmpty()).toBeTrue();
            });

            it("if a cell has two live neighbours, it survives", () -> {
                Cell cell1 = new Cell(0,0);
                Cell cell2 = new Cell(0,1);
                Cell cell3 = new Cell(1,1);

                Set<Cell> cells = gameOfLife.evolve(new HashSet<Cell>() {
                    {
                        add(cell1);
                        add(cell2);
                        add(cell3);
                    }
                });

                expect(cells.isEmpty()).toBeFalse();
                expect(cells.size()).toEqual(3);
            });

            it("if a cell has three live neighbours, it survives", () -> {
                Cell cell1 = new Cell(0,0);
                Cell cell2 = new Cell(0,1);
                Cell cell3 = new Cell(1,1);
                Cell cell4 = new Cell(1,0);

                Set<Cell> cells = gameOfLife.evolve(new HashSet<Cell>() {
                    {
                        add(cell1);
                        add(cell2);
                        add(cell3);
                        add(cell4);
                    }
                });

                expect(cells.isEmpty()).toBeFalse();
                expect(cells.size()).toEqual(4);
            });

            it("if a cell has more than three neighbours, it dies", () -> {
                Cell cell1 = new Cell(0,0);
                Cell cell2 = new Cell(0,1);
                Cell cell3 = new Cell(1,1);
                Cell cell4 = new Cell(1,0);
                Cell cell5 = new Cell(1, 2);

                Set<Cell> cells = gameOfLife.evolve(new HashSet<Cell>() {
                    {
                        add(cell1);
                        add(cell2);
                        add(cell3);
                        add(cell4);
                        add(cell5);
                    }
                });

                expect(cells.isEmpty()).toBeFalse();
                expect(cells.contains(cell3)).toBeFalse();
            });

            it("if a cell has three neighbours it comes to life"()->{

            });
        });
    }
    //Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
    //Any live cell with two or three live neighbours lives on to the next generation.
    //Any live cell with more than three live neighbours dies, as if by overpopulation.
    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
}
