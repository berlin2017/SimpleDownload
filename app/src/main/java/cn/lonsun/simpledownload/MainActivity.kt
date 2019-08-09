package cn.lonsun.simpledownload

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.lonsun.download.DownloadManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var REQUEST_CODE_WRITE_STORAGE_PERMISION = 105

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        download.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_WRITE_STORAGE_PERMISION
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun download() {
        val folder = File(Environment.getExternalStorageDirectory().toString() + "/" + "HelloWorld")
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val fileName = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date()) + ".mp3"
        val urlOfTheFile = "https://sample-videos.com/audio/mp3/wave.mp3"
        DownloadManager.initDownload(this, urlOfTheFile, folder.absolutePath, fileName)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_WRITE_STORAGE_PERMISION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    download()
                }
            }

        }
    }
}
