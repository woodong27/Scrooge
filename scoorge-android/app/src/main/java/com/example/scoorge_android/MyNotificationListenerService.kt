package com.example.scoorge_android

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.scoorge_android.model.PayInfo
import com.example.scoorge_android.model.ResponseResult
import com.example.scoorge_android.network.RetrofitService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MyNotificationListenerService : NotificationListenerService() {

    private val TAG = "MyNotificationListenerService"

    companion object {
        private const val SAMSUNG_PAY_PACKAGE_NAME = "com.samsung.android.spay"
        private const val KAKAO_PACKAGE_NAME = "com.kakao.talk"
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val packageName: String = sbn?.packageName ?: ""
        val extras = sbn?.notification?.extras
        val used_at: String = extras?.get(Notification.EXTRA_TITLE).toString()
        val extraText: String = extras?.get(Notification.EXTRA_TEXT).toString()

        println(packageName+" "+used_at)
        try {
            if (used_at != "null") {
                println(packageName)

                /* 자체 알림 테스트 진행 */
                val test: List<String> = extraText.split("₩")
                Log.d(
                    TAG, "onNotificationPosted:\n" +
                            "PackageName: $packageName\n" +
                            "Title: $used_at\n" +
                            "Card: ${test[0].trim()}\n"
                            + "Price: ${test[1].trim().replace(",", "")}\n"
                )

                /* 레트로핏 테스트 */
                val requestParams = PayInfo(user_id="하린", used_at = used_at, card_name = test[0].trim(),
                    amount = test[1].trim().replace(",", "").toInt() )

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
                println("끝")
                /* 자체 알림 테스트 끝 */
                when (packageName) {
                    SAMSUNG_PAY_PACKAGE_NAME
                    -> {
                        val test: List<String> = extraText.split("₩")
                        Log.d(
                            TAG, "onNotificationPosted:\n" +
                                    "PackageName: $packageName\n" +
                                    "used_at: ${used_at}\n" +
                                    "card_name: ${test[0].trim()}\n"
                                    + "amount: ${test[1].trim().replace(",", "")}\n"
                        )
                    }

                    KAKAO_PACKAGE_NAME
                    -> {
                        val test: List<String> = extraText.split("₩")
                        Log.d(
                            TAG, "onNotificationPosted:\n" +
                                    "PackageName: $packageName\n" +
                                    "Title: $used_at\n" +
                                    "Card: ${test[0].trim()}\n"
                                    + "Price: ${test[1].trim().replace(",", "")}\n"
                        )

                        /* 레트로핏 테스트 */
                        val requestParams = PayInfo(user_id="하린", used_at = used_at, card_name = test[0].trim(),
                            amount = test[1].trim().replace(",", "").toInt() )

                        RetrofitService.instance.postPayInfo(requestParams)
                            .enqueue(object : retrofit2.Callback<ResponseResult>{
                                override fun onResponse(
                                    call: Call<ResponseResult>,
                                    response: Response<ResponseResult>
                                ){
                                    Log.d("ㅠㅠ", response.body().toString())
                                    if(response.isSuccessful){
                                        val result = response.body()
                                        println("성공 $result")
                                    }
                                    else {
                                        println("여기서 실패")
                                    }
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