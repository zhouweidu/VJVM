package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;

import java.io.DataInput;

public class DoubleConstant extends Constant{
  private final double value;
  @SneakyThrows
  public DoubleConstant(DataInput input) {
    this.value = input.readDouble();
  }

  @Override
  public String toString() {
    return String.format("Double: %a",value);
  }
}
