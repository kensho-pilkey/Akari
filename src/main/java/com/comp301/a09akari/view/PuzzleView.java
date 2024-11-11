package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.concurrent.atomic.AtomicBoolean;

public class PuzzleView implements FXComponent {
  private final Model model;
  private ClassicMvcController controller;

  public PuzzleView(Model model, ClassicMvcController controller) {
    if (model == null || controller == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.controller = controller;
  }

  public Button cellLabel(Button button, int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) == CellType.WALL) {
      button.setStyle(
          "-fx-background-color: #000000; -fx-border-color: #000000; -fx-border-width: 1px;");
      return button;
    }
    if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (model.isLamp(r, c)) {
        Image image = new Image("/light-bulb.png");
        ImageView im = new ImageView(image);
        im.setFitHeight(30);
        im.setFitWidth(30);
        button.setGraphic(im);
        if (model.isLampIllegal(r, c)) {
          button.setStyle(
              "-fx-background-color: #CD5C5C; -fx-border-color: #000000; -fx-border-width: 1px;");
        } else {
          button.setStyle(
              "-fx-background-color: #FDFD96; -fx-border-color: #000000; -fx-border-width: 1px;");
        }
      } else if (model.isLit(r, c)) {
        button.setStyle(
            "-fx-background-color: #FDFD96; -fx-border-color: #000000; -fx-border-width: 1px;");
      } else {
        button.setStyle(
            "-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
      }
      return button;
    } else {
      button.setText(Integer.toString(model.getActivePuzzle().getClue(r, c)));
      if (model.isClueSatisfied(r, c)) {
        button.setStyle(
            "-fx-text-fill: #3CB043; -fx-background-color: #000000; -fx-border-color: #000000; -fx-border-width: 1px;");
      } else {
        button.setStyle(
            "-fx-text-fill: #FFFFFF; -fx-background-color: #000000; -fx-border-color: #000000; -fx-border-width: 1px;");
      }
      return button;
    }
  }

  @Override
  public Parent render() {
    GridPane pane = new GridPane();
    pane.setPrefHeight(500);
    pane.setPrefWidth(500);
    pane.getStyleClass().add("puzzleBoard");

    double height = (double) 500 / model.getActivePuzzle().getHeight();
    double width = (double) 500 / model.getActivePuzzle().getWidth();

    for (int r = 0; r < model.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < model.getActivePuzzle().getWidth(); c++) {
        Button button = new Button("");
        button.getStyleClass().add("tile");
        button.setPrefSize(width, height);
        int row = r;
        int col = c;
        button.setOnAction(
            (ActionEvent event) -> {
              controller.clickCell(row, col);
            });
        Button updated = cellLabel(button, r, c);
        pane.add(updated, c, r);
      }
    }
    return pane;
  }
}
