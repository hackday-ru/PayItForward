package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.FavorType
import com.epamhackday.payitforward.model.User
import com.epamhackday.payitforward.model.UserFavor
import com.epamhackday.payitforward.model.request.AddUserFavor
import com.epamhackday.payitforward.repository.UserFavorRepository
import com.epamhackday.payitforward.util.FavorGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.annotation.PostConstruct
import javax.validation.Valid

/**
 * Created by bu3apd on 4/16/2016.
 */
@RestController
@RequestMapping("/user/favor")
class UserFavorController {

    @Autowired
    private UserFavorRepository userFavorRepository

    @Autowired
    private FavorGenerator favorGenerator

    @PostConstruct
    void init() {
//        favorGenerator.createUserFavors()
    }

    @RequestMapping(value = '/{id}', method = RequestMethod.DELETE)
    void delete(@PathVariable String id) {
        userFavorRepository.delete(id)
    }

    @RequestMapping(method = RequestMethod.POST)
    String save(@Valid @RequestBody AddUserFavor userFavor) {
        // TODO current user
        final currentUser = new User(id: '57128f83e4b065a8c4d71236')
        userFavorRepository.save(new UserFavor(
                user: currentUser,
                favor: userFavor.favor,
                type: userFavor.type,
                description: userFavor.description
        ))
    }

// TODO implement findById if needed
//    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
//    String get(@PathVariable Long id) {
//        def jsonBuilder = new JsonBuilder(userFavorRepository.findById(id));
//        return jsonBuilder.toPrettyString()
//    }

    @RequestMapping(method = RequestMethod.GET)
    def list() {
        // TODO user findByUser here
        userFavorRepository.findAll()
    }

    @RequestMapping(value = "/type/{favorType}", method = RequestMethod.GET)
    @ResponseBody
    def categoriesByType(@PathVariable FavorType favorType) {
        def userFavors = userFavorRepository.findByType(favorType)
        userFavors.collect { userFavor -> userFavor.favor.category }.toSet()
    }

    @RequestMapping(value = "/category/{categoryName}/{favorType}", method = RequestMethod.GET)
    @ResponseBody
    def favorsByCategoryAndType(@PathVariable String category,
                                @PathVariable FavorType favorType) {
        def userFavors = userFavorRepository.findByType(favorType)
        userFavors.findAll { userFavor -> userFavor.favor.category.name == category }
                .collect { userFavor -> userFavor.favor }.toSet()
    }

    @RequestMapping(value = "/{favorType}", method = RequestMethod.GET)
    def favorsByUserAndType(@PathVariable FavorType favorType) {
        final currentUser = new User(id: '57128f83e4b065a8c4d71236')
        userFavorRepository.findByUserAndType(currentUser, favorType)
    }

}
