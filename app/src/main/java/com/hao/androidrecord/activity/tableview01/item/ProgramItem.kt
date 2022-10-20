package com.hao.androidrecord.activity.tableview01.item

import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tableview01.model.Program
import com.hao.androidrecord.databinding.ItemProgramBinding
import com.xwray.groupie.databinding.BindableItem

class ProgramItem(private val program: Program) : BindableItem<ItemProgramBinding>() {
  override fun getLayout() = R.layout.item_program

  override fun bind(viewBinding: ItemProgramBinding, position: Int) {
    viewBinding.program = program
  }
}
