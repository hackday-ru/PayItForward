package com.epamhackday.payitforward.util

import com.epamhackday.payitforward.model.Favor
import com.epamhackday.payitforward.model.FavorType
import com.epamhackday.payitforward.model.User
import com.epamhackday.payitforward.model.UserFavor
import com.epamhackday.payitforward.repository.CategoryRepository
import com.epamhackday.payitforward.repository.FavorRepository
import com.epamhackday.payitforward.repository.UserFavorRepository
import com.epamhackday.payitforward.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
class FavorGenerator {

    @Autowired
    private CategoryRepository categoryRepository

    @Autowired
    private FavorRepository favorRepository

    @Autowired
    private UserRepository userRepository

    @Autowired
    private UserFavorRepository userFavorRepository

    void createUserFavors() {
        def user = new User(name: 'Санек')
        userRepository.save(user)
        def categories = [new com.epamhackday.payitforward.model.Category(name: 'Фото'),
                          new com.epamhackday.payitforward.model.Category(name: 'Секас'),
                          new com.epamhackday.payitforward.model.Category(name: 'Клининг'),
                          new com.epamhackday.payitforward.model.Category(name: 'Другое')]
        categories.each { categoryRepository.save(it) }
        def favors = [new Favor(name: 'Съемка в USA', category: categories[0]),
                      new Favor(name: 'Съемка в EU', category: categories[0]),
                      new Favor(name: 'На выезде', category: categories[1]),
                      new Favor(name: 'Апартаменты', category: categories[1]),
                      new Favor(name: 'Уборка квартиры под ключ', category: categories[2]),
                      new Favor(name: 'Мытье окон и лоджий', category: categories[2])]
        favors.each { favorRepository.save(it) }
        def userFavors = [new UserFavor(user: user, favor: favors[0], type: FavorType.CAN),
                          new UserFavor(user: user, favor: favors[1], type: FavorType.CAN),
                          new UserFavor(user: user, favor: favors[2], type: FavorType.WANT),
                          new UserFavor(user: user, favor: favors[3], type: FavorType.CAN),
                          new UserFavor(user: user, favor: favors[4], type: FavorType.WANT),
                          new UserFavor(user: user, favor: favors[5], type: FavorType.WANT)]
        userFavors.each { userFavorRepository.save(it)}
    }

}
