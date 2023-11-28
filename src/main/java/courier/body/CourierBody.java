package courier.body;

public class CourierBody {
    private String login;
    private String password;
    private String firstName;
    private String id;


    public CourierBody(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierBody(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public CourierBody() {}
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId(){
        return id;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }
}
