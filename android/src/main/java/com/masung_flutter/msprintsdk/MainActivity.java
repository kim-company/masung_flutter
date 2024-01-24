package com.example.updatefirware;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.printsdk.usbsdk.UsbDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    private static final String ACTION_USB_PERMISSION = "com.usb.sample.USB_PERMISSION";
    private static final int FILE_SELECT_CODE = 0;
    private byte[] CODEdata;
    private int JYValue = 0;
    private byte[] PrinterName = new byte[16];
    int SizeA;
    private Button mBtnPrint;
    private Button mBtnUpdate;
    private EditText mEdt_Path;
    private EditText mEdt_Prompt;
    /* access modifiers changed from: private */
    public UsbDriver mUsbDriver;
    BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r8, android.content.Intent r9) {
            /*
                r7 = this;
                r6 = 2
                r5 = 0
                java.lang.String r0 = r9.getAction()
                java.lang.String r3 = "com.usb.sample.USB_PERMISSION"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0084
                monitor-enter(r7)
                java.lang.String r3 = "device"
                android.os.Parcelable r1 = r9.getParcelableExtra(r3)     // Catch:{ all -> 0x0079 }
                android.hardware.usb.UsbDevice r1 = (android.hardware.usb.UsbDevice) r1     // Catch:{ all -> 0x0079 }
                java.lang.String r3 = "permission"
                r4 = 0
                boolean r3 = r9.getBooleanExtra(r3, r4)     // Catch:{ all -> 0x0079 }
                if (r3 == 0) goto L_0x007c
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                com.printsdk.usbsdk.UsbDriver r3 = r3.mUsbDriver     // Catch:{ all -> 0x0079 }
                boolean r3 = r3.openUsbDevice(r1)     // Catch:{ all -> 0x0079 }
                if (r3 == 0) goto L_0x0051
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                r3.m_Device = r1     // Catch:{ all -> 0x0079 }
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                int r3 = r3.m_IntOper     // Catch:{ all -> 0x0079 }
                r4 = 1
                if (r3 != r4) goto L_0x0053
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                r4 = 0
                r3.m_IntOper = r4     // Catch:{ all -> 0x0079 }
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                com.printsdk.usbsdk.UsbDriver r3 = r3.mUsbDriver     // Catch:{ all -> 0x0079 }
                com.example.updatefirware.MainActivity r4 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                java.lang.String r4 = r4.m_StrPrint     // Catch:{ all -> 0x0079 }
                byte[] r4 = r4.getBytes()     // Catch:{ all -> 0x0079 }
                r3.write(r4)     // Catch:{ all -> 0x0079 }
            L_0x0051:
                monitor-exit(r7)     // Catch:{ all -> 0x0079 }
            L_0x0052:
                return
            L_0x0053:
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                int r3 = r3.m_IntOper     // Catch:{ all -> 0x0079 }
                if (r3 != r6) goto L_0x0051
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                r4 = 0
                r3.m_IntOper = r4     // Catch:{ all -> 0x0079 }
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ Exception -> 0x006e }
                r4 = 100
                r3.sleep(r4)     // Catch:{ Exception -> 0x006e }
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ Exception -> 0x006e }
                r3.downUpdate()     // Catch:{ Exception -> 0x006e }
                goto L_0x0051
            L_0x006e:
                r2 = move-exception
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x0079 }
                r3.promptUpdate(r4)     // Catch:{ all -> 0x0079 }
                goto L_0x0051
            L_0x0079:
                r3 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x0079 }
                throw r3
            L_0x007c:
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ all -> 0x0079 }
                java.lang.String r4 = "permission denied for device"
                r3.promptUpdate(r4)     // Catch:{ all -> 0x0079 }
                goto L_0x0051
            L_0x0084:
                java.lang.String r3 = "android.hardware.usb.action.USB_DEVICE_ATTACHED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x00d3
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                java.lang.String r4 = "Printer device attached"
                r3.promptUpdate(r4)
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                int r3 = r3.m_IntOper
                if (r3 != r6) goto L_0x0052
                java.lang.String r3 = "device"
                android.os.Parcelable r1 = r9.getParcelableExtra(r3)
                android.hardware.usb.UsbDevice r1 = (android.hardware.usb.UsbDevice) r1
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                com.printsdk.usbsdk.UsbDriver r3 = r3.mUsbDriver
                r3.usbAttached((android.hardware.usb.UsbDevice) r1)
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                com.printsdk.usbsdk.UsbDriver r3 = r3.mUsbDriver
                boolean r3 = r3.openUsbDevice(r1)
                if (r3 == 0) goto L_0x0052
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                r3.m_Device = r1
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                r3.m_IntOper = r5
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this     // Catch:{ Exception -> 0x00c8 }
                r3.downUpdate()     // Catch:{ Exception -> 0x00c8 }
                goto L_0x0052
            L_0x00c8:
                r2 = move-exception
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                java.lang.String r4 = r2.getMessage()
                r3.promptUpdate(r4)
                goto L_0x0052
            L_0x00d3:
                java.lang.String r3 = "android.hardware.usb.action.USB_DEVICE_DETACHED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0052
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                com.printsdk.usbsdk.UsbDriver r3 = r3.mUsbDriver
                boolean r3 = r3.isConnected()
                if (r3 == 0) goto L_0x00f0
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                com.printsdk.usbsdk.UsbDriver r3 = r3.mUsbDriver
                r3.closeUsbDevice()
            L_0x00f0:
                com.example.updatefirware.MainActivity r3 = com.example.updatefirware.MainActivity.this
                java.lang.String r4 = "Printer device detached"
                r3.promptUpdate(r4)
                goto L_0x0052
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.updatefirware.MainActivity.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    /* access modifiers changed from: private */
    public UsbDevice m_Device;
    /* access modifiers changed from: private */
    public int m_IntOper = 0;
    String m_StrPrint = "print test\n";
    private int m_iUpdateStatus = -1;
    private String m_strPrompt = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.mBtnPrint = (Button) findViewById(R.id.btnPrint);
        this.mBtnPrint.setOnClickListener(new PrintClickListener());
        this.mBtnUpdate = (Button) findViewById(R.id.btnUpdate);
        this.mBtnUpdate.setOnClickListener(new UpdateClickListener());
        this.mEdt_Path = (EditText) findViewById(R.id.edtPath);
        this.mEdt_Path.setOnClickListener(new BrowerClickListener());
        this.mEdt_Prompt = (EditText) findViewById(R.id.edtPrompt);
        this.mUsbDriver = new UsbDriver((UsbManager) getSystemService("usb"), this);
        this.mUsbDriver.setPermissionIntent(PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        registerReceiver(this.mUsbReceiver, filter);
    }

    /* access modifiers changed from: package-private */
    public boolean PrintConnStatus() {
        try {
            if (this.mUsbDriver.isConnected()) {
                return true;
            }
            for (UsbDevice device : ((UsbManager) getSystemService("usb")).getDeviceList().values()) {
                if ((device.getProductId() == 8211 && device.getVendorId() == 1305) || (device.getProductId() == 8213 && device.getVendorId() == 1305)) {
                    promptUpdate("Connect printer...");
                    boolean blnRtn = this.mUsbDriver.usbAttached(device);
                    if (!blnRtn) {
                        return blnRtn;
                    }
                    boolean blnRtn2 = this.mUsbDriver.openUsbDevice(device);
                    this.m_Device = device;
                    return blnRtn2;
                }
            }
            return false;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
            return false;
        }
    }

    class BrowerClickListener implements View.OnClickListener {
        BrowerClickListener() {
        }

        public void onClick(View view) {
            try {
                MainActivity.this.showFileChooser();
            } catch (Exception e) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void showFileChooser() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*.bin");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a BIN file"), 0);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Please install a File Manager.", 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == -1) {
                    this.mEdt_Path.setText(FileUtils.getPath(this, data.getData()));
                    break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class PrintClickListener implements View.OnClickListener {
        PrintClickListener() {
        }

        public void onClick(View view) {
            try {
                if (!MainActivity.this.PrintConnStatus()) {
                    MainActivity.this.m_IntOper = 1;
                    MainActivity.this.promptUpdate("Please wait...");
                } else if (MainActivity.this.mUsbDriver.write(MainActivity.this.m_StrPrint.getBytes()) < 0) {
                    MainActivity.this.mUsbDriver.closeUsbDevice(MainActivity.this.m_Device);
                }
            } catch (Exception e) {
                MainActivity.this.promptUpdate(e.getMessage());
            }
        }
    }

    class UpdateClickListener implements View.OnClickListener {
        UpdateClickListener() {
        }

        public void onClick(View view) {
            try {
                if (MainActivity.this.PrintConnStatus()) {
                    ProgressDialog waitingDialog = new ProgressDialog(MainActivity.this);
                    waitingDialog.setTitle("Updatefirware");
                    waitingDialog.setMessage("Please wait...");
                    waitingDialog.setIndeterminate(true);
                    waitingDialog.setCancelable(false);
                    waitingDialog.show();
                    MainActivity.this.updateFirwareFile();
                    waitingDialog.cancel();
                    return;
                }
                MainActivity.this.promptUpdate("Please connect printer");
            } catch (Exception e) {
                Toast.makeText(MainActivity.this.getApplicationContext(), e.getMessage(), 0).show();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateFirwareFile() {
        if (this.mEdt_Path.getText().toString().equals("")) {
            promptUpdate("Please select update file!");
            return;
        }
        try {
            FileInputStream fin = new FileInputStream(new File(this.mEdt_Path.getText().toString()));
            int length = fin.available();
            this.CODEdata = new byte[length];
            fin.read(this.CODEdata);
            fin.close();
            this.m_strPrompt = "";
            promptUpdate("Updatefirware begin");
            promptUpdate("Read update file......");
            this.SizeA = length - 14;
            for (int N = 0; N < 12; N++) {
                this.PrinterName[N] = this.CODEdata[this.SizeA + N];
            }
            promptUpdate("Initialization printer");
            this.mUsbDriver.write(new byte[]{27, 64});
            delayL();
            this.JYValue = 0;
            SendNAME();
            if (this.JYValue == 3) {
                this.JYValue = 0;
                promptUpdate("Rest printer");
                PrinterREST();
                if (this.JYValue == 0) {
                    this.mUsbDriver.closeUsbDevice();
                    promptUpdate("Printer Rest success ");
                    this.m_IntOper = 2;
                    return;
                }
                this.m_iUpdateStatus = 1;
                promptUpdate("Printer communication failure\n");
            } else if (this.JYValue == 2) {
                this.m_iUpdateStatus = 2;
                promptUpdate("BIN files and printers do not match\n");
            } else {
                downUpdate();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "open file:" + e.getMessage(), 0).show();
        } catch (Exception e2) {
            Toast.makeText(getApplicationContext(), e2.getMessage(), 0).show();
        }
    }

    private void showNormalDialog() {
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
        normalDialog.setIcon(R.drawable.ic_launcher);
        normalDialog.setTitle("确定");
        normalDialog.setMessage("点击确定");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        normalDialog.show();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x02ba A[LOOP:5: B:38:0x020b->B:61:0x02ba, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x029f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void downUpdate() {
        /*
            r19 = this;
            r8 = 0
            r17 = 0
            r0 = r17
            r1 = r19
            r1.JYValue = r0
            java.lang.String r17 = "Wait for printer response"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            r0 = r19
            int r0 = r0.SizeA
            r17 = r0
            r0 = r19
            r1 = r17
            r0.SendStart(r1)
            r0 = r19
            int r0 = r0.JYValue
            r17 = r0
            r18 = 2
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x003f
            r17 = 5
            r0 = r17
            r1 = r19
            r1.m_iUpdateStatus = r0
            java.lang.String r17 = "BIN files and printers do not match\n"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
        L_0x003e:
            return
        L_0x003f:
            r0 = r19
            int r0 = r0.JYValue
            r17 = r0
            if (r17 != 0) goto L_0x003e
            java.lang.String r17 = "Updatefirware please wait "
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            r19.delayL()
            r19.delayL()
            r19.delayL()
            java.lang.String r17 = "..."
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            r17 = 1033(0x409, float:1.448E-42)
            r0 = r17
            byte[] r6 = new byte[r0]
            r17 = 4
            r0 = r17
            byte[] r13 = new byte[r0]
            r0 = r19
            int r0 = r0.SizeA
            r17 = r0
            int r5 = r17 + 2
            int r4 = r5 % 1024
            int r17 = r5 - r4
            r0 = r17
            int r3 = r0 / 1024
            r11 = 0
            r15 = 0
        L_0x0080:
            if (r15 < r3) goto L_0x018d
        L_0x0082:
            if (r4 <= 0) goto L_0x014f
            r0 = r19
            int r0 = r0.JYValue
            r17 = r0
            if (r17 != 0) goto L_0x014f
            r9 = 0
            int r7 = r3 * 1024
            int r10 = r9 + 1
            r17 = -70
            r6[r9] = r17
            int r9 = r10 + 1
            r17 = -69
            r6[r10] = r17
            int r10 = r9 + 1
            r17 = -68
            r6[r9] = r17
            int r9 = r10 + 1
            r17 = -67
            r6[r10] = r17
            int r10 = r9 + 1
            int r17 = r7 >> 16
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r9] = r17
            int r9 = r10 + 1
            int r17 = r7 >> 8
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r10] = r17
            int r10 = r9 + 1
            r0 = r7 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r9] = r17
            r14 = 0
            r16 = 0
            r12 = r11
        L_0x00da:
            r0 = r16
            if (r0 < r4) goto L_0x02c7
            r16 = r4
        L_0x00e0:
            r17 = 1024(0x400, float:1.435E-42)
            r0 = r16
            r1 = r17
            if (r0 < r1) goto L_0x02fb
            int r9 = r10 + 1
            int r17 = r14 >> 8
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r10] = r17
            int r10 = r9 + 1
            r0 = r14 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r9] = r17
            r2 = 0
        L_0x0107:
            r0 = r19
            com.printsdk.usbsdk.UsbDriver r0 = r0.mUsbDriver
            r17 = r0
            r0 = r17
            int r17 = r0.read(r13, r6)
            if (r17 <= 0) goto L_0x0324
            r17 = 0
            byte r17 = r13[r17]
            r18 = -70
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0308
            r17 = 1
            byte r17 = r13[r17]
            r18 = -69
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0308
            r17 = 2
            byte r17 = r13[r17]
            r18 = -68
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0308
            r17 = 3
            byte r17 = r13[r17]
            r18 = -67
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0308
            java.lang.String r17 = "Check ok"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            r11 = r12
        L_0x014f:
            r0 = r19
            int r0 = r0.JYValue
            r17 = r0
            if (r17 != 0) goto L_0x033e
            r19.delayL()
            r19.delayL()
            r19.delayL()
            java.lang.String r17 = "Rest Printer"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            r19.WRITEflagandREST()
            r0 = r19
            int r0 = r0.JYValue
            r17 = r0
            r18 = 1
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0333
            java.lang.String r17 = "The program update is complete, Rest Printer fail!"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
        L_0x0183:
            r17 = 0
            r0 = r17
            r1 = r19
            r1.m_iUpdateStatus = r0
            goto L_0x003e
        L_0x018d:
            r9 = 0
            int r7 = r15 * 1024
            java.lang.String r17 = "..."
            r0 = r19
            r1 = r17
            r0.promptUpdateLine(r1)
            int r10 = r9 + 1
            r17 = -70
            r6[r9] = r17
            int r9 = r10 + 1
            r17 = -69
            r6[r10] = r17
            int r10 = r9 + 1
            r17 = -68
            r6[r9] = r17
            int r9 = r10 + 1
            r17 = -67
            r6[r10] = r17
            int r10 = r9 + 1
            int r17 = r7 >> 16
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r9] = r17
            int r9 = r10 + 1
            int r17 = r7 >> 8
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r10] = r17
            int r10 = r9 + 1
            r0 = r7 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r9] = r17
            r14 = 0
            r16 = 0
            r12 = r11
        L_0x01e4:
            r17 = 1024(0x400, float:1.435E-42)
            r0 = r16
            r1 = r17
            if (r0 < r1) goto L_0x025a
            int r9 = r10 + 1
            int r17 = r14 >> 8
            r0 = r17
            r0 = r0 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r10] = r17
            int r10 = r9 + 1
            r0 = r14 & 255(0xff, float:3.57E-43)
            r17 = r0
            r0 = r17
            byte r0 = (byte) r0
            r17 = r0
            r6[r9] = r17
            r2 = 0
        L_0x020b:
            r0 = r19
            com.printsdk.usbsdk.UsbDriver r0 = r0.mUsbDriver
            r17 = r0
            r0 = r17
            int r17 = r0.read(r13, r6)
            if (r17 <= 0) goto L_0x02b0
            r17 = 0
            byte r17 = r13[r17]
            r18 = -70
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x028e
            r17 = 1
            byte r17 = r13[r17]
            r18 = -69
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x028e
            r17 = 2
            byte r17 = r13[r17]
            r18 = -68
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x028e
            r17 = 3
            byte r17 = r13[r17]
            r18 = -67
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x028e
        L_0x0249:
            r0 = r19
            int r0 = r0.JYValue
            r17 = r0
            r18 = 1
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x02bf
            r11 = r12
            goto L_0x0082
        L_0x025a:
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            byte r17 = r17[r12]
            if (r17 >= 0) goto L_0x0285
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            byte r17 = r17[r12]
            r0 = r17
            int r8 = r0 + 256
        L_0x0270:
            int r14 = r14 + r8
            int r9 = r10 + 1
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            int r11 = r12 + 1
            byte r17 = r17[r12]
            r6[r10] = r17
            int r16 = r16 + 1
            r12 = r11
            r10 = r9
            goto L_0x01e4
        L_0x0285:
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            byte r8 = r17[r12]
            goto L_0x0270
        L_0x028e:
            java.lang.String r17 = "updaet check error"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
        L_0x0297:
            int r2 = r2 + 1
            r17 = 3
            r0 = r17
            if (r2 <= r0) goto L_0x02ba
            r17 = 1
            r0 = r17
            r1 = r19
            r1.JYValue = r0
            r17 = 7
            r0 = r17
            r1 = r19
            r1.m_iUpdateStatus = r0
            goto L_0x0249
        L_0x02b0:
            java.lang.String r17 = "update check fail"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            goto L_0x0297
        L_0x02ba:
            r19.delay()
            goto L_0x020b
        L_0x02bf:
            r19.delay()
            int r15 = r15 + 1
            r11 = r12
            goto L_0x0080
        L_0x02c7:
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            byte r17 = r17[r12]
            if (r17 >= 0) goto L_0x02f2
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            byte r17 = r17[r12]
            r0 = r17
            int r8 = r0 + 256
        L_0x02dd:
            int r14 = r14 + r8
            int r9 = r10 + 1
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            int r11 = r12 + 1
            byte r17 = r17[r12]
            r6[r10] = r17
            int r16 = r16 + 1
            r12 = r11
            r10 = r9
            goto L_0x00da
        L_0x02f2:
            r0 = r19
            byte[] r0 = r0.CODEdata
            r17 = r0
            byte r8 = r17[r12]
            goto L_0x02dd
        L_0x02fb:
            int r14 = r14 + 255
            int r9 = r10 + 1
            r17 = -1
            r6[r10] = r17
            int r16 = r16 + 1
            r10 = r9
            goto L_0x00e0
        L_0x0308:
            java.lang.String r17 = "Check error"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
        L_0x0311:
            int r2 = r2 + 1
            r17 = 3
            r0 = r17
            if (r2 <= r0) goto L_0x032e
            r17 = 1
            r0 = r17
            r1 = r19
            r1.JYValue = r0
            r11 = r12
            goto L_0x014f
        L_0x0324:
            java.lang.String r17 = "Check fail"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            goto L_0x0311
        L_0x032e:
            r19.delay()
            goto L_0x0107
        L_0x0333:
            java.lang.String r17 = "The program update is complete, please restart the printer!"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            goto L_0x0183
        L_0x033e:
            r17 = 7
            r0 = r17
            r1 = r19
            r1.m_iUpdateStatus = r0
            java.lang.String r17 = "Updatefirware fail"
            r0 = r19
            r1 = r17
            r0.promptUpdate(r1)
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.updatefirware.MainActivity.downUpdate():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void SendNAME() {
        /*
            r14 = this;
            r13 = 22
            r12 = 1
            r11 = 3
            r10 = 2
            r9 = 0
            r4 = 0
            byte[] r2 = new byte[r11]
            r7 = 24
            byte[] r1 = new byte[r7]
            r7 = 4
            byte[] r5 = new byte[r7]
            r7 = 19
            r2[r9] = r7
            r7 = 119(0x77, float:1.67E-43)
            r2[r12] = r7
            r7 = -103(0xffffffffffffff99, float:NaN)
            r2[r10] = r7
            r7 = -86
            r1[r9] = r7
            r7 = -85
            r1[r12] = r7
            r7 = -84
            r1[r10] = r7
            r7 = -83
            r1[r11] = r7
            r7 = 4
            r1[r7] = r10
            r7 = 5
            r8 = 16
            r1[r7] = r8
            r7 = 6
            r1[r7] = r9
            r7 = 7
            r1[r7] = r9
            r7 = 8
            r1[r7] = r9
            r3 = 9
        L_0x0040:
            if (r3 < r13) goto L_0x0080
            r6 = 0
            r3 = 0
        L_0x0044:
            if (r3 < r13) goto L_0x008b
            int r7 = r6 >> 8
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r1[r13] = r7
            r7 = 23
            r8 = r6 & 255(0xff, float:3.57E-43)
            byte r8 = (byte) r8
            r1[r7] = r8
            r0 = 0
        L_0x0055:
            com.printsdk.usbsdk.UsbDriver r7 = r14.mUsbDriver
            r7.write((byte[]) r2, (int) r11)
            com.printsdk.usbsdk.UsbDriver r7 = r14.mUsbDriver
            int r7 = r7.read(r5, r1)
            if (r7 <= 0) goto L_0x00d6
            byte r7 = r5[r9]
            r8 = -86
            if (r7 != r8) goto L_0x009a
            byte r7 = r5[r12]
            r8 = -85
            if (r7 != r8) goto L_0x009a
            byte r7 = r5[r10]
            r8 = -84
            if (r7 != r8) goto L_0x009a
            byte r7 = r5[r11]
            r8 = -83
            if (r7 != r8) goto L_0x009a
            r14.JYValue = r9
            r14.delayL()
        L_0x007f:
            return
        L_0x0080:
            byte[] r7 = r14.PrinterName
            int r8 = r3 + -9
            byte r7 = r7[r8]
            r1[r3] = r7
            int r3 = r3 + 1
            goto L_0x0040
        L_0x008b:
            byte r7 = r1[r3]
            if (r7 >= 0) goto L_0x0097
            byte r7 = r1[r3]
            int r4 = r7 + 256
        L_0x0093:
            int r6 = r6 + r4
            int r3 = r3 + 1
            goto L_0x0044
        L_0x0097:
            byte r4 = r1[r3]
            goto L_0x0093
        L_0x009a:
            byte r7 = r5[r9]
            r8 = 102(0x66, float:1.43E-43)
            if (r7 != r8) goto L_0x00b8
            byte r7 = r5[r12]
            r8 = 119(0x77, float:1.67E-43)
            if (r7 != r8) goto L_0x00b8
            byte r7 = r5[r10]
            r8 = -120(0xffffffffffffff88, float:NaN)
            if (r7 != r8) goto L_0x00b8
            byte r7 = r5[r11]
            r8 = -103(0xffffffffffffff99, float:NaN)
            if (r7 != r8) goto L_0x00b8
            r14.JYValue = r10
            r14.delayL()
            goto L_0x007f
        L_0x00b8:
            byte r7 = r5[r9]
            r8 = -22
            if (r7 != r8) goto L_0x00d6
            byte r7 = r5[r12]
            r8 = -21
            if (r7 != r8) goto L_0x00d6
            byte r7 = r5[r10]
            r8 = -20
            if (r7 != r8) goto L_0x00d6
            byte r7 = r5[r11]
            r8 = -19
            if (r7 != r8) goto L_0x00d6
            r14.JYValue = r11
            r14.delayL()
            goto L_0x007f
        L_0x00d6:
            int r0 = r0 + 1
            r7 = 5
            if (r0 <= r7) goto L_0x00de
            r14.JYValue = r12
            goto L_0x007f
        L_0x00de:
            r14.delay()
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.updatefirware.MainActivity.SendNAME():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void SendStart(int r14) {
        /*
            r13 = this;
            r12 = 22
            r11 = 3
            r10 = 2
            r9 = 0
            r8 = 1
            r3 = 0
            r6 = 24
            byte[] r1 = new byte[r6]
            r6 = 4
            byte[] r4 = new byte[r6]
            r6 = -86
            r1[r9] = r6
            r6 = -85
            r1[r8] = r6
            r6 = -84
            r1[r10] = r6
            r6 = -83
            r1[r11] = r6
            r6 = 4
            r1[r6] = r8
            r6 = 5
            r7 = 16
            r1[r6] = r7
            r6 = 6
            int r7 = r14 >> 16
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r1[r6] = r7
            r6 = 7
            int r7 = r14 >> 8
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r1[r6] = r7
            r6 = 8
            r7 = r14 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r1[r6] = r7
            r2 = 9
        L_0x003f:
            if (r2 < r12) goto L_0x007a
            r5 = 0
            r2 = 0
        L_0x0043:
            if (r2 < r12) goto L_0x0085
            int r6 = r5 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = (byte) r6
            r1[r12] = r6
            r6 = 23
            r7 = r5 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r1[r6] = r7
            r0 = 0
        L_0x0054:
            com.printsdk.usbsdk.UsbDriver r6 = r13.mUsbDriver
            int r6 = r6.read(r4, r1)
            if (r6 <= 0) goto L_0x00b2
            byte r6 = r4[r9]
            r7 = -86
            if (r6 != r7) goto L_0x0094
            byte r6 = r4[r8]
            r7 = -85
            if (r6 != r7) goto L_0x0094
            byte r6 = r4[r10]
            r7 = -84
            if (r6 != r7) goto L_0x0094
            byte r6 = r4[r11]
            r7 = -83
            if (r6 != r7) goto L_0x0094
            r13.JYValue = r9
            r13.delayL()
        L_0x0079:
            return
        L_0x007a:
            byte[] r6 = r13.PrinterName
            int r7 = r2 + -9
            byte r6 = r6[r7]
            r1[r2] = r6
            int r2 = r2 + 1
            goto L_0x003f
        L_0x0085:
            byte r6 = r1[r2]
            if (r6 >= 0) goto L_0x0091
            byte r6 = r1[r2]
            int r3 = r6 + 256
        L_0x008d:
            int r5 = r5 + r3
            int r2 = r2 + 1
            goto L_0x0043
        L_0x0091:
            byte r3 = r1[r2]
            goto L_0x008d
        L_0x0094:
            byte r6 = r4[r9]
            r7 = 102(0x66, float:1.43E-43)
            if (r6 != r7) goto L_0x00b2
            byte r6 = r4[r8]
            r7 = 119(0x77, float:1.67E-43)
            if (r6 != r7) goto L_0x00b2
            byte r6 = r4[r10]
            r7 = -120(0xffffffffffffff88, float:NaN)
            if (r6 != r7) goto L_0x00b2
            byte r6 = r4[r11]
            r7 = -103(0xffffffffffffff99, float:NaN)
            if (r6 != r7) goto L_0x00b2
            r13.JYValue = r10
            r13.delayL()
            goto L_0x0079
        L_0x00b2:
            int r0 = r0 + 1
            r6 = 25
            if (r0 <= r6) goto L_0x00bb
            r13.JYValue = r8
            goto L_0x0079
        L_0x00bb:
            r13.delay()
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.updatefirware.MainActivity.SendStart(int):void");
    }

    /* access modifiers changed from: package-private */
    public void WRITEflagandREST() {
        byte[] buffer = new byte[16];
        byte[] stause = new byte[16];
        buffer[0] = -54;
        buffer[1] = -53;
        buffer[2] = -52;
        buffer[3] = -51;
        buffer[4] = -54;
        buffer[5] = -53;
        buffer[6] = -52;
        buffer[7] = -51;
        int RUNtime = 0;
        while (this.mUsbDriver.read(stause, buffer) > 0) {
            if (stause[0] != -54 || stause[1] != -53 || stause[2] != -52 || stause[3] != -51) {
                RUNtime++;
                if (RUNtime > 5) {
                    this.JYValue = 1;
                    return;
                }
                delay();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void delay() {
        sleep(120);
    }

    /* access modifiers changed from: package-private */
    public void delayL() {
        sleep(1200);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void sleep(long r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0006, all -> 0x0008 }
        L_0x0004:
            monitor-exit(r1)
            return
        L_0x0006:
            r0 = move-exception
            goto L_0x0004
        L_0x0008:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.updatefirware.MainActivity.sleep(long):void");
    }

    /* access modifiers changed from: package-private */
    public void PrinterREST() {
        byte[] buffer = new byte[16];
        byte[] stause = new byte[16];
        buffer[0] = 19;
        buffer[1] = 119;
        buffer[2] = -120;
        buffer[3] = 18;
        buffer[4] = 52;
        buffer[5] = 86;
        buffer[6] = 120;
        if (this.mUsbDriver.read(stause, buffer) <= 0) {
            this.JYValue = 1;
        } else if (stause[0] != 18 || stause[1] != 52 || stause[2] != 86 || stause[3] != 120) {
            this.JYValue = 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void promptUpdate(String strInfo) {
        this.m_strPrompt = String.valueOf(this.m_strPrompt) + "\n" + new SimpleDateFormat(" HH:mm:ss").format(new Date(System.currentTimeMillis())) + ":" + strInfo;
        this.mEdt_Prompt.setText(this.m_strPrompt);
    }

    /* access modifiers changed from: package-private */
    public void promptUpdateLine(String strInfo) {
        this.m_strPrompt = String.valueOf(this.m_strPrompt) + strInfo;
        this.mEdt_Prompt.setText(this.m_strPrompt);
    }
}
