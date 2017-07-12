package com.iobit.kotlindemo.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.File
import java.io.InputStreamReader
import java.util.regex.Pattern

/**
 * root相关操作了
 * Created by kahcem on 2017/7/3.
 */


/**
 * 判断设备是否root
 */
fun isRoot(): Boolean {
    val paths = arrayOf("/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
            "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su")

    return paths.any { File(it).exists() }
}

/**
 * 检查app是否已经获取root权限
 */
fun checkRoot(packageCodePath: String): Boolean {
    var process: Process? = null
    try {
        val cmd: String = "ls -l " + packageCodePath
        process = Runtime.getRuntime().exec(cmd)

        val mReader: BufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        val sb: StringBuilder = StringBuilder()
        val chars = CharArray(1024 * 10)
        var num: Int

        loop@ while (true) {
            num = mReader.read(chars)
            if (num == -1) break@loop

            sb.append(chars, 0, num)
        }
        mReader.close()

        val regx = "-rwxr[\\s\\S]+" //root后权限的表达式
        return Pattern.matches(regx, sb.toString())
    } catch (e: Exception) {
        return false
    } finally {
        process?.destroy()
    }
}

/**
 * 申请root权限
 */
fun grantRoot(packageCodePath: String): Boolean {
    val cmd: String = "chmod 777 " + packageCodePath
    return runCommand(cmd)
}

/**
 * 停止指定包名的应用
 */
fun killProcess(packageName: String): Boolean {
    val cmd = Command.COMMAND_KILL_PROCESS + packageName
    return runCommand(cmd)
}

/**
 * 冻结指定包名的应用
 */
fun disableApp(packageName: String): Boolean {
    val cmd = Command.COMMAND_DISABLE_APP + packageName
    return runCommand(cmd)
}

/**
 * 解冻指定包名的应用
 */
fun enableApp(packageName: String): Boolean {
    val cmd = Command.COMMAND_ENABLE_APP + packageName
    return runCommand(cmd)
}

/**
 * 静默卸载指定的应用
 */
fun uninstallApp(packageName: String): Boolean {
    val cmd = Command.COMMAND_UNINSTALL_APP + packageName
    return runCommand(cmd)
}

/**
 * 静默安装指定路径的apk
 */
fun installApp(apkPath: String): Boolean {
    val cmd = Command.COMMAND_INSTALL_APP + apkPath
    return runCommand(cmd)
}

/**
 * 初始化指定包名的应用
 */
fun initializeApp(packageName: String): Boolean {
    val cmd = Command.COMMAND_INITIALIZE_APP + packageName
    return runCommand(cmd)
}


/**
 * 运行adb shell命令
 * @param cmd adb shell相关命令
 */
fun runCommand(cmd: String): Boolean {
    var process: Process? = null
    var os: DataOutputStream? = null
    try {
        process = Runtime.getRuntime().exec("su")
        os = DataOutputStream(process.outputStream)
        os.writeBytes(cmd + "\n")
        os.writeBytes("exit\n")
        os.flush()
        process.waitFor()
    } catch (e: Exception) {
        return false
    } finally {
        try {
            os?.close()
            process?.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return true
}

fun queryBootApps(context: Context): ArrayList<BootApp> {
    val appList: ArrayList<String> = ArrayList()
    val bootList: ArrayList<BootApp> = ArrayList()
    val intent: Intent = Intent(Intent.ACTION_BOOT_COMPLETED)
    val resolveInfoList: List<ResolveInfo>
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        resolveInfoList = context.packageManager.queryBroadcastReceivers(intent,
                PackageManager.MATCH_DISABLED_COMPONENTS)
    } else {
        resolveInfoList = context.packageManager.queryBroadcastReceivers(intent,
                PackageManager.GET_DISABLED_COMPONENTS)
    }

    var appInfo: ApplicationInfo
    var componentName: ComponentName
    for (info in resolveInfoList) {
        appInfo = info.activityInfo.applicationInfo
        if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) continue
        componentName = ComponentName(appInfo.packageName, info.activityInfo.name)
        if (context.packageManager.getComponentEnabledSetting(componentName)
                != PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            bootList.add(BootApp(appInfo.packageName, true))
        } else {
            bootList.add(BootApp(appInfo.packageName, false))
        }
    }

    return bootList
}

data class BootApp(val pkgName: String, val isEnable: Boolean)