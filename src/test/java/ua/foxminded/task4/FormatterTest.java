package ua.foxminded.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormatterTest {
    @Test
    void testForActualStringFromTheTask() {
        Calculator calculator = new Calculator();
        LongDivisionResult resultObject = calculator.doLongDivision(78945, 4);
        Formatter formatter = new Formatter();
        String result = formatter.printResult(resultObject);
        Assertions.assertEquals("_78945|4\n" +
                " 4    |-----\n" +
                " -    |19736\n" +
                "_38\n" +
                " 36\n" +
                " --\n" +
                " _29\n" +
                "  28\n" +
                "  --\n" +
                "  _14\n" +
                "   12\n" +
                "   --\n" +
                "   _25\n" +
                "    24\n" +
                "    --\n" +
                "      1", result);
    }
}