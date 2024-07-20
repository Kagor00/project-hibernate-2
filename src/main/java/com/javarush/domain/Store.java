package com.javarush.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "movie", name = "store")
@Getter
@Setter
@ToString
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte id;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
