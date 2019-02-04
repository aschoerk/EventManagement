package tn.esprit.b2.esprit1718b2eventmanagement.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.oneandone.iocunit.IocUnitRunner;
import com.oneandone.iocunit.analyzer.annotations.SutClasspaths;
import com.oneandone.iocunit.analyzer.annotations.TestClasses;
import com.oneandone.iocunit.ejb.persistence.TestPersistenceFactory;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bank;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserService;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServices;

/**
 * @author aschoerk
 */
@RunWith(IocUnitRunner.class)
@TestClasses({TestPersistenceFactory.class})
@SutClasspaths({User.class, UserService.class, Bank.class})
public class BankServiceTest {
    @Inject
    EntityManager entityManager;

    @Inject
    UserTransaction userTransaction;

    @Inject
    BankServices bankServices;

    @Test
    public void canInsertBank() throws Exception {

        userTransaction.begin();
        Bank bank = new Bank();
        bankServices.save(bank);
        userTransaction.commit();

        List<Bank> banks = bankServices.findAll();
        assertEquals(1, banks.size());


    }
}
