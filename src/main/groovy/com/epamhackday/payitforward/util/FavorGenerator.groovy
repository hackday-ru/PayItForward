package com.epamhackday.payitforward.util

import com.epamhackday.payitforward.model.*
import com.epamhackday.payitforward.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

    @Autowired
    private DealRepository dealRepository

    void createUserFavors() {
        def user = new User(name: 'Санек')
        userRepository.save(user)
        def categories = [new Category(name: 'Фото'),
                          new Category(name: 'Секас'),
                          new Category(name: 'Клининг'),
                          new Category(name: 'Другое')]
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
        userFavors.each { userFavorRepository.save(it) }
    }

    void createDeals() {
        def user = new User(name: 'Mike')
        def user2 = new User(name: 'Анюта')

        user = userRepository.save(user)
        user2 = userRepository.save(user2)
        println user

        def categories = [new Category(name: 'Спорт'),
                          new Category(name: 'Поездки')]

        categories = categories.collect { categoryRepository.save(it) }
        def favors = [new Favor(name: 'Тренировка', category: categories[0]),
                      new Favor(name: 'Поездка', category: categories[1])]

        favors = favors.collect { favorRepository.save(it) }
        def userFavors = [new UserFavor(user: user, favor: favors[0], type: FavorType.CAN),
                          new UserFavor(user: user, favor: favors[1], type: FavorType.WANT),
                          new UserFavor(user: user2, favor: favors[1], type: FavorType.CAN),
                          new UserFavor(user: user2, favor: favors[0], type: FavorType.WANT)]
        userFavors = userFavors.collect { userFavorRepository.save(it) }

        def deals = [new Deal(initiator: userFavors[0], acceptor: userFavors[2], status: Status.PENDING),
                     new Deal(initiator: userFavors[0], acceptor: userFavors[2], status: Status.ACCEPTED),
                     new Deal(initiator: userFavors[2], acceptor: userFavors[0], status: Status.REJECTED),
                     new Deal(initiator: userFavors[2], acceptor: userFavors[0], status: Status.PENDING)]

        dealRepository.save(deals)
    }

}
