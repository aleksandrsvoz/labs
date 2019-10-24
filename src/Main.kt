import java.util.*

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    println("Gaussian Elimination Algorithm Test\n")

    println("\nEnter number of variables")
    val matrixSize = scan.nextInt()

    //Or constant
    //val N = 3

    val matrixA = Array(matrixSize) { DoubleArray(matrixSize) }
    val matrixB = DoubleArray(matrixSize)

    println("Enter $matrixSize equations coefficients ")
    for (rowNum in 0 until matrixSize) {
        for (columnNum in 0 until matrixSize) {
            matrixA[rowNum][columnNum] = scan.nextDouble()
        }
    }

    //Or constant
//    matrixA[0][0] = 2.0
//    matrixA[0][1] = 1.0
//    matrixA[0][2] = -1.0
//
//    matrixA[1][0] = -3.0
//    matrixA[1][1] = -1.0
//    matrixA[1][2] = 2.0
//
//    matrixA[2][0] = -2.0
//    matrixA[2][1] = 1.0
//    matrixA[2][2] = 2.0


    println("Enter $matrixSize solutions")
    for (index in 0 until matrixSize) {
        matrixB[index] = scan.nextDouble()
    }

//Or constant
//    matrixB[0] = 8.0
//    matrixB[1] = -11.0
//    matrixB[2] = -3.0


    GaussianElimination().solve(matrixA, matrixB)
}