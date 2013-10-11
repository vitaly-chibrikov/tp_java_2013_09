package example;

public class UserSession {
    private Address accountService;

    private String name;
    private String sessionId;
    private Long userId;

    public UserSession(String sessionId, String name, AddressService addressService) {
        this.sessionId = sessionId;
        this.name = name;
        this.accountService = addressService.getAccountService();
    }

    public Address getAccountService() {
        return accountService;
    }

    public String getName(){
        return name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
