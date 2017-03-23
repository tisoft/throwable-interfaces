package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.LongPredicateWithThrowable.aLongPredicateThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class LongPredicateWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aLongPredicateThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        throw expected;
      }).test(0);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aLongPredicateThatUnSafelyThrowsUncheckedThrowable((v1) -> {
        if(false) throw new IOException();
        return false;
      }).test(0);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
