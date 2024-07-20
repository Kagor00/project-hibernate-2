package com.javarush.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "movie", name = "payment")
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Short id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "rental_id")
    private Rental rental;

    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
