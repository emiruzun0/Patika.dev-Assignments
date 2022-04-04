import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account {

    public enum AuthenticationStatus {
        SUCCESS,
        FAIL
    }

    Scanner input = new Scanner(System.in);

    private User user;
    AuthenticationStatus authStatus;
    private ArrayList<Insurance> insuranceList = new ArrayList<>();


    public Account(User user) {
        this.user = user;
    }

    public void accountMenu() {
        System.out.println();
        System.out.println("Account Owner: " + this.getUser().getLastName());
        System.out.println("---------------------------------------");
        System.out.println("1 - Show Account Information");
        System.out.println("2 - Add Addresses");
        System.out.println("3 - Show Addresses");
        System.out.println("4 - Add Insurance");
        System.out.println("5 - Show Insurance");
        System.out.println("6 - Calculate the total cost of your insurance");
        System.out.println("0 - Log out the account");
        System.out.print("Your choice: ");
        int choice = input.nextInt();
        boolean isExit = false;

        switch (choice) {
            case 1:
                this.showUserInfo();
                break;
            case 2:
                this.showAddresses();
                break;
            case 3:
                this.addAddresses();
                break;
            case 4:
                this.addInsurance();
                break;
            case 5:
                this.showInsuranceList();
                break;
            case 6:
                // Calculate total cost
                break;
            case 0:
                System.out.println("Logging out the account...");
                isExit = true;
                break;
            default:
                System.out.println("There is no such an option. Try again.");
                break;
        }

        if (isExit) {
            return;
        }

        this.accountMenu();

    }

    public final void showUserInfo() {
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Occupation: " + user.getOccupation());
        System.out.println("Age: " + user.getAge());
        System.out.println("Last Log In Date: ");
    }

    public void addAddresses() {
        System.out.println();
        System.out.println("1 - Home Addresses");
        System.out.println("2 - Business Addresses");
        System.out.println("0 - Go Back To Menu");
        System.out.print("Your choice: ");
        int choice = input.nextInt();

        System.out.print("Neighbourhood: ");
        String neighbourhood = input.next();
        System.out.print("Street Name: ");
        String streetName = input.next();
        System.out.print("Building Name: ");
        String buildingName = input.next();
        System.out.print("Floor Name: ");
        int floorNumber = input.nextInt();
        System.out.print("Flat Number: ");
        int flatNumber = input.nextInt();
        System.out.print("Post Code: ");
        String postCode = input.next();
        System.out.print("Town: ");
        String town = input.next();
        System.out.print("District: ");
        String district = input.next();
        System.out.print("Province: ");
        String province = input.next();
        System.out.print("Country: ");
        String country = input.next();

        switch (choice) {
            case 1:
                HomeAddress newHomeAddress = new HomeAddress(neighbourhood, streetName, buildingName, floorNumber,
                                                             flatNumber, postCode, town, district, province, country);

                AddressManager.addHomeAddress(newHomeAddress);
                break;
            case 2:
                BusinessAddress newBusinessAddress = new BusinessAddress(neighbourhood, streetName, buildingName,
                                                                         floorNumber, flatNumber, postCode, town,
                                                                         district, province, country);
                AddressManager.addBusinessAddress(newBusinessAddress);
                break;
            case 0:
                System.out.println("Going back to Account Menu...");
                break;
            default:
                System.out.println("There is no such an option. Please try again.");
                addAddresses();
        }
    }

    public void showAddresses() {
        System.out.println();
        System.out.println("1 - Home Addresses");
        System.out.println("2 - Business Addresses");
        System.out.println("0 - Go Back To Menu");
        System.out.print("Your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println();
                System.out.println("Home Addressess");
                System.out.println("------------------------");
                for (HomeAddress homeAddress:AddressManager.getListHomeAddress()) {
                    System.out.print("Neighbourhood: " + homeAddress.getNeighbourhood());
                    System.out.print("Street Name: " + homeAddress.getStreetName());
                    System.out.print("Building Name: " + homeAddress.getBuildingName());
                    System.out.print("Floor Name: " + homeAddress.getFloorNumber());
                    System.out.print("Flat Number: " + homeAddress.getFlatNumber());
                    System.out.print("Post Code: " + homeAddress.getPostCode());
                    System.out.print("Town: " + homeAddress.getTown());
                    System.out.print("District: " + homeAddress.getDistrict());
                    System.out.print("Province: " + homeAddress.getProvince());
                    System.out.print("Country: " + homeAddress.getCountry());
                }
                break;
            case 2:
                System.out.println();
                System.out.println("Business Addressess");
                System.out.println("------------------------");
                for (BusinessAddress businessAddress:AddressManager.getListBusinessAddress()) {
                    System.out.print("Neighbourhood: " + businessAddress.getNeighbourhood());
                    System.out.print("Street Name: " + businessAddress.getStreetName());
                    System.out.print("Building Name: " + businessAddress.getBuildingName());
                    System.out.print("Floor Name: " + businessAddress.getFloorNumber());
                    System.out.print("Flat Number: " + businessAddress.getFlatNumber());
                    System.out.print("Post Code: " + businessAddress.getPostCode());
                    System.out.print("Town: " + businessAddress.getTown());
                    System.out.print("District: " + businessAddress.getDistrict());
                    System.out.print("Province: " + businessAddress.getProvince());
                    System.out.print("Country: " + businessAddress.getCountry());
                }
                break;
            case 0:
                System.out.println("Going back to Account Menu...");
                break;
            default:
                System.out.println("There is no such an option. Please try again.");
                showAddresses();
        }
    }

    public void logIn(String email, String password) throws InvalidAuthenticationException {
        if (email.equals(this.getUser().getEmail()) && password.equals(user.getPassword())) {
            authStatus = AuthenticationStatus.SUCCESS;
        } else {
            authStatus = AuthenticationStatus.FAIL;
        }

        switch (authStatus) {
            case SUCCESS:
                System.out.println("You've succeeded in logging in your account.");
                this.accountMenu();
                break;
            case FAIL:
                throw new InvalidAuthenticationException("Invalid Authentication");
        }

    }

    public void signUp() {

    }

    public double calculate() {
        return 0;
    }

    public void addInsurance() {
        System.out.println();
        System.out.println("Insurance Types");
        System.out.println("1 - Car Insurance");
        System.out.println("2 - Health Insurance");
        System.out.println("3 - Residence Insurance");
        System.out.println("4 - Travel Insurance");
        System.out.println("0 - Back To Account Menu");
        System.out.print("Your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                insuranceList.add(new CarInsurance());
                break;
            case 2:
                insuranceList.add(new HealthInsurance());
                break;
            case 3:
                insuranceList.add(new ResidenceInsurance());
                break;
            case 4:
                insuranceList.add(new TravelInsurance());
                break;
            case 0:
                System.out.println("Going back to Account Menu...");
                break;
            default:
                System.out.println("There is no such an option. Please try again.");
                addInsurance();
        }
    }



    public void showInsuranceList() {
        System.out.println();
        int count = 1;
        for (Insurance insurance:insuranceList) {
            System.out.println(count++ + " - " + insurance.getClass().getName());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
