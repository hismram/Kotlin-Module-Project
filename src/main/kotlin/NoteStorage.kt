/**
 * Хранилище заметок, архив
 */
class NoteStorage(name: String): StorageItem<Note>(name, ItemTypes.NOTES_STORAGE) {
    override val items: ArrayList<Note> = ArrayList()

    // Создает новую заметку
    override fun create() {
        items.add(Note(
            Listener.readLine(
                "Введите название заметки:",
                "Название заметки не может быть пустым!"
            ),
            Listener.readLine(
                "Введите текст заметки:",
                "Текст заметки не может быть пустым!"
            )
        ))
    }
}
