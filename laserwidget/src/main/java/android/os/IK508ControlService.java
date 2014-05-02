package android.os;


import java.io.InvalidClassException;
import java.io.InvalidObjectException;

public class IK508ControlService {
    public void setVal(int x, long y){}
    public static class Stub{
        public static IK508ControlService asInterface(IBinder binder){
            throw new IllegalStateException("stub");
        }
    }
}
