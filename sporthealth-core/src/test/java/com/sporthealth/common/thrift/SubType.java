/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sporthealth.common.thrift;


public enum SubType implements org.apache.thrift.TEnum {
  INIT(0),
  ADD(1),
  DEL(2),
  CANCEL(3);

  private final int value;

  private SubType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static SubType findByValue(int value) { 
    switch (value) {
      case 0:
        return INIT;
      case 1:
        return ADD;
      case 2:
        return DEL;
      case 3:
        return CANCEL;
      default:
        return null;
    }
  }
}