package org.slieb.throwables;

import java.lang.FunctionalInterface;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.LongPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Generated from LongPredicate
 * Extends java.util.function.LongPredicate to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface LongPredicateWithThrowable<E extends Throwable> extends LongPredicate {

    /**
     * Utility method to mark lambdas of type LongPredicateWithThrowable
     *
     * @param longpredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongPredicateWithThrowable<E> castLongPredicateWithThrowable(final LongPredicateWithThrowable<E> longpredicatewiththrowable) {
        return longpredicatewiththrowable;
    }

    /**
     * Utility method to unwrap lambdas of type LongPredicate and rethrow any Exception
     *
     * @param longpredicatewiththrowable The interface instance
     * @param <E> The type this interface is allowed to throw
     * @throws E the original Exception from longpredicatewiththrowable
     * @return the cast interface
     */
    static <E extends Throwable> LongPredicate rethrowLongPredicate(final LongPredicateWithThrowable<E> longpredicatewiththrowable) throws E {
        return longpredicatewiththrowable.rethrow();
    }

    /**
     * Utility method to convert LongPredicateWithThrowable
     * @param longpredicate The interface instance
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> LongPredicateWithThrowable<E> asLongPredicateWithThrowable(final LongPredicate longpredicate) {
        return longpredicate::test;
    }

    /**
     * Overridden method of LongPredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(final long v1) {
        try {
            return testWithThrowable(v1);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    /**
     * Functional method that will throw exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     * @throws E some exception
     */
    boolean testWithThrowable(final long v1) throws E;


    /**
     * @param defaultReturnValue A value to return if any throwable is caught.
     * @return An interface that returns a default value if any exception occurs.
     */
    default LongPredicate thatReturnsOnCatch(final boolean defaultReturnValue) {
      return (final long v1) -> {
        try {
          return testWithThrowable(v1);
        } catch(final Throwable throwable) {
          return defaultReturnValue;
        }
      };
    }


    /**
     * @throws E if an exception E has been thrown, it is rethrown by this method
     * @return An interface that is only returned if no exception has been thrown.
     */
    default LongPredicate rethrow() throws E {
      return (final long v1) -> {
        try {
          return testWithThrowable(v1);
        } catch(final Throwable throwable) {
          SuppressedException.throwAsUnchecked(throwable);
          throw new RuntimeException("Unreachable code.");
        }
      };
    }


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongPredicateWithThrowable<E> withLogging(final Logger logger, final String message) {
        return (final long v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.error(message, v1, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default LongPredicateWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in LongPredicateWithThrowable with the argument [{}]");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default LongPredicateWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongPredicateWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return (final long v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }


    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default LongPredicateWithThrowable<E> onException(final java.util.function.BiConsumer<Throwable, Object[]> consumer) {
        return (final long v1) -> {
            try {
                return testWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable, new Object[]{v1});
                throw throwable;
            }
        };
    }
}
