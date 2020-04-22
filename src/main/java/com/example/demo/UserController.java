package com.example.demo;
import com.example.db.UserDB;
import com.example.data.User;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/users")
public class UserController {

    UserDB userDB = UserDB.getInstance();

    @GetMapping(value ="")
    public ResponseEntity<Object> getUsers(){

        List<User> userList = userDB.getUsers();

        Map<Integer, JSONObject> entities = new HashMap<Integer, JSONObject>();

        for (User user : userList) { //拿到所有使用者
            JSONObject entity = new JSONObject();
            int id = user.getId();
            entity.put("phoneNumber", user.getPhoneNumber());
            entities.put(id, entity);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable int userId) {

        User user = userDB.getUser(userId);

        Map<Integer, JSONObject> entities = new HashMap<Integer, JSONObject>();
        if(user != null) {
            JSONObject entity = new JSONObject();
            int id = user.getId();
            entity.put("phoneNumber", user.getPhoneNumber());
            entities.put(id, entity);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);

    }

    @PostMapping(value = "")
    public ResponseEntity<String> createUser(@RequestParam String name, @RequestParam String phone) {
        boolean is_success = userDB.createUser(name, phone);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>("is_success", headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        boolean is_success = userDB.deleteUser(name);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>("is_success", headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
