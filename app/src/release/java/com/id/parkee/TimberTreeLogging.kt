package com.id.parkee

import timber.log.Timber

class TimberTreeLogging: Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        //Empty
    }
}
