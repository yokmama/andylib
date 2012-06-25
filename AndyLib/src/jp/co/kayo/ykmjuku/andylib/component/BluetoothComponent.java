package jp.co.kayo.ykmjuku.andylib.component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

import jp.co.kayo.ykmjuku.andylib.DeviceListActivity;
import jp.co.kayo.ykmjuku.andylib.R;
import jp.co.kayo.ykmjuku.andylib.cmd.BlueToothCommand;
import jp.co.kayo.ykmjuku.andylib.net.CommandMessage;
import jp.co.kayo.ykmjuku.andylib.net.RecieveCommandMessage;
import jp.co.kayo.ykmjuku.andylib.tools.Logger;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BluetoothComponent extends DeviceComponent {
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int MAX_READSIZE = 16384;
    public static final byte MESSAGE_STATE_CHANGE = 1;
    public static final byte MESSAGE_READ = 2;
    public static final byte MESSAGE_WRITE = 3;
    public static final byte MESSAGE_DEVICE_CONNECTED = 4;
    public static final byte MESSAGE_DEVICE_CONNECT_FAILED = 5;
    public static final byte MESSAGE_DEVICE_CONNECT_LOST = 6;
    private static final String NAME_SECURE = "andytank_link_Secure";
    private static final String NAME_INSECURE = "andytank_link_Insecure";
    
    // Constants that indicate the current connection state
    public static final byte STATE_NONE = 0;       // we're doing nothing
    public static final byte STATE_LISTEN = 1;     // now listening for incoming connections
    public static final byte STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final byte STATE_CONNECTED = 3;  // now connected to a remote device

    private static final UUID MY_UUID_SECURE =
        UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");//fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final UUID MY_UUID_INSECURE =
        UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");//8ce255c0-200a-11e0-ac64-0800200c9a66");

    // Member fields
    private BluetoothAdapter mAdapter;
    private AcceptThread mSecureAcceptThread;
    private AcceptThread mInsecureAcceptThread;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private int mState;
    private BluetoothAdapter mBluetoothAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        return new View(getActivity());
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }
    
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bt_option_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.secure_connect_scan){
            // Launch the DeviceListActivity to see devices and do scan
            DeviceListActivity newFragment = new DeviceListActivity(getTag(), true);
            newFragment.show(getFragmentManager(), "dialog");
            return true;
        }
        else if (item.getItemId() == R.id.insecure_connect_scan){
            // Launch the DeviceListActivity to see devices and do scan
            DeviceListActivity newFragment = new DeviceListActivity(getTag(), false);
            newFragment.show(getFragmentManager(), "dialog");
            return true;
        }
        else if (item.getItemId() == R.id.discoverable){
            // Ensure this device is discoverable by others
            ensureDiscoverable();
            return true;
        }
        return false;
    }
    
    private void setupConroler() {

    }
    
    public void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras().getString(
                DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BLuetoothDevice object
        if (address != null && address.length() > 0) {
            try {
                BluetoothDevice device = mBluetoothAdapter
                        .getRemoteDevice(address);
                // Attempt to connect to the device
                connect(device, secure);
            } catch (Exception e) {
                Logger.e("connectDevice", e);
            }
        }
    }
    
    private void ensureDiscoverable() {
        Logger.d("ensure discoverable");
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d("onActivityResult " + resultCode);
        if(requestCode == REQUEST_ENABLE_BT){
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a chat session
                setupConroler();
            } else {
                // User did not enable Bluetooth or an error occured
                Logger.d("BT not enabled");
                Toast.makeText(getActivity(), R.string.bt_not_enabled_leaving,
                        Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
    }
    

    
    @Override
    public boolean sendCommand(CommandMessage msg) throws IOException {
         write(msg.getBuffer());
        return true;
    }
    
    private synchronized void setState(byte state) {
        Logger.d("setState() " + mState + " -> " + state);
        mState = state;

        Message m = Message.obtain(getHandler(), MESSAGE_STATE_CHANGE);
        BlueToothCommand cmd = new BlueToothCommand(MESSAGE_STATE_CHANGE, state);
        m.obj = cmd;
        getHandler().sendMessage(m);
    }

    /**
     * Return the current connection state. */
    public synchronized int getState() {
        return mState;
    }

    /**
     * Start the chat service. Specifically start AcceptThread to begin a
     * session in listening (server) mode. Called by the Activity onResume() */
    public synchronized void start() {
        Logger.d("bluetooth start");

        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        setState(STATE_LISTEN);

        // Start the thread to listen on a BluetoothServerSocket
        if (mSecureAcceptThread == null) {
            mSecureAcceptThread = new AcceptThread(true);
            mSecureAcceptThread.start();
        }
        if (mInsecureAcceptThread == null) {
            mInsecureAcceptThread = new AcceptThread(false);
            mInsecureAcceptThread.start();
        }
    }

    /**
     * Start the ConnectThread to initiate a connection to a remote device.
     * @param device  The BluetoothDevice to connect
     * @param secure Socket Security type - Secure (true) , Insecure (false)
     */
    public synchronized void connect(BluetoothDevice device, boolean secure) {
        Logger.d("connect to: " + device);

        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device, secure);
        mConnectThread.start();
        setState(STATE_CONNECTING);
    }

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     * @param socket  The BluetoothSocket on which the connection was made
     * @param device  The BluetoothDevice that has been connected
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice
            device, final String socketType) {
        Logger.d("connected, Socket Type:" + socketType);

        // Cancel the thread that completed the connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Cancel the accept thread because we only want to connect to one device
        if (mSecureAcceptThread != null) {
            mSecureAcceptThread.cancel();
            mSecureAcceptThread = null;
        }
        if (mInsecureAcceptThread != null) {
            mInsecureAcceptThread.cancel();
            mInsecureAcceptThread = null;
        }

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket, socketType);
        mConnectedThread.start();

        // Send the name of the connected device back to the UI Activity
        Message m = Message.obtain(getHandler());
        BlueToothCommand cmd = new BlueToothCommand(MESSAGE_DEVICE_CONNECTED);
        m.obj = cmd;
        getHandler().sendMessage(m);

        setState(STATE_CONNECTED);
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        Logger.d("stop");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        if (mSecureAcceptThread != null) {
            mSecureAcceptThread.cancel();
            mSecureAcceptThread = null;
        }

        if (mInsecureAcceptThread != null) {
            mInsecureAcceptThread.cancel();
            mInsecureAcceptThread = null;
        }
        setState(STATE_NONE);
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     * @param out The bytes to write
     * @see ConnectedThread#write(byte[])
     */
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private void connectionFailed() {
        Message m = Message.obtain(getHandler());
        BlueToothCommand cmd = new BlueToothCommand(MESSAGE_DEVICE_CONNECT_FAILED, "Unable to connect device");
        m.obj = cmd;
        getHandler().sendMessage(m);

        BluetoothComponent.this.start();
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private void connectionLost() {
        Message m = Message.obtain(getHandler());
        BlueToothCommand cmd = new BlueToothCommand(MESSAGE_DEVICE_CONNECT_LOST, "Device connection was lost");
        m.obj = cmd;
        getHandler().sendMessage(m);

        BluetoothComponent.this.start();
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */
    private class AcceptThread extends Thread {
        // The local server socket
        private final BluetoothServerSocket mmServerSocket;
        private String mSocketType;

        public AcceptThread(boolean secure) {
            BluetoothServerSocket tmp = null;
            mSocketType = secure ? "Secure":"Insecure";

            // Create a new listening server socket
            try {
                if (secure) {
                    tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME_SECURE,
                        MY_UUID_SECURE);
                } else {
                    tmp = mAdapter.listenUsingInsecureRfcommWithServiceRecord(
                            NAME_INSECURE, MY_UUID_INSECURE);
                }
            } catch (IOException e) {
                Logger.e("Socket Type: " + mSocketType + "listen() failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            Logger.d("Socket Type: " + mSocketType +
                    "BEGIN mAcceptThread" + this);
            setName("AcceptThread" + mSocketType);

            BluetoothSocket socket = null;

            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED) {
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Logger.e("Socket Type: " + mSocketType + "accept() failed", e);
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    synchronized (BluetoothComponent.this) {
                        switch (mState) {
                        case STATE_LISTEN:
                        case STATE_CONNECTING:
                            // Situation normal. Start the connected thread.
                            connected(socket, socket.getRemoteDevice(),
                                    mSocketType);
                            break;
                        case STATE_NONE:
                        case STATE_CONNECTED:
                            // Either not ready or already connected. Terminate new socket.
                            try {
                                socket.close();
                            } catch (IOException e) {
                                Logger.e("Could not close unwanted socket", e);
                            }
                            break;
                        }
                    }
                }
            }
            Logger.d("END mAcceptThread, socket Type: " + mSocketType);

        }

        public void cancel() {
            Logger.d("Socket Type" + mSocketType + "cancel " + this);
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Logger.e("Socket Type" + mSocketType + "close() of server failed", e);
            }
        }
    }


    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private String mSocketType;

        public ConnectThread(BluetoothDevice device, boolean secure) {
            mmDevice = device;
            BluetoothSocket tmp = null;
            mSocketType = secure ? "Secure" : "Insecure";

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                if (secure) {
                    tmp = device.createRfcommSocketToServiceRecord(
                            MY_UUID_SECURE);
                } else {
                    tmp = device.createInsecureRfcommSocketToServiceRecord(
                            MY_UUID_INSECURE);
                }
            } catch (IOException e) {
                Logger.e("Socket Type: " + mSocketType + "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            Logger.d("BEGIN mConnectThread SocketType:" + mSocketType);
            setName("ConnectThread" + mSocketType);

            // Always cancel discovery because it will slow down a connection
            mAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket.connect();
            } catch (IOException e) {
                // Close the socket
                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Logger.e("unable to close() " + mSocketType +
                            " socket during connection failure", e2);
                }
                connectionFailed();
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (BluetoothComponent.this) {
                mConnectThread = null;
            }

            // Start the connected thread
            connected(mmSocket, mmDevice, mSocketType);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Logger.e("close() of connect " + mSocketType + " socket failed", e);
            }
        }
    }

    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket, String socketType) {
            Logger.d("create ConnectedThread: " + socketType);
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Logger.e("temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        
        private class IRecieveCommandMessage extends RecieveCommandMessage{
            byte cmd;
            IRecieveCommandMessage(byte cmd, byte[] buffer){
                super(buffer);
                this.cmd = cmd;
            }

            @Override
            public byte getCommand() {
                return cmd;
            }
            
        }
        
        private void parseData(int size, byte[] pack){
            int pos=0;
            byte len;
            do{
                len = (byte)(pack[pos]+1);
                if(len>0 && (pos+len)<=size){
                    byte[] chunk = Arrays.copyOfRange(pack, pos, pos+len);
                    byte cmd = chunk[1];
                    RecieveCommandMessage msg = null;
                    if(chunk.length>1){
                        msg = new IRecieveCommandMessage(cmd, Arrays.copyOfRange(chunk, 2, chunk.length));
                    }
                    else{
                        msg = new IRecieveCommandMessage(cmd, new byte[0]);
                    }
                    Message m = Message.obtain(getHandler(), cmd);
                    m.obj = msg;
                    getHandler().sendMessage(m);
                }
                pos+=len;
            }while(pos<size);
        }

        public void run() {
            try{
                Logger.d("BEGIN mConnectedThread");
                byte[] buffer = new byte[MAX_READSIZE];
                int size = 0;
                Logger.d("start listen input:"+mmInStream);
                while ((size = mmInStream.read(buffer)) >= 0) {
                    parseData(size, buffer);
                }
            } catch (IOException e) {
                Logger.e("disconnected", e);
                connectionLost();
            }
        }

        /**
         * Write to the connected OutStream.
         * @param buffer  The bytes to write
         */
        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);

                Message m = Message.obtain(getHandler(), MESSAGE_WRITE);
                BlueToothCommand cmd = new BlueToothCommand(MESSAGE_WRITE, buffer);
                m.obj = cmd;
                getHandler().sendMessage(m);
            } catch (IOException e) {
                Logger.e("Exception during write", e);
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Logger.e("close() of connect socket failed", e);
            }
        }
    }


}
