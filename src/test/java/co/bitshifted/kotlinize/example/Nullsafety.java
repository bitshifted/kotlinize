/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.example;

import static co.bitshifted.kotlinize.NullAware.*;
import static org.junit.jupiter.api.Assertions.*;

import co.bitshifted.kotlinize.data.Address;
import co.bitshifted.kotlinize.data.City;
import co.bitshifted.kotlinize.data.MutableData;
import co.bitshifted.kotlinize.data.Person;
import org.junit.jupiter.api.Test;

/** Example class to demonstrate null safety concepts. */
public class Nullsafety {

  @Test
  void example() {
    var city = new City("Springfield", "10001");
    var homeAddr = new Address("EVergreen Terrace 742", city, "USA");
    var workAddr = new Address("742 Work St", null, "USA");
    var person = new Person("Homer", "Simpson", homeAddr, workAddr);

    // accessing null-properties throws NullPointerException
    try {
      var cityName = person.workAddress().city().name();
    } catch (NullPointerException ex) {
      System.out.println(
          "Caught NullPointerException when accessing null property: " + ex.getMessage());
    }
    // using NullAware to safely access potentially null properties. This will not throw an
    // exception
    var cityNameSafe = nullSafe(() -> person.workAddress().city().name());
    System.out.println("Safely accessed city name: " + cityNameSafe);

    // return default value if null encountered
    cityNameSafe = nullSafe(() -> person.homeAddress().city().name(), "New York");
    System.out.println("Safely accessed city name with default: " + cityNameSafe);
    // non-null value is returned as is
    cityNameSafe = nullSafe(() -> person.homeAddress().city().name());
    assertEquals(homeAddr.city().name(), cityNameSafe);

    // replicates Kotlin elvis operator (?:)
    var zipCode = nullAware(() -> person.workAddress().city().zipCode()).ifNull(() -> "00000");
    System.out.println("Safely accessed zip code with default: " + zipCode);
    assertEquals("00000", zipCode);

    // using let to process non-null values
    var upperCityName =
        nullAware(() -> person.homeAddress().city().name()).let(name -> name.toUpperCase());
    assertEquals("SPRINGFIELD", upperCityName);

    // using apply to modify mutable objects
    var mutable = new MutableData("status 1");
    var finalStatus = nullAware(() -> mutable).apply(m -> m.setStatus("updated status"));
    assertEquals("updated status", finalStatus.getStatus());
    // running apply on null does nothing and returns null
    finalStatus = nullAware(() -> (MutableData) null).apply(m -> m.setStatus("will not be set"));
    assertNull(finalStatus);
  }
}
