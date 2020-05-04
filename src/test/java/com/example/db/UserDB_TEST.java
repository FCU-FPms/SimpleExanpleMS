package com.example.db;
import com.example.data.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDB_TEST {

    @Test
    public void createUser_TEST(){ // Create new User
        UserDB userDB = UserDB.getInstance();
        assertTrue(userDB.createUser("unitTestUser", "0912345678"));
    }

    @Test
    public void getUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        User user = userDB.getUser("unitTestUser");
        assertEquals("0912345678", user.getPhoneNumber());
        assertEquals("unitTestUser", user.getUserName());
    }

    @Test
    public void deleteUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        assertTrue(userDB.deleteUser("unitTestUser"));
    }


}
