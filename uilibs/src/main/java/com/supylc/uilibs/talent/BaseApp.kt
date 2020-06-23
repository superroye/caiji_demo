package com.supylc.uilibs.talent

import android.app.Application

class BaseApp {

    companion object {
        lateinit var context: Application

        fun initApp(app: Application) {
            context = app
        }
    }


}

