package service.service;

import service.model.entity.User;

public interface JwtService {
    String generateToken(User user);

    String extractUsername(String token);
}
