package com.example.fastfx.src.fcomponents;

import javafx.scene.control.CheckBox;
import javafx.scene.control.skin.CheckBoxSkin;
import javafx.scene.layout.Region;

public class FCheckBox extends CheckBox {

    private Region box;
    private Region mark;

    public FCheckBox () {
        setText("");
    }

    public FCheckBox (String text) {
        setText(text);
    }

    public Region getBox () {
        if (getSkin() == null) {
            this.setSkin(new CheckBoxSkin(this));
        }
        if (box == null) {
            box = (Region) this.lookup(".box");
        }
        return box;
    }

    public Region getMark () {
        if (getSkin() == null) {
            this.setSkin(new CheckBoxSkin(this));
        }
        if (mark == null) {
            mark = (Region) lookup(".mark");
        }
        return mark;
    }
}
