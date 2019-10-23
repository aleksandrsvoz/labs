import java.util.*

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    println("Gaussian Elimination Algorithm Test\n")

//    println("\nEnter number of variables")
//    val N = scan.nextInt()
    val N = 3

    val matrixA = Array(N) { DoubleArray(N) }
    val matrixB = DoubleArray(N)

//    println("\nEnter $N equations coefficients ")
//    for (i in 0 until N)
//        for (j in 0 until N)
//            matrixA[i][j] = scan.nextDouble()

    matrixA[0][0] = 2.0
    matrixA[0][1] = 1.0
    matrixA[0][2] = -1.0

    matrixA[1][0] = -3.0
    matrixA[1][1] = -1.0
    matrixA[1][2] = 2.0

    matrixA[2][0] = -2.0
    matrixA[2][1] = 1.0
    matrixA[2][2] = 2.0



//    println("\nEnter $N solutions")
//    for (i in 0 until N)
//        matrixB[i] = scan.nextDouble()

    matrixB[0] = 8.0
    matrixB[1] = -11.0
    matrixB[2] = -3.0


    GaussianElimination().solve(matrixA, matrixB)
}