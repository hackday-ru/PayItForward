package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.FavorType
import com.epamhackday.payitforward.model.UserFavor
import com.epamhackday.payitforward.repository.UserFavorRepository
import com.epamhackday.payitforward.util.FavorGenerator
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.annotation.PostConstruct

@Controller
@RequestMapping("/userfavor")
class UserFavorController {

    @Autowired
    private UserFavorRepository userFavorRepository

    @Autowired
    private FavorGenerator favorGenerator

    @PostConstruct
    void init() {
        favorGenerator.createUserFavors()
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    String delete(@RequestBody UserFavor userFavor) {
        def jsonBuilder = new JsonBuilder(userFavorRepository.delete(userFavor.id));
        return "UserFavor with id " + userFavor.user.email + " successfully deleted";
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

    @RequestMapping(value = "/type/{favorType}", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    String categoriesByType(@PathVariable FavorType favorType) {
        def userFavors = userFavorRepository.findByType(favorType)
        def categories = userFavors.collect { userFavor -> userFavor.favor.category }.toSet()
        new JsonBuilder(categories).toPrettyString()
    }

    @RequestMapping(value = "/category/{categoryName}/{favorType}", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    String favorsByCategoryAndType(@PathVariable String category,
                                   @PathVariable FavorType favorType) {
        def userFavors = userFavorRepository.findByType(favorType)
        def favors = userFavors.findAll { userFavor -> userFavor.favor.category.name == category }
                .collect { userFavor -> userFavor.favor }.toSet()
        new JsonBuilder(favors).toPrettyString()
    }
}
