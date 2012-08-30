//
//	DTalker TTS Service for Android
//		CallbackListener
//
//	2010/07/14		http://www.createsystem.co.jp/
//					[IDTalkerSpeechServiceCallbackListener.aidl]
//
package jp.co.createsystem;

interface IDTalkerSpeechServiceCallbackListener{
	void	didFinishPlaying(int id);
	void	nowPosition(int position);
	void	didGotString(String speakText);
	void	didGotStringOffset(int offset, int length);
}
