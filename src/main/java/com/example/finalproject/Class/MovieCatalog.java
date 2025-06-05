package com.example.finalproject.Class;

import com.example.finalproject.AVL.AVLTree;
import com.example.finalproject.Hash.Hash;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MovieCatalog {

    private Hash<Movie> hash = new Hash<>();

    public void addMovie(Movie movie) {
        boolean unique = true;
        for (AVLTree<Movie> tree : hash) {
            if (tree.find(movie) != null) {
                unique = false;
                break;
            }
        }
        if (!unique)
            throw new AlertException("This movie title already exists");

        hash.insert(movie);
    }

    public void updateMovie(Movie movie, String description, int year, double rating) {
        movie.setDescription(description);
        movie.setYear(year);
        movie.setRating(rating);
    }

    public Movie getMovie(String title) {
        for (AVLTree<Movie> tree : hash) {
            Movie movie = tree.find(new Movie(title, "", 2000, 1));
            if (movie != null)
                return movie;
        }
        return null;
    }

    public Movie erase(String title) {
        for (AVLTree<Movie> tree : hash) {
            Movie movie = tree.delete(new Movie(title, "", 2000, 1));
            if (movie != null)
                return movie;
        }
        return null;
    }

    public void saveMoviesToFile() throws IOException {
        PrintWriter output = new PrintWriter("movies.txt");
        for (AVLTree<Movie> tree : hash) {
            output.print(tree.inOrderTraverse());
        }
    }

    public void loadMoviesFromFile() throws IOException {
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new File("movies.txt"))) {
            lineNumber = 1;
            while (scanner.hasNext()) {
                String title = scanner.nextLine().replace("Title:", "").trim();
                String description = scanner.nextLine().replace("Description:", "").trim();
                String year = scanner.nextLine().replace("Year:", "").trim();
                String rating = scanner.nextLine().replace("Rating:", "").trim();
                addMovie(new Movie(title, description, Integer.parseInt(year), Double.parseDouble(rating)));
                lineNumber += 5;
            }

        } catch (Exception e) {
            throw new AlertException(e.getMessage() + ", the error in the movie in line " + lineNumber);
        }
    }

    public int hashFunction(String title) {
        return ((int) Math.abs(title.hashCode())) % hash.length();
    }

    public void deallocate() {
        hash.clear();
    }

    public Hash<Movie> getHash() {
        return hash;
    }
}
