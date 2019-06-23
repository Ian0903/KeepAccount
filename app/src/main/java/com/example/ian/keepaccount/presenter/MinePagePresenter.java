package com.example.ian.keepaccount.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.ian.keepaccount.contract.MinePageContract;
import com.example.ian.keepaccount.receiver.AlarmReceiver;
import com.example.ian.keepaccount.utils.ACache;

import java.util.Calendar;
import java.util.TimeZone;

public class MinePagePresenter extends BasePresenter<MinePageContract.View> implements MinePageContract.Presenter {

    private ACache mCache;
    private boolean isSetRemind;
    private Context context;

    public MinePagePresenter(Context context) {
        this.mCache = ACache.get();
        this.context = context;
    }

    @Override
    public void getRemindTime() {
        String remindTime = mCache.getAsString("remind_time");
        if (remindTime == null){
            remindTime = "12:00";
        }
        setRemindTime(remindTime);
    }

    @Override
    public void getSwitchState() {
        isSetRemind = mCache.getAsBoolean("is_set_remind", false);
        getView().showSwitch(isSetRemind);
    }

    @Override
    public void setRemindTime(String remindTime) {
        mCache.put("remind_time", remindTime);
        //设置通知
        startRemind(remindTime);
        getView().showTime(remindTime);
    }

    @Override
    public void setSwitch(boolean switchRemind) {
        mCache.put("is_set_remind", switchRemind);
    }

    @Override
    public void closeRemind() {
        mCache.remove("remind_time");
        //取消通知
        stopRemind();
    }

    /**
     * 开启提醒
     */
    private void startRemind(String remindTime){
        String[] remindArr = remindTime.split(":");
        int hour = Integer.valueOf(remindArr[0]);
        int min = Integer.valueOf(remindArr[1]);

        //得到日历实例，主要是为了下面的获取时间
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        //获取当前毫秒值
        long systemTime = System.currentTimeMillis();

        //是设置日历的时间，主要是让日历的年月日和当前同步
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //设置在几点提醒
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        //设置在几分提醒
        mCalendar.set(Calendar.MINUTE, min);
        //下面这两个看字面意思也知道
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);

        //上面设置的就是时间点

        //获取上面设置毫秒值
        long selectTime = mCalendar.getTimeInMillis();

        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //AlarmReceiver.class为广播接受者
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,0, intent, 0);
        //得到AlarmManager实例
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        //**********注意！！下面的两个根据实际需求任选其一即可*********

        /**
         * 单次提醒
         * mCalendar.getTimeInMillis() 上面设置的13点25分的时间点毫秒值
         */
        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);

        /**
         * 重复提醒
         * 第一个参数是警报类型；下面有介绍
         * 第二个参数网上说法不一，很多都是说的是延迟多少毫秒执行这个闹钟，但是我用的刷了MIUI的三星手机的实际效果是与单次提醒的参数一样，即设置的13点25分的时间点毫秒值
         * 第三个参数是重复周期，也就是下次提醒的间隔 毫秒值 我这里是一天后提醒
         */
        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);

    }


    /**
     * 关闭提醒
     */
    private void stopRemind(){

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0,
                intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //取消警报
        am.cancel(pi);
    }


}
