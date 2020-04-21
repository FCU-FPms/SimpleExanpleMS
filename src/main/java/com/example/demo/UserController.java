package com.example.demo;
import com.example.db.UserDB;
import com.example.data.User;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/users")
public class UserController {

    UserDB userDB = UserDB.getInstance();

    @GetMapping(value ="")
    public ResponseEntity<Object> getUsers(){

        List<User> userList = userDB.getUsers();

        List<JSONObject> entities = new ArrayList<JSONObject>();

        for (User user : userList) { //拿到所有使用者
            JSONObject entity = new JSONObject();
            entity.put("id", user.getId());
            entities.add(entity);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable int userId){

        User user = userDB.getUser(userId);

        JSONObject entity = new JSONObject();

        if(user != null) {
            entity.put("id", user.getId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<Object>(entity, headers, HttpStatus.OK);

    }

}
