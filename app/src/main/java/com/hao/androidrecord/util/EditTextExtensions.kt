/*
 * Copyright (c) 2020 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package com.hao.androidrecord.util

import android.widget.EditText

fun EditText.setTextWithSelection(text: CharSequence?) {
    setText(text)
    setSelection(0, this.text.length)
}
