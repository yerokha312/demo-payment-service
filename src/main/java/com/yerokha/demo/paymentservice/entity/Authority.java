package com.yerokha.demo.paymentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class Authority {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private AppUser appUser;

}
