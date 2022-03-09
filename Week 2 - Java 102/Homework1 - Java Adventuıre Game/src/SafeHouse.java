public class SafeHouse extends NormalLocation{

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation(){
        System.out.println(" You are in safe house ! ");
        System.out.println(" You are healing ! ");
        System.out.println();
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        if(this.getPlayer().isCompletedForest() && this.getPlayer().isCompletedCave() &&
                this.getPlayer().isCompletedRiver() && this.getPlayer().isCompletedMine()){
            System.out.println("You defeated all enemies in every location. CONGRATULATIONS ! ");
            return false;
        }
        return true;
    }
}
