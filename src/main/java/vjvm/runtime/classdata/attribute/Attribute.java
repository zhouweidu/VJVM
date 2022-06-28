package vjvm.runtime.classdata.attribute;

import lombok.var;
import lombok.SneakyThrows;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

public abstract class Attribute {

  @SneakyThrows
  public static Attribute constructFromData(DataInput input, ConstantPool constantPool) {
    var nameIndex = input.readUnsignedShort();
    var attrLength = Integer.toUnsignedLong(input.readInt());
    String attributeName = ((UTF8Constant) (constantPool.constant(nameIndex))).value();
    if (attributeName.equals("Code")){
      return new Code(input,constantPool);
    }
    // TODO: detect and construct Code attribute
    return new UnknownAttribute(input, attrLength);
  }
}
