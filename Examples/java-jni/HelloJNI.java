public class HelloJNI {
    public native void sayHi(String who, int times);

    static { System.loadLibrary("HelloImpl"); }
    
    public static void main(String[] args){
	HelloJNI hello = new HelloJNI();
	hello.sayHi(args[0], Integer.parseInt(args[1]));
    }
}

