package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.User
import com.epamhackday.payitforward.repository.UserRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Created by bu3apd on 4/16/2016.
 */
@Controller
@RequestMapping("/user")
class UserController {

    @Autowired
    UserRepository userRepository

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    String delete(@RequestBody User user) {
        def jsonBuilder = new JsonBuilder(userRepository.delete(user.email));
        return "User with id " + user.email + " successfully deleted";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String save(@RequestBody User user) {
        def jsonBuilder = new JsonBuilder(userRepository.save(user));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String get(@PathVariable String email) {
        def jsonBuilder = new JsonBuilder(userRepository.findByEmail(email));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String list() {
        def jsonBuilder = new JsonBuilder(userRepository.findAll())
        return jsonBuilder.toPrettyString()
    }
}
