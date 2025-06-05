package com.example.finalproject.FX;

import com.example.finalproject.Class.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ShowMovies extends BorderPane {

    private TableView<Movie> tableView;
    private TextField searchField;
    private ComboBox<String> filterCombo;
    private Button searchButton, editButton, deleteButton, backButton, addMovieButton;

    public ShowMovies() {
        initializeUI();
    }

    private void initializeUI() {
        // Title
        Text sceneTitle = new Text("🎥 Movie Catalog");
        sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        setMargin(sceneTitle, new Insets(10));
        this.setTop(sceneTitle);
        BorderPane.setAlignment(sceneTitle, Pos.TOP_CENTER);

        // Top: Search Area
        searchField = new TextField();
        searchField.setPromptText("Enter search term");

        filterCombo = new ComboBox<>();
        filterCombo.getItems().addAll("Title", "Year");
        filterCombo.setValue("Title");

        searchButton = new Button("Search");
        styleButton(searchButton);

        HBox topBox = new HBox(10, filterCombo, searchField, searchButton);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));
        this.setTop(topBox);

        ObservableList<Movie> movieList = FXCollections.observableArrayList();

        Main.movieCatalog.addToObservableList(movieList);

        // Table View
        tableView = new TableView<>(movieList);

        TableColumn<Movie, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Movie, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Movie, Double> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Movie, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll(titleCol, yearCol, ratingCol, descCol);

        tableView.setMinHeight(300);

        this.setCenter(tableView);

        // Bottom Buttons
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        backButton = new Button("Back");
        addMovieButton = new Button("Add movie");

        styleButton(editButton);
        styleButton(deleteButton);
        styleButton(backButton);
        styleButton(addMovieButton);

        HBox buttonBox = new HBox(15, editButton, deleteButton, addMovieButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        this.setBottom(buttonBox);

        addMovieButton.setOnAction(e -> Main.scene.setRoot(new AddMovie()));
        searchButton.setOnAction(e -> {
            if (searchField.getText().isEmpty()) {
                tableView.setItems(movieList);
                return;
            }
            ObservableList<Movie> filteredList = FXCollections.observableArrayList(movieList);
            if (filterCombo.getValue().equals("Title")) {
                filteredList.removeIf(movie -> !movie.getTitle().toLowerCase().contains(searchField.getText().toLowerCase()));
                if (filteredList.isEmpty()) {
                    Main.showError("There is no movie with this title");
                    return;
                }
            }else {
                filteredList.removeIf(movie -> movie.getYear() != Integer.parseInt(searchField.getText()));
                if (filteredList.isEmpty()) {
                    Main.showError("There is no movie in this year");
                    return;
                }
            }
            tableView.setItems(filteredList);
        });

        deleteButton.setOnAction(e -> {
            Movie selectedMovie = tableView.getSelectionModel().getSelectedItem();
            if (selectedMovie == null) {
                Main.showInformation("Please select the movie you want to delete");
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete "+selectedMovie.getTitle()+" movie?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    Main.movieCatalog.erase(selectedMovie.getTitle());
                    movieList.remove(selectedMovie);
                    Main.showInformation("The movie has been deleted");
                }
            }
        });

        editButton.setOnAction(e -> {
            Movie selectedMovie = tableView.getSelectionModel().getSelectedItem();
            if (selectedMovie == null) {
                Main.showInformation("Please select the movie you want to delete");
            }else
                Main.scene.setRoot(new EditMovie(selectedMovie));
        });

        backButton.setOnAction(e -> Main.scene.setRoot(new MainMenu()));

        // Style background
        this.setStyle("-fx-background-color: #1c1f2b; -fx-padding: 20;");
        sceneTitle.setStyle("-fx-fill: white; -fx-font-weight: bold;");
    }

    private void styleButton(Button btn) {
        btn.setStyle(
                "-fx-background-color: #4a90e2; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 8 16 8 16; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6;"
        );
    }



}