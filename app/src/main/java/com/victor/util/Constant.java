package com.victor.util;

import com.victor.app.App;

import java.net.URLEncoder;

/**
 * Created by victor on 2015/12/25.
 */
public class Constant {
    public static final boolean MODEL_DEBUG = true;
    public static final boolean MODEL_ONLINE = true;
    public static final int BUILD_CODE = 0;

    public static final String MA_DATA                              = "madata";
    public static final String SHARE_TYPE                           = "text/plain";
    public static final String ACTION_KEY                           = "ACTION_KEY";
    public static final String INTENT_DATA_KEY                     = "INTENT_DATA_KEY";
    public static final String CHANNEL_DATA_KEY                    = "CHANNEL_DATA_KEY";
    public static final String STATUS_KEY                           = "STATUS_KEY";
    public static final String IS_SHOW_GENTRUE_TIP                 = "IS_SHOW_GENTRUE_TIP";
    public static final String IS_SHAKE_CHANGE_PROGRAM            = "IS_SHAKE_CHANGE_PROGRAM";
    public static final String IS_PLAY_WELCOME                      = "IS_PLAY_WELCOME";
    public static final String HEAD_IMG_URL                         = "HEAD_IMG_URL";
    public static final String REQUEST_MSG_KEY                      = "REQUEST_MSG_KEY";
    public static final String CURRENT_PLAY_URL                     = "CURRENT_PLAY_URL";
    public static final String CURRENT_PLAY_NAME                    = "CURRENT_PLAY_NAME";
    public static final String CURRENT_PLAY_EPG                     = "CURRENT_PLAY_EPG";
    public static final String CURRENT_PAGE_KEY                     = "CURRENT_PAGE_KEY";
    public static final String CURRENT_MEI_PAI_URL                  = "CURRENT_MEI_PAI_URL";
    public static final String CURRENT_CATEGORY_POSITION           = "CURRENT_CATEGORY_POSITION";
    public static final String CURRENT_PLAY_POSITION                = "CURRENT_PLAY_POSITION";
    public static final String CURRENT_LIVE_PLAY_POSITION          = "CURRENT_LIVE_PLAY_POSITION";
    public static final int BG_COLOR_MAX                            = 255;
    public static final int BG_COLOR_MIN                            = 232;
    public static final int HEADER_HIDE_ANIM_DURATION             = 300;
    public static final int APP_ID                                   = 21523;//易原appId
    public static final String APP_SECRET                            = "0e12ee2930874644a9c88e30d44c3fea";//易原秘钥
    public static final String TIME_FORMAT                           = "yyyyMMddHHmmss";//时间格式
    public static final String CHANNEL_URL                           = "https://raw.githubusercontent.com/Victor2018/SeagullTv/master/docs/channels_json.txt";
    public static final String CHANNEL_URL_NEW                       = "https://raw.githubusercontent.com/Victor2018/SeagullTv/master/docs/channels.json";
    public static final String DEFAULT_PLAY_URL                      = "http://222.191.24.5:6610/2/2/ch00000090990000001251/index.m3u8?ispcode=3";
    public static final String DEFAULT_EPG_URL                       = "http://ivi.bupt.edu.cn/hls/hbhd.m3u8";
    public static final String CATEGORY_URL                          = "https://raw.githubusercontent.com/Victor2018/SeagullTv/master/docs/category_json.txt";
    public static final String CATEGORY_URL_NEW                      = "https://raw.githubusercontent.com/Victor2018/SeagullTv/master/docs/category.json";
    public static final String GANK_BASE_URL                          = "http://gank.io/api/data/" + URLEncoder.encode("福利");
    public static final String GANK_URL                               = "/%s/%s";
    public static final String YOUTUBE_IMG_URL                       = "https://i.ytimg.com/vi/%s/hqdefault.jpg?sqp=-oaymwEWCMQBEG5IWvKriqkDCQgBFQAAiEIYAQ==&rs=AOn4CLBTZi8JCWrkzvXrj3TsxNGrbkmzpw";
    public static final int PAGE_SIZE                                = 30;
    public static final String dir = FileUtil.getDiskCacheDir(App.getContext()) + "/girls";
    public static class Msg {
        public static final int REQUEST_SUCCESS                             = 0x102;//请求成功
        public static final int REQUEST_SUCCESS_NO_DATA                    = 0x103;//请求成功，没有数据
        public static final int REQUEST_FAILED                              = 0x104;//请求失败
        public static final int PARSING_EXCEPTION                           = 0x105;//数据解析异常
        public static final int NETWORK_ERROR                               = 0x106;//网络错误
        public static final int GIF_REQUEST                                 = 0x107;
        public static final int FUNNY_REQUEST                               = 0x108;
        public static final int VOICE_REQUEST                               = 0x109;
        public static final int VIDEO_REQUEST                               = 0x110;
        public static final int PLAY_VOICE                                  = 0x111;
        public static final int PLAY_VIDEO                                  = 0x112;
        public static final int HIDE_PLAY_CTRL_VIEW                        = 0x113;
        public static final int HIDE_PLAY_TITLE_VIEW                       = 0x114;
        public static final int BASE_REQUEST                                = 0x115;
        public static final int SOCKET_TIME_OUT                             = 0x116;
        public static final int CHANNEL_REQUEST                             = 0x117;
        public static final int PICTURE_REQUEST                             = 0x118;
        public static final int HIDE_ACTION_BAR                             = 0x119;
        public static final int DRAW_HEART_BUBBLE                           = 0x120;
        public static final int MEIPAI_REQUEST                              = 0x121;
        public static final int MEIPAI_CATEGORY_REQUEST                    = 0x122;
        public static final int REQUEST_LIVE_EPG                            = 0x123;
        public static final int SHOW_LIVE_EPG                               = 0x124;
        public static final int CATEGORY_REQUEST                            = 0x125;
        public static final int HIDE_VOICE_BRIGHT_VIEW                      = 0x126;
        public static final int GOTO_WONDERFUL_LIVE                         = 0x127;
        public static final int SHOW_NETSPEED                                = 0x128;
        public static final int GET_NETSPEED                                 = 0x129;
        public static final int SAVE_PICTURE_SUCCESS                        = 0x130;
        public static final int SET_WALLPAPER_SUCCESS                       = 0x131;
        public static final int SET_WALLPAPER_FAILED                        = 0x132;
        public static final int REQUEST_YOUTUBE                              = 0x133;
    }

    public static class Action {
        public static final int ON_CATEGORY_ITEM_CLICK                      = 0x201;
        public static final int ON_CHANNEL_ITEM_CLICK                       = 0x202;
        public static final int PLAY_MEIPAI_VIDEO                            = 0x203;
        public static final int SHARE_MEIPAI                                 = 0x204;
        public static final int PLAY_NEXT_MEIPAI_VIDEO                      = 0x205;
        public static final int DIY_CHANNEL                                   = 0x206;
        public static final int SHARE_FUNNY                                   = 0x207;
        public static final int SHARE_PICTURE                                 = 0x208;
        public static final int SAVE_PICTURE                                  = 0x209;
        public static final int SET_WALLPAPER                                 = 0x210;
        public static final int YOUTUBE_VIDEO                                 = 0x211;
        public static final int LIVE                                        = 1;
        public static final int HOT                                         = 2;
        public static final int FUNNY                                       = 29;
        public static final int DELICIOUS                                   = 3;
        public static final int EAT_SHOW                                    = 4;
        public static final int MUSIC                                        = 5;
        public static final int DANCE                                        = 6;
        public static final int STAR                                         = 7;
        public static final int GODDESS                                      = 8;
        public static final int PET                                           = 9;
        public static final int MANUAL                                        = 10;
        public static final int WEAR_SHOW                                     = 11;
        public static final int DUANZI                                        = 12;

    }

    public static class ScreenScale {
        public static final int SCALE_FULLSCREEN                             = 0;
        public static final int SCALE_16_9                                    = 1;
        public static final int SCALE_4_3                                     = 2;
        public static final int SCALE_ORIGINAL                                = 3;
    }

    /**
     * 数据库配置信息
     * @author victor
     * @date 2016-2-24
     */
    public static class DbConfig {
        public static final String DB_NAME 						= "video_db";
        public static final String SCHEME 						= "content://";
        public static final String AUTHORITY 					= "content.video.content";
        public static final String URI_PATH 					    = SCHEME + AUTHORITY + "/";
        public static final int DB_VERSION 						= 6;
    }

    public static class TB {
        public static final String VIDEO_DIY 					 = "video_diy";
    }

}
