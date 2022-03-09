import java.util.Random;

public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name,Obstacle obstacle,String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        if(isCompleted(this.getName())){
            System.out.println("You've already defeated all enemies in " + this.getName());
            return true;
        }
        if(this.getName().equals("Mine")){
            Random r = new Random();
            int damage = r.nextInt(4) + 3;
            this.getObstacle().setDamage(damage);
        }
        int obsNumber = this.randomObstacleNumber();
        System.out.println("You are here now : " + this.getName());
        System.out.println("Please be careful in here ! " + obsNumber + " " + this.getObstacle().getName() + " is living here! ");
        System.out.println("<F>ight or <R>un" );
        String selectCase = input.next().toUpperCase();
        if(selectCase.equals("F") && combat(obsNumber)){
            System.out.println("In " + this.getName() + ", You defeated all the enemies ");
            return true;
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.println("You died ! ");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){
        Random r = new Random();
        int start = r.nextInt(2) ;
        for(int i = 1; i <= obsNumber; ++i){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<H>it or <R>un");
                String selectCombat = input.next().toUpperCase();
                if(selectCombat.equals("H")){
                    if(start == 1){
                        System.out.println("You hit first ");
                        playerHitFirst();
                    }
                    else{
                        System.out.println("Obstacle hit first ");
                        obstacleHitFirst();
                    }
                }
                else{
                    return false;
                }
            }

            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("You defeated the enemies ! ");
                if(this.getName().equals("Mine")){
                    int prize = r.nextInt(100) + 1;
                    if( prize <= 15){
                        int randomGun = r.nextInt(100) + 1;
                        if(randomGun <= 20){
                            Weapon selectedWeapon = Weapon.getWeaponObjByID(3);
                            this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        }
                        else if( randomGun <= 50){
                            Weapon selectedWeapon = Weapon.getWeaponObjByID(2);
                            this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        }
                        else{
                            Weapon selectedWeapon = Weapon.getWeaponObjByID(1);
                            this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        }
                    }
                    else if(prize <= 30){
                        int randomArmor = r.nextInt(100) + 1;
                        if(randomArmor <= 20){
                            Armor selectedArmor = Armor.getArmorObjByID(3);
                            this.getPlayer().getInventory().setArmor(selectedArmor);
                        }
                        else if(randomArmor <= 50){
                            Armor selectedArmor = Armor.getArmorObjByID(2);
                            this.getPlayer().getInventory().setArmor(selectedArmor);
                        }
                        else{
                            Armor selectedArmor = Armor.getArmorObjByID(1);
                            this.getPlayer().getInventory().setArmor(selectedArmor);
                        }
                    }
                    else if(prize <= 55){
                        int randomMoney = r.nextInt(100) + 1;
                        if(randomMoney <= 20){
                            System.out.println( "10 money earned ! ");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 10 );
                            System.out.println("Current money : " + this.getPlayer().getMoney());
                        }
                        else if(randomMoney <= 50){
                            System.out.println( "5 money earned ! ");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 5 );
                            System.out.println("Current money : " + this.getPlayer().getMoney());
                        }
                        else{
                            System.out.println( "1 money earned ! ");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 1 );
                            System.out.println("Current money : " + this.getPlayer().getMoney());
                        }
                    }
                    else{
                        System.out.println("You didn't earn anything ! ");
                    }


                }
                else{
                    System.out.println(this.getObstacle().getAward() + " money earned ! ");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward() );
                    System.out.println("Current money : " + this.getPlayer().getMoney());
                }

            }
            else{
                return false;
            }
        }
        assignComplete(super.getName());
        return true;
    }

    private void obstacleHitFirst() {
        System.out.println("Obstacle hits you");
        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
        if(obstacleDamage < 0 ) obstacleDamage = 0;
        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
        afterHit();
        if(this.getPlayer().getHealth() > 0){
            System.out.println("You hit ");
            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
            afterHit();
        }
    }

    private void playerHitFirst() {
        System.out.println("You hit ! ");
        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
        afterHit();
        if(this.getObstacle().getHealth() > 0 ){
            System.out.println();
            System.out.println("Obstacle hits you ! ");
            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
            System.out.println(obstacleDamage);
            if(obstacleDamage < 0 ) obstacleDamage = 0;
            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
            afterHit();
        }
    }

    private boolean isCompleted(String name) {
        System.out.println("gelen isim : " + name);
        switch (name){
            case "Cave":
                return this.getPlayer().isCompletedCave();
            case "Forest":
                return this.getPlayer().isCompletedForest();
            case "River":
                return this.getPlayer().isCompletedRiver();
            case "Mine":
                return this.getPlayer().isCompletedMine();
        }
        return false;
    }

    private void assignComplete(String name) {
        switch (name){
            case "Cave":
                this.getPlayer().setCompletedCave(true);
                this.getPlayer().getInventory().setFood(true);
                break;
            case "Forest":
                this.getPlayer().setCompletedForest(true);
                this.getPlayer().getInventory().setFirewood(true);
                break;
            case "River":
                this.getPlayer().setCompletedRiver(true);
                this.getPlayer().getInventory().setWater(true);
                break;
            case "Mine":
                this.getPlayer().setCompletedMine(true);
                break;
        }

    }

    public void afterHit(){
        System.out.println("Your health : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "'s health : " + this.obstacle.getHealth());
        System.out.println();
    }

    private void playerStats() {
        System.out.println("---------------------");
        System.out.println("Player Stats ");
        System.out.println("Health : " + this.getPlayer().getHealth());
        System.out.println("Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage  : " + this.getPlayer().getTotalDamage());
        System.out.println("Armor : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Armor Block : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money : " + this.getPlayer().getMoney());
        System.out.println();

    }

    public void obstacleStats(int i){
        System.out.println("---------------------");
        System.out.println(i + "." + this.getObstacle().getName() + " Stats");
        System.out.println("Health : " + this.getObstacle().getHealth());
        System.out.println("Damage : " + this.getObstacle().getDamage());
        System.out.println("Award : " + this.getObstacle().getAward());
        System.out.println();
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1 ;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

}
