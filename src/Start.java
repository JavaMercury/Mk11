/*
    水银第11代
    2023.02.09 - 2023.0

    待办：拼图小游戏通关后提供两个按钮，一个退出，一个重玩

    史诗级更新： 1. 将ArrayList替换为HashSet用于存储用户数据，提高代码效率
                2. 签到系统
                3. 用户资料界面

    总体：
        整理了所有代码
        优化了用户体验
        优化了继承体系
        给验证码添加了鼠标停留时的下划线效果
    登录界面：
        增加了一个快捷入口，方便开发者测试
    进制转换工具：
        使用正则表达式优化数字格式检验
    拼图小游戏：
        增加了更改移动模式的选项
        将默认移动方式从移动空白方块改为移动拼图
    重设密码：
        修复了关于验证手机号码的问题
    重设手机号码：
        修复了验证码输入正确但仍然提示有误的问题
        修复了输入已被占用的手机号码但不显示提示的问题
        修复了新手机号码合法但验证码输入有误仍然提示手机号码无效的问题
 */

///水银入口
public class Start {
    public static void main(String[] args) {
        new Login();
    }
}
