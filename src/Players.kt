abstract class Players(
    var name: String?,
    var health: Int,
    var gold: Int,
    var lovkost: Int,
    var experience: Int,
    var power: Int
) : Fighter {

    override fun toString(): String {
        return String.format("%s здоровье:%d", name, health)
    }

    private val randomValue: Int
        private get() = (Math.random() * 100).toInt()

    override fun attack(): Int {
        return if (lovkost * 3 > randomValue) power else 0
    }
}