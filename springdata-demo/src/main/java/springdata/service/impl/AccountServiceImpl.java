package springdata.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springdata.entity.Account;
import springdata.entity.User;
import springdata.exception.InvalidAccountOperationException;
import springdata.exception.NonExistingEntityException;
import springdata.repository.AccountRepository;
import springdata.service.AccountService;
import java.math.BigDecimal;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(String.format("Entity with ID: %s does not exist.", accountId)));
            account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(String.format("Entity with ID: %s does not exist.", accountId)));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InvalidAccountOperationException(
                    String.format("Exceeded withdrawal limit for account with ID: %s. The balance is less than %s.", accountId, amount));
        } else {
            account.setBalance(account.getBalance().subtract(amount));
        }
    }

    @Override
    public void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        depositMoney(amount, toAccountId);
        withdrawMoney(amount, fromAccountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
