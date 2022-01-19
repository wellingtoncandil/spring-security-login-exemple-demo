package com.example.demo.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationControler {
	
	@Autowired
	RegistrationService registrationService;

	@PostMapping
	public String register (@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	    //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(request.getEmail()).toUri();
		//return ResponseEntity.created(uri).body(request);
	}
	
	@GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
	
	
}
