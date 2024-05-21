/**
 * Абстракнтый родительский элемент реестра
 */
abstract class StorageItem<T>(name: String, type: ItemTypes): Item(name, type) where T: Item {
    abstract val items: ArrayList<T>

    // Создание дочерней записи
    abstract fun create()
}
