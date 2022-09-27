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
        println("������� ��� ������ ���������")
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
            println(String.format("������ ��� ��� �� �������� �������� %s! �� �������� � ����� ����!", (player as Hero).name))
            printNavigation()
        }
        when (string) {
            "1" -> {
                println("�������� ���, ������� �����")
                command(br!!.readLine())
            }
            "2" -> {
                commitFight()
            }
            "3" -> {
                System.exit(1)
            }
            "��" -> {
                command("2")
            }
            "���" -> {
                printNavigation()
                command(br!!.readLine())
            }
        }
        command(br!!.readLine())
    }

    private fun printNavigation() {
        println("���� �� ������ �����?")
        println("1. � ��������")
        println("2. � ������ ��� ���� ����� �������")
        println("3. �����")
    }

    private fun commitFight() {
        battleScene!!.fight(player!!, createMonster(), object : FightCallback {
            override fun fightWin() {
                println(
                    String.format(
                        "%s �������! ������ � ��� %d ����� � %d ������, � ����� �������� %d ������ ��������.",
                        player!!.name,
                        player!!.experience,
                        player!!.gold,
                        player!!.health
                    )
                )
                println("������� ���������� ����� ��� ��������� � �����? (��/���)")
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
        return if (random % 2 == 0) Goblin("������", 50, 20, 10, 100, 10) else Skeleton("������", 25, 10, 20, 100, 20)
    }

    interface FightCallback {
        fun fightWin()
        fun fightLost()
    }
}