package com.example.spacedream.Service;

import com.example.spacedream.Entities.Customer;
import com.example.spacedream.Exceptions.StillLoggedInException;
import com.example.spacedream.Repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    private CustomerService customerService;
    @MockBean
    private CustomerRepository customerRepositoryMock;
    private Customer customerToTest;
    private String userName;


    @Before
    public void setUp() {
        this.customerToTest = new Customer();
        this.userName = "Armstrong";
        this.customerService = new CustomerService(customerRepositoryMock);
        customerToTest.setUserName(this.userName);
        when(customerRepositoryMock.findById(userName)).thenReturn(Optional.of(customerToTest));
        when(customerRepositoryMock.existsById(userName)).thenReturn(true);
    }


    // Kann ich das überhaupt testen? Alles was ich doch machen kann ist ein findby und diese Verhalten müsste ich Mocken.
    // Damit habe ich rein gar nichts getestet.
    @Test
    public void testCreateCustomer() {
        // given

        // when
        customerService.createCustomer(customerToTest);

        // then
        verify(customerRepositoryMock).save(customerToTest);
    }


    @Test
    public void testGetCustomer() {
        // given

        // when
        Optional<Customer> result = customerService.getCustomer(userName);

        // then
        assertThat(result).isEqualTo(Optional.of(customerToTest));
    }


    @Test
    public void testValidCustomerAndPassword_rightPassword() {
        // given
        String password = "Artemis1";
        customerToTest.setPassword(password);

        // when
        boolean resultRightInput = customerService.validCustomerAndPassword(userName, password);

        // then
        assertThat(resultRightInput).isTrue();
    }

    @Test
    public void testValidCustomerAndPassword_falsePassword() {
        // given
        String password = "Artemis1";
        customerToTest.setPassword(password);

        // when
        boolean resultFalseInput = customerService.validCustomerAndPassword(userName, "123");

        // then
        assertThat(resultFalseInput).isFalse();
    }

    @Test
    public void testUpdateCustomer_customerIsSaved() {
        // given
        Customer notSavedCustomer = new Customer();

        // when
        customerService.updateCustomer(customerToTest);

        // then
        verify(customerRepositoryMock).save(customerToTest);
    }

    @Test
    public void testUpdateCustomer_customerIsNotSaved() {
        // given
        Customer notSavedCustomer = new Customer();
        String expectedMessage = "This instance doesn't exits!";

        // when
        Throwable thrown = catchThrowable(() -> {
            customerService.updateCustomer(notSavedCustomer);
        });

        // then
        verify(customerRepositoryMock, times(0)).save(customerToTest);
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class).hasMessageContaining(expectedMessage);
    }
}
