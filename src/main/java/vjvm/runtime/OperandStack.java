package vjvm.runtime;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.Getter;

public class OperandStack {
  @Getter
  private final Slots slots;

  @Getter
  private int top;

  public OperandStack(int stackSize) {
    // TODO: initialize data structures
//    throw new UnimplementedError();
    slots = new Slots(stackSize);
    top = 0;
  }

  public void pushInt(int value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.int_(top++, value);
  }

  public int popInt() {
    // TODO: pop value
//    throw new UnimplementedError();
    return slots.int_(--top);
  }

  public void pushFloat(float value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.float_(top++, value);
  }

  public float popFloat() {
    // TODO: pop value
//    throw new UnimplementedError();
    return slots.float_(--top);
  }

  public void pushLong(long value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.long_(top, value);
    top += 2;
  }

  public long popLong() {
    // TODO: pop value
//    throw new UnimplementedError();
    top -= 2;
    return slots.long_(top);
  }

  public void pushDouble(double value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.double_(top, value);
    top += 2;
  }

  public double popDouble() {
    // TODO: pop value
//    throw new UnimplementedError();
    top -= 2;
    return slots.double_(top);
  }

  public void pushByte(byte value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.byte_(top++, value);
  }

  public byte popByte() {
    // TODO: pop value
//    throw new UnimplementedError();
    return slots.byte_(--top);
  }

  public void pushChar(char value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.char_(top++, value);
  }

  public char popChar() {
    // TODO: pop value
//    throw new UnimplementedError();
    return slots.char_(--top);
  }

  public void pushShort(short value) {
    // TODO: push value
//    throw new UnimplementedError();
    slots.short_(top++, value);
  }

  public short popShort() {
    // TODO: pop value
//    throw new UnimplementedError();
    return slots.short_(--top);
  }

  public void pushSlots(Slots slots) {
    // TODO: push slots
//    throw new UnimplementedError();
    slots.copyTo(0, slots.size(), this.slots, top);
    top += slots.size();
  }

  public Slots popSlots(int count) {
    // TODO: pop count slots and return
//    throw new UnimplementedError();
    assert top >= count;
    Slots res = new Slots(count);
    top -= count;
    slots.copyTo(top, count, res, 0);
    return res;
  }

  public void clear() {
    // TODO: pop all slots
//    throw new UnimplementedError();
    top = 0;
  }

  @Override
  public String toString() {
    return "OperandStack{"+"slots="+slots+", top="+top+"}";
  }
}
