package com.hao.androidrecord.activity.tableview01.decoration

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tableview01.model.Period
import io.moyuru.timetablelayout.decoration.ColumnNameDecoration

class StageNameDecoration(context: Context, private val periods: List<Period>, columnCount: Int) :
  ColumnNameDecoration(
    columnCount,
    context.resources.getDimensionPixelSize(R.dimen.columnWidth),
    context.resources.getDimensionPixelSize(R.dimen.stageLabelHeight),
    context.resources.getDimension(R.dimen.stageLabelTextSize),
    Color.WHITE,
    ContextCompat.getColor(context, R.color.black)
  ) {

  override fun getColumnNumber(position: Int): Int {
    return periods.getOrNull(position)?.stageNumber ?: 0
  }

  override fun getColumnName(columnNumber: Int): String {
    return when (columnNumber) {
      0 -> "Melodic Hardcore"
      1 -> "Metalcore"
      2 -> "Hardcore"
      3 -> "Deathcore"
      else -> "Djent"
    }
  }
}
