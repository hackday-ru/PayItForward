package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Favor
import com.epamhackday.payitforward.repository.FavorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('/favor')
class FavorController {

    @Autowired
    private FavorRepository favorRepository

    @RequestMapping(value = '/{id}', method = RequestMethod.DELETE)
    def delete(@PathVariable String id) {
        favorRepository.delete(id)
    }

    @RequestMapping(method = RequestMethod.POST)
    def save(@RequestBody Favor favor) {
        favorRepository.save(favor)
    }

    @RequestMapping(method = RequestMethod.GET)
    def list() {
        favorRepository.findAll()
    }

    @RequestMapping(method = RequestMethod.GET, params = 'categoryId')
    def list(@RequestParam String categoryId) {
        favorRepository.findByCategoryId(categoryId)
    }
}
