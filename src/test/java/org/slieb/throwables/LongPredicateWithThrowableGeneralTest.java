package org.slieb.throwables;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.slieb.throwables.LongPredicateWithThrowable.castLongPredicateWithThrowable;
import static org.junit.Assert.assertEquals;
public class LongPredicateWithThrowableGeneralTest {
 @Test
 public void testReturnTypeExceptionWithTrue() {
        boolean expected = true;
        boolean result = castLongPredicateWithThrowable((v1) -> {
      throw new Exception("expect exception");
    }).thatReturnsOnCatch(expected).test(0);
        assertEquals(expected, result);
 }

 @Test
 public void testReturnTypeExceptionWithFalse() {
        boolean expected = false;
        boolean result = castLongPredicateWithThrowable((v1) -> {
      throw new Exception("expect exception");
    }).thatReturnsOnCatch(expected).test(0);
        assertEquals(expected, result);
 }

 @Test
 public void testNormalOperation() {
    boolean expected = true;
    boolean result = castLongPredicateWithThrowable((v1) -> expected).thatReturnsOnCatch(false).test(0);
    assertEquals(expected, result);
 }

 @Test
 public void testOnException() {
        AtomicReference<java.lang.Throwable> reference = new AtomicReference<>();
        java.lang.Exception expected = new java.lang.Exception("expected");
        try {
        castLongPredicateWithThrowable((v1) -> {
      throw expected;
    }).onException(reference::set).test(0);
        } catch (Throwable ignored) {}
        assertEquals(expected, reference.get());
 }

}
