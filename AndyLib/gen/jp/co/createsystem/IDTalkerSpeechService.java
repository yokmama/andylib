/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hijirinhijirin/git/github/andylib/AndyLib/src/jp/co/createsystem/IDTalkerSpeechService.aidl
 */
package jp.co.createsystem;
public interface IDTalkerSpeechService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements jp.co.createsystem.IDTalkerSpeechService
{
private static final java.lang.String DESCRIPTOR = "jp.co.createsystem.IDTalkerSpeechService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an jp.co.createsystem.IDTalkerSpeechService interface,
 * generating a proxy if needed.
 */
public static jp.co.createsystem.IDTalkerSpeechService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof jp.co.createsystem.IDTalkerSpeechService))) {
return ((jp.co.createsystem.IDTalkerSpeechService)iin);
}
return new jp.co.createsystem.IDTalkerSpeechService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
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
case TRANSACTION_speak:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.speak(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_speakAt:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
int _result = this.speakAt(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_speakPhoneme:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.speakPhoneme(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_sing:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.sing(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_wavPlay:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.wavPlay(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_speakSyosai:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.speakSyosai(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_speakSyosaiForIME:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.speakSyosaiForIME(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_stop:
{
data.enforceInterface(DESCRIPTOR);
this.stop();
reply.writeNoException();
return true;
}
case TRANSACTION_pause:
{
data.enforceInterface(DESCRIPTOR);
this.pause();
reply.writeNoException();
return true;
}
case TRANSACTION_resume:
{
data.enforceInterface(DESCRIPTOR);
this.resume();
reply.writeNoException();
return true;
}
case TRANSACTION_resumeNext:
{
data.enforceInterface(DESCRIPTOR);
this.resumeNext();
reply.writeNoException();
return true;
}
case TRANSACTION_busy:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.busy();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isPause:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isPause();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getPositionTTS:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getPositionTTS();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setPositionTTS:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setPositionTTS(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getOffset:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getOffset();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setOffset:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setOffset(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setVoice:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setVoice(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getVoice:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getVoice();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getVoiceName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getVoiceName();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_setSpeed:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setSpeed(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getSpeed:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getSpeed();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setTone:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setTone(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getTone:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getTone();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setVolume:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setVolume(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getVolume:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getVolume();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setHightone:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setHightone(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getHightone:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getHightone();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setIntonation:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setIntonation(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getIntonation:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getIntonation();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setEcho:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setEcho(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getEcho:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getEcho();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setFastFoward:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setFastFoward(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getFastFoward:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getFastFoward();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setWarpRate:
{
data.enforceInterface(DESCRIPTOR);
float _arg0;
_arg0 = data.readFloat();
this.setWarpRate(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getWarpRate:
{
data.enforceInterface(DESCRIPTOR);
float _result = this.getWarpRate();
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_setTempoRate:
{
data.enforceInterface(DESCRIPTOR);
float _arg0;
_arg0 = data.readFloat();
this.setTempoRate(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getTempoRate:
{
data.enforceInterface(DESCRIPTOR);
float _result = this.getTempoRate();
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_getKigouYomi:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.getKigouYomi();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setKigouYomi:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setKigouYomi(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getKutouten:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.getKutouten();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setKutouten:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setKutouten(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getNumAnalysis:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.getNumAnalysis();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setNumAnalysis:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setNumAnalysis(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getRomajiNumb:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getRomajiNumb();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setRomajiNumb:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setRomajiNumb(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getCrlfDelimitation:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.getCrlfDelimitation();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setCrlfDelimitation:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setCrlfDelimitation(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getSpaceDelimitation:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.getSpaceDelimitation();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setSpaceDelimitation:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setSpaceDelimitation(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setDefault:
{
data.enforceInterface(DESCRIPTOR);
this.setDefault();
reply.writeNoException();
return true;
}
case TRANSACTION_addListenner:
{
data.enforceInterface(DESCRIPTOR);
jp.co.createsystem.IDTalkerSpeechServiceCallbackListener _arg0;
_arg0 = jp.co.createsystem.IDTalkerSpeechServiceCallbackListener.Stub.asInterface(data.readStrongBinder());
this.addListenner(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_removeListener:
{
data.enforceInterface(DESCRIPTOR);
jp.co.createsystem.IDTalkerSpeechServiceCallbackListener _arg0;
_arg0 = jp.co.createsystem.IDTalkerSpeechServiceCallbackListener.Stub.asInterface(data.readStrongBinder());
this.removeListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_clipBoardSpeech:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.clipBoardSpeech(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_addUsrDict:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
int _result = this.addUsrDict(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_deleteUsrDict:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _result = this.deleteUsrDict(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getUsrDictContents:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.getUsrDictContents(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getUsrDictContentsKanj:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getUsrDictContentsKanj();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getUsrDictContentsKana:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getUsrDictContentsKana();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getUsrDictContentsHins:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getUsrDictContentsHins();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getUsrDictContentsKatu:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getUsrDictContentsKatu();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_checkUsrDictWordKanj:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.checkUsrDictWordKanj(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_checkUsrDictWordKana:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.checkUsrDictWordKana(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_accentChange:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.accentChange(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_kanjiToKanaConvert:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.kanjiToKanaConvert(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_flush:
{
data.enforceInterface(DESCRIPTOR);
this.flush();
reply.writeNoException();
return true;
}
case TRANSACTION_getVersion:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getVersion();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getDTServiceStatus:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getDTServiceStatus();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements jp.co.createsystem.IDTalkerSpeechService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
//speak control

@Override public int speak(java.lang.String textStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(textStr);
mRemote.transact(Stub.TRANSACTION_speak, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int speakAt(java.lang.String textStr, int offset) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(textStr);
_data.writeInt(offset);
mRemote.transact(Stub.TRANSACTION_speakAt, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int speakPhoneme(java.lang.String phonemeStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(phonemeStr);
mRemote.transact(Stub.TRANSACTION_speakPhoneme, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int sing(java.lang.String mmlStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(mmlStr);
mRemote.transact(Stub.TRANSACTION_sing, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int wavPlay(java.lang.String fileName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(fileName);
mRemote.transact(Stub.TRANSACTION_wavPlay, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int speakSyosai(java.lang.String textStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(textStr);
mRemote.transact(Stub.TRANSACTION_speakSyosai, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int speakSyosaiForIME(java.lang.String textStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(textStr);
mRemote.transact(Stub.TRANSACTION_speakSyosaiForIME, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void stop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void pause() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_pause, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void resume() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_resume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void resumeNext() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_resumeNext, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean busy() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_busy, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isPause() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isPause, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getPositionTTS() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPositionTTS, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setPositionTTS(int pos) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(pos);
mRemote.transact(Stub.TRANSACTION_setPositionTTS, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getOffset() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getOffset, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setOffset(int pos) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(pos);
mRemote.transact(Stub.TRANSACTION_setOffset, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//property 
//basic voice parameter

@Override public void setVoice(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setVoice, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0=Taro, 1=Hanako, 2= ----	(0)

@Override public int getVoice() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVoice, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0=Taro, 1=Hanako, 2= ----	(0)

@Override public java.lang.String getVoiceName() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVoiceName, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//

@Override public void setSpeed(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setSpeed, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0-9				(6)

@Override public int getSpeed() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSpeed, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0-9				(6)

@Override public void setTone(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setTone, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//1-5				(3)

@Override public int getTone() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getTone, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//1-5				(3)

@Override public void setVolume(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0-9				(7)

@Override public int getVolume() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0-9				(7)

@Override public void setHightone(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setHightone, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0,1				(0)

@Override public int getHightone() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getHightone, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0,1				(0)

@Override public void setIntonation(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setIntonation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0-3				(2)

@Override public int getIntonation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getIntonation, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0-3				(2)
//optional parameter

@Override public void setEcho(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setEcho, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0, 1				(0)

@Override public int getEcho() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEcho, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0, 1				(0)

@Override public void setFastFoward(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setFastFoward, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0, 1				(0)

@Override public int getFastFoward() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getFastFoward, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0, 1				(0)

@Override public void setWarpRate(float v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(v);
mRemote.transact(Stub.TRANSACTION_setWarpRate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0.6 - 1.0			(1.0)

@Override public float getWarpRate() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getWarpRate, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0.6 - 1.0			(1.0)

@Override public void setTempoRate(float v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(v);
mRemote.transact(Stub.TRANSACTION_setTempoRate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//0.5 - 1.5			(1.0)

@Override public float getTempoRate() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getTempoRate, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//0.5 - 1.5			(1.0)

@Override public boolean getKigouYomi() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getKigouYomi, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setKigouYomi(boolean v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((v)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setKigouYomi, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean getKutouten() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getKutouten, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setKutouten(boolean v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((v)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setKutouten, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean getNumAnalysis() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getNumAnalysis, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setNumAnalysis(boolean v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((v)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setNumAnalysis, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getRomajiNumb() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRomajiNumb, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setRomajiNumb(int v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(v);
mRemote.transact(Stub.TRANSACTION_setRomajiNumb, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean getCrlfDelimitation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCrlfDelimitation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setCrlfDelimitation(boolean v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((v)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setCrlfDelimitation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean getSpaceDelimitation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSpaceDelimitation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setSpaceDelimitation(boolean v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((v)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setSpaceDelimitation, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setDefault() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_setDefault, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//Listener

@Override public void addListenner(jp.co.createsystem.IDTalkerSpeechServiceCallbackListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addListenner, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void removeListener(jp.co.createsystem.IDTalkerSpeechServiceCallbackListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//Clip board speech

@Override public void clipBoardSpeech(boolean v) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((v)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_clipBoardSpeech, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// user dictionary

@Override public int addUsrDict(java.lang.String kanjStr, java.lang.String kanaStr, int hins, int katu) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(kanjStr);
_data.writeString(kanaStr);
_data.writeInt(hins);
_data.writeInt(katu);
mRemote.transact(Stub.TRANSACTION_addUsrDict, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int deleteUsrDict(java.lang.String kanjStr, java.lang.String kanaStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(kanjStr);
_data.writeString(kanaStr);
mRemote.transact(Stub.TRANSACTION_deleteUsrDict, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean getUsrDictContents(int index) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(index);
mRemote.transact(Stub.TRANSACTION_getUsrDictContents, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getUsrDictContentsKanj() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getUsrDictContentsKanj, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getUsrDictContentsKana() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getUsrDictContentsKana, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getUsrDictContentsHins() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getUsrDictContentsHins, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getUsrDictContentsKatu() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getUsrDictContentsKatu, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String checkUsrDictWordKanj(java.lang.String srcStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(srcStr);
mRemote.transact(Stub.TRANSACTION_checkUsrDictWordKanj, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String checkUsrDictWordKana(java.lang.String srcStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(srcStr);
mRemote.transact(Stub.TRANSACTION_checkUsrDictWordKana, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String accentChange(java.lang.String kanaStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(kanaStr);
mRemote.transact(Stub.TRANSACTION_accentChange, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String kanjiToKanaConvert(java.lang.String kanjStr) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(kanjStr);
mRemote.transact(Stub.TRANSACTION_kanjiToKanaConvert, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void flush() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_flush, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getDTServiceStatus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDTServiceStatus, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_speak = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_speakAt = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_speakPhoneme = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_sing = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_wavPlay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_speakSyosai = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_speakSyosaiForIME = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_stop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_pause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_resume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_resumeNext = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_busy = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_isPause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getPositionTTS = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_setPositionTTS = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_getOffset = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_setOffset = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_setVoice = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_getVoice = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_getVoiceName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_setSpeed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_getSpeed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_setTone = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_getTone = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_setVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_getVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_setHightone = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_getHightone = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_setIntonation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_getIntonation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_setEcho = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_getEcho = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_setFastFoward = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_getFastFoward = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_setWarpRate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
static final int TRANSACTION_getWarpRate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 35);
static final int TRANSACTION_setTempoRate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 36);
static final int TRANSACTION_getTempoRate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 37);
static final int TRANSACTION_getKigouYomi = (android.os.IBinder.FIRST_CALL_TRANSACTION + 38);
static final int TRANSACTION_setKigouYomi = (android.os.IBinder.FIRST_CALL_TRANSACTION + 39);
static final int TRANSACTION_getKutouten = (android.os.IBinder.FIRST_CALL_TRANSACTION + 40);
static final int TRANSACTION_setKutouten = (android.os.IBinder.FIRST_CALL_TRANSACTION + 41);
static final int TRANSACTION_getNumAnalysis = (android.os.IBinder.FIRST_CALL_TRANSACTION + 42);
static final int TRANSACTION_setNumAnalysis = (android.os.IBinder.FIRST_CALL_TRANSACTION + 43);
static final int TRANSACTION_getRomajiNumb = (android.os.IBinder.FIRST_CALL_TRANSACTION + 44);
static final int TRANSACTION_setRomajiNumb = (android.os.IBinder.FIRST_CALL_TRANSACTION + 45);
static final int TRANSACTION_getCrlfDelimitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 46);
static final int TRANSACTION_setCrlfDelimitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 47);
static final int TRANSACTION_getSpaceDelimitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 48);
static final int TRANSACTION_setSpaceDelimitation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 49);
static final int TRANSACTION_setDefault = (android.os.IBinder.FIRST_CALL_TRANSACTION + 50);
static final int TRANSACTION_addListenner = (android.os.IBinder.FIRST_CALL_TRANSACTION + 51);
static final int TRANSACTION_removeListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 52);
static final int TRANSACTION_clipBoardSpeech = (android.os.IBinder.FIRST_CALL_TRANSACTION + 53);
static final int TRANSACTION_addUsrDict = (android.os.IBinder.FIRST_CALL_TRANSACTION + 54);
static final int TRANSACTION_deleteUsrDict = (android.os.IBinder.FIRST_CALL_TRANSACTION + 55);
static final int TRANSACTION_getUsrDictContents = (android.os.IBinder.FIRST_CALL_TRANSACTION + 56);
static final int TRANSACTION_getUsrDictContentsKanj = (android.os.IBinder.FIRST_CALL_TRANSACTION + 57);
static final int TRANSACTION_getUsrDictContentsKana = (android.os.IBinder.FIRST_CALL_TRANSACTION + 58);
static final int TRANSACTION_getUsrDictContentsHins = (android.os.IBinder.FIRST_CALL_TRANSACTION + 59);
static final int TRANSACTION_getUsrDictContentsKatu = (android.os.IBinder.FIRST_CALL_TRANSACTION + 60);
static final int TRANSACTION_checkUsrDictWordKanj = (android.os.IBinder.FIRST_CALL_TRANSACTION + 61);
static final int TRANSACTION_checkUsrDictWordKana = (android.os.IBinder.FIRST_CALL_TRANSACTION + 62);
static final int TRANSACTION_accentChange = (android.os.IBinder.FIRST_CALL_TRANSACTION + 63);
static final int TRANSACTION_kanjiToKanaConvert = (android.os.IBinder.FIRST_CALL_TRANSACTION + 64);
static final int TRANSACTION_flush = (android.os.IBinder.FIRST_CALL_TRANSACTION + 65);
static final int TRANSACTION_getVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 66);
static final int TRANSACTION_getDTServiceStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 67);
}
//speak control

public int speak(java.lang.String textStr) throws android.os.RemoteException;
public int speakAt(java.lang.String textStr, int offset) throws android.os.RemoteException;
public int speakPhoneme(java.lang.String phonemeStr) throws android.os.RemoteException;
public int sing(java.lang.String mmlStr) throws android.os.RemoteException;
public int wavPlay(java.lang.String fileName) throws android.os.RemoteException;
public int speakSyosai(java.lang.String textStr) throws android.os.RemoteException;
public int speakSyosaiForIME(java.lang.String textStr) throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
public void pause() throws android.os.RemoteException;
public void resume() throws android.os.RemoteException;
public void resumeNext() throws android.os.RemoteException;
public boolean busy() throws android.os.RemoteException;
public boolean isPause() throws android.os.RemoteException;
public int getPositionTTS() throws android.os.RemoteException;
public void setPositionTTS(int pos) throws android.os.RemoteException;
public int getOffset() throws android.os.RemoteException;
public void setOffset(int pos) throws android.os.RemoteException;
//property 
//basic voice parameter

public void setVoice(int v) throws android.os.RemoteException;
//0=Taro, 1=Hanako, 2= ----	(0)

public int getVoice() throws android.os.RemoteException;
//0=Taro, 1=Hanako, 2= ----	(0)

public java.lang.String getVoiceName() throws android.os.RemoteException;
//

public void setSpeed(int v) throws android.os.RemoteException;
//0-9				(6)

public int getSpeed() throws android.os.RemoteException;
//0-9				(6)

public void setTone(int v) throws android.os.RemoteException;
//1-5				(3)

public int getTone() throws android.os.RemoteException;
//1-5				(3)

public void setVolume(int v) throws android.os.RemoteException;
//0-9				(7)

public int getVolume() throws android.os.RemoteException;
//0-9				(7)

public void setHightone(int v) throws android.os.RemoteException;
//0,1				(0)

public int getHightone() throws android.os.RemoteException;
//0,1				(0)

public void setIntonation(int v) throws android.os.RemoteException;
//0-3				(2)

public int getIntonation() throws android.os.RemoteException;
//0-3				(2)
//optional parameter

public void setEcho(int v) throws android.os.RemoteException;
//0, 1				(0)

public int getEcho() throws android.os.RemoteException;
//0, 1				(0)

public void setFastFoward(int v) throws android.os.RemoteException;
//0, 1				(0)

public int getFastFoward() throws android.os.RemoteException;
//0, 1				(0)

public void setWarpRate(float v) throws android.os.RemoteException;
//0.6 - 1.0			(1.0)

public float getWarpRate() throws android.os.RemoteException;
//0.6 - 1.0			(1.0)

public void setTempoRate(float v) throws android.os.RemoteException;
//0.5 - 1.5			(1.0)

public float getTempoRate() throws android.os.RemoteException;
//0.5 - 1.5			(1.0)

public boolean getKigouYomi() throws android.os.RemoteException;
public void setKigouYomi(boolean v) throws android.os.RemoteException;
public boolean getKutouten() throws android.os.RemoteException;
public void setKutouten(boolean v) throws android.os.RemoteException;
public boolean getNumAnalysis() throws android.os.RemoteException;
public void setNumAnalysis(boolean v) throws android.os.RemoteException;
public int getRomajiNumb() throws android.os.RemoteException;
public void setRomajiNumb(int v) throws android.os.RemoteException;
public boolean getCrlfDelimitation() throws android.os.RemoteException;
public void setCrlfDelimitation(boolean v) throws android.os.RemoteException;
public boolean getSpaceDelimitation() throws android.os.RemoteException;
public void setSpaceDelimitation(boolean v) throws android.os.RemoteException;
public void setDefault() throws android.os.RemoteException;
//Listener

public void addListenner(jp.co.createsystem.IDTalkerSpeechServiceCallbackListener listener) throws android.os.RemoteException;
public void removeListener(jp.co.createsystem.IDTalkerSpeechServiceCallbackListener listener) throws android.os.RemoteException;
//Clip board speech

public void clipBoardSpeech(boolean v) throws android.os.RemoteException;
// user dictionary

public int addUsrDict(java.lang.String kanjStr, java.lang.String kanaStr, int hins, int katu) throws android.os.RemoteException;
public int deleteUsrDict(java.lang.String kanjStr, java.lang.String kanaStr) throws android.os.RemoteException;
public boolean getUsrDictContents(int index) throws android.os.RemoteException;
public java.lang.String getUsrDictContentsKanj() throws android.os.RemoteException;
public java.lang.String getUsrDictContentsKana() throws android.os.RemoteException;
public int getUsrDictContentsHins() throws android.os.RemoteException;
public int getUsrDictContentsKatu() throws android.os.RemoteException;
public java.lang.String checkUsrDictWordKanj(java.lang.String srcStr) throws android.os.RemoteException;
public java.lang.String checkUsrDictWordKana(java.lang.String srcStr) throws android.os.RemoteException;
public java.lang.String accentChange(java.lang.String kanaStr) throws android.os.RemoteException;
public java.lang.String kanjiToKanaConvert(java.lang.String kanjStr) throws android.os.RemoteException;
public void flush() throws android.os.RemoteException;
public int getVersion() throws android.os.RemoteException;
public int getDTServiceStatus() throws android.os.RemoteException;
}
