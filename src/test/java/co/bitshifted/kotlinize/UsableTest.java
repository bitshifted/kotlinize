/*
 * Copyright © 2025, Bitshift D.O.O <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UsableTest {

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
  void usableInitializesAndUseClosesResource() throws Exception {
    TestResource res = new TestResource("hello");
    var usable = Usable.usable(() -> res);

    // inside the block the resource must not be closed
    var out =
        usable.use(
            r -> {
              assertFalse(r.isClosed());
              return r.read();
            });

    // the returned NullAware should contain the value
    assertEquals("hello", out.value());

    // resource must be closed after use returns
    assertTrue(res.isClosed());
  }

  @Test
  void usePropagatesExceptionAndClosesResource() {
    TestResource res = new TestResource("hello");
    var usable = Usable.usable(() -> res);

    var ex =
        assertThrows(
            RuntimeException.class,
            () ->
                usable.use(
                    r -> {
                      throw new RuntimeException("boom");
                    }));
    assertEquals("boom", ex.getMessage());

    // resource must be closed even when the block throws
    assertTrue(res.isClosed());
  }
}
