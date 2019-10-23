class GaussianElimination {
    private var matrixSize: Int = 0

    fun solve(matrix: Array<DoubleArray>, answers: DoubleArray) {

        matrixSize = answers.size
        matrix.getMatrixViewFrom()

        for (columnNum in 0 until matrixSize) {

            val pivotRowNumber = findPivotRowNumber(columnNum, matrix)

            matrix.swapRowInMatrix(columnNum, pivotRowNumber)

            answers.swapAnswers(columnNum, pivotRowNumber)

            /** pivot within matrix and answers  */
            for (rowNum in columnNum + 1 until matrixSize) {
                val factor = matrix[rowNum][columnNum] / matrix[columnNum][columnNum]
                answers[rowNum] -= factor * answers[columnNum]
                for (j in columnNum until matrixSize)
                    matrix[rowNum][j] -= factor * matrix[columnNum][j]
            }
        }

        /** Print row echelon form  */
        printRowEchelonForm(matrix, answers)

        /** back substitution  */
        val solution = DoubleArray(matrixSize)
        for (i in matrixSize - 1 downTo 0) {
            var sum = 0.0
            for (j in i + 1 until matrixSize)
                sum += matrix[i][j] * solution[j]
            solution[i] = (answers[i] - sum) / matrix[i][i]
        }
        /** Print solution  */
        printSolution(solution)
    }

    private fun findPivotRowNumber(columnNumber: Int, matrix: Array<DoubleArray>): Int {
        var pivotRowNumber = columnNumber

        for (rowNumber in columnNumber + 1 until matrixSize) {

            val isThisNumberTheBiggestInTheRow =
                Math.abs(matrix[rowNumber][columnNumber]) > Math.abs(matrix[pivotRowNumber][columnNumber])

            if (isThisNumberTheBiggestInTheRow) {
                pivotRowNumber = rowNumber
            }
        }
        return pivotRowNumber
    }

    private fun Array<DoubleArray>.swapRowInMatrix(firstRowNum: Int, secondRowNum: Int) {
        val firstRow = this[firstRowNum]
        this[firstRowNum] = this[secondRowNum]
        this[secondRowNum] = firstRow
    }

    private fun DoubleArray.swapAnswers(firstRowNum: Int, secondRowNum: Int) {
        val firstAnswer = this[firstRowNum]
        this[firstRowNum] = this[secondRowNum]
        this[secondRowNum] = firstAnswer
    }

    /** function to print in row    echleon form  */
    fun printRowEchelonForm(A: Array<DoubleArray>, B: DoubleArray) {
        val N = B.size
        println("\nRow Echelon form : ")
        for (i in 0 until N) {
            for (j in 0 until N)
                System.out.printf("%.3f ", A[i][j])
            System.out.printf("| %.3f\n", B[i])
        }
        println()
    }

    /** function to print solution  */
    fun printSolution(sol: DoubleArray) {
        val N = sol.size
        println("\nSolution : ")
        for (i in 0 until N)
            System.out.printf("%.3f ", sol[i])
        println()
    }


    private fun Array<DoubleArray>.getMatrixViewFrom(): String {
        var a = "matrix = "
        for (column in 0 until this.size) {
            for (number in this[column]) {
                if (number > -1) {
                    a += " "
                }
                a += (number.toString() + "  ")
            }
            a += ("\n         ")
        }
        println(a)
        return a
    }
}