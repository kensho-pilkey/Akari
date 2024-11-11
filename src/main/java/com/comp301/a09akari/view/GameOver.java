package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GameOver implements FXComponent {
  private ClassicMvcController controller;

  public GameOver(ClassicMvcController controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    this.controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane pane = new BorderPane();
    pane.setPrefHeight(10);
    pane.setPrefWidth(10);
    pane.getStyleClass().add("gameOver");
    pane.setStyle(
        "-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
    Label label = new Label("Puzzle Completed!");
    pane.setCenter(label);
    return pane;
  }
}
