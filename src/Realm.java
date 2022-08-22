import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Realm {
    private static BufferedReader br;
    private static Players player = null;
    private static Battle battleScene = null;

    public static void main(String[] args) {

        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new Battle();
        System.out.println("Введите имя вашего персонажа");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(string, 100, 0, 20, 0, 20);
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да прибудет с тобой сила!", player.getName()));
            printNavigation();
        }

        switch (string) {
            case "1" -> {
                System.out.println("Торговца нет, зайдите позже");
                command(br.readLine());
            }
            case "2" -> {
                commitFight();
            }
            case "3" -> {
                System.exit(1);
            }
            case "да" -> {
                command("2");
            }
            case "нет" -> {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес бить белых ходоков");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getExperience(), player.getGold(), player.getHealth()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
            }
        });
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    private static Players createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Гоблин", 50, 20, 10, 100, 10);
        else return new Skeleton("Скелет", 25, 10, 20, 100, 20);
    }
}
