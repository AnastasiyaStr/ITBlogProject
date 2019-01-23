package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.UserDetailsDTO;
import home.ua.springbootapp.entity.UserDetailsEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.service.UserDetailsService;
import home.ua.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/userDetails")
public class UserDetailsController  {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createUserDetails(
            @RequestBody UserDetailsDTO userDetails
    ){
        //System.out.println(userEntity.getFirstName()+" "+userEntity.getLastName());
        userDetailsService.saveUserDetails(userDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Витягнути з бази
    @GetMapping
    public ResponseEntity<?> getUserDetails(){
        List<UserDetailsDTO> users = userDetailsService.findAllUserDetails();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?>getUserById(@PathVariable("userId") Long id)
    {
        UserDetailsDTO userDetails = userDetailsService.findUserDetailsById(id);
        if(userDetails == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

    @PutMapping("{user1Id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("user1Id") Long id,
            @RequestBody UserDetailsDTO userDetailsToUpdate
    )
    {
        UserDetailsDTO userDetails = userDetailsService.updateUserDetails(id,userDetailsToUpdate);
        if(userDetails == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }


}
