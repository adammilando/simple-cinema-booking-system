package org.livecodeJPA.Controller;
import java.util.List;
import java.util.Scanner;

import org.livecodeJPA.Model.Film;
import org.livecodeJPA.Model.Seat;
import org.livecodeJPA.Model.Theater;
import org.livecodeJPA.Service.*;

public class theaterController implements IController{
    private theaterService theaterService;
    private filmService filmService;

    public theaterController(org.livecodeJPA.Service.theaterService theaterService, org.livecodeJPA.Service.filmService filmService) {
        this.theaterService = theaterService;
        this.filmService = filmService;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void add() {
        System.out.print("Enter Theater number: ");
        String number = scanner.nextLine();
        System.out.print("Enter Film ID: ");
        Long filmId = scanner.nextLong();
        scanner.nextLine();

        Film filmr = filmService.getById(filmId);
        if (filmr == null) {
            System.out.println("Theater with ID " + filmId + " not found!");
            return;
        }

        Theater theater = new Theater();
        theater.setTheaterNumber(number);
        theater.setFilms(filmr);

        theaterService.add(theater);
        System.out.println("Seat created successfully!");
    }

    @Override
    public void update() {
        System.out.print("Enter theater ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Theater theater = theaterService.getById(id);
        if (theater == null) {
            System.out.println("theater not found!");
            return;
        }

        System.out.print("Enter new theater number (leave blank if no changes): ");
        String nomor = scanner.nextLine();
        if (!nomor.isBlank()) {
            theater.setTheaterNumber(nomor);
        }

        System.out.print("Enter new film ID (leave blank if no changes): ");
        String tIDString = scanner.nextLine();
        if (!tIDString.isBlank()){
            Long tID = Long.parseLong(tIDString);
            Film film = filmService.getById(tID);
            if (film == null) {
                System.out.println("Theater not found!");
                return;
            }
            theater.setFilms(film);
        }

        theaterService.update(theater);
        System.out.println("Seat updated successfully!");
    }

    @Override
    public void findAll() {
        System.out.println("Masukkan page: ");
        Integer page = scanner.nextInt();
        System.out.println("masukkan pageSize: ");
        Integer pageSize = scanner.nextInt();
        List<Theater> theaters = theaterService.findAll(page, pageSize);

        if (theaters.isEmpty()) {
            System.out.println("No seats found!");
            return;
        }

        System.out.println("Seats: ");
        for (Theater theater : theaters) {
            System.out.printf("%d. %s (Film: %s) Stock: %d\n", theater.getId(), theater.getTheaterNumber(), theater.getFilms(), theater.getStock());
        }
    }

    @Override
    public void getById() {
        System.out.print("Enter Theater ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Theater theater = theaterService.getById(id);
        if (theater == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.printf("Theater ID: %d\n", theater.getId());
        System.out.printf("Theater Number: %s\n", theater.getTheaterNumber());
        System.out.printf("Film: %s\n", theater.getFilms());
    }

    @Override
    public void delete() {
        System.out.print("Enter theater ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        theaterService.delete(id);
        System.out.println("seat deleted successfully!");
    }
}
