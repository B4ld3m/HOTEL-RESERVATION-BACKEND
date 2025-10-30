package com.baldeon.hotelreservation.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cus_id_in")
    private int id;

    @Column(name = "cus_name", nullable = false)
    private String name;
    @Column(name = "cus_last_name_vc", nullable = false)
    private String lastname;

    @Column(name = "cus_dni_vc", nullable = false)
    private int edad;

    @Column(name = "cus_email", nullable = false)
    private String email;

    @Column(name = "cus_regis_date_dt", nullable = false)
    private LocalDate registerDate;

    @Column(name = "cus_upda_date_dt")
    private LocalDate updateDate;

    @OneToOne
    @JoinColumn(name = "user_id_in", referencedColumnName = "user_id")
    private User user;
}