package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.DoubleSupplierWithThrowable.rethrowDoubleSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class DoubleSupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowDoubleSupplier(() -> {
        throw expected;
      }).getAsDouble();
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowDoubleSupplier(() -> {
        if(false) throw new IOException();
        return 0;
      }).getAsDouble();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
