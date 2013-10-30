package example;

public class MsgUpdateUserId extends MsgToFrontend {

	private String sessionId;
	private Long id;
	
	public MsgUpdateUserId(Address from, Address to, String sessionId, Long id) {
		super(from, to);
		this.sessionId = sessionId;
		this.id = id;
	}

	void exec(Frontend frontend) {
		frontend.setId(sessionId, id);
	}

}
