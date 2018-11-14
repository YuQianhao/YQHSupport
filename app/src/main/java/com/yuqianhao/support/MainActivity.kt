package com.yuqianhao.support

import android.os.Bundle
import com.yuqianhao.support.activity.YPermissionActivity

class MainActivity : YPermissionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
