package org.slieb.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * Generated from Runnable
 * Extends java.lang.Runnable to allow for a checked exception.
 *
 * @param <E> The extension
 */
@FunctionalInterface
@SuppressWarnings({"WeakerAccess"})
public interface RunnableWithThrowable<E extends Throwable> extends Runnable {

    /**
     * Utility method to mark lambdas of type RunnableWithThrowable
     *
     * @param runnablewiththrowable The interface instance
     * @param <E>                   The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> RunnableWithThrowable<E> castRunnableWithThrowable(final RunnableWithThrowable<E> runnablewiththrowable) {
        return runnablewiththrowable;
    }

    /**
     * Utility method to convert RunnableWithThrowable
     *
     * @param runnable The interface instance
     * @param <E>      The type this interface is allowed to throw
     * @return the cast interface
     */
    static <E extends Throwable> RunnableWithThrowable<E> asRunnableWithThrowable(final Runnable runnable) {
        return runnable::run;
    }

    /**
     * Overridden method of RunnableWithThrowable that will call runWithThrowable, but catching any exceptions.
     */
    @Override
    default void run() {
        try {
            runWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    /**
     * Functional method that will throw exceptions.
     *
     * @throws E some exception
     */
    void runWithThrowable() throws E;

    /**
     * @return An interface that completely ignores exceptions. Consider using this method withLogging() as well.
     */
    default Runnable thatThrowsNothing() {
        return () -> {
            try {
                runWithThrowable();
            } catch (Throwable ignored) {}
        };
    }

    /**
     * @param logger  The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default RunnableWithThrowable<E> withLogging(final Logger logger, final String message) {
        return () -> {
            try {
                runWithThrowable();
            } catch (final Throwable throwable) {
                logger.error(message, throwable);
                throw throwable;
            }
        };
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default RunnableWithThrowable<E> withLogging(final Logger logger) {
        return withLogging(logger, "Exception in RunnableWithThrowable");
    }

    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     *
     * @return An interface that will log exceptions on global logger
     */
    default RunnableWithThrowable<E> withLogging() {
        return withLogging(LoggerFactory.getLogger(getClass()));
    }

    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default RunnableWithThrowable<E> onException(final Consumer<Throwable> consumer) {
        return () -> {
            try {
                runWithThrowable();
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
