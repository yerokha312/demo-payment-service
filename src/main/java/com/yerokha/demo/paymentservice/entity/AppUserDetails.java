package com.yerokha.demo.paymentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user_details")
@Data
public class AppUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_details_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "username")
    private AppUser user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToMany(mappedBy = "appUserDetails")
    private Set<Wallet> wallets;
}
