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
package org.cactoos.func;

import org.cactoos.BiFunc;
import org.cactoos.BiProc;
import org.cactoos.Func;
import org.cactoos.Proc;
import org.cactoos.Scalar;

/**
 * Represents many possible inputs as {@link BiFunc}.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <X> Type of input
 * @param <Y> Type of input
 * @param <Z> Type of output
 * @since 0.20
 */
public final class BiFuncOf<X, Y, Z> extends BiFuncEnvelope<X, Y, Z> {

    /**
     * Ctor.
     * @param scalar The scalar
     */
    public BiFuncOf(final Scalar<Z> scalar) {
        this((first, second) -> scalar.value());
    }

    /**
     * Ctor.
     * @param fnc The func
     */
    public BiFuncOf(final Func<X, Z> fnc) {
        this((first, second) -> fnc.apply(first));
    }

    /**
     * Ctor.
     * @param proc The proc
     * @param result Result to return
     */
    public BiFuncOf(final Proc<X> proc, final Z result) {
        this(
            (first, second) -> {
                proc.exec(first);
                return result;
            }
        );
    }

    /**
     * Ctor.
     * @param proc The proc
     * @param result Result to return
     */
    public BiFuncOf(final BiProc<X, Y> proc, final Z result) {
        this(
            (first, second) -> {
                proc.exec(first, second);
                return result;
            }
        );
    }

    /**
     * Ctor.
     * @param fnc The func
     */
    public BiFuncOf(final BiFunc<X, Y, Z> fnc) {
        super(
            fnc::apply
        );
    }
}
