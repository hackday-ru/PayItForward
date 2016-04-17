package repository

import com.epamhackday.payitforward.Application
import com.epamhackday.payitforward.model.*
import com.epamhackday.payitforward.repository.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
class RepositoriesIT {
    private static final USERS_FILE = "User.csv"
    private static final CATEGORIES_FILE = "Category.csv"
    private static final FAVORS_FILE = "Favor.csv"
    private static final USER_FAVORS_FILE = "UserFavor.csv"
    private static final DEALS_FILE = "Deal.csv"

    private static final USER_HEADERS = ["id", "name", "email", "password"] as String[]
    private static final CATEGORY_HEADERS = ["id", "name", "parent"] as String[]
    private static final FAVOR_HEADERS = ["id", "name", "category"] as String[]
    private static final USER_FAVOR_HEADERS = ["id", "user", "favor", "description", "type", "deleted"] as String[]
    private static final DEAL_HEADERS = ["id", "initiator", "acceptor", "status"] as String[]

    @Autowired
    private CategoryRepository categoryRepository

    @Autowired
    private DealRepository dealRepository

    @Autowired
    private FavorRepository favorRepository

    @Autowired
    private UserFavorRepository userFavorRepository

    @Autowired
    private UserRepository userRepository

    private List<User> users = new ArrayList<>()
    private List<Category> categories = new ArrayList<>()
    private List<Favor> favors = new ArrayList<>()
    private List<UserFavor> userFavors = new ArrayList<>()
    private List<Deal> deals = new ArrayList<>()

    @Test
    public void insertAllData() {
        dropAllCollections()
        insertUsers()
        insertCategories()
        insertFavors()
        insertUserFavors()
        insertDeals()
    }

    void dropAllCollections() {
        dealRepository.deleteAll()
        userFavorRepository.deleteAll()
        favorRepository.deleteAll()
        categoryRepository.deleteAll()
        userRepository.deleteAll()
    }

    void insertUsers() {
        CSVParser parser = loadFile(USERS_FILE, USER_HEADERS)
        for (CSVRecord csvRecord : parser) {
            users.add(
                    new User(csvRecord.get("id"),
                            csvRecord.get("name"),
                            csvRecord.get("email"),
                            csvRecord.get("password")))
        }
        users = userRepository.insert(users);
    }

    void insertCategories() {
        CSVParser parser = loadFile(CATEGORIES_FILE, CATEGORY_HEADERS)
        for (CSVRecord csvRecord : parser) {
            categories.add(
                    new Category(csvRecord.get("id"),
                            csvRecord.get("name"),
                            csvRecord.get("parent")))
        }
        categories = categoryRepository.insert(categories)
    }

    void insertFavors() {
        CSVParser parser = loadFile(FAVORS_FILE, FAVOR_HEADERS)
        for (CSVRecord csvRecord : parser) {
            favors.add(
                    new Favor(csvRecord.get("id"),
                            csvRecord.get("name"),
                            categories.get(Integer.valueOf(csvRecord.get("category"))-1)
                    )
            )
        }
        favors = favorRepository.insert(favors)
    }

    void insertUserFavors() {
        CSVParser parser = loadFile(USER_FAVORS_FILE, USER_FAVOR_HEADERS)
        for (CSVRecord csvRecord : parser) {
            userFavors.add(
                    new UserFavor(csvRecord.get("id"),
                            users.get(Integer.valueOf(csvRecord.get("user"))-1),
                            favors.get(Integer.valueOf(csvRecord.get("favor"))-1),
                            csvRecord.get("description"),
                            FavorType.valueOf(csvRecord.get("type")),
                            Boolean.valueOf(csvRecord.get("deleted")))
            )
        }
        userFavors = userFavorRepository.insert(userFavors)
    }

    void insertDeals() {
        CSVParser parser = loadFile(DEALS_FILE, DEAL_HEADERS)
        for (CSVRecord csvRecord : parser) {
            deals.add(
                    new Deal(csvRecord.get("id"),
                            userFavors.get(Integer.valueOf(csvRecord.get("initiator"))-1),
                            userFavors.get(Integer.valueOf(csvRecord.get("acceptor"))-1),
                            Status.valueOf(csvRecord.get("status")),
                            null
                    )
            )
        }
        deals = dealRepository.insert(deals)
    }

    static CSVParser loadFile(String filename, String... headers) {
        Reader reader = new FileReader(new ClassPathResource(filename).file)
        return CSVFormat.RFC4180.withHeader(headers).withSkipHeaderRecord(true).parse(reader);
    }
}
