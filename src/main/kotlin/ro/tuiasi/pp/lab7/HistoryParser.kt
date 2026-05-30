package ro.tuiasi.pp.lab7

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object HistoryParser {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun parseLogContent(logContent: String): MutableMap<Long, HistoryLogRecord> {

        val blocks = logContent.split("\n\n").filter { it.isNotBlank() }


        val last50Blocks = blocks.takeLast(50)
        val logMap = mutableMapOf<Long, HistoryLogRecord>()

        for (block in last50Blocks) {
            var startDateStr = ""
            var commandLine = ""

            val lines = block.lines()
            for (line in lines) {
                if (line.startsWith("Start-Date:")) {
                    startDateStr = line.substringAfter("Start-Date:").trim().replace(Regex("\\s+"), " ")
                } else if (line.startsWith("Commandline:")) {
                    commandLine = line.substringAfter("Commandline:").trim()
                }
            }


            if (startDateStr.isNotEmpty() && commandLine.isNotEmpty()) {
                try {
                    val ldt = LocalDateTime.parse(startDateStr, dateFormatter)
                    val timestamp = ldt.toEpochSecond(ZoneOffset.UTC)
                    logMap[timestamp] = HistoryLogRecord(timestamp, commandLine)
                } catch (e: Exception) {

                }
            }
        }
        return logMap
    }
}