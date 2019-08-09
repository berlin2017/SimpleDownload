package cn.lonsun.download

import android.content.Context
import android.content.Intent

/**
 * @Copyright © 2005-2019  安徽龙讯信息科技有限公司
 * @ClassName: DownloadManager
 * @Description: ${DESCRIPTION}
 * @Author: berlin
 * @Date: 2019/8/9 0009 10:07
 */
class DownloadManager {

    companion object {
        var indexToDownload: Int = 0
        var listOfFilesToBeDownloaded: ArrayList<FileToDownload?>? = ArrayList()
        var isNotificationShowing = false
        // calling start service again doesnot create new instance if service is already running

        fun initDownload(context: Context, fileUrl: String, filePath: String, fileName: String) {
            val fileToDownload = FileToDownload()
            fileToDownload.url = fileUrl
            fileToDownload.filePath = filePath
            fileToDownload.fileName = fileName
            fileToDownload.isDownloaded = false
            listOfFilesToBeDownloaded?.add(fileToDownload)
            isNotificationShowing = listOfFilesToBeDownloaded?.size!! > 1
            val intent = Intent(context, DownloadService::class.java)
            context.startService(intent)
        }
    }
}