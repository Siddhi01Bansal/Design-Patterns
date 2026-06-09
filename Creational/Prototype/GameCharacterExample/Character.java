public class Character implements Prototype<Character>{
    private String name;
    private int health;
    private Weapon weapon; 

    public Character(String name, int health, Weapon weapon){
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    @Override
    public Character clone(){
        return new Character(
            this.name,
            this.health,
            this.weapon.clone());
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }
    @Override
    public String toString(){
        return "Character{" +
            "name='" + name + '\'' +
            ", health=" + health +
            ", weapon=" + weapon +
            '}';
    }
    
}
