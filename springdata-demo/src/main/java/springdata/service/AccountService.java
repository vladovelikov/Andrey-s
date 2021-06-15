package springdata.service;
import springdata.entity.Account;
import springdata.entity.User;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account createUserAccount(User user, Account account);
    void depositMoney(BigDecimal amount, Long id);
    void withdrawMoney(BigDecimal amount, Long id);
    void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId);
    List<Account> getAllAccounts();
}
