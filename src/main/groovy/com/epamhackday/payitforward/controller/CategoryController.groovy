package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Category
import com.epamhackday.payitforward.repository.CategoryRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Created by bu3apd on 4/16/2016.
 */
@Controller
@RequestMapping("/category")
class CategoryController {

    @Autowired
    CategoryRepository categoryRepository

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    String delete(@RequestBody Category category) {
        def jsonBuilder = new JsonBuilder(categoryRepository.delete(category.name));
        return "Category with id " + category.name + " successfully deleted";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String save(@RequestBody Category category) {
        def jsonBuilder = new JsonBuilder(categoryRepository.save(category));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String get(@PathVariable String name) {
        def jsonBuilder = new JsonBuilder(categoryRepository.findByName(name));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String list() {
        def jsonBuilder = new JsonBuilder(categoryRepository.findAll())
        return jsonBuilder.toPrettyString()
    }

}
