import java.util.HashSet;

public class AddressManager {

    static HashSet<HomeAddress> listHomeAddress = new HashSet<>();
    static HashSet<BusinessAddress> listBusinessAddress = new HashSet<>();

    public static HashSet<HomeAddress> getListHomeAddress() {
        return listHomeAddress;
    }

    public static void addHomeAddress(HomeAddress HomeAddress) {
        listHomeAddress.add(HomeAddress);
    }

    public static HashSet<BusinessAddress> getListBusinessAddress() {
        return listBusinessAddress;
    }

    public static void addBusinessAddress(BusinessAddress BusinessAddress) {
        listBusinessAddress.add(BusinessAddress);
    }
}
