package br.com.quattys.backend.domain.service;

import br.com.quattys.backend.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    String createUser(User user);

    void updateUser(String username, User updateUserInfos);

    List<User> getUserByUsername(String username);

    Page<User> getUserByUsernameAndPage(String username, Pageable pageable);

}
