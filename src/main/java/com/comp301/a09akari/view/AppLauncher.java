package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    lib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    lib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    lib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    lib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));

    Model model = new ModelImpl(lib);
    ClassicMvcController controller = new ControllerImpl(model);
    View view = new View(model, controller);

    stage.setScene(view.getScene());
    stage.setTitle("Akari");
    stage.setMinWidth(500);
    stage.setMaxWidth(500);
    stage.setMinHeight(600);
    stage.setMaxHeight(600);
    stage.show();
    // TODO: Create your Model, View, and Controller instances and launch your GUI
  }
}
