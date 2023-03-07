package org.livecodeJPA.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_seat")
public class Seat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "seat_number",nullable = false, unique = true)
//    private String seatNumber;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "theater_id")
//    private Theater theater;
//    @ManyToMany(mappedBy = "seats")
//    @JoinColumn
//    private List<Customer> customers = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "seat_number")
    private String seatNumber;
    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    private Theater theater;
    @ManyToMany(mappedBy = "seats")
    @Column(name = "Customer_id", nullable = false)
    private List<Customer> customers = new ArrayList<>();

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatNumber='" + seatNumber + '\'' +
                ", theater=" + theater +
                '}';
    }
}
