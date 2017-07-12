package com.iobit.kotlindemo.utils

/**
 * adb shell命令集
 * Created by kachem on 2017/7/3.
 */
class Command {
    companion object {
        /**
         * 杀掉指定的进程
         */
        const val COMMAND_KILL_PROCESS = "am force-stop "

        /**
         * 冻结指定的应用
         */
        const val COMMAND_DISABLE_APP = "pm disable "

        /**
         * 解冻指定的应用
         */
        const val COMMAND_ENABLE_APP = "pm enable "

        /**
         * 静默卸载指定应用
         */
        const val COMMAND_UNINSTALL_APP = "pm uninstall "

        /**
         * 静默安装指定应用
         */
        const val COMMAND_INSTALL_APP = "pm install -r "

        /**
         * 初始化指定应用
         */
        const val COMMAND_INITIALIZE_APP = "pm clear "
    }
}