package example;

public abstract class Msg {
	private Address from;
	private Address to;
	
	public Msg(Address from, Address to){
		this.from = from;
		this.to = to;
	}
	
	protected Address getFrom(){
		return from;
	}
	
	protected Address getTo(){
		return to;
	}
	
	abstract void exec(Abonent abonent);
}
