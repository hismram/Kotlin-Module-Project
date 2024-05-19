import java.util.Scanner

/**
 * Класс для считывания ввода
 */
class Listener {
    companion object {
        fun listen(): String {
            return Scanner(System.`in`).nextLine().trim()
        }

        /**
         * Выводит переданное сообщение и считывает строку
         * Если строка пустая выводит предупреждение
         */
        fun readLine(
            actionMsg: String,
            emptyMsg: String
        ): String {
            var result = ""

            while (result.isEmpty()) {
                println(actionMsg)
                result = listen()

                if (result.isEmpty()) {
                    println(emptyMsg)
                }
            }

            return result
        }
    }
}