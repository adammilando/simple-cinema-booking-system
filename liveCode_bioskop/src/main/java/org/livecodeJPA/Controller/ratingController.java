package org.livecodeJPA.Controller;

import org.livecodeJPA.Model.Film;
import org.livecodeJPA.Model.*;
import org.livecodeJPA.Service.*;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ratingController {
    Scanner scanner = new Scanner(System.in);
    private ratingService ratingService;

    public ratingController(org.livecodeJPA.Service.ratingService ratingService) {
        this.ratingService = ratingService;
    }

    public void createRating() {
            System.out.print("Enter rating code (A, BO, R, or D): ");
            String ratingCodeStr = scanner.nextLine();
            ratingCode ratingCode = null;
            ratingCode = ratingCode.valueOf(ratingCodeStr);

            System.out.print("Enter rating description: ");
            String description = scanner.nextLine();

            Rating rating = new Rating();
            rating.setRating(ratingCode);
            rating.setDescription(description);

            ratingService.add(rating);
            System.out.println("Rating added successfully with ID " + rating.getId());

    }
    public void updateRating() {
        System.out.print("Enter rating ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Rating rating = ratingService.getById(id);
        if (rating == null) {
            System.out.println("Rating not found!");
            return;
        }

        System.out.print("Enter new rating code (leave blank if no changes): ");
        String ratingCodeStr = scanner.nextLine();
        if (!ratingCodeStr.isBlank()) {
            rating.setRating(ratingCode.valueOf(ratingCodeStr));
        }

        System.out.print("Enter new rating description (leave blank if no changes): ");
        String description = scanner.nextLine();
        if (!description.isBlank()) {
            rating.setDescription(description);
        }

        ratingService.update(rating);
        System.out.println("Rating updated successfully!");
    }


    public void deleteRating() {
        System.out.print("Enter rating ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Rating rating = ratingService.getById(id);
        if (rating == null) {
            System.out.println("Rating not found!");
            return;
        }else {
            ratingService.delete(id);
            System.out.println("Rating deleted successfully!");
        }
    }

    public void listRatings() {
        System.out.println("Masukkan page: ");
        Integer page = scanner.nextInt();
        System.out.println("masukkan pageSize: ");
        Integer pageSize = scanner.nextInt();
        List<Rating> ratings = ratingService.findAll(page, pageSize);

        if (ratings.isEmpty()) {
            System.out.println("No seats found!");
            return;
        }
        System.out.println("Rantings: ");
        for (Rating rating: ratings) {
            System.out.printf("%d. %s (deskripsi: %s)\n", rating.getId(), rating.getRating(), rating.getDescription());
        }
    }

    public void getRatingById() {
        System.out.print("Enter rating ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Rating rating = ratingService.getById(id);
        if (rating == null) {
            System.out.println("Rating not found!");
            return;
        }

        System.out.println(rating);
    }



}
