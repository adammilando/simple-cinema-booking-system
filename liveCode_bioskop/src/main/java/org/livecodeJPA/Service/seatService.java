package org.livecodeJPA.Service;
import org.livecodeJPA.Model.Seat;
import org.livecodeJPA.Model.Theater;
import org.livecodeJPA.Repository.seatRepo;

import java.util.List;

public class seatService implements IService<Seat>{
    private seatRepo seatRepo;
    private theaterService theaterService;
    private customerService customerService;

    public seatService(seatRepo seatRepo, theaterService theaterService, customerService customerService) {
        this.seatRepo = seatRepo;
        this.theaterService = theaterService;
        this.customerService = customerService;
    }

    public void insert(Seat seat, Long theaterId){
        Theater theater = theaterService.getById(theaterId);
        seat.setTheater(theater);
        seatRepo.add(seat);
    }

    @Override
    public void add(Seat seat) {seatRepo.add(seat);
    }

    @Override
    public void update(Seat seat) {
        Seat existingSeat = seatRepo.getById(seat.getId());
        existingSeat.setSeatNumber(seat.getSeatNumber());
        existingSeat.setCustomers(seat.getCustomers());
        seatRepo.update(existingSeat);
    }

    @Override
    public List<Seat> findAll(Integer page, Integer pageSize) {
        List<Seat> seats = seatRepo.findAll(page, pageSize);
        return seats;
    }

    @Override
    public Seat getById(Long id) {
        Seat seat = seatRepo.getById(id);
        return seat;
    }

    @Override
    public void delete(Long id) {
        Seat seat = seatRepo.getById(id);
        seat.getCustomers().forEach(customer -> customer.getSeats().remove(seat));
        seat.setTheater(null);
        seatRepo.delete(id);
    }
}
