package example;

import java.util.HashMap;
import java.util.Map;

public class AccountService implements Abonent, Runnable{
	private Address address;
	private MessageSystem ms;

	private Map<String, Long> fakeAccounter = new HashMap<>();
	
	public AccountService(MessageSystem ms){
		this.ms = ms;
		this.address = new Address();
		ms.addService(this);
        ms.getAddressService().setAccountService(address);

		this.fakeAccounter.put("Tully", 1L);
		this.fakeAccounter.put("Sully", 2L);
	}
	
	
	public void run(){
		while(true){
				ms.execForAbonent(this);
				TimeHelper.sleep(10);
		}
	}

	public Long getUserId(String name){
		TimeHelper.sleep(5000);
		return fakeAccounter.get(name);
	}
	
	public Address getAddress() {
		return address;
	}
	
	public MessageSystem getMessageSystem(){
		return ms;
	}
}
