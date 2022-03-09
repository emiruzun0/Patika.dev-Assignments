public class ToolStore extends NormalLocation{

    public ToolStore(Player player) {
        super(player, "Store");
    }



    @Override
    public boolean onLocation(){
        System.out.println("Welcome to the store ! ");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Weapons");
            System.out.println("2 - Armor");
            System.out.println("3 - Exit");
            System.out.print("Your choice : ");
            int selectCase = Location.input.nextInt();
            while(selectCase < 1 || selectCase > 3){
                System.out.println("Invalid value, please choose again : ");
                selectCase = input.nextInt();
            }
            switch (selectCase)
            {
                case 1 :
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("See you soon ! ");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("----- Weapons -----");
        for(Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getName() + " <Price : " + w.getPrice() + " , Damage : " + w.getDamage()  + " >");
        }
        System.out.println("0 - Exit");
    }

    public void buyWeapon(){
        System.out.print("Please choose a weapon : ");
        int selectWeaponID = input.nextInt();

        while(selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("Invalid value, please choose again : ");
            selectWeaponID = input.nextInt();
        }

        if(selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if(selectedWeapon!= null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Doesn't enough money ! ");
                }
                else{
                    System.out.println(" You bought this weapon : " + selectedWeapon.getName());
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Remaining money : " + this.getPlayer().getMoney());
                    System.out.println("Previous weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("New weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }


    }

    public void printArmor(){
        System.out.println("----- Armors -----");
        for(Armor a : Armor.armors()){
            System.out.println(a.getId() + " - " + a.getName() +
                    "<Price : " + a.getPrice() + " , Block Value : " + a.getBlock()  + " >");
        }
        System.out.println("0 - Exit");
    }

    private void buyArmor() {
        System.out.print("Please choose an armor : ");
        int selectArmorID = input.nextInt();

        while(selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.print("Invalid value, please choose again : ");
            selectArmorID = input.nextInt();
        }


        if(selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if(selectedArmor!= null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Doesn't enough money ! ");
                }
                else{
                    System.out.println(" You bought this weapon : " + selectedArmor.getName());
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Remaining money : " + this.getPlayer().getMoney());
                    System.out.println("Previous armor : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("New armor : " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }
}
