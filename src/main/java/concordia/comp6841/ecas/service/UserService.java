package concordia.comp6841.ecas.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import concordia.comp6841.ecas.dto.UserRegisterDto;
import concordia.comp6841.ecas.entity.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegisterDto registration);
}