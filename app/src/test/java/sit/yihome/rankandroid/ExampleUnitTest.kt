package sit.yihome.rankandroid

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCalculator() {

    }

    fun calculator(expr: String) {
        var finalStack:Stack<Char> = Stack()
        var operatorStack:Stack<Char> = Stack()
        var preCh = ""
        for (ch in expr) {
            if(isNum(ch)){
                finalStack.push(ch)
            }else {
                when(ch){
                    '+' -> {
                        val opera = operatorStack.peek()
                        if (opera == '*'|| opera== '/'){

                        }
                    }
                    '-' -> {
                        val opera = operatorStack.peek()
                        if (opera == '*'|| opera== '/'){

                        }}
                    '(' -> operatorStack.push(ch)
                    ')' -> {
                        var ch1:Char =operatorStack.pop()
                        while (ch1 !='('){

                            ch1 = operatorStack.pop()
                        }
                    }
                    '*' ->operatorStack.push(ch)
                    '/' ->operatorStack.push(ch)
                }
            }
        }
    }

    fun isNum(char: Char):Boolean{
        return char in '0'..'9'
    }

    fun isOperator(char: Char):Boolean{
        return char=='*'||char=='/'||char=='-'||char=='+';
    }
}
