package br.com.mecontrata.management.client.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginNotificationService {

    void createLogin(String email, String password);

}
