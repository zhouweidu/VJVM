package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.diff.StringsComparator;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class StringConstant extends Constant{
  private final int stringIndex;
  private String string;
  private final JClass self;
  @SneakyThrows
  public StringConstant(DataInput input, JClass jClass) {
    this.stringIndex=input.readUnsignedShort();
    self=jClass;
  }
  public String string(){
    if (string==null){
      string=((UTF8Constant)(self.constantPool().constant(stringIndex))).value();
    }
    return string;
  }
  @Override
  public String toString() {
    return String.format("String: \"%s\"", StringEscapeUtils.escapeJava(string()));
  }
}
