package Comparator;


import interfaces.IComparator;
import model.User;

public class ComparatorFactory {
    public IComparator createComparator(User user){
        return new ComparatorComposite(null,null);
    }
}
