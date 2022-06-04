package vjvm.classloader.searchpath;

import vjvm.utils.UnimplementedError;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Represents a path to search class files in.
 * A search path may hold resources, such as jar files, so it must implement the Closeable interface.
 * If a subclass doesn't hold any resources, then just do nothing.
 */
public abstract class ClassSearchPath implements Closeable {
  /**
   * Construct search path objects with a given path.
   */
  public static ClassSearchPath[] constructSearchPath(String path) {
    String sep = System.getProperty("path.separator");
    String[] paths = path.split(sep);
    ArrayList<ClassSearchPath> classSearchPaths = new ArrayList<>();
    for (String searchPath :
      paths) {
      if (searchPath.endsWith(".jar")||searchPath.endsWith(".JAR")){
        classSearchPaths.add(new JarSearchPath(searchPath));
      }else{
        classSearchPaths.add(new DirSearchPath(searchPath));
      }
    }
    return classSearchPaths.toArray(new ClassSearchPath[0]);
//    throw new UnimplementedError("TODO: parse path and return an array of search paths");
  }

  /**
   * Find a class with specified name.
   *
   * @param name name of the class to find.
   * @return Returns a stream containing the binary data if such class is found, or null if not.
   */
  public abstract InputStream findClass(String name);
}
