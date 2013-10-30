package example;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageSystem {
	private Map<Address, ConcurrentLinkedQueue<Msg>> messages = new HashMap<Address, ConcurrentLinkedQueue<Msg>>();
	private AddressService addressService = new AddressService();
	
	public void addService(Abonent abonent){
		messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Msg>());
	}
	
	public void sendMessage(Msg message){
		Queue<Msg> messageQueue = messages.get(message.getTo());
		messageQueue.add(message);		
	}
		
	public void execForAbonent(Abonent abonent) {
		Queue<Msg> messageQueue = messages.get(abonent.getAddress());
		if(messageQueue == null){
			return;
		}
		while(!messageQueue.isEmpty()){
			Msg message = messageQueue.poll();
			message.exec(abonent);
		}
	}
	
	public AddressService getAddressService(){
		return addressService;
	}
}
