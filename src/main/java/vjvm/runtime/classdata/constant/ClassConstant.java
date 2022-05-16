package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class ClassConstant extends Constant{
  private final int nameIndex;
  private String name;
  private final JClass self;
  @SneakyThrows
  public ClassConstant(DataInput input, JClass jClass) {
    this.nameIndex = input.readUnsignedShort();
    this.self=jClass;
  }
  public String name(){
    if (name==null){
      name=((UTF8Constant)self.constantPool().constant(nameIndex)).value();
    }
    return name;
  }

  @Override
  public String toString() {
    return String.format("Class: %s",name());
  }
}
