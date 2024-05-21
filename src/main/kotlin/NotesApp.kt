import java.lang.NumberFormatException

/**
 * Корневой класс приложения заметок
 */
class NotesApp {
    private var archives: ArchivesStorage = ArchivesStorage("Main")
    private var screen: Screen = Screen.StorageScreen(null, archives) { screen = it }

    init {
        start()
    }

    /**
     * Отсеивает текстовый ввод, передает идентификатор команды
     */
    private fun execute(line: String) {
        val command: Int?

        try {
            command = line.toInt()
        } catch(e: NumberFormatException) {
            println("Допустимы только номера пунктов меню, повторите ввод")
            return
        }

        screen.executeCommand(command)
    }

    /**
     * Запускает приложение, выводит меню
     */
    fun start() {
        while (true) {
            screen.printMenu()
            execute(Listener.listen())
        }
    }

}

