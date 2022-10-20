package com.hao.androidrecord.activity.tableview01.item

import com.hao.androidrecord.R
import com.hao.androidrecord.databinding.ItemSpaceBinding
import com.xwray.groupie.databinding.BindableItem

class SpaceItem : BindableItem<ItemSpaceBinding>() {
  override fun getLayout() = R.layout.item_space

  override fun bind(viewBinding: ItemSpaceBinding, position: Int) {
  }
}
