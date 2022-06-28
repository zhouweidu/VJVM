package vjvm.interpreter.instruction.stores;

import java.util.Objects;
import java.util.function.BiConsumer;

public interface TriConsumer<T1, T2, T> {
  void accept(T1 t1, T2 t2, T t);

  default TriConsumer<T1, T2, T> andThen(TriConsumer<? super T1, ? super T2,? super T> after) {
    Objects.requireNonNull(after);

    return (t1, t2,t) -> {
      accept(t1, t2,t);
      after.accept(t1,t2, t);
    };
  }
}
