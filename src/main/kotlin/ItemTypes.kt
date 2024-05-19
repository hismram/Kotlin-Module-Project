/**
 * Типы записей
 */
enum class ItemTypes(val title: String, val mainAction: String): Printable {
    ARCHIVE_STORAGE("Список архивов:", "Создать архив") {
        override fun getTitle(name: String?): String {
            return title
        }
    },
    NOTES_STORAGE("Список заметок в архиве", "Создать заметку") {
        override fun getTitle(name: String?): String {
            return "$title \"$name\":"
        }
    },
    NOTE("Заметка", "Просмотреть заметку") {
        override fun getTitle(name: String?): String {
            return "$title \"$name\""
        }
    }
}
