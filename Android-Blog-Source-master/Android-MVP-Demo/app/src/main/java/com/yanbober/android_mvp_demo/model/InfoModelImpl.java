package com.yanbober.android_mvp_demo.model;

import com.yanbober.android_mvp_demo.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-12
 * Time         : 08:35
 * Description  :
 */
public class InfoModelImpl implements IInfoModel {
    //ģ��洢����
    private InfoBean infoBean = new InfoBean();

    @Override
    public InfoBean getInfo() {
        //ģ��洢���ݣ���ʵ�кܶ����
        return infoBean;
    }

    @Override
    public void setInfo(InfoBean info) {
        //ģ��洢���ݣ���ʵ�кܶ����
        infoBean = info;
    }
}
