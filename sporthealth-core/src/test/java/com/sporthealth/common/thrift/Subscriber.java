/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sporthealth.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-07-09")
public class Subscriber implements org.apache.thrift.TBase<Subscriber, Subscriber._Fields>, java.io.Serializable, Cloneable, Comparable<Subscriber> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Subscriber");

  private static final org.apache.thrift.protocol.TField APP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("appName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SUBSCRIPTIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("subscriptions", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SubscriberStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SubscriberTupleSchemeFactory();

  public java.lang.String appName; // required
  public java.util.List<Subscription> subscriptions; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP_NAME((short)1, "appName"),
    SUBSCRIPTIONS((short)2, "subscriptions");

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
        case 1: // APP_NAME
          return APP_NAME;
        case 2: // SUBSCRIPTIONS
          return SUBSCRIPTIONS;
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
    tmpMap.put(_Fields.APP_NAME, new org.apache.thrift.meta_data.FieldMetaData("appName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SUBSCRIPTIONS, new org.apache.thrift.meta_data.FieldMetaData("subscriptions", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Subscription.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Subscriber.class, metaDataMap);
  }

  public Subscriber() {
  }

  public Subscriber(
    java.lang.String appName,
    java.util.List<Subscription> subscriptions)
  {
    this();
    this.appName = appName;
    this.subscriptions = subscriptions;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Subscriber(Subscriber other) {
    if (other.isSetAppName()) {
      this.appName = other.appName;
    }
    if (other.isSetSubscriptions()) {
      java.util.List<Subscription> __this__subscriptions = new java.util.ArrayList<Subscription>(other.subscriptions.size());
      for (Subscription other_element : other.subscriptions) {
        __this__subscriptions.add(new Subscription(other_element));
      }
      this.subscriptions = __this__subscriptions;
    }
  }

  public Subscriber deepCopy() {
    return new Subscriber(this);
  }

  @Override
  public void clear() {
    this.appName = null;
    this.subscriptions = null;
  }

  public java.lang.String getAppName() {
    return this.appName;
  }

  public Subscriber setAppName(java.lang.String appName) {
    this.appName = appName;
    return this;
  }

  public void unsetAppName() {
    this.appName = null;
  }

  /** Returns true if field appName is set (has been assigned a value) and false otherwise */
  public boolean isSetAppName() {
    return this.appName != null;
  }

  public void setAppNameIsSet(boolean value) {
    if (!value) {
      this.appName = null;
    }
  }

  public int getSubscriptionsSize() {
    return (this.subscriptions == null) ? 0 : this.subscriptions.size();
  }

  public java.util.Iterator<Subscription> getSubscriptionsIterator() {
    return (this.subscriptions == null) ? null : this.subscriptions.iterator();
  }

  public void addToSubscriptions(Subscription elem) {
    if (this.subscriptions == null) {
      this.subscriptions = new java.util.ArrayList<Subscription>();
    }
    this.subscriptions.add(elem);
  }

  public java.util.List<Subscription> getSubscriptions() {
    return this.subscriptions;
  }

  public Subscriber setSubscriptions(java.util.List<Subscription> subscriptions) {
    this.subscriptions = subscriptions;
    return this;
  }

  public void unsetSubscriptions() {
    this.subscriptions = null;
  }

  /** Returns true if field subscriptions is set (has been assigned a value) and false otherwise */
  public boolean isSetSubscriptions() {
    return this.subscriptions != null;
  }

  public void setSubscriptionsIsSet(boolean value) {
    if (!value) {
      this.subscriptions = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case APP_NAME:
      if (value == null) {
        unsetAppName();
      } else {
        setAppName((java.lang.String)value);
      }
      break;

    case SUBSCRIPTIONS:
      if (value == null) {
        unsetSubscriptions();
      } else {
        setSubscriptions((java.util.List<Subscription>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case APP_NAME:
      return getAppName();

    case SUBSCRIPTIONS:
      return getSubscriptions();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case APP_NAME:
      return isSetAppName();
    case SUBSCRIPTIONS:
      return isSetSubscriptions();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Subscriber)
      return this.equals((Subscriber)that);
    return false;
  }

  public boolean equals(Subscriber that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_appName = true && this.isSetAppName();
    boolean that_present_appName = true && that.isSetAppName();
    if (this_present_appName || that_present_appName) {
      if (!(this_present_appName && that_present_appName))
        return false;
      if (!this.appName.equals(that.appName))
        return false;
    }

    boolean this_present_subscriptions = true && this.isSetSubscriptions();
    boolean that_present_subscriptions = true && that.isSetSubscriptions();
    if (this_present_subscriptions || that_present_subscriptions) {
      if (!(this_present_subscriptions && that_present_subscriptions))
        return false;
      if (!this.subscriptions.equals(that.subscriptions))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAppName()) ? 131071 : 524287);
    if (isSetAppName())
      hashCode = hashCode * 8191 + appName.hashCode();

    hashCode = hashCode * 8191 + ((isSetSubscriptions()) ? 131071 : 524287);
    if (isSetSubscriptions())
      hashCode = hashCode * 8191 + subscriptions.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Subscriber other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAppName()).compareTo(other.isSetAppName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appName, other.appName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSubscriptions()).compareTo(other.isSetSubscriptions());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubscriptions()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subscriptions, other.subscriptions);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Subscriber(");
    boolean first = true;

    sb.append("appName:");
    if (this.appName == null) {
      sb.append("null");
    } else {
      sb.append(this.appName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("subscriptions:");
    if (this.subscriptions == null) {
      sb.append("null");
    } else {
      sb.append(this.subscriptions);
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

  private static class SubscriberStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SubscriberStandardScheme getScheme() {
      return new SubscriberStandardScheme();
    }
  }

  private static class SubscriberStandardScheme extends org.apache.thrift.scheme.StandardScheme<Subscriber> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Subscriber struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APP_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.appName = iprot.readString();
              struct.setAppNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SUBSCRIPTIONS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.subscriptions = new java.util.ArrayList<Subscription>(_list8.size);
                Subscription _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new Subscription();
                  _elem9.read(iprot);
                  struct.subscriptions.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setSubscriptionsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Subscriber struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.appName != null) {
        oprot.writeFieldBegin(APP_NAME_FIELD_DESC);
        oprot.writeString(struct.appName);
        oprot.writeFieldEnd();
      }
      if (struct.subscriptions != null) {
        oprot.writeFieldBegin(SUBSCRIPTIONS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.subscriptions.size()));
          for (Subscription _iter11 : struct.subscriptions)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SubscriberTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SubscriberTupleScheme getScheme() {
      return new SubscriberTupleScheme();
    }
  }

  private static class SubscriberTupleScheme extends org.apache.thrift.scheme.TupleScheme<Subscriber> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Subscriber struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAppName()) {
        optionals.set(0);
      }
      if (struct.isSetSubscriptions()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetAppName()) {
        oprot.writeString(struct.appName);
      }
      if (struct.isSetSubscriptions()) {
        {
          oprot.writeI32(struct.subscriptions.size());
          for (Subscription _iter12 : struct.subscriptions)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Subscriber struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.appName = iprot.readString();
        struct.setAppNameIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.subscriptions = new java.util.ArrayList<Subscription>(_list13.size);
          Subscription _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new Subscription();
            _elem14.read(iprot);
            struct.subscriptions.add(_elem14);
          }
        }
        struct.setSubscriptionsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

