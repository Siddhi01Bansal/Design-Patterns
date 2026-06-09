public class Weapon implements Prototype<Weapon>{
    private String name;
    private int damage;
    public Weapon(String name, int damage){
        this.name = name;
        this.damage = damage;
    }
    @Override
    public Weapon clone(){
        return new Weapon(
            this.name,
            this.damage);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    @Override
    public String toString(){
        return "Weapon{" +
            "name='" + name + '\'' +
            ", damage=" + damage +
            '}';
    }
}
