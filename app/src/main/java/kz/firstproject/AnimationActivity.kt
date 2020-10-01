package kz.firstproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animation.*


class AnimationActivity : AppCompatActivity() {

//    val videoUrl = "https://www.audiusa.com/content/dam/nemo/us/models/A6/RS 6 Avant/header-transition/1920x1080/1920x1080_2021-Audi-Rs6-Avant-001-Intro 1920 V2-1.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
//        val animation1 = AlphaAnimation(0.1f, 1.0f)
//        animation1.duration = 1000
//        animation1.startOffset = 5000
//        animation1.fillAfter = true
//        imageView2.startAnimation(animation1)

        imageView2.setOnClickListener {
//            val animationRotateCenter = AnimationUtils.loadAnimation(
//                    this, R.anim.rotation_anim);
//            it.startAnimation(animationRotateCenter)
//            val scaleAnim = AnimationUtils.loadAnimation(this, R.anim.enlarge)
//            it.startAnimation(scaleAnim)
//            val translate = AnimationUtils.loadAnimation(this, R.anim.translate)
//            it.startAnimation(translate)
        }
//        videoView.setVideoURI(Uri.parse(videoUrl))
//        videoView.setMediaController(MediaController(this))
//        videoView.requestFocus();
//        videoView.start();

    }


}