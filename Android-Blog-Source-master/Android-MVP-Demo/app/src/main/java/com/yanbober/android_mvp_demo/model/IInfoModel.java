package com.yanbober.android_mvp_demo.model;

import com.yanbober.android_mvp_demo.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-11
 * Time         : 17:29
 * Description  :
 */
public interface IInfoModel {
    //�������ṩ�߻�ȡ���ݷ���
    InfoBean getInfo();
    //���������ṩ�߷���
    void setInfo(InfoBean info);
}
