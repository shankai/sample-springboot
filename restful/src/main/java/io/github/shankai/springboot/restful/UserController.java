package io.github.shankai.springboot.restful;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
public class UserController {

    private static List<User> mockUsers = new ArrayList<>();

    static {
        User u1 = new User("1", "user1", 10);
        User u2 = new User("2", "user2", 20);

        mockUsers.add(u1);
        mockUsers.add(u2);
    }

    @RequestMapping("/ctrl/name")
    public String getControllerName() {
        return "UserController";
    }

    /**
     * 搜索用户
     * 
     * @param name
     * @return
     */
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "name", required = false) String name) {
        if (name == null || "".equals(name)) {
            return new ResponseEntity<List<User>>(mockUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<User>>(
                    mockUsers.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList()),
                    HttpStatus.OK);
        }
    }

    /**
     * 获取指定用户
     * 
     * @param id
     * @return
     */
    @RequestMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        User user = mockUsers.stream().filter((User u) -> id.equals(u.getId())).findAny().orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * 创建新用户示例
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        mockUsers.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * 删除用户
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        boolean result = mockUsers.removeIf(u -> u.getId().equals(id));
        return new ResponseEntity<>("Delete: " + result, HttpStatus.OK);
    }

    /**
     * 更新用户
     * 
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        User oldUser = mockUsers.stream().filter((User u) -> u.getId().equals(id)).findAny().orElse(null);
        if (oldUser != null) {
            Collections.replaceAll(mockUsers, oldUser, user);
        }
        return new ResponseEntity<>("Update Num: " + (oldUser == null ? 0 : 1), HttpStatus.OK);
    }

}