package notifier.senders;


import notifier.senders.configuration.Configuration;

public abstract class Sender implements Sendable{ 

    
    protected boolean configured;
    protected Configuration configuration;
    
    
    public Sender(Configuration configuration)
    { 
    	this.configuration = configuration;
		this.configured = false;
    }
	
    public abstract void configure();
    
    public Configuration getConfiguration() {
    	return this.configuration;
    }
}
