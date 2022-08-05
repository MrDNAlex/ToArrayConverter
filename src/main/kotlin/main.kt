import java.io.File


lateinit var clean: String
lateinit var Type: String
lateinit var Remove: String
lateinit var finalinfo: String
lateinit var task: String
var splitNum: Int = 0
var lastPercent: Int = 0;
lateinit var RemAr: ArrayList<String>


fun main() {
println("What kind of task are you trying to accomplish? [clean] [sort]")
    task = readLine().toString();

    when (task) {

        "clean" -> cleanToArray();
        "sort" -> sortToArray();
        else -> {

        }

    }
}

fun sortToArray() {

    var InputInfo =
        File("A:\\Home\\Programming Projects\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\input").readLines() as ArrayList<String>


    println("how do you want to sort it? [alpha] [length]")
    var type: String = readLine().toString();
    when (type) {
        "alpha" -> {
            //Sort the items alphabetically

        }
        "length" -> {
            //Sort the items by length
            println("How do you want to sort by length? [sort] [remove]")
            var lengthType = readLine().toString();

            when (lengthType) {
                "sort" -> {

                }
                "remove" -> {

                    var finalList = arrayListOf<String>();

                    println("How long are the words you are sorting for?")
                    var wordLength: Int = Integer.parseInt(readLine().toString())

                    println("Do the words need to be cleaned? [yes] [no]")
                    var cleanStat = readLine().toString()

                    if (cleanStat == "yes") {
                        println("Type all unwanted characters")
                        Remove = readLine().toString()

                        RemAr = arrayListOf<String>()
                        for (i: Char in Remove) {
                            RemAr.add(i.toString())
                        }
                    }

                    println("What type are the words? [string] [int]")
                    Type = readLine().toString()

                    var count = 0;
                    for (i in InputInfo) {
                        percentComplete(count, InputInfo.size);
                        if (i.length == wordLength) {
                            finalList.add(i);
                        }
                        count++;
                    }

                    finalinfo = format(cleanerArray(finalList, RemAr), Type);

                    File("A:\\Home\\Programming Projects\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\output").writeText(
                        finalinfo
                    )
                }
            }
        }
    }
}


fun cleanToArray() {
    var InputInfo =
        File("A:\\Home\\Programming Projects\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\input").readLines() as ArrayList<String>
    println("does it need cleanup? [yes] [no]")
    clean = readLine().toString()
    println("What type is this? [string] [int]")
    Type = readLine().toString()
    println("How would you like to split it? [Number]")
    splitNum = Integer.parseInt(readLine().toString())
    println("Type all characters you want removed from the input")
    Remove = readLine().toString()

    if (clean.equals("yes")) {

        // InputInfo = File("A:\\Home\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\input").readLines().toString()
        var strInfo = ""
        for (i in InputInfo) {
            strInfo = strInfo + i
        }
        RemAr = arrayListOf<String>()
        for (i: Char in Remove) {
            RemAr.add(i.toString())
        }
        // println(cleaner(strInfo, RemAr))

        //  println(splitter(cleaner(strInfo, RemAr), splitNum))

        //  println(format(splitter(cleaner(strInfo, RemAr), splitNum), Type))

        finalinfo = format(splitter(cleanerChar(strInfo, RemAr), splitNum), Type)

    } else {
        // println(format(InputInfo, Type))
        finalinfo = format(InputInfo, Type)
    }

    File("A:\\Home\\Programming Projects\\IntelliJ Project\\codingsimplifier\\src\\main\\resources\\output").writeText(
        finalinfo
    )
}


fun cleanerChar(input: String, removable: ArrayList<String>): String {
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

fun cleanerArray (input: ArrayList<String>, removable: ArrayList<String>) : ArrayList<String> {

    var newArray = arrayListOf<String>();

    for (i in input) {
        var verdict = true;
        for (j in removable) {
            if (i.contains(j)) {
                verdict = false;
            }
        }
        if (verdict) {
            newArray.add(i);
        }
    }

    return newArray;
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


fun percentComplete(currentIndex: Int, total: Int) {
    var Percent = Math.floor(((currentIndex / total) * 100).toDouble()).toInt();
    if (Percent > lastPercent) {
        lastPercent = Percent;
        println(Percent.toString() + "%");
    }

}


