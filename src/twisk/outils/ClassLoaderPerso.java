package twisk.outils;

public class ClassLoaderPerso extends ClassLoader {

    public ClassLoaderPerso(ClassLoader parent){
        super(parent);//bonjoir
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
