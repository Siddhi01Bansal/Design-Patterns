public class BurgerMeal {
    private final PattyType patty;
    private final boolean cheese;
    private final boolean fries;
    private final ColdDrink coldDrink;
    private BurgerMeal(Builder builder){
        this.patty = builder.patty;
        this.cheese = builder.cheese;
        this.fries = builder.fries;
        this.coldDrink = builder.coldDrink;
    }
    @Override
    public String toString() {
        return "BurgerMeal{" +
                "patty='" + patty + '\'' +
                ", cheese=" + cheese +
                ", fries=" + fries +
                ", coldDrink=" + coldDrink +
                '}';
    }
    public static class Builder {
        private PattyType patty;
        private boolean cheese;
        private boolean fries;
        private ColdDrink coldDrink;
        public Builder(PattyType patty){
            this.patty = patty;
        }
        public Builder addCheese(){
            this.cheese = true;
            return this;
        }
        public Builder addFries(){
            this.fries = true;
            return this;
        }
        public Builder addColdDrink(ColdDrink coldDrink){
            this.coldDrink = coldDrink;
            return this;
        }
        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    }
}