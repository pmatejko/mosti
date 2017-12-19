package exceptions;


import fetcher.DataProvider;

public class DataProviderCOnflictException extends Exception {
    public DataProviderCOnflictException(DataProvider provider1, DataProvider provider2) {
        System.out.println("Conflict between "+provider1 +" and  "+provider2);
        //TODO add logging, and better message will be given
    }
}
