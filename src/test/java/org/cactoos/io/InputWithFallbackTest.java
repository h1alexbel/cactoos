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
package org.cactoos.io;

import java.io.File;
import java.io.IOException;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.EndsWith;

/**
 * Test case for {@link InputWithFallback}.
 *
 * @since 0.9
 * @checkstyle JavadocMethodCheck (500 lines)
 */
final class InputWithFallbackTest {

    @Test
    void readsAlternativeInput() {
        new Assertion<>(
            "Can't read alternative source",
            new TextOf(
                new InputWithFallback(
                    new InputOf(
                        new File("/this-file-is-absent-for-sure.txt")
                    ),
                    new InputOf("hello, world!")
                )
            ),
            new EndsWith("world!")
        ).affirm();
    }

    @Test
    void readsAlternativeInputUri() {
        new Assertion<>(
            "Can't read alternative source from URI",
            new TextOf(
                new InputWithFallback(
                    () -> {
                        throw new IOException("Always fails!");
                    },
                    new InputOf("it works!")
                )
            ),
            new EndsWith("works!")
        ).affirm();
    }

}
