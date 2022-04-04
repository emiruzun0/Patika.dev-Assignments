import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        return o1.getUser().getLastName().compareTo(o2.getUser().getLastName());
    }
}
