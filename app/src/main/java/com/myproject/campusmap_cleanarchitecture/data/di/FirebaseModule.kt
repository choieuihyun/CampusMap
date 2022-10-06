package com.campusmap.android.campusmap_with_kakao.data.di

import com.google.firebase.ktx.Firebase

// 이건 읽기 쓰기를 둘다 하려고 할 때 Firebase 싱글톤으로 생성하는 것인듯.

object FirebaseModule {

    fun createFirebase(firebase: Firebase): Firebase {
        return firebase
    }

}

/*
class FirebaseModule private constructor(){


companion object {

        private var INSTANCE: Firebase? = null

        fun initialize() {
            if(INSTANCE == null) {
                INSTANCE = Firebase
            }
        }

        fun get(): Firebase {
            return INSTANCE ?:
            throw IllegalStateException("Firebase가 이미 초기화 되어있음")
        }
    }*/

