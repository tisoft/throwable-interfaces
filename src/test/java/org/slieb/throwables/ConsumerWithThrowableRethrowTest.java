package org.slieb.throwables;
import org.junit.Test;
import java.io.IOException;
import static org.slieb.throwables.ConsumerWithThrowable.rethrowConsumer;
@java.lang.SuppressWarnings({"CodeBlock2Expr"})
public class ConsumerWithThrowableRethrowTest {


  @Test
  public void testThrowCheckedException() {
    IOException expected = new IOException("EXPECTED ERROR");
    IOException actual = null;
    try {
      rethrowConsumer((v1) -> {
        throw expected;
      }).accept(null);
      org.junit.Assert.fail("Exception should have been thrown");
    } catch (IOException e) {
      actual=e;
    }
    org.junit.Assert.assertEquals(expected, actual);
  }


  @Test
  public void testNormalOperation() {
    try {
      rethrowConsumer((v1) -> {
        if(false) throw new IOException();
      }).accept(null);
    } catch (IOException ignored) {
      org.junit.Assert.fail("");
    }
  }


}
