package com.comp301.a09akari;

import static org.junit.Assert.assertTrue;

import com.comp301.a09akari.model.*;
import org.junit.Test;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    int[][] a = {
            {6, 6, 6, 6, 1, 6, 6},
            {6, 6, 6, 5, 6, 6, 6},
            {0, 6, 6, 6, 6, 6, 6},
            {6, 5, 6, 6, 6, 4, 6},
            {6, 6, 6, 6, 6, 6, 5},
            {6, 6, 6, 2, 6, 6, 6},
            {6, 6, 5, 6, 6, 6, 6},
    };
    PuzzleLibrary b = new PuzzleLibraryImpl();
    b.addPuzzle(new PuzzleImpl(a));
    Model mod = new ModelImpl(b);
    mod.addLamp(2, 5);
    mod.addLamp(3, 4);
    mod.addLamp(3, 6);
    mod.addLamp(4, 5);
    mod.addLamp(0, 3);
    mod.addLamp(1, 1);
    mod.addLamp(5, 2);
    mod.addLamp(5, 6);
    mod.addLamp(6, 0);
    mod.addLamp(6, 3);
    assertTrue(mod.isSolved());
  }
}
