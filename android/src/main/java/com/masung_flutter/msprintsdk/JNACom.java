package com.masung_flutter.msprintsdk;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface JNACom extends Library {
    JNACom INSTANCE = (JNACom) Native.loadLibrary("msprintcom", JNACom.class);

    public int SetDevname(int iDevtype, String cDevname, int iBaudrate);
    public int SetInit();
    public int SetClose();
    int PrintTransmitJNA(String strCmd, int iLength);
    String GetTransmitJNA(String strCmd, int iLength);
}
