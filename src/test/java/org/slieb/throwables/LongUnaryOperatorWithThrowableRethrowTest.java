package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongUnaryOperatorWithThrowable.rethrowLongUnaryOperator;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongUnaryOperatorWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowLongUnaryOperator((v1) -> {
        throw expected;
      }).applyAsLong(0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowLongUnaryOperator((v1) -> {
        if(false) throw new IOException();
        return 0;
      }).applyAsLong(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
