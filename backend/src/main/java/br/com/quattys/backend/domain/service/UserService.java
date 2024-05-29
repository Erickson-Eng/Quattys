package br.com.quattys.backend.domain.service;

import br.com.quattys.backend.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    String createUser(User user);

    void updateUser(String username, User updateUserInfos);

    User getUserByUsername(String username);

    Page<User> getUserByUsernameAndPage(String username, Pageable pageable);

}
