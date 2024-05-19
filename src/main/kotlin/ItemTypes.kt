/**
 * Типы записей
 */
enum class ItemTypes(val title: String, val mainAction: String): Printable {
    ARCHIVE_STORAGE("Список архивов:", "Создать архив") {
        override fun getTitle(name: String?): String {
            return title
        }
    },
    NOTES_STORAGE("Список заметок:", "Создать заметку") {
        override fun getTitle(name: String?): String {
            return "Архив \"$name\"\n$title"
        }
    },
    NOTE("Заметка", "Просмотреть заметку") {
        override fun getTitle(name: String?): String {
            return "$title \"$name\""
        }
    }
}