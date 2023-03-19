/*
    水银第11代
    2023.02.09 - 2023.03.14

    待办：

    史诗级更新： 1. 将ArrayList替换为HashSet用于存储用户数据，提高代码效率
                2. 签到系统
                3. 用户资料界面
                4. 独立功能菜单与游戏菜单
                5. 数据存储系统

    总体：
        整理了所有代码
        优化了用户体验
        优化了继承体系
        优化了代码
        给验证码添加了鼠标停留时的下划线效果
    登录界面：
        增加了一个快捷入口，方便开发者测试
    进制转换工具：
        使用正则表达式优化数字格式检验
    质数判断器：
        修复了将1判断为质数的问题
    随机数生成器：
        增大了部分文字的大小
    拼图小游戏：
        增加了更改移动模式的选项
        将默认移动方式从移动空白方块改为移动拼图
        替换了一部分图片
    重设密码：
        修复了关于验证手机号码的问题
    重设手机号码：
        修复了验证码输入正确但仍然提示有误的问题
        修复了输入已被占用的手机号码但不显示提示的问题
        修复了新手机号码合法但验证码输入有误仍然提示手机号码无效的问题
        修复了重设后退出登录的问题
 */

///水银入口
public class Start {
    public static void main(String[] args) {
        new Login();
    }
}
