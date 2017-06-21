package com.victor.interfaces;import com.victor.model.data.Channel;import java.util.List;/** * Created by victor on 2016/6/13. */public interface DbInterface {    /**     * @param tbName     * 清空表数据     */    void clearTb(String tbName);    /**     * 添加自定义频道     * * @param channel     */    void insertChannel(Channel channel);    /**     * 删除音乐自定义频道     * @param channel     */    void removeChannel(Channel channel);    /**     * 查询自定义频道     * @return     */    List<Channel> queryChannel();    /**     * 查询自定义频道总数     * @return     */    int queryDiyChannelCount ();}