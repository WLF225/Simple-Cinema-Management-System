package com.example.finalproject.FX;

import com.example.finalproject.Class.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.time.LocalDate;

public class EditMovie extends BorderPane {

    private TextField titleField;
    private TextArea descriptionArea;
    private DatePicker yearPicker;
    private TextField ratingField;
    private Button submitButton;
    private Button backButton;
    private Button refillButton;

    public EditMovie(Movie movie) {

        Text sceneTitle = new Text("🎬 Edit Movie");
        sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        setMargin(sceneTitle, new Insets(10, 10, 20, 10));
        this.setTop(sceneTitle);
        BorderPane.setAlignment(sceneTitle, Pos.TOP_CENTER);

        // Form Layout
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(10));

        // Title Field
        Label titleLabel = new Label("Title:");

        titleField = new TextField();
        titleField.setPromptText("Enter movie title");
        titleField.setDisable(true);

        // Description Field
        Label descriptionLabel = new Label("Description:");
        descriptionArea = new TextArea();
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefRowCount(3);

        // Year Field (DatePicker)
        Label yearLabel = new Label("Year:");

        yearPicker = new DatePicker();
        yearPicker.setPromptText("Select year");

        // Rating Field
        Label ratingLabel = new Label("Rating:");
        ratingField = new TextField();
        ratingField.setPromptText("e.g., 8.5");

        // Add components to grid
        int row = 0;
        formGrid.add(titleLabel, 0, row);
        formGrid.add(titleField, 1, row++);

        formGrid.add(descriptionLabel, 0, row);
        formGrid.add(descriptionArea, 1, row++);

        formGrid.add(yearLabel, 0, row);
        formGrid.add(yearPicker, 1, row++);

        formGrid.add(ratingLabel, 0, row);
        formGrid.add(ratingField, 1, row++);

        // Center the form
        this.setCenter(formGrid);
        BorderPane.setAlignment(formGrid, Pos.CENTER);

        // Buttons: Submit, Clear, Back
        submitButton = new Button("Edit Movie");
        backButton = new Button("Back");
        refillButton = new Button("Clear");

        // Style for all buttons (same style as MainMenu)
        String buttonStyle = "-fx-background-color: #4a90e2; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10 20 10 20; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);";

        submitButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);
        refillButton.setStyle(buttonStyle);

        // Arrange buttons horizontally
        HBox buttonBox = new HBox(10); // spacing between buttons
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.getChildren().addAll(backButton, refillButton, submitButton);

        // Add button box at bottom
        this.setBottom(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(20));

        backButton.setOnAction(e -> Main.scene.setRoot(new ShowMovies()));
        refillButton.setOnAction(e -> {
            titleField.setText(movie.getTitle());
            descriptionArea.setText(movie.getDescription());
            yearPicker.setValue(LocalDate.of(movie.getYear(), 1, 1));
            ratingField.setText(String.valueOf(movie.getRating()));
        });

        submitButton.setOnAction(e -> {
            try {
                if (yearPicker.getValue() == null) {
                    throw new Exception("Please enter a valid year");
                }
                if (titleField.getText().isEmpty() ||  descriptionArea.getText().isEmpty() ||
                        ratingField.getText().isEmpty()) {
                    throw new Exception("Text fields cannot be empty");
                }
                Main.movieCatalog.updateMovie(movie,descriptionArea.getText(),
                        yearPicker.getValue().getYear(),Double.parseDouble(ratingField.getText()));

                Main.showInformation("Movie edited successfully!");
            }catch (Exception ex){
                Main.showError(ex.getMessage());
            }
        });

        refillButton.fire();

        // Style background
        this.setStyle("-fx-background-color: #1c1f2b; -fx-padding: 20;");
        sceneTitle.setStyle("-fx-fill: white; -fx-font-weight: bold;");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        descriptionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        yearLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        ratingLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
    }
}