import kotlin.system.exitProcess

/**
 * Класс экрана, управляет отображаемой сущностью
 */
sealed class Screen(
    val onChangeScreen: (Screen) -> Unit,
    private val parent: Screen? = null
) {
    var menu: Menu? = null

    fun executeCommand(id: Int) {
        try {
            menu!!.items[id].action.invoke()
        } catch (e: IndexOutOfBoundsException) {
            println()
            println("Пункт под номером $id не существует, повторите ввод")
        }
    }

    abstract fun initMenu()

    fun printMenu() {
        menu ?: initMenu()
        menu!!.print()
    }

    fun open(item: Item) {
        when (item.type) {
            ItemTypes.NOTES_STORAGE -> onChangeScreen(StorageScreen(
                this,
                item as NoteStorage,
                onChangeScreen
            ))
            ItemTypes.NOTE -> onChangeScreen(ItemScreen(
                this,
                item as Note,
                onChangeScreen
            ))
            else -> {}
        }
    }

    fun back() {
        if (parent != null) {
            onChangeScreen(parent)
        } else {
            exitProcess(0)
        }
    }

    /**
     * Экран хранилища
     */
    class StorageScreen<T : Item>(
        parent: Screen?,
        private val storage: StorageItem<T>,
        onChangeScreen: (Screen) -> Unit
    ) : Screen(onChangeScreen, parent) {
        override fun initMenu() {
            val menuItems = storage.items.map { MenuItem(it.name) { open(it) } }

            menuItems.addFirst(MenuItem(storage.type.mainAction) {
                storage.create()
                initMenu()
            })
            menuItems.addLast(MenuItem(EXIT_CAPTION) { back() })

            menu = Menu(
                storage.type.getTitle(storage.name),
                menuItems
            )
        }
    }

    /**
     * Экран заметки
     */
    class ItemScreen(
        parent: Screen,
        private val item: Note,
        onChangeScreen: (Screen) -> Unit
    ): Screen(onChangeScreen, parent) {
        override fun initMenu() {
            val menuItems = listOf(
                MenuItem(item.type.mainAction) {showContent()},
                MenuItem(EXIT_CAPTION) {back()}
            )

            menu = Menu(
                item.type.getTitle(item.name),
                menuItems
            )
        }

        private fun showContent() {
            onChangeScreen(ContentScreen(this, item, onChangeScreen))
        }
    }

    /**
     * Экран контента заметки
     */
    class ContentScreen(
        parent: Screen,
        private val item: Note,
        onChangeScreen: (Screen) -> Unit
    ): Screen(onChangeScreen, parent) {
        override fun initMenu() {
            menu = Menu(
                "Содержимое заметки \"${item.name}\":\n${item.content}",
                listOf(MenuItem(EXIT_CAPTION) { back() })
            )
        }
    }

    companion object {
        const val EXIT_CAPTION = "Выход"
    }
}


