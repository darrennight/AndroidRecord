package com.hao.androidrecord.custom

import android.text.InputFilter
import android.text.Spanned

class RemarkFilter(private var max: Int) : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int,
                        dend: Int): CharSequence? {

        var keep = max - (dest?.length!! - (dend - dstart))
        if (keep <= 0) {
            return ""
        } else if (keep >= end - start) {
            return null
        } else {
            keep += start
            source?.let {
                if (Character.isHighSurrogate(it[keep - 1])) {
                    --keep
                    if (keep == start) {
                        return ""
                    }
                }
            }

            return source?.subSequence(start, keep)
        }
    }
}