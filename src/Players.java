public abstract class Players implements Fighter {

    String name;
    int health;
    int power;
    int lovkost;
    int gold;
    int experience;

    public Players(String name, int health, int gold, int lovkost, int experience, int power) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.lovkost = lovkost;
        this.experience = experience;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLovkost() {
        return lovkost;
    }

    public void setLovkost(int lovkost) {
        this.lovkost = lovkost;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, health);
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public int attack() {
        if (lovkost * 3 > getRandomValue()) return power;
        else return 0;
    }

}
