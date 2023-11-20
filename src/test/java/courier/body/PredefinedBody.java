package courier.body;

import java.util.Random;

public class PredefinedBody {

    private String login = "Ivan" + new Random().nextInt(100);
    private String password = "1234";

    public PredefinedBody() {}

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
