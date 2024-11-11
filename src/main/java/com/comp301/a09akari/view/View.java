package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Stack;

public class View implements FXComponent, ModelObserver {
  private final Model model;
  private ClassicMvcController controller;
  private final Scene scene;
  private final PuzzleView puzzleView;
  private final ControlView controlView;
  private final GameOver gameOver;

  public View(Model model, ClassicMvcController controller) {
    this.puzzleView = new PuzzleView(model, controller);
    this.controlView = new ControlView(controller);
    this.gameOver = new GameOver(controller);
    this.model = model;
    this.controller = controller;
    this.model.addObserver(this);
    this.scene = new Scene(render());
    this.scene.getStylesheets().add("main.css");
  }

  public Scene getScene() {
    return scene;
  }

  @Override
  public Parent render() {
    // VBox pane = new VBox();
    BorderPane pane = new BorderPane();

    pane.setPrefHeight(600);
    pane.setPrefWidth(500);
    // pane.setSpacing(10);
    // pane.setAlignment(Pos.CENTER);

    Label label =
        new Label(
            "Puzzle " + (model.getActivePuzzleIndex() + 1) + " of " + model.getPuzzleLibrarySize());
    HBox hbox = new HBox();
    hbox.setStyle("-fx-background-color: #D5FFD9");
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().add(label);

    //    pane.getChildren().add(hbox);
    //    pane.getChildren().add(controlView.render());
    pane.setCenter(controlView.render());
    pane.setTop(hbox);

    StackPane pane1 = new StackPane();
    pane1.getChildren().add(puzzleView.render());

    if (model.isSolved()) {
      // pane.getChildren().add(gameOver.render());
      pane1
          .getChildren()
          .addAll(new Rectangle(500, 500, Color.WHITE), new Label("Puzzle Completed"));
    }
    pane.setBottom(pane1);
    return pane;
  }

  @Override
  public void update(Model model) {
    scene.setRoot(render());
  }
}
