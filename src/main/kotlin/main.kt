import java.io.File

lateinit var clean: String
lateinit var Type: String
lateinit var Remove: String
lateinit var finalinfo: String
var splitNum: Int = 0
lateinit var RemAr: ArrayList<String>



fun main()  {

    var InputInfo = File("A:\\Home\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\input").readLines() as ArrayList<String>
    println("does it need cleanup")
    clean = readLine().toString()
    println("What type is this")
    Type = readLine().toString()

    if (clean.equals("yes")) {

        // InputInfo = File("A:\\Home\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\input").readLines().toString()
            var strInfo = ""
        for (i in InputInfo) {
           strInfo = strInfo + i
        }

        println("How would you like to split it")
        splitNum = Integer.parseInt(readLine().toString())
        println("Type all characters you want removed from the input")
        Remove = readLine().toString()
        RemAr = arrayListOf<String>()
        for (i: Char in Remove) {
            RemAr.add(i.toString())
        }
        println(cleaner(strInfo, RemAr))

        println(splitter(cleaner(strInfo, RemAr), splitNum))

        println(format(splitter(cleaner(strInfo, RemAr), splitNum), Type))

        finalinfo = format(splitter(cleaner(strInfo, RemAr), splitNum), Type)

    } else {
        println(format(InputInfo, Type))
        finalinfo = format(InputInfo, Type)
    }

    File("A:\\Home\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\output").writeText(finalinfo)



}


fun cleaner(input: String, removable: ArrayList<String>): String {
    var InpAr = arrayListOf<String>()
    var Out: String = ""
    for (i: Char in input) {
        InpAr.add(i.toString())
    }

    for (i in removable) {
        while (InpAr.contains(i)) {
            InpAr.remove(i)
        }
    }

    for (i in InpAr) {
        Out = Out + i
    }
    return Out
}

fun splitter(input: String, space: Int): ArrayList<String> {

    var array = arrayListOf<String>()

    var tempSt = ""
    for (i: Char in input) {
        tempSt = tempSt + i
        if (tempSt.length == space) {
            array.add(tempSt)
            tempSt = ""
        }
    }
    return array
}

fun format(inAr: ArrayList<String>, type: String): String {

    when (type.toLowerCase()) {
        "string" -> {
            var output = "{"

            for (i in inAr) {
                output = output + "\u0022" + i + "\u0022" + " " + ","
            }
            output = output + "}"
            return output
        }
        "int" -> {
            var output = "{"
            for (i in inAr) {
                output = output + i + "," + " "
            }
            output = output + "}"

            return output
        }
        else -> {
            return "brokey"
        }
    }
}


