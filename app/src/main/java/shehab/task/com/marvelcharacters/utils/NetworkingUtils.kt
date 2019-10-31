package shehab.task.com.marvelcharacters.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Created by shehab on 4/17/2018.
 */

class NetworkingUtils constructor (var context: Context) {


    /*
     * HACKISH: These constants aren't yet available in my API level (7), but I need to handle these cases if they come up, on newer versions
     */
    val NETWORK_TYPE_EHRPD = 14 // Level 11
    val NETWORK_TYPE_EVDO_B = 12 // Level 9
    val NETWORK_TYPE_HSPAP = 15 // Level 13
    val NETWORK_TYPE_IDEN = 11 // Level 8
    val NETWORK_TYPE_LTE = 13 // Level 11
    var TYPE_NOT_CONNECTED = 0
    /** A type which indicates that the device is connected via Wi-Fi  */
    var TYPE_WIFI = 1
    /** A type which indicates that the device is connected via Mobile Data  */
    var TYPE_MOBILE = 2

    /**
     * Indicates whether the device is connected to a network, doesn't
     * necessarily mean that Internet is present
     *
     * @return True if the device is connected, false otherwise
     */
    val isNetworkConnected: Boolean
        get() {
//            val connectivity = AppController.instance!!.applicationContext
            val connectivity = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.allNetworkInfo
                if (info != null)
                    for (i in info.indices)
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
            }
            return false
        }



}
