package com.fanyafeng.rxandroid.hong9.network;

/**
 * Created by 365rili on 16/6/20.
 */
public class Urls {
    public static final String USER_PROTOCOL_URL = "http://putaoputao.cn/agreement.html";

    /* 以下使用新的接口 */

    public static final String HTTP_URL = "http://m.putaoputao.cn";

    public static final String HTTPS_URL = "https://m.putaoputao.cn";

    //微信登录
    public static final String WEIXIN_SSO_LOGIN = HTTPS_URL + "/wx/sso/login.do";

    //微信绑定
    public static final String WEIXIN_BIND_URL = HTTP_URL + "/wx/sso/bind.do";

    //QQ登录
    public static final String QQ_SSO_URL = HTTPS_URL + "/qq/sso/login.do";

    //QQ绑定
    public static final String QQ_BIND_URL = HTTP_URL + "/qq/sso/bind.do";

    public static final String USER_SMS_URL = HTTP_URL + "/account/p-login.do";

    public static final String USER_LOGIN_URL = HTTPS_URL + "/account/p-login-done.do";

    //设置昵称
    public static final String SET_NICK_URL = HTTP_URL + "/account/setNickname.do";

    //设置生日
    public static final String SET_BIRTHDAY_URL = HTTP_URL + "/account/setBirthday.do";

    //设置邮箱
    public static final String SET_EMAIL_URL = HTTP_URL + "/account/setEmail.do";

    //首页接口
    public static final String MAIN_URL = HTTP_URL + "/homepage/index.do";

    //首页多页加载
    public static final String GOOD_LIST_URL = HTTP_URL + "/homepage/wineList.do?p=";

    //收藏接口,收藏的情况下提交pid=?字段,不收藏得情况提交cs=1,pid=?
    public static final String COLLECT_URL = HTTP_URL + "/product/collect.do";

    //获取购物车数量
    public static final String GET_CART_COUNT_URL = HTTP_URL + "/cart/count.do";

    //获取购物车列表接口,get请求
    public static final String CART_LIST_URL = HTTP_URL + "/cart/list.do";

    //更改商品数量 post请求,提交id,num
    public static final String CART_CHANGE_URL = HTTP_URL + "/cart/change.do";

    //购物车单个商品选中和不被选中,post请求,提交id
    public static final String CART_ITEM_CHECKONE_CHANGED = HTTP_URL + "/cart/checkItem.do";

    //购物车所有商品选中状态
    public static final String CART_ALL_CHECK_CHANGED = HTTP_URL + "/cart/checkAll.do";

    //删除单个商品,post请求,提交id
    public static final String CART_REMOVE_URL = HTTP_URL + "/cart/remove.do";


    //获取用户信息
    public static final String GET_USER_INFO = HTTP_URL + "/account/getUserInfo.do";

    //绑定手机号
    public static final String PHONE_BIND_URL = HTTP_URL + "/account/p-bind.do";

    //绑定完成接口
    public static final String PHONE_BIND_DONE_URL = HTTP_URL + "/account/p-bind-done.do";

    //收藏接口,get请求
    public static final String USER_COLLECTIONS_LIST_URL = HTTP_URL + "/product/collections.do?p=";

    //微信支付
    public static final String WEIXIN_PAY_URL = HTTPS_URL + "/tenpay/prepay.do";

    //获取订单接口
    public static final String ORDER_GET_URL = HTTP_URL + "/order/show.do?status=";

    //微信支付结果查询
    public static final String WEIXIN_PAY_RESULT_URL = HTTPS_URL + "/tenpay/payResult.do";

    //订单详情接口
    public static final String GET_ORDER_INFO = HTTP_URL + "/order/detail.do?orderId=";

    //获取优惠券接口
    public static final String USER_COUPON_URL = HTTP_URL + "/coupon/getAllCoupons.do";

    //地址管理
//    public static final String ADDRESS_URL = HTTP_URL + "/action/pay/address_manage.html";
    //    产区选择
    public static final String PLACES_FILTERS_URL = HTTP_URL + "/search/places.do";


    public static final String COUNTRY_FILTERS_URL = HTTP_URL + "/search/country.do";

    //    品牌选择
    public static final String BRANDS_FILTERS_URL = HTTP_URL + "/search/brands.do";


    public static final String CATEGORIES_FILTERS_URL = HTTP_URL + "/search/categories.do";

    //类型选择
    public static final String TYPES_FILTERS_URL = HTTP_URL + "/search/types.do";

    //过滤器
    public static final String ALL_FILTERS_URL = HTTP_URL + "/search/all.do";

    //首页热搜,get请求
    public static final String HOT_SEARCH_URL = HTTP_URL + "/search/hotwords.do";

    //筛选
    public static final String FILTER_WINE_URL = HTTP_URL + "/search/count.do";

    public static final String FILTER_SEARCH_URL = HTTP_URL + "/search/go.do";

    //    根据热搜词搜索,get请求
    public static final String HOT_SEARCH_RESULT_URL = HTTP_URL + "/search/go.do?kw=";

    //    地区操作
    public static final String ORIGIN_SEARCH_RESULT_URL = HTTP_URL + "/search/go.do?origin=";

    //    品牌操作
    public static final String BRAND_SEARCH_RESULT_URL = HTTP_URL + "/search/go.do?brand=";

    //品种操作
    public static final String CATEGORY_SEARCH_RESULT_URL = HTTP_URL + "/search/go.do?category=";

    //类型操作
    public static final String TYPE_SEARCH_RESULT_URL = HTTP_URL + "/search/go.do?type=";

    //国家操作
    public static final String COUNTRY_SEARCH_RESULT_URL = HTTP_URL + "/search/go.do?country=";

    //消息接口
    public static final String MESSAGE_URL = HTTP_URL + "/message/getList.do?ts=";

    //未支付订单接口
    public static final String UNPAYED_COUNT_URL = HTTP_URL + "/order/getNotPayNums.do";

    //取红点状态
    public static final String HOMEPAGE_REDPOINT_URL = HTTP_URL + "/homepage/red.do?ts=";

    //关于页面
    public static final String ABOUT_US_URL = HTTP_URL + "/aboutus.html";

    public static final String ACCOUNT_SET_HEADER = HTTP_URL + "/account/setHeader.do";

    public static final String UPYUN_SIGNATURE = HTTP_URL + "/account/getHeaderSignature.do";

    //订单删除
    public static final String DELETE_ORDER_URL = HTTP_URL + "/order/delete.do?orderId=";

    //信鸽提交token接口
    public static final String XG_REGISTER_URL = HTTP_URL + "/device/androidRegister.do";

    public static final String ALI_PAY_URL = HTTPS_URL + "/alipay/payData.do";

    public static final String UPDATE_URL = HTTP_URL + "/homepage/getBootinfo.do";

    //查看顺丰物流接口
    public static final String CHECK_EXPRESS_URL = HTTP_URL + "/express/routeRequest.do?orderId=";
}
