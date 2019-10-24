class GaussianElimination {
    private var matrixSize: Int = 0
    private lateinit var matrix: Array<DoubleArray>
    private lateinit var answers: DoubleArray

    fun solve(inputMatrix: Array<DoubleArray>, inputAnswers: DoubleArray) {

        matrix = inputMatrix
        answers = inputAnswers

        matrixSize = answers.size
        matrix.print()

        for (columnNum in 0 until matrixSize) {

            val pivotRowNumber = findPivotRowNumber(columnNum, matrix)

            matrix.swapRows(columnNum, pivotRowNumber)

            answers.swap(columnNum, pivotRowNumber)

            pivotWithin(columnNum)
        }

        printRowEchelonForm()

        val solution = getSolution()

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

    private fun Array<DoubleArray>.swapRows(firstRowNum: Int, secondRowNum: Int) {
        val firstRow = this[firstRowNum]
        this[firstRowNum] = this[secondRowNum]
        this[secondRowNum] = firstRow
    }

    private fun DoubleArray.swap(firstRowNum: Int, secondRowNum: Int) {
        val firstAnswer = this[firstRowNum]
        this[firstRowNum] = this[secondRowNum]
        this[secondRowNum] = firstAnswer
    }

    private fun pivotWithin(startColumnNumber: Int) {
        for (rowNum in startColumnNumber + 1 until matrixSize) {

            val factor = matrix[rowNum][startColumnNumber] / matrix[startColumnNumber][startColumnNumber]
            answers[rowNum] -= factor * answers[startColumnNumber]

            for (columnNum in startColumnNumber until matrixSize) {
                matrix[rowNum][columnNum] -= factor * matrix[startColumnNumber][columnNum]
            }
        }
    }

    private fun printRowEchelonForm() {
        val answersSize = answers.size

        println("Row Echelon form : ")

        for (rowNum in 0 until answersSize) {
            for (columnNum in 0 until answersSize) {
                System.out.printf("%.3f ", matrix[rowNum][columnNum])
            }
            System.out.printf("| %.3f\n", answers[rowNum])
        }
        println()
    }

    private fun getSolution(): DoubleArray {
        val solutions = DoubleArray(matrixSize)

        for (rowNum in matrixSize - 1 downTo 0) {
            var sum = 0.0

            for (columnNum in rowNum + 1 until matrixSize) {
                sum += matrix[rowNum][columnNum] * solutions[columnNum]
            }
            solutions[rowNum] = (answers[rowNum] - sum) / matrix[rowNum][rowNum]
        }
        return solutions
    }

    private fun printSolution(solutions: DoubleArray) {
        val solutionsSize = solutions.size

        println("\nSolution : ")

        for (solutionNum in 0 until solutionsSize) {
            System.out.printf("%.3f ", solutions[solutionNum])
        }
        println()
    }

    private fun Array<DoubleArray>.print(): String {
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