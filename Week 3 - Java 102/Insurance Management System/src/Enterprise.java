public class Enterprise extends Account{

    private User user;

    public Enterprise(User user) {
        super(user);
    }

    @Override
    public void signUp() {
        System.out.println("    CREATE A NEW ACCOUNT    ");
        System.out.println("============================");
        System.out.print("First Name: ");
        this.user.setFirstName(input.next());
        System.out.print("Last Name: ");
        this.user.setLastName(input.next());
        System.out.print("Email: ");
        String email = input.next();
        this.user.setEmail(email);
        System.out.print("Password: ");
        String password = input.next();
        this.user.setPassword(password);
        System.out.print("Occupation: ");
        String occupation = input.next();
        this.getUser().setOccupation(occupation);
        System.out.print("Age: ");
        int age = input.nextInt();
        this.getUser().setAge(age);

        for (Account account:AccountManager.getAccountsEnterprise()) {
            if (email.equals(account.getUser().getEmail())) {
                System.out.println("Such an account exists in the system. Be sure of creating a new account.");
                return;
            }
        }

        AccountManager.addAccountsEnterprise(this);
        System.out.println("You've signed up the system.");
    }

}
