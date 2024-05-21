/**
 * Класс архива
 */
class ArchivesStorage(name: String): StorageItem<NoteStorage>(name, ItemTypes.ARCHIVE_STORAGE) {
    override val items: ArrayList<NoteStorage> = ArrayList()

    override fun create() {
        items.add(NoteStorage(Listener.readLine(
            "Введите название архива:",
            "Название архива не может быть пустым!"
        )))
    }
}
