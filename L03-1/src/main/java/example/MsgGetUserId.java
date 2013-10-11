package example;

public class MsgGetUserId extends MsgToAS {
	private String name;
    private String sessionId;
	
	public MsgGetUserId(Address from, Address to, String name, String sessionId) {
		super(from, to);
		this.name= name;
        this.sessionId = sessionId;
	}

	void exec(AccountService accountService) {
		Long id = accountService.getUserId(name);
		accountService.getMessageSystem().sendMessage(new MsgUpdateUserId(getTo(), getFrom(), sessionId, id));
	}
}
