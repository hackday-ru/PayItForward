package repository

import com.epamhackday.payitforward.model.*
import com.epamhackday.payitforward.repository.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class RepositoriesIT {
    private static final USERS_FILE = ""
    private static final CATEGORIES_FILE = ""
    private static final FAVORS_FILE = ""
    private static final USER_FAVORS_FILE = ""
    private static final DEALS_FILE = ""

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
        insertUsers()
        insertCategories()
        insertFavors()
        insertUserFavors()
        insertDeals()
    }

    void insertUsers() {
        CSVParser parser = loadFile(USERS_FILE)
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
        CSVParser parser = loadFile(CATEGORIES_FILE)
        for (CSVRecord csvRecord : parser) {
            categories.add(
                    new Category(csvRecord.get("id"),
                            csvRecord.get("name"),
                            csvRecord.get("parent")))
        }
        categories = categoryRepository.insert(categories)
    }

    void insertFavors() {
        CSVParser parser = loadFile(FAVORS_FILE)
        for (CSVRecord csvRecord : parser) {
            favors.add(
                    new Favor(csvRecord.get("id"),
                            csvRecord.get("name"),
                            categories.get(Integer.valueOf(csvRecord.get("category")))
                    )
            )
        }
        favors = favorRepository.insert(favors)
    }

    void insertUserFavors() {
        CSVParser parser = loadFile(USER_FAVORS_FILE)
        for (CSVRecord csvRecord : parser) {
            userFavors.add(
                    new UserFavor(csvRecord.get("id"),
                            users.get(Integer.valueOf(csvRecord.get("user"))),
                            favors.get(Integer.valueOf(csvRecord.get("favor"))),
                            csvRecord.get("description"),
                            FavorType.valueOf(csvRecord.get("type")),
                            Boolean.valueOf(csvRecord.get("deleted")))
            )
        }
        userFavors = userFavorRepository.insert(userFavors)
    }

    void insertDeals() {
        CSVParser parser = loadFile(DEALS_FILE)
        for (CSVRecord csvRecord : parser) {
            deals.add(
                    new Deal(csvRecord.get("id"),
                            userFavors.get(Integer.valueOf(csvRecord.get("initiator"))),
                            userFavors.get(Integer.valueOf(csvRecord.get("acceptor"))),
                            Status.valueOf(csvRecord.get("status")),
                            null
                    )
            )
        }
        deals = dealRepository.insert(deals)
    }

    static CSVParser loadFile(String filename) {
        File csvData = new File(filename)
        return CSVParser.parse(csvData, CSVFormat.EXCEL)
    }
}
