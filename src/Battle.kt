import Realm.FightCallback

class Battle {
    fun fight(hero: Players, monster: Players, fightCallback: FightCallback) {
        val runnable = Runnable {
            var turn = 1
            var isFightEnded = false
            while (!isFightEnded) {
                println("----���:$turn----")
                isFightEnded = if (turn++ % 2 != 0) {
                    makeHit(monster, hero, fightCallback)
                } else {
                    makeHit(hero, monster, fightCallback)
                }
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        val thread = Thread(runnable)
        thread.start()
    }

    private fun makeHit(defender: Players, attacker: Players, fightCallback: FightCallback): Boolean {
        val hit = attacker.attack()
        val defenderHealth = defender.health - hit
        if (hit != 0) {
            println(String.format("%s ����� ���� � %d ������!", attacker.name, hit))
            println(String.format("� %s �������� %d ������ ��������...", defender.name, defenderHealth))
        } else {
            println(String.format("%s �����������!", attacker.name))
        }
        return if (defenderHealth <= 0 && defender is Hero) {
            println("��������, �� ���� � ���... ")
            fightCallback.fightLost()
            true
        } else if (defenderHealth <= 0) {
            println(
                String.format(
                    "���� ��������! �� ��������� %d ���� � %d ������",
                    defender.experience,
                    defender.gold
                )
            )
            attacker.experience = attacker.experience + defender.experience
            attacker.gold = attacker.gold + defender.gold
            fightCallback.fightWin()
            true
        } else {
            defender.health = defenderHealth
            false
        }
    }
}