fun main() {
    val list1 = listOf("one","two","Three")
    val list2 = listOf(1,2,3)

    val join = list2.joinToString()
    println(join)

    val split = join.split(",")
    println(split)


}


