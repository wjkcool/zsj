package com.goworld21.service;

import java.util.Map;

/**
 * Created by DELL on 2017/11/3.
 */
public interface WeiXinService {
    /**
     * 根据微信code查询登陆
     * @param code
     * @return
     */
    Map getModelByWeiXin(String code);
}
