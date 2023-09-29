package com.springBoot.springboot.controller;

import com.springBoot.springboot.Bean.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("User")
public class UserController {
@GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"Kenan","Kenanov"));
        userList.add(new User(2,"Resid","Residov"));
        userList.add(new User(3,"Elmira","Bekirova"));

        return   ResponseEntity.ok().header("user-header","resid").body(userList);

    }

    @GetMapping
            public ResponseEntity<User>  getUser() {
        User user = new User(1,
                "Kenan",
                "Kenanov");
//        return new ResponseEntity<>(user, HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header","kenan")
                .body(user);

    }


    @GetMapping("/{userid}/{name}/{lastName}")
    public ResponseEntity<User> getUser(@PathVariable("userid") Integer userid,
                        @PathVariable("name") String name,
                        @PathVariable("lastName")String lastName){

    User user = new User(userid,name,lastName);
    return ResponseEntity.ok(user);
    }


//    http://localhost:8080/user/query?id=1
    @GetMapping("/query")
    public ResponseEntity<User> userRequestVariable(@RequestParam int userId,
                                    @RequestParam String name,
                                    @RequestParam String lastName
    ){
    User user  = new User(userId,name,lastName);
    return ResponseEntity.ok(user);

    }

@DeleteMapping("/{id}/delete")
@ResponseStatus(HttpStatus.ACCEPTED)
public ResponseEntity<String> deleteUser(@PathVariable("id")  int userId){
    System.out.println(userId);
    return  ResponseEntity.ok( "Users deleted successfully!");

}
@PutMapping("{id}/update")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") int userId){

    System.out.println(user.getName());
    System.out.println(user.getLastName());
    return ResponseEntity.ok(user);

}
@PostMapping("/created")
    public ResponseEntity<User> addUser(@RequestBody User user){
    System.out.println(user.getUserId());
    System.out.println(user.getName());
    System.out.println(user.getLastName());
    return new ResponseEntity<>(user,HttpStatus.CREATED);
//    return ResponseEntity.created(user);
//   User user = new User(id,name,lastName);
//return ResponseEntity.ok().header("custom-user","Any user").body(user);
}


}
