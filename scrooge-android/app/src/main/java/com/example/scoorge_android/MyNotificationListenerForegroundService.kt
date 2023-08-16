package com.example.scoorge_android

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.scoorge_android.model.PayInfo
import com.example.scoorge_android.model.ResponseResult
import com.example.scoorge_android.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class MyNotificationListenerForegroundService : NotificationListenerService() {

    private val TAG = "MyNotificationListenerService"


    companion object {
        private const val SAMSUNG_PAY_PACKAGE_NAME = "com.samsung.android.spay"
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val packageName: String = sbn?.packageName ?: ""
        val extras = sbn?.notification?.extras

        val extraTitle: String = extras?.get(Notification.EXTRA_TITLE).toString()
        val extraText: String = extras?.get(Notification.EXTRA_TEXT).toString()

        try {
            if (extraTitle != "null") {
                println(packageName)



                when (packageName) {
                    SAMSUNG_PAY_PACKAGE_NAME
                    -> {
                        var used_at:String = ""
                        var card_name:String = ""
                        var amount:Int = 0


                        if("₩" in extraTitle) {
                            card_name = " "
                            val test: List<String> = extraTitle.split("₩")
                            used_at = test[0].trim()
                            amount = test[1].trim().replace(",", "").toInt()
                        }
                        else {
                            used_at = extraTitle
                            val test: List<String> = extraText.split("₩")
                            card_name = test[0].trim()
                            amount = test[1].trim().replace(",", "").toInt()
                        }


                        /* 레트로핏 테스트 */
                        val requestParams = PayInfo(used_at, card_name, amount)

                        RetrofitService.instance.postPayInfo(requestParams)
                            .enqueue(object : retrofit2.Callback<ResponseResult>{
                                override fun onResponse(
                                    call: Call<ResponseResult>,
                                    response: Response<ResponseResult>
                                ){
                                    println(response)
                                }
                                override fun onFailure(call: Call<ResponseResult>, t: Throwable){
                                    println("실패")
                                    println(t.message.toString())
                                }
                            })
                    }

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}