package com.raju.adobepannel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.pannel_main_layout.*

class PanelActivity : AppCompatActivity(), View.OnClickListener {

    private var fadeOut: Animation? = null
    private var fadeIn: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pannel_main_layout)

        // Declare in and out animations and load them using AnimationUtils class
        fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        // set the animation type to ViewSwitcher
        vsLeftPanel.inAnimation = fadeIn
        vsLeftPanel.outAnimation = fadeOut


        btnNext.setOnClickListener(this)
        btnPrevious.setOnClickListener(this)
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNext -> rightPanels.leftPanelShowNext()
            R.id.btnPrevious -> rightPanels.leftPanelShowPrevious()
            R.id.button01 -> {
                rightPanels.toggleLeftPanel(button01.isChecked)
                button01.toggle()
            }
            R.id.button02 -> {
                rightPanels.toggleRightPanel(button02.isChecked)
                button02.toggle();
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        rightPanels.tearDown()
        AnimatorUtils.cancel(fadeIn);
        AnimatorUtils.cancel(fadeOut);
    }
}
