package ro.tuiasi.pp.lab7

import java.io.File

fun main() {
    val file = File("history.log")

    if (!file.exists()) {
        println("Fișierul 'history.log' nu a fost găsit. Asigură-te că ai rulat: cp history.sample.log history.log")
        return
    }

    val logContent = file.readText()

    val logMap = HistoryParser.parseLogContent(logContent)

    println("Au fost procesate cu succes ${logMap.size} intrări și salvate în MutableHashMap.\n")
    println("-".repeat(40))


    if (logMap.size >= 2) {
        val valuesList = logMap.values.toList()
        val record1 = valuesList[0]
        val record2 = valuesList[1]


        val maxRecord = getMaxRecord(record1, record2)
        println("TEST FUNCȚIE MAXIM:")
        println("Record A: $record1")
        println("Record B: $record2")
        println("=> Cel mai recent (maximul) este: $maxRecord\n")


        val dummyRecord = HistoryLogRecord(999999999L, "apt-get install inlocuire-test")
        println("TEST CĂUTARE ȘI ÎNLOCUIRE:")
        println("Căutăm: $record1")
        println("Înlocuim cu: $dummyRecord")

        findAndReplace(searchFor = record1, replaceWith = dummyRecord, map = logMap)

        val isReplaced = logMap.containsValue(dummyRecord)
        println("=> Obiectul a fost înlocuit cu succes în dicționar: $isReplaced")
    }
}