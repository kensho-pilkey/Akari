package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class PlacedLamps {
  private boolean[][] lamplist;

  public PlacedLamps(int r, int c) {
    lamplist = new boolean[r][c];
  }

  public boolean getHasLamp(int r, int c) {
    return lamplist[r][c];
  }

  public void placeLamp(int r, int c) {
    lamplist[r][c] = true;
  }

  public void removeLamp(int r, int c) {
    lamplist[r][c] = false;
  }

  public void clear() {
    int r = lamplist.length;
    int c = lamplist[0].length;
    lamplist = new boolean[r][c];
  }
}
