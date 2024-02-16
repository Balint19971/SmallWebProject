package edu.bbte.bibliospringdata.api;

import edu.bbte.bibliospringdata.api.exeption.NotFoundException;
import edu.bbte.bibliospringdata.assembler.UserAssembler;
import edu.bbte.bibliospringdata.dto.incoming.UserInDTO;
import edu.bbte.bibliospringdata.dto.outcoming.UserOutDTO;
import edu.bbte.bibliospringdata.model.User;
import edu.bbte.bibliospringdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserOutDTO> getAll(){
        List<UserOutDTO> userOutDTOList = new ArrayList<>();
        List<User> userList = userService.getAll();

        for (User user: userList) {
            userOutDTOList.add(userAssembler.userToUserOutDTO(user));
        }

        return userOutDTOList;
    }

    @GetMapping("/users/{id}")
    public UserOutDTO getById(@PathVariable(value = "id") Long id){

        User user = userService.getById(id);
        if (user == null){
            throw new NotFoundException(User.class, id);
        }
        return userAssembler.userToUserOutDTO(user);
    }

    @PostMapping
    public ResponseEntity<UserOutDTO> registerUser(@RequestBody UserInDTO userInDTO){
        UserOutDTO userOutDTO = userAssembler.userToUserOutDTO(userService.create(userAssembler.userInDTOToUser(userInDTO)));
        URI uri = URI.create("/users/" + userOutDTO.getId());
        return ResponseEntity.created(uri).body(userOutDTO);
    }
}
