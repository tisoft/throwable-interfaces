package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.SupplierWithThrowable.rethrowSupplier;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class SupplierWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowSupplier(() -> {
        throw expected;
      }).get();
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowSupplier(() -> {
        if(false) throw new IOException();
        return null;
      }).get();
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
