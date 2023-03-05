import com.stilyanov.ArithmeticExpressionChecker;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ArithmeticExpressionCheckerTest {

    @BeforeClass
    public static void init() {
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
    }

    @Test
    public void testIsValidArithmeticExpressionWithValidExpressions() {
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
        String[] validExpressions = {
                "a",
                "-a",
                "a + b",
                "a + b - c",
                "a * b / c",
                "a + (b - c) / d"
        };
        for (String expression : validExpressions) {
            assertTrue(ArithmeticExpressionChecker.isArithmeticExpression(expression));
        }
    }

    @Test
    public void testIsValidArithmeticExpressionWithInvalidExpressions() {
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
        String[] invalidExpressions = {
                "a+",
                "*a",
                "ab",
                "a++b",
                "(a+b",
                "(a+b))"
        };
        for (String expression : invalidExpressions) {
            assertFalse(ArithmeticExpressionChecker.isArithmeticExpression(expression));
        }
    }

    @Test
    public void testEvaluateExpressionWithInvalidExpression() {
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
        String input = "a + * b";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ArithmeticExpressionChecker.evaluateExpression(input));
        assertEquals("Invalid arithmetic expression", exception.getMessage());
    }
}