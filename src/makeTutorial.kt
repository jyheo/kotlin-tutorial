import java.io.File

fun writeSourceToFile(fileName : String, tmpFile : File)
{
    File(fileName).forEachLine {
        tmpFile.appendText("$it\n")
    }
}

fun main(args : Array<String>) {
    var bFence = false
    var bReplacing = false
    var srcFile : String

    val fileName =
        if (args.isNotEmpty()) args[0]
        else "kotlin-basic.md"
    val tmpFile = File("$fileName.tmp")

    tmpFile.delete()
    File(fileName).forEachLine {
        if (it == "```kotlin")
            bFence = true
        else if (bFence) {
            if (it == "```") {
                bFence = false
                bReplacing = false
            } else if (it.startsWith("//")) {
                srcFile = it.substringAfter(' ')
                if (srcFile.endsWith(".kt")) {
                    tmpFile.appendText("$it\n")
                    writeSourceToFile(srcFile, tmpFile)
                    bReplacing = true
                }
            }
        }
        if (!bReplacing) {
            tmpFile.appendText("$it\n")
        }
    }

    File(fileName).delete()
    tmpFile.renameTo(File(fileName))
}
