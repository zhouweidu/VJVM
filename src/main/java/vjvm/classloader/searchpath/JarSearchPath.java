package vjvm.classloader.searchpath;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JarSearchPath extends ClassSearchPath{
  private JarFile jarFile;

  public JarSearchPath(String jarName) {
    try {
      jarFile = new JarFile(jarName);
    } catch (IOException e) {
      jarFile=null;
    }
  }

  @SneakyThrows
  @Override
  public InputStream findClass(String name) {
    if (jarFile==null){
      return null;
    }
    ZipEntry entry = jarFile.getEntry(name + ".class");
    return entry==null?null:jarFile.getInputStream(entry);
  }

  @Override
  public void close() throws IOException {
    if (jarFile!=null){
      jarFile.close();
    }
  }
}
