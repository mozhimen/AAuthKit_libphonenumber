package com.mozhimen.authk.libphonenumber

import android.content.Context
import android.util.Log
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.kotlin.utilk.kotlin.text.join_sta_plus
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil

/**
 * @ClassName AuthKPhoneNumberUtil
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/22 23:53
 * @Version 1.0
 */

object AuthKPhoneNumberUtil : IUtilK {

    /**
     * 根据区号判断是否是正确的电话号码
     * 格式：国家码 86  手机号 +8618800183546
     * @param phoneNumber :带国家码的电话号码 +8618800183546
     * @param countryCode :默认国家码 86
     * return ：true 合法  false：不合法
     */
    @JvmStatic
    fun isPhoneNumberValid(context: Context, phoneNumber: String, countryCode: String): Boolean {
        Log.d(TAG, "isPhoneNumberValid: $phoneNumber/$countryCode")
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.createInstance(context)
        try {
            val numberProto = phoneUtil.parse("$countryCode$phoneNumber".join_sta_plus(), countryCode)
            return phoneUtil.isValidNumber(numberProto)
        } catch (e: NumberParseException) {
            e.printStackTrace()
            Log.e(TAG, "isPhoneNumberValid: NumberParseException was thrown: $e")
        }
        return false
    }
}