public class Client {
    public static void main(String[] args) {
        PrototypeRegistry<Character> registry = new PrototypeRegistry<>();
        Character warrior = new Character(
            "Warrior",
            100,
            new Weapon("Iron Sword", 50));
        registry.register("Warrior", warrior);
        Character eliteWarrior = registry.get("Warrior");

        eliteWarrior.setName("Elite Warrior");
        eliteWarrior.getWeapon().setDamage(100);

        System.out.println("Original: "+ warrior);
        System.out.println("Clone: "+ eliteWarrior);

    }
}
