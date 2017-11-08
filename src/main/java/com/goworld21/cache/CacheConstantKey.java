package com.goworld21.cache;

/**
 * Created by DELL on 2017/11/3.
 */
public class CacheConstantKey {
    /**
     * 走世界用户token
     * redis结构:String
     * key = key + userId
     * value = 32位md5
     */
    public static final String ZSJ_APP_TOKEN = "zsj_app_token_";

    /**
     * 手机验证码缓存
     * redis结构:String
     * key = key + phone + type(验证码类型)
     * value = verifyCode
     */
    public static final String PHONE_VERIFY_CODE = "phone_verify_code_";
    /**
     * 邮箱验证码缓存
     * redis结构:String
     * key = key + mail + type(验证码类型)
     * value = verifyCode
     */
    public static final String MAIL_VERIFY_CODE = "mail_verify_code_";

    /**
     * 验证码校验
     * redis结构:String
     * key = key+phone+type
     */
    public static final String VERIFY_CODE_CHECK = "verify_code_check_";
    /**
     * 验证码校验状态缓存
     * redis结构:String
     * key = key+phone+type+value
     * value = 成功时Md5码/失败次数/fail
     */
    public static final String VERIFY_CODE_CHECK_STATE = "verify_code_check_state";
    /**
     * 重置密码步骤验证
     * redis结构：String
     * key = key + userId
     * value = 1、2、3、4
     */
    public static final String RESET_PASSWORD_STEP = "reset_password_step_";


    /**
     * 银行卡步骤验证
     * redis结构：String
     * key = key + userId
     * value = 1、2、3、4
     */
    public static final String BANK_CARD_STEP = "bank_card_step_";
    /**
     * 更改邮箱步骤验证
     * redis结构：String
     * key = key + userId
     * value = 1、2
     */
    public static final String CHANGE_EMAIL_STEP = "change_email_step_";
    /**
     * 更改手机号步骤验证
     * redis结构：String
     * key = key + userId
     * value = 1、2
     */
    public static final String CHANGE_PHONE_STEP = "change_phone_step_";
    /**
     * 视频步骤验证
     * redis结构：String
     * key = key + userId
     * value = 1、2
     */
    public static final String VIDEO_APPROVE_STEP = "video_approve_step_";
    /**
     * 银行卡临时存储信息
     * redis结构：String
     * key = key + userId
     * value = json
     */
    public static final String BANK_INFO = "bank_info_";


    /*************************** 区域相关缓存begin ****************************/
    /**
     * area实体缓存
     * redis结构:object(areaEntity)
     * key = key + areaId
     * value = areaEntity
     */
    public static final String AREA_OBJECT_KEY = "area_object_";
    /**
     * area子类区域列表缓存
     * redis结构:object(List<Area>)
     * key = key + areaId
     * value = list
     */
    public static final String AREA_LIST_SON_KEY = "area_list_son_";

    /**
     * 所有省缓存
     * redis结构：List<Area>
     * key = key
     * value = list
     */
    public static final String AREA_LIST_PROVINCE = "area_list_province";

    /*************************** 区域相关缓存end ****************************/

    /***************************游记相关缓存begin ****************************/
    /**
     * 游记实体Object缓存
     * redis结构:obj
     * key =key +travelId
     * value = Travel(Entity)
     */
    public static final String ZSJ_TRAVEL_OBJ = "zsj_travel_";
    /**
     * 推荐游记list缓存
     * redis结构:zset
     * key = key  +city
     * value = 游记Id
     * score = 游记发布时间时间戳
     */
    public static final String ZSJ_GROOM_TRAVEL_LIST = "zsj_travel_groom_list_";
    /**
     * 城市游记list缓存
     * redis结构:zset
     * key = key + city
     * value = 游记id
     * score = 游记发布时间时间戳
     */
    public static final String ZSJ_TRAVEL_LIST = "zsj_travel_list_";
    /**
     * 我的游记list缓存
     * redis结构:zset
     * key = key + userid
     * value = 游记id
     * score = 游记发布时间时间戳
     */
    public static final String ZSJ_MY_TRAVEL_LIST = "zsj_travel_my_list_";

    /*****************************景点缓存***********************************/
    /**
     * 景点集合缓存
     * redis结构:zset
     * key = key + city+_类别id
     * value = 景点id
     * score = 景点的排序
     */
    public static final String ZSJ_SCENIC_LIST = "zsj_scenic_list_";
    /**
     * 景点实体缓存
     * redis结构:obj
     * key = key + 景点id
     * value = Scenic(Entity)
     */
    public static final String ZSJ_SCENIC_OBJ = "zsj_scenic_obj_";
    /**
     * 小景点实体缓存
     * redis结构:obj
     * key = key + 景点id
     * value = Scenic(Entity)
     */
    public static final String ZSJ_SCENIC_SMALL_OBJ = "zsj_scenic_small_obj_";

    /************************优质路线缓存***************************/
    /**
     * 优质路线实体缓存
     * redis结构:obj
     * key = key + 路线id
     * value = QualityPath(Entity)
     */
    public static final String ZSJ_QUALITY_PATH_OBJ = "zsj_quality_path_obj_";

    /**
     * 优质路线集合缓存
     * redis结构:zset
     * key = key + city
     * value = 优质路线id
     * score = 优质路线sort
     */
    public static final String ZSJ_QUALITY_PATH_LIST = "zsj_quality_path_list_";
}
