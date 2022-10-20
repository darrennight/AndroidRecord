package com.hao.androidrecord.custom.niftydialog;


import com.hao.androidrecord.custom.niftydialog.effects.BaseEffects;
import com.hao.androidrecord.custom.niftydialog.effects.FadeIn;
import com.hao.androidrecord.custom.niftydialog.effects.Fall;
import com.hao.androidrecord.custom.niftydialog.effects.FlipH;
import com.hao.androidrecord.custom.niftydialog.effects.FlipV;
import com.hao.androidrecord.custom.niftydialog.effects.NewsPaper;
import com.hao.androidrecord.custom.niftydialog.effects.RotateBottom;
import com.hao.androidrecord.custom.niftydialog.effects.RotateLeft;
import com.hao.androidrecord.custom.niftydialog.effects.Shake;
import com.hao.androidrecord.custom.niftydialog.effects.SideFall;
import com.hao.androidrecord.custom.niftydialog.effects.SlideBottom;
import com.hao.androidrecord.custom.niftydialog.effects.SlideLeft;
import com.hao.androidrecord.custom.niftydialog.effects.SlideRight;
import com.hao.androidrecord.custom.niftydialog.effects.SlideTop;
import com.hao.androidrecord.custom.niftydialog.effects.Slit;

/*
 * Copyright 2014 litao
 * https://github.com/sd6352051/NiftyDialogEffects
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(com.hao.androidrecord.custom.niftydialog.effects.SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(com.hao.androidrecord.custom.niftydialog.effects.Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException | InstantiationException | IllegalAccessException e) {
            throw new Error("Can not init animatorClazz instance");
        }

        return bEffects;
    }
}
