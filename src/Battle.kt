import Realm.FightCallback

class Battle {
    fun fight(hero: Players, monster: Players, fightCallback: FightCallback) {
        val runnable = Runnable {
            var turn = 1
            var isFightEnded = false
            while (!isFightEnded) {
                println("----Ход:$turn----")
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
            println(String.format("%s Нанес удар в %d единиц!", attacker.name, hit))
            println(String.format("У %s осталось %d единиц здоровья...", defender.name, defenderHealth))
        } else {
            println(String.format("%s промахнулся!", attacker.name))
        }
        return if (defenderHealth <= 0 && defender is Hero) {
            println("Извините, вы пали в бою... ")
            fightCallback.fightLost()
            true
        } else if (defenderHealth <= 0) {
            println(
                String.format(
                    "Враг повержен! Вы получаете %d опыт и %d золота",
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