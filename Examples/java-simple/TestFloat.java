import java.nio.*; 
import java.util.*; 

public class TestFloat {
    public static void main(String[] args){
        // Declaring the capacity of the FloatBuffer 
        int capacity = 3;
    
        // Creating the FloatBuffer 
        try { 

            // creating object of floatbuffer 
            // and allocating size capacity 
            FloatBuffer fb = FloatBuffer.allocate(capacity); 

            // putting the value in floatbuffer using put() method 
            fb.put(8.56F); 
            fb.put(9.61F); 
            fb.put(7.86F); 
            fb.rewind(); 

            // print the FloatBuffer 
            System.out.println("Original FloatBuffer:  "
                            + Arrays.toString(fb.array())); 
        } 

        catch (BufferOverflowException e) { 

            System.out.println("Exception throws : " + e); 
        } 

        catch (ReadOnlyBufferException e) { 

            System.out.println("Exception throws : " + e); 
        } 
    }
}
