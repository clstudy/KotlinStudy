package com.example.coroutine_ext_library.coroutine

import kotlinx.coroutines.experimental.Job

/**
 * Created by zzy05 on 2017/8/23.
 */
interface JobHolder {
    val job: Job
}