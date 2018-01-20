package notifier.informer;


import java.util.ArrayList;
import java.util.Iterator;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import exceptions.SenderException;
import interfaces.IProvider;
import model.UserNewsDTO;
import notifier.informer.struct.Struct;

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


		ArrayList<Struct> structs = this.userWayOfNotifyingManager.getListOfStructs(userNewsDTO);

		Iterator<Struct> iterator = structs.iterator();
		while(iterator.hasNext()) {
			iterator.next().sendMessage();
		}



	}
	
	
}
