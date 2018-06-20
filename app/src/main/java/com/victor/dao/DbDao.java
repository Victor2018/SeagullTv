package com.victor.dao;import android.content.ContentValues;import android.content.Context;import android.database.Cursor;import android.database.sqlite.SQLiteDatabase;import android.net.Uri;import com.victor.data.Channel;import com.victor.db.DataBase;import com.victor.interfaces.DbInterface;import com.victor.data.ChannelUrl;import com.victor.util.Constant;import com.victor.util.Loger;import java.util.ArrayList;import java.util.List;/** * Created by victor on 2016/6/13. */public class DbDao implements DbInterface{    private String TAG = "DbDao";    private Context mContext;    private DataBase db;    private static DbDao dbDao;    public DbDao (Context context){        mContext = context;        db = new DataBase(mContext, null, null, 0);    }    public static DbDao getInstance (Context context) {        if (dbDao == null) {            dbDao = new DbDao(context);        }        return dbDao;    }    @Override    public void clearTb(String tbName) {        Loger.e(TAG, "clearTb()......tbName = " + tbName);        if (!db.tabbleIsExist(tbName)){            return;        }        SQLiteDatabase sdb = db.getReadableDatabase();        try {            sdb.delete(tbName, null, null);        } catch (Exception e) {            e.printStackTrace();        } finally {            if (sdb != null) {                sdb.close();            }        }    }    @Override    public void insertChannel(Channel channel) {        Loger.e(TAG, "insertChannel()......");        if (!db.tabbleIsExist(Constant.TB.VIDEO_DIY)){            return;        }        SQLiteDatabase sdb = db.getWritableDatabase();        try {            ContentValues values = new ContentValues();            values.put("title", channel.getChannel_name());            values.put("duration", channel.getDuration());            values.put("current", channel.getCurrent());            values.put("video_url", channel.getPlay_urls().get(0).getPlay_url());            sdb.insert(Constant.TB.VIDEO_DIY, null, values);        } catch (Exception e) {            e.printStackTrace();        } finally {            if (sdb != null) {                sdb.close();            }        }    }    @Override    public void removeChannel(Channel channel) {        Loger.e(TAG, "removeChannel()......");        if (!db.tabbleIsExist(Constant.TB.VIDEO_DIY)){            return;        }        SQLiteDatabase sdb = db.getReadableDatabase();        try {            sdb.delete(Constant.TB.VIDEO_DIY, "_id = ?",  new String[]{String.valueOf(channel.get_id())});        } catch (Exception e) {            e.printStackTrace();        }finally {            if (sdb != null) {                sdb.close();            }        }    }    @Override    public List<Channel> queryChannel() {        Loger.e(TAG, "queryChannel()......");        List<Channel> channels = new ArrayList<>();        if (!db.tabbleIsExist(Constant.TB.VIDEO_DIY)){            return channels;        }        Uri uri = Uri.parse(Constant.DbConfig.URI_PATH + Constant.TB.VIDEO_DIY);        Cursor cursor = mContext.getContentResolver().query(uri,                null, null , null, null);        try {            int row = cursor.getCount();            if (row > 0) {                for (int i = 0; i < cursor.getCount(); i++) {                    cursor.moveToPosition(i);                    Channel info = new Channel();                    info.set_id(cursor.getInt(cursor.getColumnIndex("_id")));                    info.setChannel_name(cursor.getString(cursor.getColumnIndex("title")));                    info.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));                    info.setCurrent(cursor.getLong(cursor.getColumnIndex("current")));                    List<ChannelUrl> urls = new ArrayList<>();                    ChannelUrl url = new ChannelUrl();                    url.setPlay_url(cursor.getString(cursor.getColumnIndex("video_url")));                    urls.add(url);                    info.setPlay_urls(urls);                    channels.add(info);                }            }        }catch (Exception e) {            e.printStackTrace();        } finally {            if (cursor != null) {                cursor.close();            }        }        return channels;    }    @Override    public int queryDiyChannelCount() {        Loger.e(TAG, "queryDiyChannelCount()......");        int count = 0;        if (!db.tabbleIsExist(Constant.TB.VIDEO_DIY)){            return count;        }        Uri uri = Uri.parse(Constant.DbConfig.URI_PATH + Constant.TB.VIDEO_DIY);        Cursor cursor = mContext.getContentResolver().query(uri,                null, null , null, null);        try {            count = cursor.getCount();        }catch (Exception e) {            e.printStackTrace();        } finally {            if (cursor != null) {                cursor.close();            }        }        return count;    }}