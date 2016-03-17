package org.barman;

public class GameConfig {
	public static final int SOUND_EFFECT_NUM = 32;
	public static final int BOOZER_ANI_IMAGE_NUM = 20;
	public static final int BOOZER_IMAGE_NUM = 10;

	public static final int ANI_LAYER = 1;
	public static final int BOOZER_LAYER1 = 2;
	public static final int BOOZER_LAYER2 = 3;
	public static final int BOOZER_LAYER3 = 4;
	public static final int BOOZER_LAYER4 = 5;

	public static final int WAITER_LAYER = 7;

	public static final int BOOZER_NORMAL_NUM = 5;

	public static final int MESSAGE_LAYER = 8;

	public static final int BTN_LAYER  = 8;
	public static final int BOOZER_BEER_NUM = 15;

	public static final float MESSAGE_DALAY = 0.5f;

	public static final float BOOZER_SLOW_ANI_INTERVAL = 0.2f;
	public static final float BOOZER_FAST_ANI_INTERVAL = 0.1f;
	public static final float WAITER_ANI_INTERVAL = 0.1f;
	public static final float BEER_ANI_INTERVAL = 0.05f;
	public static final float BONUS_ANI_INTERVAL = 0.1f;
	public static final float TIP_ANI_INTERVAL = 0.1f;

	public static final float LAMP_ANI_INTERVAL = 0.2f;
	public static final float LAMP_BAR_ANI_INTERVAL = 0.5f;

	public static final float GUITRA_ANI_INTERVAL = 0.2f;
	public static final float PIANO_ANI_INTERVAL = 0.2f;

	public static final int FIRST_BOOZER_X  = 200;
	public static final int FIRST_BOOZER_Y = 233-5;
	public static final int FIRST_BOOZER_LAST_X = 644;

	public static final int SECOND_BOOZER_X = 174;
	public static final int SECOND_BOOZER_Y = 345-5;
	public static final int SECOND_BOOZER_LAST_X = 652;

	public static final int THIRD_BOOZER_X = 148;
	public static final int THIRD_BOOZER_Y = 478-5;
	public static final int THIRD_BOOZER_LAST_X = 660;

	public static final int FORTH_BOOZER_X = 122;
	public static final int FORTH_BOOZER_Y = 615+10-5;
	public static final int FORTH_BOOZER_LAST_X = 668;

	public static final int FIRST_WAITER_X = 726;
	public static final int FIRST_WAITER_Y = 320;

	public static final int SECOND_WAITER_X = 748;
	public static final int SECOND_WAITER_Y = 420;

	public static final int THIRD_WAITER_X = 774;
	public static final int THIRD_WAITER_Y = 548;

	public static final int FORTH_WAITER_X = 812;
	public static final int FORTH_WAITER_Y = 680;

	public static final int FIRST_BEER_X = 188;
	public static final int FIRST_BEER_Y = 310;
	public static final int FIRST_BEER_LAST_X = 662;

	public static final int SECOND_BEER_X = 160;
	public static final int SECOND_BEER_Y = 426;
	public static final int SECOND_BEER_LAST_X = 676;

	public static final int THIRD_BEER_X = 128;
	public static final int THIRD_BEER_Y = 561;
	public static final int THIRD_BEER_LAST_X = 688;

	public static final int FORTH_BEER_X = 96;
	public static final int FORTH_BEER_Y = 700;
	public static final int FORTH_BEER_LAST_X = 712;

	public static final int MAX_LEVEL = 20;

	public static final int SHORT_TH_X = 556;
	public static final int MIDDLE_TH_X = 394;
	public static final int LARGE_TH_X = 254;
	
	public static final int BEER_STATE = 0;
	public static final int NON_BEER_STATE = 1;
	public static final int BREAK_BEER_STATE = 2;

	enum EFFECT_SOUND
	{
	    APPLAUSE_OYE_SOUND,
	    BONUS_SOUND,
	    BOOZER_ANGRY_SOUND,
	    BOOZER_BLOP_SOUND,
	    BOOZER_DRINK_SOUND,
	    BOOZER_DRINK_ALIEN_SOUND,
	    CATCH_COINS_SOUND,
	    CATCH_MUG_SOUND,
	    CATCH_TURBO_SOUND,
	    DROUGH_BEER_SOUND,
	    ELECTRIC_WIRE_SOUND,
	    ENTER_PUB_SOUND,
	    FAIL_BOOZERWAITER_SOUND,
	    FAIL_MUGBREAK_SOUND,
	    LOAD_SOUND,
	    MAIN_MENU_SOUND,
	    MENU_CLICK_SOUND,
	    MESSAGEBOARD_SOUND,
	    STEP_SOUND,
	    THROW_MUG_SOUND,
	    TIP_SOUND,
	    TIP_TURBO_SOUND,
	    FLY_SOUND,
	    FUCKUP_SOUND1,
	    FUCKUP_SOUND2,
	    FUCKUP_SOUND3,
	    HAPPY_SOUND1,
	    HAPPY_SOUND2,
	    HAPPY_SOUND3,
	    HICKUP_SOUND,
	    TELEPORT_SOUND,
	    TIPFAIL_SOUND,
	};

	
//	enum MessageBoardState
//	{
//	    GAME_OVER_STATE,
//	    NEXT_LEVEL_STATE,
//	    PAUSED_STATE,
//	    RESTART_STATE,
//	    TAP_STATE,
//	};

	static final int GAME_OVER_STATE = 0;
	static final int NEXT_LEVEL_STATE = 1;
	static final int PAUSED_STATE = 2;
	static final int RESTART_STATE = 3;
	static final int TAP_STATE = 4;
	static final int GAME_PLAY = 5;
	static final int GAME_INIT = 6;
	
	enum BoozerState
	{
	    BOOZER_STATE1,
	    BOOZER_STATE2,
	    BOOZER_STATE3,
	    BOOZER_STATE4,
	    BOOZER_STATE5,
	    BOOZER_STATE6,
	    BOOZER_STATE7,
	    BOOZER_STATE8,
	    BOOZER_STATE9,
	    BOOZER_STATE10,    
	};

	enum LightBulbBarState
	{
	    LAMP_STATE,
	    TOMATO_STATE,
	};

//	enum WaterState
//	{
//	    NON_WAITER_STATE,
//	    WELCOME_WAITER_STATE,
//	    BEER_WAITER_STATE,
//	    LIGHT_BEER_STATE,
//	    FAIL_WAITER_STATE,
//	    GAIN_CUP_WAITER_STATE,
//	    LIGHTED_WAITER_STATE,
//	    UP_WAITER_STATE,
//	    DOWN_WAITER_STATE,
//	    GET_WAITER_NORMAL_STATE,
//	    GET_WAITER_SPECIAL_STATE,
//	};

	public static final int NON_WAITER_STATE = 0;
	public static final int WELCOME_WAITER_STATE = 1;
	public static final int BEER_WAITER_STATE = 2;
	public static final int LIGHT_BEER_STATE = 3;
	public static final int FAIL_WAITER_STATE = 4;
	public static final int GAIN_CUP_WAITER_STATE = 5;
	public static final int LIGHTED_WAITER_STATE = 6;
	public static final int UP_WAITER_STATE = 7;
	public static final int DOWN_WAITER_STATE = 8;
	public static final int GET_WAITER_NORMAL_STATE = 9;
	public static final int GET_WAITER_SPECIAL_STATE = 10;
	
	
//	enum BeerState
//	{
//	    BEER_STATE,
//	    NON_BEER_STATE,
//	    BREAK_BEER_STATE,
//	};

	enum PainoState
	{
	    STAND_PIANO_STATE,
	    SIT_PIANO_STATE,
	    PLAY_PIANO_STATE,
	    HAPPY_PIANO_STATE,
	    UNHAPPY_PIANO_STATE,
	};
	
	public static final int STAND_PIANO_STATE = 0;
	public static final int SIT_PIANO_STATE = 1;
	public static final int PLAY_PIANO_STATE = 2;
	public static final int HAPPY_PIANO_STATE = 3;
	public static final int UNHAPPY_PIANO_STATE = 4;

//	enum GainCupWaiterState
//	{
//	    SHORT_GAIN_STATE,
//	    MIDDLE_GAIN_STATE,
//	    LARGE_GAIN_STATE,
//	};

	public static final int SHORT_GAIN_STATE = 0;
	public static final int MIDDLE_GAIN_STATE = 1;
	public static final int LARGE_GAIN_STATE = 2;
//	enum TipState
//	{
//	    COIN_GET_STATE,
//	    BIG_TIP_STATE,
//	};
	
	public static final int COIN_GET_STATE = 0;
	public static final int BIG_TIP_STATE = 1;

//	enum ScoreState
//	{
//	    NORMAL_SCORE,
//	    TIP_SCORE,
//	    ONE_SHORT_SCORE,
//	    NEXT_LEVEL_SCORE,
//	};
	
	public static final int NORMAL_SCORE = 0;
	public static final int TIP_SCORE = 1;
	public static final int ONE_SHORT_SCORE = 2;
	public static final int NEXT_LEVEL_SCORE = 3;
	
}
