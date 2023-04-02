/*
    水银第11代
    2023.02.09 - 2023.04.03

    史诗级更新： 1. 签到系统
                2. 用户资料界面
                3. 用户数据本地读写系统
                4. 拼图小游戏存档系统
                5. 拼图小游戏计时器功能

    总体：
        整理了所有代码
        优化了用户体验
        优化了继承体系
        优化了代码
        优化了界面
        给验证码添加了鼠标停留时的下划线效果
        修复了从功能页面中退出后直接返回主菜单的问题
        删除了一些窗口的选项中退出登录的按钮
        优化了软件的内存占用
        修复了部分窗口退出会产生多个重复窗口的问题
    登录：
        修复了若显示密码但输入错误导致图标显示错误且失效的问题
    进制转换工具：
        使用正则表达式优化数字格式检验
    质数判断器：
        修复了将1判断为质数的问题
    随机数生成器：
        增大了部分文字的大小
    拼图小游戏：
        修复了通关后点击退出按钮会出现两个窗口的问题
        增加了更改移动模式的选项
        将默认移动方式从移动空白方块改为移动拼图
        替换了一部分图片
        修复了帮助窗口可以重复打开的问题
        修复了帮助窗口可以随意调整大小的问题
        修复了保存游戏后重启软件拼图出现两个空格的问题
        禁用了开始游戏前使用ctrl查看原图
        修复了通关后重玩游戏计时器不计时的问题
    重设密码：
        修复了关于验证手机号码的问题
        修复了重设后退出登录的问题
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
