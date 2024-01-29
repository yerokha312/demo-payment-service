package com.yerokha.demo.paymentservice.service;

import com.yerokha.demo.paymentservice.dto.RegistrationRequest;
import com.yerokha.demo.paymentservice.entity.AppUser;
import com.yerokha.demo.paymentservice.entity.AppUserDetails;
import com.yerokha.demo.paymentservice.entity.Authority;
import com.yerokha.demo.paymentservice.repository.AppUserDetailsRepository;
import com.yerokha.demo.paymentservice.repository.AppUserRepository;
import com.yerokha.demo.paymentservice.repository.AuthorityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AppUserRepository appUserRepository;
    private final AppUserDetailsRepository appUserDetailsRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(AppUserRepository appUserRepository, AppUserDetailsRepository appUserDetailsRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserDetailsRepository = appUserDetailsRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEnabled(true);
        user = appUserRepository.save(user);

        Authority authority = new Authority();
        authority.setUsername(request.username());
        authority.setAuthority("ROLE_USER");
        authority.setAppUser(user);
        authorityRepository.save(authority);

        AppUserDetails details = new AppUserDetails();
        details.setFirstName(request.firstName());
        details.setLastName(request.lastName());
        details.setEmail(request.email());
        details.setDob(request.dob());
        details.setUser(user);

        appUserDetailsRepository.save(details);
    }
}
