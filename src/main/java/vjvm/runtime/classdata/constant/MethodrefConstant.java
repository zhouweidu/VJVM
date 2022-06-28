package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.MethodInfo;

import java.io.DataInput;

public class MethodrefConstant extends Constant {
  private final int classIndex;
  private final int nameAndTypeIndex;
  private final JClass self;
  private NameAndTypeConstant nameAndTypeConstant;
  private String className;
  private MethodInfo method;

  @SneakyThrows
  public MethodrefConstant(DataInput input, JClass jClass) {
    this.self = jClass;
    classIndex = input.readUnsignedShort();
    nameAndTypeIndex = input.readUnsignedShort();
  }

  public NameAndTypeConstant nameAndTypeConstant() {
    if (nameAndTypeConstant == null) {
      nameAndTypeConstant = (NameAndTypeConstant) (self.constantPool().constant(nameAndTypeIndex));
    }
    return nameAndTypeConstant;
  }
  public JClass jClass(){
    return self.classLoader().loadClass("L"+className().replace('.','/')+";");
  }
  public String className() {
    if (className == null) {
      className=((ClassConstant) (self.constantPool().constant(classIndex))).name();
    }
    return className;
  }
  public MethodInfo value(){
    if (method!=null){
      return method;
    }
    NameAndTypeConstant nameAndType=nameAndTypeConstant();
    method=jClass().findMethod(nameAndType.name(),nameAndType.type());
    return method;
  }
  @Override
  public String toString() {
    return String.format("Methodref: %s.%s:%s", className(), nameAndTypeConstant().name(), nameAndTypeConstant().type());
  }
}
