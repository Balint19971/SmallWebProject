package edu.bbte.bibliospringdata.service.impl;

import edu.bbte.bibliospringdata.model.User;
import edu.bbte.bibliospringdata.repository.UserRepository;
import edu.bbte.bibliospringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);

    }
}
