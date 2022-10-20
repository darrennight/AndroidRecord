package com.hao.androidrecord.activity.expandrv.expand;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.expandrv.Genre;
import com.hao.androidrecord.activity.expandrv.check.MultiCheckGenre;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class GenreViewHolder extends GroupViewHolder {

  private TextView genreName;
  private ImageView arrow;
  private ImageView icon;
  private Boolean isExpand = false;

  public GenreViewHolder(View itemView) {
    super(itemView);
    genreName = (TextView) itemView.findViewById(R.id.list_item_genre_name);
    arrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
    icon = (ImageView) itemView.findViewById(R.id.list_item_genre_icon);
  }

  public void setGenreTitle(ExpandableGroup genre) {
    if (genre instanceof Genre) {
      genreName.setText(genre.getTitle());
      icon.setBackgroundResource(((Genre) genre).getIconResId());
      if(isExpand){
        arrow.setImageResource(R.drawable.img_close_comments);
      }else{
        arrow.setImageResource(R.drawable.ic_arrow_down);
      }

    }
    if (genre instanceof MultiCheckGenre) {
      genreName.setText(genre.getTitle());
      icon.setBackgroundResource(((MultiCheckGenre) genre).getIconResId());
    }
    /*if (genre instanceof SingleCheckGenre) {
      genreName.setText(genre.getTitle());
      icon.setBackgroundResource(((SingleCheckGenre) genre).getIconResId());
    }*/
  }

  @Override
  public void expand() {
//    animateExpand();
    isExpand = true;
    arrow.setImageResource(R.drawable.img_close_comments);
  }

  @Override
  public void collapse() {
//    animateCollapse();
    isExpand = false;
    arrow.setImageResource(R.drawable.ic_arrow_down);
  }

  private void animateExpand() {
    RotateAnimation rotate =
        new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(0);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }

  private void animateCollapse() {
    RotateAnimation rotate =
        new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(0);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }
}
