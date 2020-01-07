package ru.nsu.fit.endpoint.manager;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import ru.nsu.fit.endpoint.Validate.EmailValidator;
import ru.nsu.fit.endpoint.database.IDBService;
import ru.nsu.fit.endpoint.database.data.CustomerPojo;

import java.util.List;
import java.util.UUID;

public class CustomerManager extends ParentManager {
    public CustomerManager(IDBService dbService, Logger flowLog) {
        super(dbService, flowLog);
    }
    private EmailValidator emailValidator = new EmailValidator();;
    /**
     * Метод создает новый объект типа Customer. Ограничения:
     * Аргумент 'customerData' - не null;
     * firstName - нет пробелов, длина от 2 до 12 символов включительно, начинается с заглавной буквы, остальные символы строчные, нет цифр и других символов;
     * lastName - нет пробелов, длина от 2 до 12 символов включительно, начинается с заглавной буквы, остальные символы строчные, нет цифр и других символов;
     * login - указывается в виде email, проверить email на корректность, проверить что нет customer с таким же email;
     * pass - длина от 6 до 12 символов включительно, не должен быть простым (123qwe или 1q2w3e), не должен содержать части login, firstName, lastName
     * money - должно быть равно 0.
     */
    public CustomerPojo createCustomer(CustomerPojo customer) {
        String firstName = customer.firstName;
        String lastName = customer.lastName;
        String email = customer.login;
        String password = customer.pass;


        Validate.notNull(customer, "Argument 'customerData' is null.");

        //Password
        Validate.notNull(password, "Password is null");
        Validate.isTrue(password.length() >= 6 && password.length() < 13, "Password's length should be more or equal 6 symbols and less or equal 12 symbols.");
        Validate.isTrue(!password.equalsIgnoreCase("123qwe"), "Password is easy.");
        Validate.isTrue(!password.contains(firstName), "Password contains part of the FirstName.");
        Validate.isTrue(!password.contains(lastName), "Password contains part of the LastName.");
        Validate.isTrue(!password.contains(email), "Password contains part of the Email.");

        //FirstName
        Validate.isTrue(!firstName.contains(" "), "FirstName have space.");
        Validate.isTrue(firstName.length() >= 2 && firstName.length() < 13, "FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        Validate.isTrue(Character.isUpperCase(firstName.charAt(0)), "The first character FirstName is lowercase.");
        for(int i = 1, n = firstName.length() ; i < n ; i++) {
            Validate.isTrue(firstName.charAt(i) >= 'a' && firstName.charAt(i) <= 'z', "Two or more characters FirstName is uppercase or not letter.");
        }

        //LastName
        Validate.isTrue(!lastName.contains(" "), "LastName have space.");
        Validate.isTrue(lastName.length() >= 2 && lastName.length() < 13, "LastName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        Validate.isTrue(Character.isUpperCase(lastName.charAt(0)), "The first character LastName is lowercase.");
        for(int i = 1, n = lastName.length() ; i < n ; i++) {
            Validate.isTrue(lastName.charAt(i) >= 'a' && lastName.charAt(i) <= 'z', "Two or more characters LastName is uppercase or not letter.");
        }

        //Email
        //System.out.println(dbService.getCustomers().size());
        Validate.isTrue(emailValidator.validate(email), "Email is not correct.");

        //Balance
        Validate.isTrue(customer.balance == 0,"Balance is not null." );

        // TODO: необходимо дописать дополнительные проверки

        return dbService.createCustomer(customer);
    }

    /**
     * Метод возвращает список объектов типа customer.
     */
    public List<CustomerPojo> getCustomers() {
        return dbService.getCustomers();
    }


    /**
     * Метод обновляет объект типа Customer.
     * Можно обновить только firstName и lastName.
     */
    public CustomerPojo updateCustomer(CustomerPojo customer) {
        throw new NotImplementedException("Please implement the method.");
    }

    public void removeCustomer(UUID id) {
        throw new NotImplementedException("Please implement the method.");
    }

    /**
     * Метод добавляет к текущему баласу amount.
     * amount - должен быть строго больше нуля.
     */
    public CustomerPojo topUpBalance(UUID customerId, int amount) {
        throw new NotImplementedException("Please implement the method.");
    }
}
