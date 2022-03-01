import java.text.DecimalFormat

fun main() {
    var selection = 1

    while (selection != 3) {
        selection = valid() //validation loop

        if (selection == 1){
            println("Enter your name:")
            val name = readLine()!!.toString()
            println("Enter your Phone Number:")
            val phoneNumber = readLine()!!.toString()
            println("Enter your address:")
            val address = readLine()!!.toString()
            println("Enter the Square Footage of the area you need mowed:")
            val squareFeet = readLine()!!.toDouble()
            val senior = seniorDiscount() // boolean variable
            var customer1 = Residential(senior, 6.0/1000.00, "$name", "$phoneNumber","$address",squareFeet)
            var cost = customer1.doCalculate()
            var info = customer1.customerInfo
            println("$info\n$cost")


        }
        else if (selection == 2){
            println("Enter your name:")
            val name = readLine()!!.toString()
            println("Enter your Phone Number:")
            val phoneNumber = readLine()!!.toString()
            println("Enter your address:")
            val address = readLine()!!.toString()
            println("Enter the Square Footage of the area you need mowed:")
            val squareFeet = readLine()!!.toDouble()
            val multi = multiProperty() // boolean variable
            var customer2 = Commercial(multi, 5.0/1000.00, "$name", "$phoneNumber","$address",squareFeet)
            var cost = customer2.doCalculate()
            var info = customer2.customerInfo
            println("$info\n$cost")
        }



    }
}
    fun valid(): Int {
        println("Are you a Residential or Commercial customer?\n1. Residential\n2. Commercial\n3. Quit")
        var choice = readLine()!!.toInt()
        val range = 1..3
        while (choice !in range){ // if not a valid selection
            println("Please make a valid selection:\nAre you a Residential or Commercial customer?\n1. Residential\n2. Commercial\n3. Quit")
            choice = readLine()!!.toInt()
        }

        return choice
    }

    fun multiProperty(): Boolean{
        println("Do you qualify for the multi-property discount?\n1. yes\n2. no")
        var multi = false
        var choice = readLine()!!.toInt()
        val range = 1..2
        while (choice !in range){ // if not a valid selection
            println("Please make a valid selection:\nDo you qualify for the multi-property discount?\n1. Yes\n2. No")
            choice = readLine()!!.toInt()
        }
        if (choice == 1){
            multi = true
        }
        else if (choice == 2){
            multi = false
        }
        return multi
    }

    fun seniorDiscount(): Boolean {
        println("Do you qualify for the senior discount?\n1. yes\n2. no")
        var senior = false
        var choice = readLine()!!.toInt()
        val range = 1..2
        while (choice !in range){ // if not a valid selection
            println("Please make a valid selection:\nDo you qualify for the senior discount?\n1. Yes\n2. No")
            choice = readLine()!!.toInt()
        }
        if (choice == 1){
            senior = true
        }
        else if (choice == 2){
            senior = false
        }
        return senior
    }

    open class Customer constructor(
        customerName: String,
        customerPhone: String,
        customerAddress: String,
        squareFootage: Double
    ) {
        var customerName: String = ""
            get() = field
            set(value) {
                field = value
            }
        var customerPhone: String = ""
            get() = field
            set(value) {
                field = value
            }
        var customerAddress: String = ""
            get() = field
            set(value) {
                field = value
            }
        var squareFootage: Double = 0.0
            get() = field
            set(value) {
                field = value
            }
        var customerInfo: String = ""

        init {
            this.customerName = customerName
            this.customerPhone = customerPhone
            this.customerAddress = customerAddress
            this.squareFootage = squareFootage
            this.customerInfo = setCustomerInfo().toString()

        }

        fun setCustomerInfo(): String {
            return "$customerName\n$customerPhone\n$customerAddress"
        }
    }


    class Commercial constructor(
        multiProperty: Boolean,
        rate: Double,
        customerName: String,
        customerPhone: String,
        customerAddress: String,
        squareFootage: Double
    ) : Customer(
        customerName,
        customerPhone,
        customerAddress,
        squareFootage
    ) {
        var multiProperty: Boolean = false
            get() = field
            set(value) {
                field = value
            }
        var rate: Double = 5.0/1000.00
            get() = field
            set(value) {
                field = value
            }
        var calculate: String = ""
        init {
            this.multiProperty = multiProperty
            this.rate = rate
            this.calculate = doCalculate()
        }

        fun doCalculate(): String{
            var money = DecimalFormat("$###,###,###.00") // format for money
            var feet = DecimalFormat("###,###,###,###.00") // format for square footage
            var cost = 0.0
            if (multiProperty == true){
                cost = (rate * .9) * squareFootage
            }
            else{
                cost = rate * squareFootage
            }
            return "The cost to mow your ${feet.format(squareFootage)} square feet will cost ${money.format(cost)} a week"
        }

    }

    class Residential constructor(
        senior: Boolean,
        rate: Double,
        customerName: String,
        customerPhone: String,
        customerAddress: String,
        squareFootage: Double
    ) : Customer(
        customerName,
        customerPhone,
        customerAddress,
        squareFootage
    ) {
        var senior: Boolean = false
            get() = field
            set(value) {
                field = value
            }
        var rate: Double = 6.0/1000.0
            get() = field
            set(value) {
                field = value
            }
        var calculate: String = ""

        init {
            this.senior = senior
            this.rate = rate
            this.calculate = doCalculate()
        }
    fun doCalculate(): String{
        var money = DecimalFormat("$###,###,###.00") // format for money
        var feet = DecimalFormat("###,###,###,###.00") // format for square feet
        var cost = 0.0
        if (senior == true){
            cost = (rate * .85) * squareFootage
        }
        else{
            cost = rate * squareFootage
        }
        return "The cost to mow your ${feet.format(squareFootage)} square feet will cost ${money.format(cost)} a week"
    }
    }
