package interfaces;


import model.Condition;

public interface IConfigurableComparator extends IComparator{
    IConfigurableComparator createFor(Condition condition);
}
