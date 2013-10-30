package example;

public abstract class MsgToFrontend extends Msg{

	public MsgToFrontend(Address from, Address to) {
		super(from, to);
	}

	public void exec(Abonent abonent) {
		if(abonent instanceof Frontend){
			exec((Frontend)abonent);
		}
	}
	
	abstract void exec(Frontend frontend);

}
