package org.livecodeJPA.Controller;
import org.livecodeJPA.Model.Film;
import org.livecodeJPA.Model.Rating;
import org.livecodeJPA.Service.*;
import org.livecodeJPA.Utils.generateDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class filmController implements IController {
    private filmService filmService;
    private ratingService ratingService;
    Scanner scanner = new Scanner(System.in);

    public filmController(filmService filmService, ratingService ratingService) {
        this.filmService = filmService;
        this.ratingService = ratingService;
    }

    @Override
    public void add() {
        System.out.print("Enter film title: ");
        String title = scanner.nextLine();

        System.out.print("Enter film duration: ");
        String duration = scanner.nextLine();

        System.out.print("Enter film show date (yyyy-MM-dd): ");
        String showDateStr = scanner.nextLine();

        System.out.print("Enter film price: ");
        Integer price = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter ranting ID: ");
        Long rantingId = scanner.nextLong();
        scanner.nextLine();

        Rating ranting = ratingService.getById(rantingId);
        if (ranting == null) {
            System.out.println("Ranting not found!");
            return;
        }

        Film film = new Film();
        film.setTitle(title);
        film.setDuration(duration);
        film.setShowDate(generateDate.generate(showDateStr));
        film.setPrice(price);
        film.setRating(ranting);

        filmService.add(film);
        System.out.println("Film added successfully with ID " + film.getId());
    }

    @Override
    public void update() {
        System.out.print("Enter film ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Film film = filmService.getById(id);
        if (film == null) {
            System.out.println("Film not found!");
            return;
        }

        System.out.print("Enter new film title (leave empty to keep current title): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            film.setTitle(title);
        }

        System.out.print("Enter new film duration (leave empty to keep current duration): ");
        String durationStr = scanner.nextLine();
        if (!durationStr.isEmpty()) {
            film.setDuration(durationStr);
        }

        System.out.print("Enter new film show date (yyyy-MM-dd) (leave empty to keep current show date): ");
        String showDateStr = scanner.nextLine();
        if (!showDateStr.isEmpty()) {
            film.setShowDate(generateDate.generate(showDateStr));
        }

        System.out.print("Enter new film price (leave empty to keep current price): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) {
            Integer price = Integer.parseInt(priceStr);
            film.setPrice(price);
        }

        System.out.print("Enter new rating ID (leave empty to keep current price): ");
        Long rantingId = scanner.nextLong();
        scanner.nextLine();

        Rating ranting = ratingService.getById(rantingId);
        if (ranting == null) {
            System.out.println("Ranting not found!");
            return;
        }else {
            film.setRating(ranting);
        }
    }

    @Override
    public void findAll() {
        System.out.println("Masukkan page: ");
        Integer page = scanner.nextInt();
        System.out.println("masukkan pageSize: ");
        Integer pageSize = scanner.nextInt();
        List<Film> films = filmService.findAll(page,pageSize);
        for (Film film : films) {
            System.out.println(film);
        }
    }

    @Override
    public void getById() {
        System.out.print("Enter film ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Film film = filmService.getById(id);
        if (film == null) {
            System.out.println("Film not found!");
            return;
        }

        System.out.println(film);
    }

    @Override
    public void delete() {
        System.out.print("Enter Film ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        filmService.delete(id);
        System.out.println("Customer deleted successfully!");
    }
}
