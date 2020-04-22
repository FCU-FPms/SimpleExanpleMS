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

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();

        for (User user : userList) { //拿到所有使用者
            JSONObject entity = new JSONObject();
            int userId = user.getId();
            entity.put("phoneNumber", user.getPhoneNumber());
            entities.put(String.valueOf(userId), entity);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id) {

        User user = userDB.getUser(id);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(user != null) {
            JSONObject entity = new JSONObject();
            int userId = user.getId();
            entity.put("phoneNumber", user.getPhoneNumber());
            entities.put(String.valueOf(userId), entity);
        }

    @GetMapping("/name/{userName}")
    public ResponseEntity<Object> getUserByName(@PathVariable String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        User user = userDB.getUser(userName);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(user != null) {
            JSONObject entity = new JSONObject();
            int userId = user.getId();
            entity.put("name", user.getUserName());
            entity.put("phoneNumber", user.getPhoneNumber());

            entities.put(String.valueOf(userId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
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
