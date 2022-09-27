import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object Realm {
    private var br: BufferedReader? = null
    private var player: Players? = null
    private var battleScene: Battle? = null
    @JvmStatic
    fun main(args: Array<String>) {
        br = BufferedReader(InputStreamReader(System.`in`))
        battleScene = Battle()
        println("Введите имя вашего персонажа")
        try {
            command(br!!.readLine())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun command(string: String) {
        if (player == null) {
            player = Hero(string, 100, 0, 20, 0, 20)
            println(String.format("Спасти наш мир от драконов вызвался %s! Да прибудет с тобой сила!", (player as Hero).name))
            printNavigation()
        }
        when (string) {
            "1" -> {
                println("Торговца нет, зайдите позже")
                command(br!!.readLine())
            }
            "2" -> {
                commitFight()
            }
            "3" -> {
                System.exit(1)
            }
            "да" -> {
                command("2")
            }
            "нет" -> {
                printNavigation()
                command(br!!.readLine())
            }
        }
        command(br!!.readLine())
    }

    private fun printNavigation() {
        println("Куда вы хотите пойти?")
        println("1. К Торговцу")
        println("2. В темный лес бить белых ходоков")
        println("3. Выход")
    }

    private fun commitFight() {
        battleScene!!.fight(player!!, createMonster(), object : FightCallback {
            override fun fightWin() {
                println(
                    String.format(
                        "%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.",
                        player!!.name,
                        player!!.experience,
                        player!!.gold,
                        player!!.health
                    )
                )
                println("Желаете продолжить поход или вернуться в город? (да/нет)")
                try {
                    command(br!!.readLine())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun fightLost() {}
        })
    }

    private fun createMonster(): Players {
        val random = (Math.random() * 10).toInt()
        return if (random % 2 == 0) Goblin("Гоблин", 50, 20, 10, 100, 10) else Skeleton("Скелет", 25, 10, 20, 100, 20)
    }

    interface FightCallback {
        fun fightWin()
        fun fightLost()
    }
}