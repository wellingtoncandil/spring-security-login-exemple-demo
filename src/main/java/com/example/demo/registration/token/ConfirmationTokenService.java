package com.example.demo.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

	@Autowired
	ConfirmationTokenRepository repository;

	public void saveConfirmationToken(ConfirmationToken token) {
		repository.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
		return repository.findByToken(token);
	}

	public int setConfirmedAt(String token) {
		return repository.updateConfirmedAt(token, LocalDateTime.now());
	}

}
