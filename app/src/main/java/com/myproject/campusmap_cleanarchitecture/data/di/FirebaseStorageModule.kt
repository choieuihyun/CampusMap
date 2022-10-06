package com.campusmap.android.campusmap_with_kakao.data.di

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseStorageModule {

    //    var databaseRef: DatabaseReference =
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    var storageReference: StorageReference = storage.reference
    var pathReference: StorageReference? = storageReference.child("photo")

    // Firebase storage 경로 찍어서 이미지 가져오기.
    fun connect(c: Activity, path: String, v: ImageView) {

        if (pathReference == null) {
            Toast.makeText(c as Activity?, "저장소에 사진이 없음", Toast.LENGTH_LONG).show()
        } else {
            val submitProfile: StorageReference = storageReference.child(path)
            submitProfile.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(c).load(uri).into(v)
            }.addOnFailureListener { }
        }
    }
}

/*
class FirebaseStorageModule {

//    var databaseRef: DatabaseReference =
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    var storageReference: StorageReference = storage.reference
    var pathReference: StorageReference? = storageReference.child("photo")

    // Firebase storage 경로 찍어서 이미지 가져오기.
    fun connect(c: Activity, path: String, v: ImageView) {

        if (pathReference == null) {
            Toast.makeText(c as Activity?, "저장소에 사진이 없음", Toast.LENGTH_LONG).show()
        } else {
            val submitProfile: StorageReference = storageReference.child(path)
            submitProfile.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(c).load(uri).into(v)
            }.addOnFailureListener { }
        }
    }
}
*/





