package com.example.finalproject.FX;


import com.example.finalproject.Class.Movie;
import com.example.finalproject.Class.MovieCatalog;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    public static MovieCatalog movieCatalog = new MovieCatalog();
    public static Scene scene;
    public void start(Stage stage) {

        movieCatalog.addMovie(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into a C.E.O.'s mind.", 2010, 8.8));
        movieCatalog.addMovie(new Movie("The Matrix", "A computer programmer discovers a mysterious world of digital reality controlled by intelligent machines.", 1999, 8.7));
        movieCatalog.addMovie(new Movie("Inception2", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", 2014, 8.6));
        movieCatalog.addMovie(new Movie("Tenet", "A secret organization uses time inversion to prevent World War III, led by a nameless protagonist known only as The Protagonist.", 2020, 7.8));
        movieCatalog.addMovie(new Movie("Arrival", "A linguist works with the military to communicate with aliens after twelve mysterious spacecraft appear around the world.", 2016, 8.0));
        movieCatalog.addMovie(new Movie("Blade Runner 2049", "A replicant hunter uncovers a long-buried secret that threatens to destabilize society and begins searching for a missing person.", 2017, 8.0));
        movieCatalog.addMovie(new Movie("The Martian", "An astronaut is presumed dead and left behind on Mars, where he must find a way to survive until a rescue mission can reach him.", 2015, 8.0));
        movieCatalog.addMovie(new Movie("Gravity", "Two astronauts become stranded in space after their shuttle is destroyed and must rely on each other to survive.", 2013, 7.7));
        movieCatalog.addMovie(new Movie("Ex Machina", "A young programmer is selected to participate in a groundbreaking experiment involving a humanoid AI capable of passing the Turing test.", 2015, 7.7));
        movieCatalog.addMovie(new Movie("Edge of Tomorrow", "A soldier fighting in a war against aliens gains the ability to relive the same day repeatedly and uses it to stop the invasion.", 2014, 7.9));

        scene = new Scene(new MainMenu());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Movie Catalogue");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInformation(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
