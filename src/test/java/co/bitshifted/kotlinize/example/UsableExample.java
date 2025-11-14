/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.example;

import static co.bitshifted.kotlinize.Usable.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class UsableExample {

  static class TestResource implements AutoCloseable {
    private boolean closed = false;
    private final String value;

    TestResource(String value) {
      this.value = value;
    }

    String read() {
      return value;
    }

    boolean isClosed() {
      return closed;
    }

    @Override
    public void close() {
      closed = true;
    }
  }

  @Test
  void exampleUse() {
    var value =
        usable(() -> new TestResource("foo"))
            .use(
                r -> {
                  assertFalse(r.isClosed());
                  return r.read();
                })
            .let(data -> data.toUpperCase());
    assertEquals("FOO", value);
  }
}
