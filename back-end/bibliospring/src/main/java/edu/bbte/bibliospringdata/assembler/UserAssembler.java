package edu.bbte.bibliospringdata.assembler;

import edu.bbte.bibliospringdata.dto.incoming.UserInDTO;
import edu.bbte.bibliospringdata.dto.outcoming.UserOutDTO;
import edu.bbte.bibliospringdata.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public User userInDTOToUser(UserInDTO userInDTO){
        User user = new User(userInDTO.getUsername(), userInDTO.getPassword());
        return user;
    }

    public UserOutDTO userToUserOutDTO(User user){
        UserOutDTO userOutDTO = new UserOutDTO();
        userOutDTO.setUuid(user.getUuid());
        userOutDTO.setId(user.getId());
        userOutDTO.setUsername(user.getUsername());
        return userOutDTO;
    }
}
