package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class FieldrefConstant extends Constant{
  private final int classIndex;
  private final int nameAndTypeIndex;
  private final JClass self;
  private NameAndTypeConstant nameAndTypeConstant;
  private String className;
  @SneakyThrows
  public FieldrefConstant(DataInput input,JClass jClass) {
    this.classIndex = input.readUnsignedShort();
    this.nameAndTypeIndex = input.readUnsignedShort();
    this.self=jClass;
  }
  public NameAndTypeConstant nameAndTypeConstant(){
    if (nameAndTypeConstant==null){
      nameAndTypeConstant=(NameAndTypeConstant) self.constantPool().constant(nameAndTypeIndex);
    }
    return nameAndTypeConstant;
  }
  public String className(){
    if (className==null){
      className=((ClassConstant)(self.constantPool().constant(classIndex))).name();
    }
    return className;
  }
  @Override
  public String toString() {
    return String.format("Fieldref: %s.%s:%s",className(),nameAndTypeConstant().name(),nameAndTypeConstant().type());
  }
}
