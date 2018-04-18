package sit.yihome.rankandroid

import org.junit.Assert
import org.junit.Test
import java.util.*

public class DataStuck{

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun testCalculator() {
        Assert.assertEquals(20, calculator("9+(3-1)*3+10/2"))
    }

    fun calculator(expr: String):Int {
        var finalStack: Stack<Int> = Stack()
        var operatorStack: Stack<Char> = Stack()
        var preCh = ""
        for (ch in expr) {
            if (isNum(ch)) {
                finalStack.push(ch.toInt()-'0'.toInt())
            } else {
                when (ch) {
                    '+', '-' -> {
                        while (!operatorStack.empty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                            finalStack.push(calculate(operatorStack.pop(), finalStack.pop(), finalStack.pop()))
                        }
                        operatorStack.push(ch)
                    }
                    '(' -> operatorStack.push(ch)
                    ')' -> {
                        var ch1: Char = operatorStack.pop()
                        while (ch1 != '(') {
                            finalStack.push(calculate(ch1, finalStack.pop(), finalStack.pop()))
                            ch1 = operatorStack.pop()
                        }
                    }
                    '*', '/' -> operatorStack.push(ch)
                }
            }
        }
        while (!operatorStack.empty()) {
            finalStack.push(calculate(operatorStack.pop(), finalStack.pop(), finalStack.pop()))
        }
        return finalStack.pop()
    }

    private fun calculate(operator: Char, num2: Int, num1: Int): Int {
        return when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> 0
        }
    }

    fun isNum(char: Char): Boolean {
        return char in '0'..'9'
    }


}
