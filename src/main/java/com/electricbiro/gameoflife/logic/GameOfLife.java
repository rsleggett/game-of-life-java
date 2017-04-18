package com.electricbiro.gameoflife.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Robert Stevenson-Leggett
 */
public class GameOfLife {



    public Set<Cell> evolve(Set<Cell> cells) {
        // foreach cell
            //Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
            //Any live cell with two or three live neighbours lives on to the next generation.
            //Any live cell with more than three live neighbours dies, as if by overpopulation.
            //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        Set<Cell> toRemove = new HashSet<>();
        for(Cell c : cells) {
            int x = c.getX();
            int y = c.getY();
            int neighbours = 0;
            for(Cell c2 : cells) {
                if(c2.getX() == x + 1 || c2.getY() == y + 1) {
                    neighbours++;
                } else if (c2.getX() == x - 1 || c2.getY() == y - 1){
                    neighbours++;
                }
            }
            if(neighbours == 2 || neighbours == 3) {
                toRemove.add(c);
            }
        }
        cells.retainAll(toRemove);
        return cells;
    }
}
