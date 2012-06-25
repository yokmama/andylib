/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/yokmama/git/github/andylib/AndyLib/src/jp/co/createsystem/IDTalkerSpeechServiceCallbackListener.aidl
 */
package jp.co.createsystem;
public interface IDTalkerSpeechServiceCallbackListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements jp.co.createsystem.IDTalkerSpeechServiceCallbackListener
{
private static final java.lang.String DESCRIPTOR = "jp.co.createsystem.IDTalkerSpeechServiceCallbackListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an jp.co.createsystem.IDTalkerSpeechServiceCallbackListener interface,
 * generating a proxy if needed.
 */
public static jp.co.createsystem.IDTalkerSpeechServiceCallbackListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof jp.co.createsystem.IDTalkerSpeechServiceCallbackListener))) {
return ((jp.co.createsystem.IDTalkerSpeechServiceCallbackListener)iin);
}
return new jp.co.createsystem.IDTalkerSpeechServiceCallbackListener.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_didFinishPlaying:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.didFinishPlaying(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_nowPosition:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.nowPosition(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_didGotString:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.didGotString(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_didGotStringOffset:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.didGotStringOffset(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements jp.co.createsystem.IDTalkerSpeechServiceCallbackListener
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void didFinishPlaying(int id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(id);
mRemote.transact(Stub.TRANSACTION_didFinishPlaying, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void nowPosition(int position) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(position);
mRemote.transact(Stub.TRANSACTION_nowPosition, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void didGotString(java.lang.String speakText) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(speakText);
mRemote.transact(Stub.TRANSACTION_didGotString, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void didGotStringOffset(int offset, int length) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(offset);
_data.writeInt(length);
mRemote.transact(Stub.TRANSACTION_didGotStringOffset, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_didFinishPlaying = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_nowPosition = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_didGotString = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_didGotStringOffset = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void didFinishPlaying(int id) throws android.os.RemoteException;
public void nowPosition(int position) throws android.os.RemoteException;
public void didGotString(java.lang.String speakText) throws android.os.RemoteException;
public void didGotStringOffset(int offset, int length) throws android.os.RemoteException;
}
