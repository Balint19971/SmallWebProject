package edu.bbte.bibliospringdata.controller;

import edu.bbte.bibliospringdata.assembler.UserAssembler;
import edu.bbte.bibliospringdata.dto.outcoming.UserOutDTO;
import edu.bbte.bibliospringdata.model.User;
import edu.bbte.bibliospringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @GetMapping
    public String listUsers(Model model){
        List<User> users = userService.getAll();
        List<UserOutDTO> userOutDTOList = new ArrayList<>();
        for (User user: users) {
            userOutDTOList.add(userAssembler.userToUserOutDTO(user));
        }
        model.addAttribute("users", userOutDTOList);
        return "users-page";
    }

}
