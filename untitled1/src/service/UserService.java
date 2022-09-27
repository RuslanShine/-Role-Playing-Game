package service;

import model.User;
import util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> data = new ArrayList<>();

    {
        data.add(new User("Egor","Ivanov", LocalDate.now().minusYears(20)));
        data.add(new User("Ivan","Ivanov", LocalDate.now().minusYears(10)));
        data.add(new User("Egor","Olegov", LocalDate.now().minusYears(40)));
        data.add(new User("Oleg","Ivanov", LocalDate.now().minusYears(12)));
        data.add(new User("Sergey","Ivanov", LocalDate.now().minusYears(25)));
    }

    public List<User> getData() {
        return data;
    }

    public List<User> getValidUsers(Validator validator) {
        List<User> validUsers = new ArrayList<>();
        for (User user: data){
            if (validator.validate(user)){
                validUsers.add(user);
            }
        }
        return validUsers;

    }
}
