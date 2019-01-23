package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody UserDTO userDTO
    ){
        System.out.println(userDTO.getFirstName()+" "+userDTO.getLastName());
        userService.saveUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Витягнути з бази
    @GetMapping
    public ResponseEntity<?> getUsers(){
        List<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?>getUserById(@PathVariable("userId") Long id)
    {
      UserDTO user = userService.findUserById(id);
     // if(user == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(user,HttpStatus.OK);
}

    @PutMapping("{user1Id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("user1Id") Long id,
            @RequestBody UserDTO userToUpdate
    )
    {
        UserDTO user = userService.updateUser(id,userToUpdate);
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }



}
