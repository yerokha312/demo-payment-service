package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.entity.AppUserDetails;
import com.yerokha.demo.paymentservice.repository.AppUserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AppUserDetailsRepository appUserDetailsRepository;

    public UserService(AppUserDetailsRepository appUserDetailsRepository) {
        this.appUserDetailsRepository = appUserDetailsRepository;
    }

    public AppUserDetails getByUsername(String username) {
        return appUserDetailsRepository.findByUser_Username(username);
    }
}
