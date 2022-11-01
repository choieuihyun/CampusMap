package com.myproject.campusmap_cleanarchitecture.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.myproject.campusmap_cleanarchitecture.R

class LoadingProgress(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_progress)

        // 취소 불가능
        setCancelable(false)

        // 배경 투명하게
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

}