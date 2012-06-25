/*
 * DTalker Speech Service 
 * 2010/09/24	http://www.createsystem.co.jp/
 * 
 * Copyright (C)�N���G�[�g�V�X�e���J���������
 * 
 * �{�v���O�����́A�uDTalker Speech Service for Android�v ��
 * ���i�łƃf���ł������؂蕪�����Ďg�p���邱�Ƃ��ł��� DTalkerTTS�N���X�ł��B
 * �{�N���X���g�p���ēǂݏグ�@�\���\�z���Ă��������B
 * 
 * �R���X�g���N�^�[�́A�񓯊��TTS�T�[�r�X�̃I�[�v���������s���Ă��܂��̂ŁA
 * ���^�[�����Ă��Ă�TTS�T�[�r�X�̃I�[�v�������͏I����Ă��܂���B
 * CALLBACK_DTS_STARTED �R�[���o�b�N���m�F���Ă��������B
 * ���̎��ATTS�T�[�r�X�̎����̗L�����킩��܂��̂�
 * ���C���X�g�[���̏ꍇ�ɂ̓C���X�g�[���𑣂���ʂ����肢�������܂��B�i�T���v���Q�Ɓj
 * 
 * �d�l���ɋL�q���ꂽ�@�\��������Ɋȗ������ꂽ�@�\�́A
 * �E�ݒ�Activity�̌Ăяo���B
 * �E���[�U�[�����o�^Activity�̌Ăяo���B
 * �E�R�[���o�b�N�̏��� 
 * �EException�̏ȗ��i�K�v�ȏꍇ�͖{�N���X���C�����Ă��������j
 * �Erelease���\�b�h�̒ǉ�
 * 
 * �ȉ��̃t�H���_�[��src�t�H���_�[���ɂ����Ă��������B
 * jp\co\createsystem\IDTalkerSpeechService.aidl
 * jp\co\createsystem\IDTalkerSpeechServiceCallbackListener.aidl
 * jp\co\createsystem\DTalkerSample\IDTalkerSpeechService.aidl
 * jp\co\createsystem\DTalkerSample\\IDTalkerSpeechServiceCallbackListener.aidl
 * 
 * DTalkerTTS.java�́Apackage�������������Ă��g�p���������B
 * 
 */
package jp.co.kayo.ykmjuku.andylib.tts;

import java.util.List;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class DTalkerTTS {
	private Context	mContext;
	private int		mDemo;
	private Handler	mHandler = null;
	private int		mError;
	private jp.co.createsystem.IDTalkerSpeechService	mDTSS = null;
	private jp.co.createsystem.DTalkerTtsDemo.IDTalkerSpeechService mDTSS_Demo = null;

	public static final int CALLBACK_DTS_FINISHED = 0;
	public static final int CALLBACK_DTS_STRING = 1;
	public static final int CALLBACK_DTS_OFFSET = 2;
	public static final int CALLBACK_DTS_POSITION = 3;
	public static final int CALLBACK_DTS_STARTED = 4;

	public DTalkerTTS(Context context, Handler handle){
		mContext = context;
		mHandler = handle;
		mError = 0;
		Intent intent1 = new Intent(jp.co.createsystem.IDTalkerSpeechService.class.getName());
		List<ResolveInfo> info = context.getPackageManager().queryIntentServices(intent1,  PackageManager.GET_SERVICES);
		if (info.size() != 0){
			//���i�ŃT�[�r�X���o�C���h����
			Intent intent = new Intent(jp.co.createsystem.IDTalkerSpeechService.class.getName());
			context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
			mDemo = 0;
		}
		else{
			intent1 = new Intent(jp.co.createsystem.DTalkerTtsDemo.IDTalkerSpeechService.class.getName());
			info = context.getPackageManager().queryIntentServices(intent1,  PackageManager.GET_SERVICES);
			if (info.size() != 0){
				//�f���ŃT�[�r�X���o�C���h����
				Intent intent = new Intent(jp.co.createsystem.DTalkerTtsDemo.IDTalkerSpeechService.class.getName());
				context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);				
				mDemo = 1;
			}
			else{
				mError = 1;
				if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_STARTED, 3, 0));
			}
		}
	}
	public void release(){
		stop();
		if (mConnection!=null && (mDTSS!=null || mDTSS_Demo!=null)) mContext.unbindService(mConnection);
		mConnection = null;
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			if (mDemo==0){
				mDTSS = jp.co.createsystem.IDTalkerSpeechService.Stub.asInterface(service);
				try {
					mDTSS.addListenner(mListener);
				} catch (RemoteException e) {
				}
			}
			else{
				mDTSS_Demo = jp.co.createsystem.DTalkerTtsDemo.IDTalkerSpeechService.Stub.asInterface(service);
				try {
					mDTSS_Demo.addListenner(mListenerDemo);
				} catch (RemoteException e) {
				}
			}
			int timeOut = 300;		//300sec
			while(getDTServiceStatus()==2){		//�l�b�g�o�R�Ŏ������[�f�B���O��
				try {
					Thread.sleep(1000);
					timeOut--;
				} catch (InterruptedException e) {
				}
			}
			int sts = getDTServiceStatus();
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_STARTED, sts));
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			if (mDemo==0){
				try {
					if (mDTSS != null) mDTSS.removeListener(mListener);
					mDTSS.flush();
					mDTSS = null;
				} catch (RemoteException e) {
				}
			}
			else{
				try {
					if (mDTSS_Demo != null) mDTSS_Demo.removeListener(mListenerDemo);
					mDTSS_Demo.flush();
					mDTSS_Demo = null;
				} catch (RemoteException e) {
				}
			}
			mDTSS = null;
		}    	
	};
	
    //
    private jp.co.createsystem.IDTalkerSpeechServiceCallbackListener mListener = new jp.co.createsystem.IDTalkerSpeechServiceCallbackListener.Stub() {

		@Override
		public void didFinishPlaying(int id) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_FINISHED,null));
		}
		@Override
		public void didGotString(String speakText) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_STRING,speakText));			
		}
		@Override
		public void didGotStringOffset(int offset, int length) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_OFFSET,offset,length));
		}
		@Override
		public void nowPosition(int position) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_POSITION, position, 0));
		}
	};
    private jp.co.createsystem.DTalkerTtsDemo.IDTalkerSpeechServiceCallbackListener mListenerDemo = new jp.co.createsystem.DTalkerTtsDemo.IDTalkerSpeechServiceCallbackListener.Stub() {

		@Override
		public void didFinishPlaying(int id) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_FINISHED,null));
		}
		@Override
		public void didGotString(String speakText) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_STRING,speakText));			
		}
		@Override
		public void didGotStringOffset(int offset, int length) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_OFFSET,offset,length));
		}
		@Override
		public void nowPosition(int position) throws RemoteException {
			if (mHandler!=null) mHandler.sendMessage(mHandler.obtainMessage(CALLBACK_DTS_POSITION, position, 0));
		}
	};


	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// �ȉ��A�ڍׂ͎d�l�����Q�Ɗ肢�܂��B
	//
	public int		speak(String textStr){
		try {
			if (mDTSS != null)		return mDTSS.speak(textStr);
			if (mDTSS_Demo != null)	return mDTSS_Demo.speak(textStr);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	
	public int		speakAt(String textStr, int offset){
		try {
			if (mDTSS != null)		return mDTSS.speakAt(textStr, offset);
			if (mDTSS_Demo != null)	return mDTSS_Demo.speakAt(textStr, offset);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public int		speakPhoneme(String phonemeStr){
		try {
			if (mDTSS != null)		return mDTSS.speakPhoneme(phonemeStr);
			if (mDTSS_Demo != null)	return mDTSS_Demo.speakPhoneme(phonemeStr);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public int		sing(String mmlStr){
		try {
			if (mDTSS != null)		return mDTSS.sing(mmlStr);
			if (mDTSS_Demo != null)	return mDTSS_Demo.sing(mmlStr);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public int		wavPlay(String fileName){
		try {
			if (mDTSS != null)		return mDTSS.wavPlay(fileName);
			if (mDTSS_Demo != null)	return mDTSS_Demo.wavPlay(fileName);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public int		speakSyosai(String textStr){
		try {
			if (mDTSS != null)		return mDTSS.speak(textStr);
			if (mDTSS_Demo != null)	return mDTSS_Demo.speak(textStr);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public int		speakSyosaiForIME(String textStr){
		try {
			if (mDTSS != null)		return mDTSS.speakSyosaiForIME(textStr);
			if (mDTSS_Demo != null)	return mDTSS_Demo.speakSyosaiForIME(textStr);			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public void	stop(){
		try {
			if (mDTSS != null)		mDTSS.stop();
			if (mDTSS_Demo != null)	mDTSS_Demo.stop();			
		} catch (RemoteException e) {
		}
	}
	public void	pause(){
		try {
			if (mDTSS != null)		mDTSS.pause();
			if (mDTSS_Demo != null)	mDTSS_Demo.pause();			
		} catch (RemoteException e) {
		}
	}
	public void	resume(){
		try {
			if (mDTSS != null)		mDTSS.resume();
			if (mDTSS_Demo != null)	mDTSS_Demo.resume();			
		} catch (RemoteException e) {
		}
	}
	public void	resumeNext(){
		try {
			if (mDTSS != null)		mDTSS.resumeNext();
			if (mDTSS_Demo != null)	mDTSS_Demo.resumeNext();			
		} catch (RemoteException e) {
		}
	}
	public boolean 	busy(){
		try {
			if (mDTSS != null)		return mDTSS.busy();
			if (mDTSS_Demo != null)	return mDTSS_Demo.busy();			
		} catch (RemoteException e) {
		}
		return false;		
	}
	public boolean	isPause(){
		try {
			if (mDTSS != null)		return mDTSS.isPause();
			if (mDTSS_Demo != null)	return mDTSS_Demo.isPause();			
		} catch (RemoteException e) {
		}
		return false;
	}
	public int		getPositionTTS(){
		try {
			if (mDTSS != null)		return mDTSS.getPositionTTS();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getPositionTTS();			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public void	setPositionTTS(int pos){
		try {
			if (mDTSS != null)		mDTSS.setPositionTTS(pos);
			if (mDTSS_Demo != null)	mDTSS_Demo.setPositionTTS(pos);			
		} catch (RemoteException e) {
		}
	}
	public int		getOffset(){
		try {
			if (mDTSS != null)		return mDTSS.getOffset();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getOffset();			
		} catch (RemoteException e) {
		}
		return -1;
	}
	public void	setOffset(int pos){
		try {
			if (mDTSS != null)		mDTSS.setOffset(pos);
			if (mDTSS_Demo != null)	mDTSS_Demo.setOffset(pos);			
		} catch (RemoteException e) {
		}
	}

	//property 
	//basic voice parameter
	public void	setVoice(int v){
		try {
			if (mDTSS != null)		mDTSS.setVoice(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setVoice(v);			
		} catch (RemoteException e) {
		}		
	}
	public int		getVoice(){
		try {
			if (mDTSS != null)		return mDTSS.getVoice();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getVoice();			
		} catch (RemoteException e) {
		}
		return 0;
	}
	public String	getVoiceName(){
		try {
			if (mDTSS != null)		return mDTSS.getVoiceName();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getVoiceName();			
		} catch (RemoteException e) {
		}
		return "";
	}
	public void	setSpeed(int v){
		try {
			if (mDTSS != null)		mDTSS.setSpeed(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setSpeed(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getSpeed(){
		try {
			if (mDTSS != null)		return mDTSS.getSpeed();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getSpeed();			
		} catch (RemoteException e) {
		}
		return 6;
	}
	public void	setTone(int v){
		try {
			if (mDTSS != null)		mDTSS.setTone(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setTone(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getTone(){
		try {
			if (mDTSS != null)		return mDTSS.getTone();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getTone();			
		} catch (RemoteException e) {
		}
		return 3;
	}
	public void	setVolume(int v){
		try {
			if (mDTSS != null)		mDTSS.setVolume(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setVolume(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getVolume(){
		try {
			if (mDTSS != null)		return mDTSS.getVolume();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getVolume();			
		} catch (RemoteException e) {
		}
		return 7;
	}
	public void	setHightone(int v){
		try {
			if (mDTSS != null)		mDTSS.setHightone(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setHightone(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getHightone(){
		try {
			if (mDTSS != null)		return mDTSS.getHightone();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getHightone();			
		} catch (RemoteException e) {
		}
		return 0;
	}
	public void	setIntonation(int v){
		try {
			if (mDTSS != null)		mDTSS.setIntonation(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setIntonation(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getIntonation(){
		try {
			if (mDTSS != null)		return mDTSS.getIntonation();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getIntonation();			
		} catch (RemoteException e) {
		}
		return 1;
	}
	
	//optional parameter
	public void	setEcho(int v){
		try {
			if (mDTSS != null)		mDTSS.setEcho(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setEcho(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getEcho(){
		try {
			if (mDTSS != null)		return mDTSS.getEcho();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getEcho();			
		} catch (RemoteException e) {
		}
		return 0;
	}
	public void	setFastFoward(int v){
		try {
			if (mDTSS != null)		mDTSS.setFastFoward(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setFastFoward(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getFastFoward(){
		try {
			if (mDTSS != null)		return mDTSS.getFastFoward();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getFastFoward();			
		} catch (RemoteException e) {
		}
		return 0;
	}
	public void	setWarpRate(float v){
		try {
			if (mDTSS != null)		mDTSS.setWarpRate(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setWarpRate(v);			
		} catch (RemoteException e) {
		}
	}
	public float	getWarpRate(){
		try {
			if (mDTSS != null)		return mDTSS.getWarpRate();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getWarpRate();			
		} catch (RemoteException e) {
		}
		return 1.0f;
	}
	public void	setTempoRate(float v){
		try {
			if (mDTSS != null)		mDTSS.setTempoRate(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setTempoRate(v);			
		} catch (RemoteException e) {
		}
	}
	public float	getTempoRate(){
		try {
			if (mDTSS != null)		return mDTSS.getTempoRate();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getTempoRate();			
		} catch (RemoteException e) {
		}
		return 1.0f;
	}
	public boolean getKigouYomi(){
		try {
			if (mDTSS != null)		return mDTSS.getKigouYomi();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getKigouYomi();			
		} catch (RemoteException e) {
		}
		return false;
	}
	public void	setKigouYomi(boolean v){
		try {
			if (mDTSS != null)		mDTSS.setKigouYomi(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setKigouYomi(v);			
		} catch (RemoteException e) {
		}
	}
	public boolean getKutouten(){
		try {
			if (mDTSS != null)		return mDTSS.getKutouten();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getKutouten();			
		} catch (RemoteException e) {
		}
		return false;
	}
	public void	setKutouten(boolean v){
		try {
			if (mDTSS != null)		mDTSS.setKutouten(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setKutouten(v);			
		} catch (RemoteException e) {
		}
	}
	public boolean getNumAnalysis(){
		try {
			if (mDTSS != null)		return mDTSS.getNumAnalysis();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getNumAnalysis();			
		} catch (RemoteException e) {
		}
		return true;
	}
	public void	setNumAnalysis(boolean v){
		try {
			if (mDTSS != null)		mDTSS.setNumAnalysis(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setNumAnalysis(v);			
		} catch (RemoteException e) {
		}
	}
	public int		getRomajiNumb(){
		try {
			if (mDTSS != null)		return mDTSS.getRomajiNumb();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getRomajiNumb();			
		} catch (RemoteException e) {
		}
		return 0;
	}
	public void	setRomajiNumb(int v){
		try {
			if (mDTSS != null)		mDTSS.setRomajiNumb(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setRomajiNumb(v);			
		} catch (RemoteException e) {
		}
	}
	public boolean	getCrlfDelimitation(){
		try {
			if (mDTSS != null)		return mDTSS.getCrlfDelimitation();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getCrlfDelimitation();			
		} catch (RemoteException e) {
		}
		return true;
	}
	public void	setCrlfDelimitation(boolean v){
		try {
			if (mDTSS != null)		mDTSS.setCrlfDelimitation(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setCrlfDelimitation(v);			
		} catch (RemoteException e) {
		}
	}
	public boolean	getSpaceDelimitation(){
		try {
			if (mDTSS != null)		return mDTSS.getSpaceDelimitation();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getSpaceDelimitation();			
		} catch (RemoteException e) {
		}
		return false;
	}
	public void	setSpaceDelimitation(boolean v){
		try {
			if (mDTSS != null)		mDTSS.setSpaceDelimitation(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.setSpaceDelimitation(v);			
		} catch (RemoteException e) {
		}
	}
	public void	setDefault(){
		try {
			if (mDTSS != null)		mDTSS.setDefault();
			if (mDTSS_Demo != null)	mDTSS_Demo.setDefault();			
		} catch (RemoteException e) {
		}
	}
	//Clip board speech
	public void	clipBoardSpeech(boolean v){
		try {
			if (mDTSS != null)		mDTSS.clipBoardSpeech(v);
			if (mDTSS_Demo != null)	mDTSS_Demo.clipBoardSpeech(v);			
		} catch (RemoteException e) {
		}
	}
	public void	flush(){
		try {
			if (mDTSS != null)		mDTSS.flush();
			if (mDTSS_Demo != null)	mDTSS_Demo.flush();			
		} catch (RemoteException e) {
		}
	}
	public int		getVersion(){
		try {
			if (mDTSS != null)		return mDTSS.getVersion();
			if (mDTSS_Demo != null)	return mDTSS_Demo.getVersion();			
		} catch (RemoteException e) {
		}
		return -1;
	}
	/*
	 * 
	 * sts = getDTServiceStatus();
	 * ste:  3 = DTalkerSpeechService���C���X�g�[������Ă��Ȃ�
	 * 		 2 = �l�b�g���玫�������[�f�B���O��
	 * 		 1 = OK
	 * 		 0 = �����I�[�v���Ɏ��s
	 * 		-1 = �l�b�g���[�N�ڑ��Ɏ��s
	 * 		-2 = Zip�𓀂Ɏ��s
	 * 
	 */
	public int		getDTServiceStatus(){
		try {
			if (mError==1) return 3;
			if (mDTSS==null && mDTSS_Demo==null) return 2;
			if (mDTSS 	   != null)		return mDTSS.getDTServiceStatus();
			if (mDTSS_Demo != null)		return mDTSS_Demo.getDTServiceStatus();			
		} catch (RemoteException e) {
		}
		return 3;
	}

	//
	//	�ݒ�Activity�̌Ăяo��
	//
	public void settings(int speakable){
		Intent intent1=new Intent("android.intent.action.MAIN");
		if (mDemo == 0) intent1.setClassName("jp.co.createsystem", "jp.co.createsystem.DTalkerSettings");
		else 			intent1.setClassName("jp.co.createsystem.DTalkerTtsDemo", "jp.co.createsystem.DTalkerTtsDemo.DTalkerSettings");
		intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent1.putExtra("dtalker_settings_speakable", speakable);		//0=Activity�͔������Ȃ��B
		try{
			mContext.startActivity(intent1);
		} catch (ActivityNotFoundException e) {
			Log.d("DTalkerTTS","ActivityNotFoundException:"+e.toString());
		}
	}

	//
	//	���[�U�[�����o�^Activity�̌Ăяo��
	//
	public void userDict(int speakable){
		Intent intent1=new Intent("android.intent.action.MAIN");
		if (mDemo == 0) intent1.setClassName("jp.co.createsystem", "jp.co.createsystem.DTalkerUserDictList");
		else 			intent1.setClassName("jp.co.createsystem.DTalkerTtsDemo", "jp.co.createsystem.DTalkerTtsDemo.DTalkerUserDictList");
		intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent1.putExtra("user_dict_speakable", speakable);		//1=Activity�͔�������B
		try{
			mContext.startActivity(intent1);
		} catch (ActivityNotFoundException e) {
			Log.d("Sample","ActivityNotFoundException:"+e.toString());
		}
	}
	
	//
	//	�ȏ�
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//
}
