package com.yanbober.android_mvp_demo.activity;

import com.yanbober.android_mvp_demo.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-11
 * Time         : 17:30
 * Description  :
 */
public interface IInfoView {
    //��UI��ʾ���ݵķ���
    void setInfo(InfoBean info);
    //��UIȡ���ݵķ���
    InfoBean getInfo();
}
