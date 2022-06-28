package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.var;

import static vjvm.classfiledefs.Descriptors.DESC_array;
import static vjvm.classfiledefs.Descriptors.DESC_reference;

public class MethodDescriptors {
  public static int argc(String descriptor) {
    assert descriptor.startsWith("(");
    int res = 0, n = 1;
    while (n < descriptor.indexOf(')')) {
      if (descriptor.charAt(n) == '[') {
        while (descriptor.charAt(n) == '[') {
          n++;
        }
        if (descriptor.charAt(n) == 'L') {
          while (descriptor.charAt(n) != ';') {
            n++;
          }
        }
        n++;
        res++;
      } else if (descriptor.charAt(n) == 'L') {
        while (descriptor.charAt(n) != ';') {
          n++;
        }
        res++;
        n++;
      } else {
        if (descriptor.charAt(n) == 'J' || descriptor.charAt(n) == 'D') {
          res += 2;
        } else {
          res += 1;
        }
        n++;
      }
    }
    // TODO: calculate arguments size in slots
//    throw new UnimplementedError();
    return res;
  }

  public static char returnType(String descriptor) {
    assert descriptor.startsWith("(");

    var i = descriptor.indexOf(')') + 1;
    assert i < descriptor.length();
    return descriptor.charAt(i);
  }
}
