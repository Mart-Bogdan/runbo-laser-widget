package android.os;

public interface IK508ControlService {
    void setVal(int x, long y) throws RemoteException;
    public static class Stub{
        public static IK508ControlService asInterface(IBinder binder){
            throw new IllegalStateException("stub");
        }
    }
}
