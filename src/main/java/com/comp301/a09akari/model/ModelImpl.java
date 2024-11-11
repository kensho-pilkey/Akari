package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private int row;
  private int col;
  private PuzzleLibrary library;
  private int activePuzzleIndex;
  private PlacedLamps lamps;
  private List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    activePuzzleIndex = 0;
    observers = new ArrayList<>();
    row = library.getPuzzle(0).getHeight();
    col = library.getPuzzle(0).getWidth();
    lamps = new PlacedLamps(row, col);
  }

  @Override
  public void addLamp(int r, int c) {
    if (!(library.getPuzzle(activePuzzleIndex).getCellType(r, c) == CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    if (!isLamp(r, c)) {
      lamps.placeLamp(r, c);
      for (ModelObserver o : observers) {
        o.update(this);
      }
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (!(library.getPuzzle(activePuzzleIndex).getCellType(r, c) == CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    if (isLamp(r, c)) {
      lamps.removeLamp(r, c);
      for (ModelObserver o : observers) {
        o.update(this);
      }
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (!(library.getPuzzle(activePuzzleIndex).getCellType(r, c) == CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    if (isLamp(r, c)) {
      return true;
    }
    // Check right
    for (int i = c; i < col; i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    // Check left
    for (int i = c; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    // Check down
    for (int i = r; i < row; i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    // Check up
    for (int i = r; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (!(library.getPuzzle(activePuzzleIndex).getCellType(r, c) == CellType.CORRIDOR)) {
      throw new IllegalArgumentException();
    }
    return lamps.getHasLamp(r, c);
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || r >= row || c < 0 || c >= col) {
      throw new IndexOutOfBoundsException();
    }
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }

    // Check right
    for (int i = c + 1; i < col; i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    // Check left
    for (int i = c - 1; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    // Check down
    for (int i = r + 1; i < row; i++) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    // Check up
    for (int i = r - 1; i >= 0; i--) {
      if (library.getPuzzle(activePuzzleIndex).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return library.getPuzzle(activePuzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= getPuzzleLibrarySize() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzleIndex = index;
    row = library.getPuzzle(activePuzzleIndex).getHeight();
    col = library.getPuzzle(activePuzzleIndex).getWidth();
    lamps = new PlacedLamps(row, col);
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    lamps.clear();
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public boolean isSolved() {
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
          if (!isLit(r, c)) {
            return false;
          }
          if (isLamp(r, c)) {
            if (isLampIllegal(r, c)) {
              return false;
            }
          }
        }
        if (getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
          if (!isClueSatisfied(r, c)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int lampCount = 0;
    int clueNum = getActivePuzzle().getClue(r, c);

    // Check down
    if ((r < row - 1) && getActivePuzzle().getCellType(r + 1, c) == CellType.CORRIDOR) {
      if (isLamp(r + 1, c)) {
        lampCount++;
      }
    }
    // Check up
    if ((r > 0) && getActivePuzzle().getCellType(r - 1, c) == CellType.CORRIDOR) {
      if (isLamp(r - 1, c)) {
        lampCount++;
      }
    }
    // Check right
    if ((c < col - 1) && getActivePuzzle().getCellType(r, c + 1) == CellType.CORRIDOR) {
      if (isLamp(r, c + 1)) {
        lampCount++;
      }
    }
    if ((c > 0) && getActivePuzzle().getCellType(r, c - 1) == CellType.CORRIDOR) {
      if (isLamp(r, c - 1)) {
        lampCount++;
      }
    }
    return lampCount == clueNum;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }
}
