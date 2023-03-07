package org.livecodeJPA.Service;

import org.livecodeJPA.Model.*;
import org.livecodeJPA.Repository.customerRepo;
import org.livecodeJPA.Utils.getAge;
import org.livecodeJPA.Repository.seatRepo;

import java.util.List;

public class customerService implements IService<Customer> {
    private customerRepo customerRepo;
    private seatService seatService;
    private theaterService theaterService;

    public customerService(customerRepo customerRepo, seatService seatService, theaterService theaterService) {
        this.customerRepo = customerRepo;
        this.seatService = seatService;
        this.theaterService = theaterService;
    }

    @Override
    public void add(Customer customer) {
        customerRepo.add(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepo.update(customer);
    }

    @Override
    public List<Customer> findAll(Integer page, Integer pageSize) {
        List<Customer> customers = customerRepo.findAll(page,pageSize);
        return customers;
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = customerRepo.getById(id);
        return customer;
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepo.getById(id);
        customer.getSeats().forEach(seat -> seat.getCustomers().remove(customer));
        customerRepo.delete(id);
    }

    public void bookSeat(Long customerId, Long seatId) {
        Customer customer = customerRepo.getById(customerId);
        Seat seat = seatService.getById(seatId);
        Film film =seat.getTheater().getFilms();
        int age = getAge.age(customer.getBirthDate(), film.getShowDate());
        try {
            if (age <= film.getRating().getRating().getAgeLimit()){
                throw new IllegalArgumentException("Customer to young");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        customer.getSeats().add(seat);
        seat.getCustomers().add(customer);
        customerRepo.update(customer);
        seatService.update(seat);

        Theater theater = seat.getTheater();
        theater.setStock(theater.getStock() - 1);
        theaterService.update(theater);
    }

    public void cancelSeat(Long customerId, Long seatId) {
        Customer customer = customerRepo.getById(customerId);
        Seat seat = seatService.getById(seatId);
        customer.getSeats().remove(seat);
        seat.getCustomers().remove(customer);
        customerRepo.update(customer);
        seatService.update(seat);

        Theater theater = seat.getTheater();
        theater.setStock(theater.getStock() + 1);
        theaterService.update(theater);
    }
}
