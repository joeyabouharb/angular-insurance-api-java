package com.joeyabouharb.insuranceapp;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import com.joeyabouharb.insuranceapp.infrastructure.models.Claim;
import com.joeyabouharb.insuranceapp.infrastructure.models.User;
import com.joeyabouharb.insuranceapp.infrastructure.repostories.ClaimRepository;
import com.joeyabouharb.insuranceapp.infrastructure.repostories.UserRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsuranceAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(InsuranceAppApplication.class, args);
  }

  @Bean
  ApplicationRunner init(ClaimRepository claimRepository, UserRepository userRepository) {
    return args -> {
      Stream.of("Car Accident at Cantebury Road", "Car Breakdown on M5").forEach(name -> {
        if (userRepository.count() == 0) {
        User user = new User(
          "user",
          "joeyabouharb@gmail.com",
          "$2b$12$Bs7WugBJaC9tpcq3BCtXpOEQW2zGe0ftuxzqO32I5VCivjSjaeDua",
          "USER");
        userRepository.save(user);
       }

        Claim claim = new Claim();
        claim.setClaim("Car");
        claim.setPolicy("Family");
        claim.setDetails(name);
        Date today = Calendar.getInstance().getTime();
        claim.setIncidentDate(today.toString());
        claim.setStatus("Completed");
        claim.setUser(userRepository.findById(1L).get());
        claimRepository.save(claim);

      });
      claimRepository.findAll().forEach(System.out::println);
    };
  }

}
