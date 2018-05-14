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
 * INIFile.java
 * Copyright (C) 2018 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.simpleini4j;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.NodeNameMatchers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple wrapper for INI files.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class INIFile
  implements Serializable {

  private static final long serialVersionUID = -751900803366565452L;

  /** the underlying configuration. */
  protected INIConfiguration m_Configuration;

  /**
   * Initializes the wrapper with an empty configuration.
   */
  public INIFile() {
    this(new INIConfiguration());
  }

  /**
   * Initializes the wrapper with the specified INI file.
   *
   * @param filename			the INI file to read
   * @throws IllegalArgumentException	if fails to read INI file
   */
  public INIFile(String filename) {
    this(readConfig(filename));
  }

  /**
   * Initializes the wrapper with the specified INI file.
   *
   * @param file			the INI file to read
   * @throws IllegalArgumentException	if fails to read INI file
   */
  public INIFile(File file) {
    this(readConfig(file.getAbsolutePath()));
  }

  /**
   * Initializes the wrapper with the specified configuration object.
   *
   * @param configuration		the configuration
   * @throws IllegalArgumentException	if configuration is null
   */
  public INIFile(INIConfiguration configuration) {
    super();
    if (configuration == null)
      throw new IllegalArgumentException("Configuration cannot be null!");
    m_Configuration = configuration;
  }

  /**
   * Returns the names of the sections stored in the configuration.
   *
   * @return		the (sorted) section names
   */
  public List<String> getSections() {
    List<String>	result;

    result = new ArrayList<>();
    result.addAll(m_Configuration.getSections());
    Collections.sort(result);

    return result;
  }

  /**
   * Ensures that section and key don't contain dots.
   *
   * @param section	the section to check
   * @param key		the key to check
   * @throws IllegalArgumentException	if either contain a dot
   */
  protected void isValid(String section, String key) {
    if (section.contains("."))
      throw new IllegalArgumentException("Section name cannot contain dots!");
    if (key.contains("."))
      throw new IllegalArgumentException("Key cannot contain dots!");
  }

  /**
   * Checks whether the key is present in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key to check(may not contain dots)
   * @return		true if available
   */
  public boolean has(String section, String key) {
    isValid(section, key);
    return (m_Configuration.getProperty(section + "." + key) != null);
  }

  /**
   * Sets the key/value in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @param value	the value
   */
  public void set(String section, String key, Object value) {
    isValid(section, key);
    m_Configuration.setProperty(section + "." + key, value);
  }

  /**
   * Returns the object associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the object
   */
  public Object get(String section, String key) {
    isValid(section, key);
    return m_Configuration.getBoolean(section + "." + key);
  }

  /**
   * Returns the boolean value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the boolean value
   */
  public boolean getBoolean(String section, String key) {
    isValid(section, key);
    return m_Configuration.getBoolean(section + "." + key);
  }

  /**
   * Returns the string value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the string value
   */
  public String getString(String section, String key) {
    isValid(section, key);
    return m_Configuration.getString(section + "." + key);
  }

  /**
   * Returns the byte value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the byte value
   */
  public byte getByte(String section, String key) {
    isValid(section, key);
    return m_Configuration.getByte(section + "." + key);
  }

  /**
   * Returns the short value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the short value
   */
  public short getShort(String section, String key) {
    isValid(section, key);
    return m_Configuration.getShort(section + "." + key);
  }

  /**
   * Returns the integer value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the integer value
   */
  public int getInt(String section, String key) {
    isValid(section, key);
    return m_Configuration.getInt(section + "." + key);
  }

  /**
   * Returns the long value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the long value
   */
  public long getLong(String section, String key) {
    isValid(section, key);
    return m_Configuration.getLong(section + "." + key);
  }

  /**
   * Returns the float value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the float value
   */
  public float getFloat(String section, String key) {
    isValid(section, key);
    return m_Configuration.getFloat(section + "." + key);
  }

  /**
   * Returns the double value associated with the key in the specified section.
   *
   * @param section	the section (may not contain dots)
   * @param key		the key (may not contain dots)
   * @return		the double value
   */
  public double getDouble(String section, String key) {
    isValid(section, key);
    return m_Configuration.getDouble(section + "." + key);
  }

  /**
   * Removes the specified key.
   *
   * @param section	the section to remove the key from
   * @param key		the key to remove
   */
  public void remove(String section, String key) {
    isValid(section, key);
    m_Configuration.getSection(section).clearTree(key);
  }

  /**
   * Removes the specified section.
   *
   * @param section	the section to remove the key from
   */
  public void remove(String section) {
    isValid(section, "");
    m_Configuration.clearTree(section);
  }

  /**
   * Reads the INI file.
   *
   * @return		null if failed to read, empty if not present (yet)
   */
  public static INIFile read(String filename) {
    INIConfiguration	config;

    config = readConfig(filename);
    if (config == null)
      return null;
    return new INIFile(config);
  }

  /**
   * Reads the INI file.
   *
   * @return		null if failed to read, empty if not present (yet)
   */
  public static INIConfiguration readConfig(String filename) {
    DefaultExpressionEngine 				engine;
    Parameters 						params;
    FileBasedConfigurationBuilder<INIConfiguration> 	builder;
    File 						file;

    file = new File(filename);
    if (!file.exists())
      return null;

    engine = new DefaultExpressionEngine(
      DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS,
      NodeNameMatchers.EQUALS_IGNORE_CASE);

    params = new Parameters();
    builder =
      new FileBasedConfigurationBuilder<>(INIConfiguration.class)
	.configure(params.hierarchical()
	  .setFileName(filename)
	  .setExpressionEngine(engine));
    try {
      return builder.getConfiguration();
    }
    catch (Exception e) {
      System.err.println("Failed to parse: " + filename);
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Writes the configuration to disk.
   *
   * @param filename	the file to write to
   * @return		null if successfully written, otherwise error message
   */
  public String write(String filename) {
    FileWriter 		fwriter;
    BufferedWriter 	bwriter;
    File		file;
    File		dir;

    fwriter = null;
    bwriter = null;
    file    = new File(filename);
    dir     = file.getParentFile();
    if (!dir.exists()) {
      if (!dir.mkdirs())
        return "Failed to create directory for configuration file: " + dir;
    }

    try {
      fwriter = new FileWriter(file);
      bwriter = new BufferedWriter(fwriter);
      m_Configuration.write(bwriter);
      return null;
    }
    catch (Exception e) {
      System.err.println("Failed to write configuration to: " + file);
      e.printStackTrace();
      return "Failed to write configuration to: " + file + "\n" + e;
    }
    finally {
      closeQuietly(bwriter);
      closeQuietly(fwriter);
    }
  }

  /**
   * Closes the writer, if possible, suppressing any exception.
   *
   * @param writer	the writer to close
   */
  protected static void closeQuietly(Writer writer) {
    if (writer != null) {
      try {
	writer.flush();
      }
      catch (Exception e) {
	// ignored
      }
      try {
	writer.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }
}
