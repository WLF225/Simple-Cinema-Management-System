package com.example.finalproject.FX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends BorderPane {

    public MainMenu() {
        // Create title
        Text title = new Text("🎬 Movie Catalogue");
        title.setFont(new Font("Arial", 36));

        // Create buttons
        Button btnAddMovie = new Button("Show all movies");
        Button btnViewMovies = new Button("Statistics");
        Button btnSearchMovie = new Button("File menu");
        Button btnExit = new Button("Exit");

        // Set button size and font
        setButtonStyle(btnAddMovie);
        setButtonStyle(btnViewMovies);
        setButtonStyle(btnSearchMovie);
        setButtonStyle(btnExit);

        // Arrange buttons vertically
        VBox buttonBox = new VBox(20); // spacing = 20
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
                btnAddMovie,
                btnViewMovies,
                btnSearchMovie,
                btnExit
        );

        btnAddMovie.setOnAction(e -> Main.scene.setRoot(new ShowMovies()));

        // Add title and buttons to BorderPane
        this.setTop(title);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);
        this.setCenter(buttonBox);

        // Optional: Set background color or image
        this.setStyle("-fx-background-color: #1c1f2b; -fx-padding: 20;");
        title.setStyle("-fx-fill: white; -fx-font-weight: bold;");
    }

    private void setButtonStyle(Button button) {
        button.setMinSize(200, 50);
        button.setFont(new Font("Arial", 18));
        button.setStyle(
                "-fx-background-color: #4a90e2;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);"
        );
    }
}