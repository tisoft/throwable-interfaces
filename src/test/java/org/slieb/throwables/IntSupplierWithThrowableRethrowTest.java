package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.IntSupplierWithThrowable.aIntSupplierThatUnSafelyThrowsUncheckedThrowable;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class IntSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      aIntSupplierThatUnSafelyThrowsUncheckedThrowable(() -> {
        throw expected;
      }).getAsInt();
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      aIntSupplierThatUnSafelyThrowsUncheckedThrowable(() -> {
        if(false) throw new IOException();
        return 0;
      }).getAsInt();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
