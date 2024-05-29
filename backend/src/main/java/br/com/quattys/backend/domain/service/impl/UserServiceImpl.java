package br.com.quattys.backend.domain.service.impl;

import br.com.quattys.backend.domain.builders.UserBuilder;
import br.com.quattys.backend.domain.entity.User;
import br.com.quattys.backend.domain.service.UserService;
import br.com.quattys.backend.infrastructure.exceptions.UserAlreadyExistsException;
import br.com.quattys.backend.infrastructure.persitence.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserBuilder {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String createUser(User user) {
        var userBuilt = buildUser(user);
        var userEntity = userRepository.save(userBuilt);
        return userEntity.getUserId().toString();
    }

    @Override
    public void updateUser(String username, User updateUserInfos) {
        var optionalUser = userRepository.findByUsername(username);
        optionalUser.ifPresent(entity -> {
            BeanUtils.copyProperties(updateUserInfos, entity, "userId", "roles");
            userRepository.save(entity);
        });
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    public Page<User> getUserByUsernameAndPage(String username, Pageable pageable) {
        return userRepository.findByUsernameContainingIgnoreCase(username, pageable);
    }

    private Boolean verifyIfExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    private Boolean verifyIfExistsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User buildUser(User userRequest) {
        var userExists = verifyIfExistsByUsername(userRequest.getUsername()) || verifyIfExistsByEmail(userRequest.getEmail());
        if (userExists){
            throw new UserAlreadyExistsException("Username or email is already being used");
        }
        var valid = true;
        var encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);
        userRequest.setAccountNonExpired(valid);
        userRequest.setAccountNonLocked(valid);
        userRequest.setCredentialsNonExpired(valid);
        userRequest.setEnabled(valid);
        return userRequest;
    }
}
