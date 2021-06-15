package springdata.init;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.entity.Account;
import springdata.entity.User;
import springdata.exception.InvalidAccountOperationException;
import springdata.service.AccountService;
import springdata.service.UserService;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Ivan Petrov", 42);
        Account account1 = new Account(new BigDecimal(3500));
        user1 = userService.registerUser(user1);
        account1 = accountService.createUserAccount(user1, account1);
        try {
            accountService.withdrawMoney(new BigDecimal(2000), account1.getId());
            accountService.depositMoney(new BigDecimal(200), account1.getId());
        } catch (InvalidAccountOperationException ex) {
            System.out.println(ex.getMessage());
        }

        User user2 = new User("Pesho Ivanov", 53);
        Account account2 = new Account(new BigDecimal(1000));
        user1 = userService.registerUser(user2);
        account2 = accountService.createUserAccount(user2, account2);

        accountService.transferMoney(new BigDecimal(1000), account1.getId(), account2.getId());

        accountService.getAllAccounts().forEach(System.out::println);


    }
}
