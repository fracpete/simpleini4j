/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * INIFileTest.java
 * Copyright (C) 2018 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.simpleini4j;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Tests the INIFile class.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class INIFileTest
  extends TestCase {

  /**
   * Initializes the test.
   *
   * @param name	the name of the test
   */
  public INIFileTest(String name) {
    super(name);
  }

  /**
   * Tests the creation of an empty INI file.
   */
  public void testEmpty() {
    INIFile ini = new INIFile();
    assertEquals("Shouldn't have any sections", 0, ini.getSections().size());
  }

  /**
   * Tests the reading of an INI file.
   */
  public void testRead() {
    String filename = "src/test/resources/com/github/fracpete/simpleini4j/simple.ini";
    INIFile ini = INIFile.read(filename);
    assertNotNull("Failed to read: " + filename, ini);
  }

  /**
   * Tests basic operations.
   */
  public void testBasicOperations() {
    INIFile ini = new INIFile();
    assertEquals("Shouldn't have any sections", 0, ini.getSections().size());

    ini.set("section1", "key1", "value1");
    assertTrue("failed to add key!", ini.has("section1", "key1"));
    ini.set("section1", "key2", 1);
    assertTrue("failed to add key!", ini.has("section1", "key2"));
    ini.set("section1", "key3", 1.0);
    assertTrue("failed to add key!", ini.has("section1", "key3"));

    assertEquals("should have 1 section", 1, ini.getSections().size());
    assertEquals("should have 1 section", "section1", ini.getSections().get(0));

    ini.remove("section1", "key3");
    assertFalse("failed to remove key!", ini.has("section1", "key3"));

    ini.remove("section1");
    assertFalse("failed to remove section!", ini.has("section1", "key1"));
    assertFalse("failed to remove section!", ini.has("section1", "key2"));
    assertFalse("failed to remove section!", ini.has("section1", "key3"));
  }

  /**
   * Returns a test suite.
   *
   * @return		the test suite
   */
  public static Test suite() {
    return new TestSuite(INIFileTest.class);
  }

  /**
   * Runs the test from commandline.
   *
   * @param args	ignored
   */
  public static void main(String[] args) {
    TestRunner.run(suite());
  }
}
