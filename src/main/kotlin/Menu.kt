/**
 * Класс данных меню
 */
data class Menu(
    val title: String,
    val items: List<MenuItem>
) {
    fun print() {
        var id = 0

        println(title)
        items.forEach { println("${id++}. ${it.title}") }
    }
}
