package notifier.informer;


import java.util.ArrayList;
import java.util.Iterator;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import exceptions.SenderException;
import interfaces.IProvider;
import model.UserNewsDTO;
import notifier.message.MessageGenerator;
import notifier.message.UglyMessageGenerator;
import notifier.senders.Sender;

@Singleton
public class NotifierManager {
	
	
	private IProvider iprovider;
	private UserWayOfNotifyingManager userWayOfNotifyingManager;
	
	@Inject
	public NotifierManager(IProvider iprovider) {
	
			this.iprovider = iprovider;
			this.userWayOfNotifyingManager = new UserWayOfNotifyingManager();
	}
	
	public void subscribe() {
        this.iprovider
                .getUserNewsObservable()
                .subscribe(this::informUser);
    }
	
	
	public void informUser(UserNewsDTO userNewsDTO) throws SenderException {
		
		System.out.println("IM IN NOTIFIER MANAGAER @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		MessageGenerator msg = new UglyMessageGenerator(userNewsDTO);
			
			
		ArrayList<Sender> senders = this.userWayOfNotifyingManager.getListOfSenders(userNewsDTO.getUser().getWayOfInforming());
			
			
		Iterator<Sender> iterator = senders.iterator();
		while(iterator.hasNext()) {
			iterator.next().send(msg);
		}
		
		
	}
	
	
}
