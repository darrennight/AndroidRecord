package com.hao.androidrecord.activity.expandrv.check;

import com.hao.androidrecord.activity.expandrv.Artist;
import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;
import java.util.List;

public class MultiCheckGenre extends MultiCheckExpandableGroup {

  private int iconResId;

  public MultiCheckGenre(String title, List<Artist> items, int iconResId) {
    super(title, items);
    this.iconResId = iconResId;
  }

  public int getIconResId() {
    return iconResId;
  }
}
