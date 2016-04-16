package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Category
import com.epamhackday.payitforward.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('/category')
class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository

    @RequestMapping(value = '/{id}', method = RequestMethod.DELETE)
    def delete(@PathVariable String id) {
        categoryRepository.delete(id)
    }

    @RequestMapping(method = RequestMethod.POST)
    def save(@RequestBody Category category) {
        categoryRepository.save(category)
    }

    @RequestMapping(method = RequestMethod.GET)
    def list() {
        categoryRepository.findAll()
    }
}
