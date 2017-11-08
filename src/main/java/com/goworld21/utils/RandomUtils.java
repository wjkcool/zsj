package com.goworld21.utils;

public class RandomUtils {

    public static final String[] citys = {"天津", "北京", "上海", "石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水", "济南", "青岛", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽", "郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作", "濮阳", "许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店", "武汉", "黄石", "十堰", "宜昌", "襄樊", "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁", "随州", "恩施土家族苗族自治州", "太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁", "合肥", "芜湖", "蚌埠", "淮南", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州", "阜阳", "宿州", "巢湖", "六安", "亳州", "池州", "宣城"};

    /**
     * 随机生成9~11位QQ邮箱
     *
     * @return
     */
   /* public static String getRandomQQMail() {
        String result = "" + getRandom(1, 10);
        // System.out.println(result);
        int count = getRandom(8, 11);
        for (int i = 0; i < count; i++) {
            result += getRandom(0, 10);
        }
        result += "@qq.com";
        return result;
    }*/

    /**
     * 生成随机手机号
     *
     * @return
     */
    public static String getRandomPhoneNum() {

        String[] starWith = {"13", "134", "139", "18", "177", "176", "150", "151", "153"};
        String result = starWith[getRandom(0, starWith.length)];
        while (result.length() < 11) {
            result += getRandom(0, 10);
        }

        return result;
    }

    /**
     * 生成固定长度的随机数
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String result = "" + getRandom(1, 10);
        while (result.length() < length) {
            result += getRandom(0, 10);
        }
        return result;
    }

    /**
     * 获取随机城市
     *
     * @return
     */
    public static String getRandomCity() {
        return citys[getRandom(0, citys.length)];
    }

    /**
     * 生成随机整数,半闭半开区间 [begin,end);
     *
     * @param begin
     * @param end
     * @return
     */
    public static int getRandom(int begin, int end) {
        int result = (int) (Math.random() * (end - begin)) + begin;
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandomNum(6));
        }
    }
}
