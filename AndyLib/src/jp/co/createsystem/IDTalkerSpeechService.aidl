//
//	DTalker TTS Service for Android
//		Service method
//
//	2010/07/14		http://www.createsystem.co.jp/
//					[IDTalkerSpeechService.aidl]
//
package jp.co.createsystem;

import jp.co.createsystem.IDTalkerSpeechServiceCallbackListener;


interface IDTalkerSpeechService {

	//speak control
	int		speak(in String textStr);
	int		speakAt(in String textStr, in int offset);
	int		speakPhoneme(in String phonemeStr);
	int		sing(in String mmlStr);
	int		wavPlay(in String fileName);
	int		speakSyosai(in String textStr);
	int		speakSyosaiForIME(in String textStr);
	void	stop();
	void	pause();
	void	resume();
	void	resumeNext();
	boolean	busy();
	boolean	isPause();
	int		getPositionTTS();
	void	setPositionTTS(in int pos);
	int		getOffset();
	void	setOffset(in int pos);

	//property 
	//basic voice parameter
	void	setVoice(in int v);				//0=Taro, 1=Hanako, 2= ----	(0)
	int		getVoice();						//0=Taro, 1=Hanako, 2= ----	(0)
	String	getVoiceName();					//
	void	setSpeed(in int v);				//0-9				(6)
	int		getSpeed();						//0-9				(6)
	void	setTone(in int v);				//1-5				(3)
	int		getTone();						//1-5				(3)
	void	setVolume(in int v);			//0-9				(7)
	int		getVolume();					//0-9				(7)
	void	setHightone(in int v);			//0,1				(0)
	int		getHightone();					//0,1				(0)
	void	setIntonation(in int v);		//0-3				(2)
	int		getIntonation();				//0-3				(2)
	//optional parameter
	void	setEcho(in int v);				//0, 1				(0)
	int		getEcho();						//0, 1				(0)
	void	setFastFoward(in int v);		//0, 1				(0)
	int		getFastFoward();				//0, 1				(0)
	void	setWarpRate(in float v);		//0.6 - 1.0			(1.0)
	float	getWarpRate();					//0.6 - 1.0			(1.0)
	void	setTempoRate(in float v);		//0.5 - 1.5			(1.0)
	float	getTempoRate();					//0.5 - 1.5			(1.0)

	boolean getKigouYomi();
	void	setKigouYomi(in boolean v);
	boolean getKutouten();
	void	setKutouten(in boolean v);
	boolean getNumAnalysis();
	void	setNumAnalysis(in boolean v);
	int		getRomajiNumb();
	void	setRomajiNumb(in int v);
	boolean	getCrlfDelimitation();
	void	setCrlfDelimitation(in boolean v);
	boolean	getSpaceDelimitation();
	void	setSpaceDelimitation(in boolean v);
	void	setDefault();
	

	//Listener
	void	addListenner(in IDTalkerSpeechServiceCallbackListener listener);
	void	removeListener(in IDTalkerSpeechServiceCallbackListener listener);

	//Clip board speech
	void	clipBoardSpeech(in boolean v);

	// user dictionary
	int		addUsrDict(in String kanjStr, in String kanaStr, in int hins, in int katu);
	int		deleteUsrDict(in String kanjStr, in String kanaStr);
	boolean	getUsrDictContents(in int index);
	String	getUsrDictContentsKanj();
	String	getUsrDictContentsKana();
	int		getUsrDictContentsHins();
	int		getUsrDictContentsKatu();
	String	checkUsrDictWordKanj(in String srcStr);
	String	checkUsrDictWordKana(in String srcStr);
	String	accentChange(in String kanaStr);
	String  kanjiToKanaConvert(in String kanjStr);

	void	flush();

	int		getVersion();
	int		getDTServiceStatus();
	
}

