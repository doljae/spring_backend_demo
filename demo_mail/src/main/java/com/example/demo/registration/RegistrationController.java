package com.example.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    // localhost:8081/api/v1/registration
	/*
	{
		"firstName": "name1",
		"lastName": "name2",
		"email": "aa@gmail.com",
		"password": "password"
	}
	*/
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    // localhost:8081/api/v1/registration/confirm?token=APP_USER_TOKEN
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}

