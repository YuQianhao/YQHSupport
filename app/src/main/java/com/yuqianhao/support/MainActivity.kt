package com.yuqianhao.support

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import com.yuqianhao.support.activity.StateBar
import com.yuqianhao.support.activity.YPermissionActivity
import com.yuqianhao.support.dialog.BasisDialog
import com.yuqianhao.support.dialog.SelectDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : YPermissionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSuccressNotifyMsg("Succress")
    }

}
