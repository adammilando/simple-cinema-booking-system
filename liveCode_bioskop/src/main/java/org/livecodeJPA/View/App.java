package org.livecodeJPA.View;

import jakarta.persistence.EntityManager;
import org.livecodeJPA.Config.Factory;
import org.livecodeJPA.Controller.*;
import org.livecodeJPA.Repository.*;
import org.livecodeJPA.Service.*;

import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);

    static EntityManager entityManager = Factory.start();
    static org.livecodeJPA.Repository.customerRepo customerRepo = new customerRepo(entityManager);
    static org.livecodeJPA.Repository.seatRepo seatRepo = new seatRepo(entityManager);
    static org.livecodeJPA.Repository.theaterRepo theaterRepo = new theaterRepo(entityManager);
    static org.livecodeJPA.Repository.filmRepo filmRepo =new filmRepo(entityManager);
    static org.livecodeJPA.Repository.ratingRepo ratingRepo = new ratingRepo(entityManager);

    static org.livecodeJPA.Service.ratingService ratingService = new ratingService(ratingRepo);
    static org.livecodeJPA.Service.filmService filmService = new filmService(filmRepo,ratingService);
    static org.livecodeJPA.Service.theaterService theaterService = new theaterService(theaterRepo,filmService);
    static org.livecodeJPA.Service.seatService seatService = new seatService(seatRepo,theaterService,new customerService(customerRepo,new seatService(seatRepo,theaterService,null), theaterService));
    static customerService customerService = new customerService(customerRepo, seatService, theaterService);

    static org.livecodeJPA.Controller.customerController customerController = new customerController(customerService);
    static org.livecodeJPA.Controller.seatController seatController = new seatController(seatService, theaterService);
    static org.livecodeJPA.Controller.theaterController theaterController = new theaterController(theaterService, filmService);
    static org.livecodeJPA.Controller.filmController filmController =new filmController(filmService,ratingService);
    static org.livecodeJPA.Controller.ratingController ratingController = new ratingController(ratingService);

    public void menuUtama(){
        boolean loop = false;
        while (!loop){
            System.out.println("=== Menu Utama ===");
            System.out.println("1. Menu user");
            System.out.println("2. Menu admin");
            System.out.println("3. exit");
            System.out.print("pilihan: ");
            int pilihan = scanner.nextInt();

            switch (pilihan){
                case 1:
                    menuUser();
                    break;
                case 2:
                    menuAdmin();
                    break;
                case 3:
                    System.out.println("terima kasih");
                    loop = true;
                    break;
                default:
                    System.out.println("input salah");
            }
        }
    }

    public void menuAdmin(){
        while (true){
            System.out.println("==== Menu admin ===");
            System.out.println("1. Menu Crud Customer");
            System.out.println("2. Menu Crud Film");
            System.out.println("3. Menu Crud Theater");
            System.out.println("4. Menu Crud Seat");
            System.out.println("5. Menu Crud Rating");
            System.out.println("6. kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    crudCustomer();
                    break;
                case 2:
                    crudFilm();
                    break;
                case 3:
                    crudTheater();
                    break;
                case 4:
                    crudSeat();
                    break;
                case 5:
                    crudRating();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }

        }
    }



    public void menuUser(){
        while (true){
            System.out.println("==== Menu User ===");
            System.out.println("1. Isi data");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Tiket");
            System.out.println("4. Kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    customerController.add();
                    break;
                case 2:
                    System.out.println("Page dan pageSize untuk Daftar theater");
                    theaterController.listTheaters();
                    customerController.bookTicket();
                    break;
                case 3:
                    customerController.cancelTicket();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }
        }

    }


    public void crudCustomer(){

        while (true){
            System.out.println("==== Menu Crud Customer ===");
            System.out.println("1. Isi data");
            System.out.println("2. Update");
            System.out.println("3. Find All");
            System.out.println("4. FindByID");
            System.out.println("5. delete");
            System.out.println("6. kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    customerController.add();
                    break;
                case 2:
                    customerController.update();
                    break;
                case 3:
                    customerController.findAll();
                    break;
                case 4:
                    customerController.getById();
                    break;
                case 5:
                    customerController.delete();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }

        }

    }

    public void crudFilm(){
        while (true){
            System.out.println("==== Menu Crud Film ===");
            System.out.println("1. Isi data");
            System.out.println("2. Update");
            System.out.println("3. Find All");
            System.out.println("4. FindByID");
            System.out.println("5. delete");
            System.out.println("6. kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    ratingController.listRatings();
                    filmController.add();
                    break;
                case 2:
                    filmController.update();
                    break;
                case 3:
                    filmController.findAll();
                    break;
                case 4:
                    filmController.getById();
                    break;
                case 5:
                    filmController.delete();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }

        }
    }

    public void crudTheater(){
        while (true){
            System.out.println("==== Menu Crud Theater ===");
            System.out.println("1. Isi data");
            System.out.println("2. Update");
            System.out.println("3. Find All");
            System.out.println("4. FindByID");
            System.out.println("5. delete");
            System.out.println("6. kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    filmController.findAll();
                    theaterController.createTheater();
                    break;
                case 2:
                    theaterController.updateTheater();
                    break;
                case 3:
                    theaterController.listTheaters();
                    break;
                case 4:
                    theaterController.findTheaterById();
                    break;
                case 5:
                    theaterController.deleteTheater();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }

        }
    }

    public void crudSeat(){
        while (true){
            System.out.println("==== Menu Crud Seat ===");
            System.out.println("1. Isi data");
            System.out.println("2. Update");
            System.out.println("3. Find All");
            System.out.println("4. FindByID");
            System.out.println("5. delete");
            System.out.println("6. kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    theaterController.listTheaters();
                    seatController.createSeat();
                    break;
                case 2:
                    seatController.updateSeat();
                    break;
                case 3:
                    seatController.listSeats();
                    break;
                case 4:
                    seatController.findSeatById();
                    break;
                case 5:
                    seatController.deleteSeat();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }

        }
    }

    public void crudRating(){
        while (true){
            System.out.println("==== Menu Crud Rating ===");
            System.out.println("1. Isi data");
            System.out.println("2. Update");
            System.out.println("3. Find All");
            System.out.println("4. FindByID");
            System.out.println("5. delete");
            System.out.println("6. kembali");
            System.out.print("Pilihan: ");
            int pilih = scanner.nextInt();

            switch (pilih){
                case 1:
                    ratingController.createRating();
                    break;
                case 2:
                    ratingController.updateRating();
                    break;
                case 3:
                    ratingController.listRatings();
                    break;
                case 4:
                    ratingController.getRatingById();
                    break;
                case 5:
                    ratingController.deleteRating();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("input salah");
                    break;
            }

        }

    }
}
