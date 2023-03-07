package org.livecodeJPA.Controller;

import org.livecodeJPA.Model.Customer;
import org.livecodeJPA.Service.customerService;

import java.util.List;
import java.util.Scanner;
import org.livecodeJPA.Utils.generateDate;

public class customerController implements IController{

    Scanner scanner =new Scanner(System.in);
    private customerService customerService;

    public customerController(customerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void add() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer birthdate (yyyy-MM-dd): ");
        String birthdate = scanner.nextLine();


        Customer customer = new Customer();
        customer.setName(name);
        customer.setBirthDate(generateDate.generate(birthdate));

        customerService.add(customer);
        System.out.println("Customer created successfully!");
    }

    @Override
    public void update() {
        System.out.print("Enter customer ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Customer customer = customerService.getById(id);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter new customer name (leave blank if no changes): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) {
            customer.setName(name);
        }

        System.out.print("Enter new customer birthdate (yyyy-MM-dd, leave blank if no changes): ");
        String birthdate = scanner.nextLine();
        if (!birthdate.isBlank()) {
            customer.setBirthDate(generateDate.generate(birthdate));
        }

        System.out.println("Masukkan seat Number customer: ");


        customerService.update(customer);
        System.out.println("Customer updated successfully!");
    }

    @Override
    public void findAll() {
        System.out.println("Masukkan page: ");
        Integer page = scanner.nextInt();
        System.out.println("masukkan pageSize: ");
        Integer pageSize = scanner.nextInt();
        List<Customer> customers = customerService.findAll(page, pageSize);

        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return;
        }

        System.out.println("Customers: ");
        for (Customer customer : customers) {
            System.out.printf("%d. %s (Birthdate: %s)\n", customer.getId(), customer.getName(), customer.getBirthDate());
        }
    }

    @Override
    public void getById() {
        System.out.print("Enter customer ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Customer customer = customerService.getById(id);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.printf("Customer ID: %d\n", customer.getId());
        System.out.printf("Name: %s\n", customer.getName());
        System.out.printf("Birthdate: %s\n", customer.getBirthDate());
    }

    @Override
    public void delete() {
        System.out.print("Enter customer ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        customerService.delete(id);
        System.out.println("Customer deleted successfully!");
    }

    public void bookTicket(){
        System.out.print("Masukkan User Id: ");
        Long userid= scanner.nextLong();
        System.out.print("Masukkan seat Id: ");
        Long seatid = scanner.nextLong();
        customerService.bookSeat(userid,seatid);
    }

    public void cancelTicket(){
        System.out.println("Masukkan User Id: ");
        Long userid= scanner.nextLong();
        System.out.println("Masukkan seat Id: ");
        Long seatid = scanner.nextLong();
        customerService.cancelSeat(userid,seatid);
    }
}
