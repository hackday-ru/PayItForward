package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.User
import com.epamhackday.payitforward.model.UserFavor
import com.epamhackday.payitforward.repository.UserFavorRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by bu3apd on 4/16/2016.
 */
@Controller
@RequestMapping("/userfavor")
class UserFavorController {

    @Autowired
    UserFavorRepository userFavorRepository

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    String delete(@RequestBody UserFavor userFavor) {
        def jsonBuilder = new JsonBuilder(userFavorRepository.delete(userFavor.id));
        return "UserFavor with id " + user.email + " successfully deleted";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String save(@RequestBody UserFavor userFavor) {
        def jsonBuilder = new JsonBuilder(userFavorRepository.save(userFavor));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String get(@PathVariable Long id) {
        def jsonBuilder = new JsonBuilder(userFavorRepository.findById(id));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String list() {
        def jsonBuilder = new JsonBuilder(userFavorRepository.findAll())
        return jsonBuilder.toPrettyString()
    }


}
