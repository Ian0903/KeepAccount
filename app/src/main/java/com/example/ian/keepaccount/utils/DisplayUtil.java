package com.example.ian.keepaccount.utils;

import android.content.Context;
import android.util.TypedValue;

import com.example.ian.keepaccount.R;

/**
 * Created by LuckyJayce on 2016/6/24.
 */
public class DisplayUtil {
    /**
     * 根据dip值转化成px值
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPix(Context context, float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }

    public static int getSecondTypeIcon(String type) {
        switch (type) {
            case "餐饮":
                return R.mipmap.food;
            case "交通":
                return R.mipmap.traffic;
            case "购物":
                return R.mipmap.shopping;
            case "娱乐":
                return R.mipmap.dancing;
            case "水电":
                return R.mipmap.water;

            case "烟酒":
                return R.mipmap.smoking;

            case "水果":
                return R.mipmap.watermelon;

            case "日用品":
                return R.mipmap.tape;

            case "美容":
                return R.mipmap.mirror;

            case "住房":
                return R.mipmap.bungalow;

            case "通讯":
                return R.mipmap.telephone;

            case "学习":
                return R.mipmap.pencil;

            case "旅游":
                return R.mipmap.beach;

            case "运动":
                return R.mipmap.basketball;

            case "孩子":
                return R.mipmap.baby;

            case "宠物":
                return R.mipmap.cat;

            case "医疗":
                return R.mipmap.doctors;

            case "礼物":
                return R.mipmap.gift;

            case "长辈":
                return R.mipmap.old;

            case "捐赠":
                return R.mipmap.volunteering;

            case "工资":
                return R.mipmap.salary;

            case "兼职":
                return R.mipmap.timer;

            case "理财":
                return R.mipmap.coins;

            case "奖金":
                return R.mipmap.bonus;

            default:
                return R.mipmap.wallet;

        }
    }
}
