/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.cactoos.scalar;

import org.cactoos.Scalar;

/**
 * Throws an exception on false expression.
 *
 * @since 0.56.0
 */
public final class ThrowsOnFalse implements Scalar<Boolean> {

    /**
     * Logical statement.
     */
    private final Scalar<Boolean> scalar;

    /**
     * Exception to throw.
     */
    private final Scalar<Exception> exception;

    /**
     * Ctor.
     * @param sclr Scalar
     * @param message Error Message
     */
    public ThrowsOnFalse(
        final Scalar<Boolean> sclr,
        final String message
    ) {
        this(
            sclr, () -> new IllegalArgumentException(message)
        );
    }

    /**
     * Ctor.
     * @param sclr Scalar
     * @param exc Exception
     */
    public ThrowsOnFalse(
        final Scalar<Boolean> sclr,
        final Scalar<Exception> exc
    ) {
        this.scalar = sclr;
        this.exception = exc;
    }

    @Override
    public Boolean value() throws Exception {
        if (!this.scalar.value()) {
            throw this.exception.value();
        }
        return true;
    }
}
