/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sporthealth.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-07-09")
public class Subscription implements org.apache.thrift.TBase<Subscription, Subscription._Fields>, java.io.Serializable, Cloneable, Comparable<Subscription> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Subscription");

  private static final org.apache.thrift.protocol.TField SUB_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("subType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SYMBOL_FIELD_DESC = new org.apache.thrift.protocol.TField("symbol", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField FEILDS_FIELD_DESC = new org.apache.thrift.protocol.TField("feilds", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SubscriptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SubscriptionTupleSchemeFactory();

  /**
   * 
   * @see SubType
   */
  public SubType subType; // required
  public java.lang.String symbol; // required
  public java.util.List<java.lang.String> feilds; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see SubType
     */
    SUB_TYPE((short)1, "subType"),
    SYMBOL((short)2, "symbol"),
    FEILDS((short)3, "feilds");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SUB_TYPE
          return SUB_TYPE;
        case 2: // SYMBOL
          return SYMBOL;
        case 3: // FEILDS
          return FEILDS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SUB_TYPE, new org.apache.thrift.meta_data.FieldMetaData("subType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, SubType.class)));
    tmpMap.put(_Fields.SYMBOL, new org.apache.thrift.meta_data.FieldMetaData("symbol", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FEILDS, new org.apache.thrift.meta_data.FieldMetaData("feilds", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Subscription.class, metaDataMap);
  }

  public Subscription() {
  }

  public Subscription(
    SubType subType,
    java.lang.String symbol,
    java.util.List<java.lang.String> feilds)
  {
    this();
    this.subType = subType;
    this.symbol = symbol;
    this.feilds = feilds;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Subscription(Subscription other) {
    if (other.isSetSubType()) {
      this.subType = other.subType;
    }
    if (other.isSetSymbol()) {
      this.symbol = other.symbol;
    }
    if (other.isSetFeilds()) {
      java.util.List<java.lang.String> __this__feilds = new java.util.ArrayList<java.lang.String>(other.feilds);
      this.feilds = __this__feilds;
    }
  }

  public Subscription deepCopy() {
    return new Subscription(this);
  }

  @Override
  public void clear() {
    this.subType = null;
    this.symbol = null;
    this.feilds = null;
  }

  /**
   * 
   * @see SubType
   */
  public SubType getSubType() {
    return this.subType;
  }

  /**
   * 
   * @see SubType
   */
  public Subscription setSubType(SubType subType) {
    this.subType = subType;
    return this;
  }

  public void unsetSubType() {
    this.subType = null;
  }

  /** Returns true if field subType is set (has been assigned a value) and false otherwise */
  public boolean isSetSubType() {
    return this.subType != null;
  }

  public void setSubTypeIsSet(boolean value) {
    if (!value) {
      this.subType = null;
    }
  }

  public java.lang.String getSymbol() {
    return this.symbol;
  }

  public Subscription setSymbol(java.lang.String symbol) {
    this.symbol = symbol;
    return this;
  }

  public void unsetSymbol() {
    this.symbol = null;
  }

  /** Returns true if field symbol is set (has been assigned a value) and false otherwise */
  public boolean isSetSymbol() {
    return this.symbol != null;
  }

  public void setSymbolIsSet(boolean value) {
    if (!value) {
      this.symbol = null;
    }
  }

  public int getFeildsSize() {
    return (this.feilds == null) ? 0 : this.feilds.size();
  }

  public java.util.Iterator<java.lang.String> getFeildsIterator() {
    return (this.feilds == null) ? null : this.feilds.iterator();
  }

  public void addToFeilds(java.lang.String elem) {
    if (this.feilds == null) {
      this.feilds = new java.util.ArrayList<java.lang.String>();
    }
    this.feilds.add(elem);
  }

  public java.util.List<java.lang.String> getFeilds() {
    return this.feilds;
  }

  public Subscription setFeilds(java.util.List<java.lang.String> feilds) {
    this.feilds = feilds;
    return this;
  }

  public void unsetFeilds() {
    this.feilds = null;
  }

  /** Returns true if field feilds is set (has been assigned a value) and false otherwise */
  public boolean isSetFeilds() {
    return this.feilds != null;
  }

  public void setFeildsIsSet(boolean value) {
    if (!value) {
      this.feilds = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case SUB_TYPE:
      if (value == null) {
        unsetSubType();
      } else {
        setSubType((SubType)value);
      }
      break;

    case SYMBOL:
      if (value == null) {
        unsetSymbol();
      } else {
        setSymbol((java.lang.String)value);
      }
      break;

    case FEILDS:
      if (value == null) {
        unsetFeilds();
      } else {
        setFeilds((java.util.List<java.lang.String>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SUB_TYPE:
      return getSubType();

    case SYMBOL:
      return getSymbol();

    case FEILDS:
      return getFeilds();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SUB_TYPE:
      return isSetSubType();
    case SYMBOL:
      return isSetSymbol();
    case FEILDS:
      return isSetFeilds();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Subscription)
      return this.equals((Subscription)that);
    return false;
  }

  public boolean equals(Subscription that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_subType = true && this.isSetSubType();
    boolean that_present_subType = true && that.isSetSubType();
    if (this_present_subType || that_present_subType) {
      if (!(this_present_subType && that_present_subType))
        return false;
      if (!this.subType.equals(that.subType))
        return false;
    }

    boolean this_present_symbol = true && this.isSetSymbol();
    boolean that_present_symbol = true && that.isSetSymbol();
    if (this_present_symbol || that_present_symbol) {
      if (!(this_present_symbol && that_present_symbol))
        return false;
      if (!this.symbol.equals(that.symbol))
        return false;
    }

    boolean this_present_feilds = true && this.isSetFeilds();
    boolean that_present_feilds = true && that.isSetFeilds();
    if (this_present_feilds || that_present_feilds) {
      if (!(this_present_feilds && that_present_feilds))
        return false;
      if (!this.feilds.equals(that.feilds))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetSubType()) ? 131071 : 524287);
    if (isSetSubType())
      hashCode = hashCode * 8191 + subType.getValue();

    hashCode = hashCode * 8191 + ((isSetSymbol()) ? 131071 : 524287);
    if (isSetSymbol())
      hashCode = hashCode * 8191 + symbol.hashCode();

    hashCode = hashCode * 8191 + ((isSetFeilds()) ? 131071 : 524287);
    if (isSetFeilds())
      hashCode = hashCode * 8191 + feilds.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Subscription other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSubType()).compareTo(other.isSetSubType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subType, other.subType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSymbol()).compareTo(other.isSetSymbol());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSymbol()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.symbol, other.symbol);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFeilds()).compareTo(other.isSetFeilds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFeilds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.feilds, other.feilds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Subscription(");
    boolean first = true;

    sb.append("subType:");
    if (this.subType == null) {
      sb.append("null");
    } else {
      sb.append(this.subType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("symbol:");
    if (this.symbol == null) {
      sb.append("null");
    } else {
      sb.append(this.symbol);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("feilds:");
    if (this.feilds == null) {
      sb.append("null");
    } else {
      sb.append(this.feilds);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SubscriptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SubscriptionStandardScheme getScheme() {
      return new SubscriptionStandardScheme();
    }
  }

  private static class SubscriptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<Subscription> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Subscription struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SUB_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.subType = com.sporthealth.common.thrift.SubType.findByValue(iprot.readI32());
              struct.setSubTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SYMBOL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.symbol = iprot.readString();
              struct.setSymbolIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // FEILDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.feilds = new java.util.ArrayList<java.lang.String>(_list0.size);
                java.lang.String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.feilds.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setFeildsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Subscription struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.subType != null) {
        oprot.writeFieldBegin(SUB_TYPE_FIELD_DESC);
        oprot.writeI32(struct.subType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.symbol != null) {
        oprot.writeFieldBegin(SYMBOL_FIELD_DESC);
        oprot.writeString(struct.symbol);
        oprot.writeFieldEnd();
      }
      if (struct.feilds != null) {
        oprot.writeFieldBegin(FEILDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.feilds.size()));
          for (java.lang.String _iter3 : struct.feilds)
          {
            oprot.writeString(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SubscriptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SubscriptionTupleScheme getScheme() {
      return new SubscriptionTupleScheme();
    }
  }

  private static class SubscriptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<Subscription> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Subscription struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSubType()) {
        optionals.set(0);
      }
      if (struct.isSetSymbol()) {
        optionals.set(1);
      }
      if (struct.isSetFeilds()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetSubType()) {
        oprot.writeI32(struct.subType.getValue());
      }
      if (struct.isSetSymbol()) {
        oprot.writeString(struct.symbol);
      }
      if (struct.isSetFeilds()) {
        {
          oprot.writeI32(struct.feilds.size());
          for (java.lang.String _iter4 : struct.feilds)
          {
            oprot.writeString(_iter4);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Subscription struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.subType = com.sporthealth.common.thrift.SubType.findByValue(iprot.readI32());
        struct.setSubTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.symbol = iprot.readString();
        struct.setSymbolIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.feilds = new java.util.ArrayList<java.lang.String>(_list5.size);
          java.lang.String _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readString();
            struct.feilds.add(_elem6);
          }
        }
        struct.setFeildsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
