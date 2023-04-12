package com.example.buyshared.ui.Activity

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.button.MaterialButton
import com.example.buyshared.R
import com.example.buyshared.ui.Activity.MainActivity
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


private val TAG: String = "pideloApp"

fun fileExist(context: Context, filedir: String): Boolean {
    var file: File = File(filedir)
    if ((file == null) || (!file.exists())) {
        Log.d(TAG, "no esta");
        return false;
    }
    Log.d(TAG, "ESTA");
    return true;
}

fun loadJSONFromAssets(flName: String?, context: Context): String? {
    var json: String? = null
    try {
        val inputStream: InputStream = context.assets.open(flName!!)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, charset("UTF-8"))
        Log.v(TAG, "Load json ok")
    } catch (ex: IOException) {
        Log.v(TAG, "Error json ok")
    }
    return json
}

fun getMin(): String? {
    val calendar = Calendar.getInstance()
    val date = calendar.time
    val sdf: SimpleDateFormat
    sdf = SimpleDateFormat("mm")
    return sdf.format(date)
}

fun getHora(): String? {
    val calendar = Calendar.getInstance()
    val date = calendar.time
    val sdf: SimpleDateFormat
    sdf = SimpleDateFormat("HH")
    return sdf.format(date)
}

//fun displayDialog(activity: Activity,title:String,descripcion:String,okBtn:String? = "Aceptar",okCancel:String? = "Cancelar"): Dialog {
//    var builder: Dialog? = null
//    builder = Dialog(activity)
//    builder.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
//    builder.setContentView(R.layout.dialog)
//    builder.setCancelable(true)
//    builder.setCanceledOnTouchOutside(true)
//    builder.findViewById<TextView>(R.id.titleDialog).text = title
//    builder.findViewById<MaterialButton>(R.id.btnOkDialog).text = okBtn
//    builder.findViewById<MaterialButton>(R.id.btnCancelDialog).text = okCancel
//    builder.findViewById<TextView>(R.id.descripcionDialog).text = descripcion
//    builder.findViewById<ImageView>(R.id.closeDialog).setOnClickListener {
//        builder.hide()
//    }
//    return builder;
//}

//@RequiresApi(Build.VERSION_CODES.O)
//private fun createForegroundNotification(on_line: String,context: Context): Notification? {
//    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
//
//    // El id del canal de notificación único.
//    val notificationChannelId = "Muevety"
//
//    // Para sistemas superiores a Android 8.0, cree un nuevo canal de mensajes
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        // Nombre del canal visible para el usuario
//        val channelName = "Muevety"
//        // La importancia del canal
//        val importance = NotificationManager.IMPORTANCE_HIGH
//        val notificationChannel =
//            NotificationChannel(notificationChannelId, channelName, importance)
//        notificationChannel.description = on_line
//        //Luz LED
//        notificationChannel.enableLights(true)
//        notificationChannel.lightColor = Color.GREEN
//        //conmoción
//        notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
//        notificationChannel.enableVibration(true)
//        notificationManager?.createNotificationChannel(notificationChannel)
//    }
//    val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, notificationChannelId)
//    // Icono de notificación
//    builder.setSmallIcon(R.mipmap.ic_launcher)
//    // Título de la notificación
//    builder.setContentTitle("Muevety")
//    // Contenido de la notificación
//    builder.setContentText(on_line)
//    builder.setProgress(0, 0, true)
//    // Estación de blecer la hora de visualizala notificación
//    builder.setWhen(System.currentTimeMillis())
//    // Establecer el contenido de inicio
//    val activityIntent = Intent(context, MainActivity::class.java)
//    val pendingIntent =
//        PendingIntent.getActivity(context, 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//    builder.setContentIntent(pendingIntent)
//    Log.v(TAG, "lanzo la notificacion")
//    // Crear notificación y regresar
//    return builder.build()
//}
