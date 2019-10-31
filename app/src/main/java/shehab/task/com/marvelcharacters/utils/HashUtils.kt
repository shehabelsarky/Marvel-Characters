package shehab.task.com.marvelcharacters.utils

import java.security.NoSuchAlgorithmException
import java.security.MessageDigest
import java.math.BigInteger


fun mD5Hash(s: String): String {
    var m: MessageDigest? = null

    try {
        m = MessageDigest.getInstance("MD5")
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    m!!.update(s.toByteArray(), 0, s.length)
    return BigInteger(1, m.digest()).toString(16)
}