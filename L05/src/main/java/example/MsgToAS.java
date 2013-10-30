package example;

public abstract class MsgToAS extends Msg{

	public MsgToAS(Address from, Address to) {
		super(from, to);		
	}

	void exec(Abonent abonent) {
		if(abonent instanceof AccountService){
			exec((AccountService) abonent);
		}
	}

	abstract void exec(AccountService accountService);
}
