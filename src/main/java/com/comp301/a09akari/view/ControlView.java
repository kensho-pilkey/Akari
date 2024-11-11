package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private ClassicMvcController controller;

  public ControlView(ClassicMvcController controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox pane = new HBox();

    pane.setAlignment(Pos.CENTER_RIGHT);
    // pane.setSpacing(50.0);
    pane.setPrefHeight(100);
    pane.setPrefWidth(500);
    pane.getStyleClass().add("controlBoard");
    pane.setStyle("-fx-background-color: #BEEDAA");
    // Left btn
    Button leftButton = new Button("");
    leftButton.getStyleClass().add("controlBtn");
    leftButton.setPrefSize(50, 50);
    Image image1 = new Image("/leftArrow.png");
    ImageView im1 = new ImageView(image1);
    im1.setFitHeight(30);
    im1.setFitWidth(30);
    leftButton.setGraphic(im1);
    leftButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    // Right btn
    Button rightButton = new Button("");
    rightButton.getStyleClass().add("controlBtn");
    rightButton.setPrefSize(50, 50);
    Image image2 = new Image("/rightArrow.png");
    ImageView im2 = new ImageView(image2);
    im2.setFitHeight(30);
    im2.setFitWidth(30);
    rightButton.setGraphic(im2);
    rightButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    // Reset btn
    Button resetButton = new Button("");
    resetButton.getStyleClass().add("controlBtn");
    resetButton.setPrefSize(50, 50);
    Image image3 = new Image("/reset.png");
    ImageView im3 = new ImageView(image3);
    im3.setFitHeight(30);
    im3.setFitWidth(30);
    resetButton.setGraphic(im3);
    resetButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    // Random btn
    Button randButton = new Button("");
    randButton.getStyleClass().add("controlBtn");
    randButton.setPrefSize(50, 50);
    Image image4 = new Image("/rand.png");
    ImageView im4 = new ImageView(image4);
    im4.setFitHeight(30);
    im4.setFitWidth(30);
    randButton.setGraphic(im4);
    randButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    // PuzzleNum
    HBox pane2 = new HBox();
    pane2.setAlignment(Pos.CENTER_RIGHT);
    // Label label = new Label("TEST");
    // label.setStyle("-fx-background-color: #FFFFFF");
    pane.setSpacing(50);
    pane2.setSpacing(100);

    leftButton.setStyle("-fx-background-color: #C0DA74");
    rightButton.setStyle("-fx-background-color: #C0DA74");
    randButton.setStyle("-fx-background-color: #C0DA74");
    resetButton.setStyle("-fx-background-color: #C0DA74");

    pane2.getChildren().add(leftButton);
    pane2.getChildren().add(randButton);
    pane2.getChildren().add(resetButton);
    pane2.getChildren().add(rightButton);
    pane.getChildren().add(pane2);
    // pane.getChildren().add(label);
    return pane;
  }
}
