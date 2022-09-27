import model.User;
import service.UserService;
import util.Validator;

import java.time.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();
        Validator validator = new Validator() {
            @Override
            public boolean validate(User user) {
                return Period.between(user.getBirthday(), LocalDate.now()).getYear() >= 18;
            }
        };

        service.getValidUsers(validator);
    }
}
