package net.cc.demo_springboot_mvc.web.controller;

import net.cc.demo_springboot_mvc.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @classname UsersController
 * @author: zhonghua.zhi
 * @date: 2019-03-14 18:02
 * @version: 1.0
 * @description: TODO
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    static Map<Integer, User> maps = Collections.synchronizedMap(new HashMap<Integer, User>());

    /**
     * 功能描述:
     * 查询所有用户
     *
     * @param
     * @return: java.util.List<net.cc.demo_springboot_mvc.domain.User>
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2019-03-14 18:22
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> list = new ArrayList<>(maps.values());
        return list;
    }

    /**
     * 功能描述:
     * 新增
     *
     * @param user
     * @return: java.lang.String
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2019-03-14 18:23
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        try {
            maps.put(user.getId(), user);
            return "SUCCESS";
        } catch (Exception e) {
            return "FAILED";
        }
    }

    /**
     * 功能描述:
     * 查询单个用户
     *
     * @param id
     * @return: net.cc.demo_springboot_mvc.domain.User
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2019-03-14 18:25
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return maps.get(id);
    }

    /**
     * 功能描述:
     * 修改单个用户
     *
     * @param id
     * @param user
     * @return: java.lang.String
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2019-03-14 18:28
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String modifyUser(@PathVariable int id, @ModelAttribute User user) {
        try {
            User u = maps.get(id);
            u.setAge(user.getAge());
            u.setName(user.getName());
            maps.put(id, u);
            return "SUCCESS";
        } catch (Exception e) {
            return "FAILED";
        }
    }

    /**
     * 功能描述:
     * 删除单个用户
     *
     * @param id
     * @return: java.lang.String
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2019-03-14 18:30
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id) {
        try {
            maps.remove(id);
            return "SUCCESS";
        } catch (Exception e) {
            return "FAILED";
        }
    }
}