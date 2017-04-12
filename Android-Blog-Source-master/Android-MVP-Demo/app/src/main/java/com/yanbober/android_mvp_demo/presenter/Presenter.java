package com.yanbober.android_mvp_demo.presenter;

import com.yanbober.android_mvp_demo.activity.IInfoView;
import com.yanbober.android_mvp_demo.bean.InfoBean;
import com.yanbober.android_mvp_demo.model.IInfoModel;
import com.yanbober.android_mvp_demo.model.InfoModelImpl;

/**
 * Author       : yanbo
 * Date         : 2015-05-12
 * Time         : 08:34
 * Description  :
 */
public class Presenter {
    private IInfoModel infoModel;
    private IInfoView infoView;

    public Presenter(IInfoView infoView) {
        this.infoView = infoView;

        infoModel = new InfoModelImpl();
    }
    //��UI����
    public void saveInfo(InfoBean bean) {
        infoModel.setInfo(bean);
    }
    //��UI����
    public void getInfo() {
        //ͨ������IInfoView�ķ�����������ʾ�����ģʽ����
        //���ƻص���������
        infoView.setInfo(infoModel.getInfo());
    }
}
