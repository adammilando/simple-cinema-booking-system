package org.livecodeJPA.Controller;

import org.livecodeJPA.Model.Customer;
import org.livecodeJPA.Model.Seat;
import org.livecodeJPA.Model.Theater;
import org.livecodeJPA.Utils.generateDate;
import org.livecodeJPA.Service.*;

import java.util.List;
import java.util.Scanner;

public class seatController implements IController{
    private seatService seatService;
    private theaterService theaterService;

    public seatController(seatService seatService, theaterService theaterService) {
        this.seatService = seatService;
        this.theaterService = theaterService;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void add() {
        System.out.print("Enter seat number: ");
        String nomor = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter theater ID: ");
        Long theaterId = scanner.nextLong();
        scanner.nextLine();

        Theater theater = theaterService.getById(theaterId);
        if (theater == null) {
            System.out.println("Theater with ID " + theaterId + " not found!");
            return;
        }

        Seat seat = new Seat();
        seat.setSeatNumber(nomor);
        seat.setTheater(theater);

        seatService.add(seat);
        System.out.println("Seat created successfully!");
    }

    @Override
    public void update() {
        System.out.print("Enter seat ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Seat seat = seatService.getById(id);
        if (seat == null) {
            System.out.println("Seat not found!");
            return;
        }

        System.out.print("Enter new seat number (leave blank if no changes): ");
        String nomor = scanner.nextLine();
        if (!nomor.isBlank()) {
            seat.setSeatNumber(nomor);
        }

        System.out.print("Enter new theater ID (leave blank if no changes): ");
        String tIDString = scanner.nextLine();
        if (!tIDString.isBlank()){
            Long tID = Long.parseLong(tIDString);
            Theater theater = theaterService.getById(tID);
            if (theater == null) {
                System.out.println("Theater not found!");
                return;
            }
            seat.setTheater(theater);
        }

        seatService.update(seat);
        System.out.println("Seat updated successfully!");
    }

    @Override
    public void findAll() {
        System.out.println("Masukkan page: ");
        Integer page = scanner.nextInt();
        System.out.println("masukkan pageSize: ");
        Integer pageSize = scanner.nextInt();
        List<Seat> seats = seatService.findAll(page, pageSize);

        if (seats.isEmpty()) {
            System.out.println("No seats found!");
            return;
        }

        System.out.println("Seats: ");
        for (Seat seat : seats) {
            System.out.printf("%d. %s (Theater: %s Film: %s) %s\n", seat.getId(), seat.getSeatNumber(), seat.getTheater().getTheaterNumber(), seat.getTheater().getFilms().getTitle(), seat.getCustomers().toString());
        }
    }

    @Override
    public void getById() {
        System.out.print("Enter customer ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Seat seat = seatService.getById(id);
        if (seat == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.printf("seat ID: %d\n", seat.getId());
        System.out.printf("seat Number: %s\n", seat.getSeatNumber());
        System.out.printf("Theater: %s\n", seat.getTheater().getTheaterNumber());
    }

    @Override
    public void delete() {
        System.out.print("Enter seat ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        seatService.delete(id);
        System.out.println("seat deleted successfully!");
    }

}
