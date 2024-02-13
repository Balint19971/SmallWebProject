package edu.bbte.bibliospringdata.service;

import edu.bbte.bibliospringdata.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    boolean deleteById(Long id);

    List<User> getAll();

    User getById(Long id);


}
