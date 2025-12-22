package com.example.fastfx.src.responsiveness;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Responsive2 {

    private Pane pane;
    private double currPaneWidth;
    private double currPaneHeight;
    private double currentWidthFlag;
    private int currWidthFlagIndex;
    private double currentHeightFlag;
    private int lastHeightFlagIndex;
    private double lastWidth;
    private double lastHeight;
    private boolean widthIncreasing;
    private boolean widthWasIncreasing;
    private boolean heightIncreasing;
    private boolean heightWasIncreasing;
    private boolean initializedWidthFlag;
    private boolean initializedHeightFlag;
    private boolean widthListenerNotified;
    private boolean defaultWidthListenerNotified;
    private boolean defaultHeightListenerNotified;
    private Map<Double, SizeChangeFlag> widthFlags;
    private SizeChangeFlag defaultWidthFlag;
    private List<Double> widthFlagsValues;
    private Map<Double, SizeChangeFlag> heightFlags;
    private SizeChangeFlag defaultHeightFlag;
    private List<Double> heightFlagsValues;

    public Responsive2(Pane pane){
        this.pane = pane;
        widthIncreasing = false;
        widthWasIncreasing = false;
        heightIncreasing = false;
        heightWasIncreasing = false;
        widthFlags = new HashMap<>();
        widthFlagsValues = new ArrayList<>();
        heightFlags = new HashMap<>();
        heightFlagsValues = new ArrayList<>();
        initializedWidthFlag = false;
        initializedHeightFlag = false;
        initWidthListener();
        initHeightListener();
    }

    /**
     * Adds a flag which gets notified when the current pane width gets lower than<br>
     * the specified width.
     */
    public Responsive2 widthLowerThan(double width, SizeChangeFlag sizeFlag){
        widthFlags.put(width, sizeFlag);
        widthFlagsValues.add(width);
        sortSizeFlagsValues(widthFlagsValues);
        return this;
    }

    /**
     * Adds a flag which is notified when the current pane width gets<br>
     * higher than the highest width flag.
     */
    public Responsive2 defaultWidthFlag(SizeChangeFlag sizeFlag){
        defaultWidthFlag = sizeFlag;
        return this;
    }

    /**
     * Adds a flag which gets notified when the current pane height gets lower than<br>
     * the specified height.
     */
    public Responsive2 heightLowerThan(double height, SizeChangeFlag sizeFlag){
        heightFlags.put(height, sizeFlag);
        heightFlagsValues.add(height);
        sortSizeFlagsValues(heightFlagsValues);
        return this;
    }

    /**
     * Adds a flag which is notified when the current pane height gets<br>
     * higher than the highest height flag.
     */
    public Responsive2 defaultHeightFlag(SizeChangeFlag sizeFlag){
        return this;
    }

    private void initWidthListener(){
        pane.prefWidthProperty().addListener((valType, oldVal, newVal) -> {
            setWidthResizeDirection((double) newVal);
            currPaneWidth = (double) newVal;
            if(!initializedWidthFlag){
                initCurrentWidthFlag();
                initializedWidthFlag = true;
            }
            else{
                setWidthFlag();
            }
        });
    }

    private void initHeightListener(){
        pane.prefHeightProperty().addListener((valType, oldVal, newVal) -> {
            setHeightResizeDirection((double) newVal);
            currPaneHeight = (double) newVal;
        });
    }

    private void initCurrentWidthFlag(){
        for(int i = widthFlagsValues.size() - 1; i >= 0; i--){
            if(currPaneWidth < widthFlagsValues.get(0)){
                currentWidthFlag = widthFlagsValues.get(0);
                currWidthFlagIndex = 0;
                notifyWidthChangeFlag(currentWidthFlag);
                widthListenerNotified = true;
                break;
            }

            double currFlag = widthFlagsValues.get(i);
            if(currPaneWidth > currFlag){
                currentWidthFlag = currFlag;
                currWidthFlagIndex = i;
                if(i == widthFlagsValues.size() - 1){
                    notifyDefaultWidthFlag();
                    currWidthFlagIndex = i;
                    defaultWidthListenerNotified = true;
//                    widthFlagNotified = true;
                }
                else{
                    notifyWidthChangeFlag(widthFlagsValues.get(i));
                    widthListenerNotified = true;
                }
                break;
            }
        }
    }

    private void setWidthFlag(){
        if(!widthIncreasing){
            if(widthWasIncreasing && currWidthFlagIndex > 0){
                currWidthFlagIndex--;
                currentWidthFlag = widthFlagsValues.get(currWidthFlagIndex);
                widthWasIncreasing = false;
            }
            if(currPaneWidth < currentWidthFlag && currWidthFlagIndex >= 0){
                notifyWidthChangeFlag(currentWidthFlag);
                currWidthFlagIndex--;
                if(currWidthFlagIndex >= 0){
                    currentWidthFlag = widthFlagsValues.get(currWidthFlagIndex);
                }
                defaultWidthListenerNotified = false;
                widthWasIncreasing = false;
            }
        }
        if(widthIncreasing){
            if(!widthWasIncreasing && currWidthFlagIndex < widthFlagsValues.size() - 1){
                currWidthFlagIndex++;
                currentWidthFlag = widthFlagsValues.get(currWidthFlagIndex);
                widthWasIncreasing = true;
            }
            if(currPaneWidth > currentWidthFlag && currWidthFlagIndex < widthFlagsValues.size()){
                if(currWidthFlagIndex < 0){
                    currWidthFlagIndex = 0;
                }
                currWidthFlagIndex++;
                if(currWidthFlagIndex < widthFlagsValues.size()){
                    currentWidthFlag = widthFlagsValues.get(currWidthFlagIndex);
                    notifyWidthChangeFlag(currentWidthFlag);
                }
                else if(!defaultWidthListenerNotified){
                    notifyDefaultWidthFlag();
                    defaultWidthListenerNotified = true;
                }
                widthWasIncreasing = true;
            }
        }
    }

    private void setHeightFlag(){

    }

    private void setWidthResizeDirection(double width){
        if(width < lastWidth){
            widthIncreasing = false;
        }
        else if(width > lastWidth){
            widthIncreasing = true;
        }
        lastWidth = width;
    }

    private void setHeightResizeDirection(double height){
        if(height < lastHeight){
            heightIncreasing = false;
        }
        else if(height > lastHeight){
            heightIncreasing = true;
        }
        lastHeight = height;
    }

    private void notifyWidthChangeFlag(double width){
        if(widthFlagsValues.contains(width)){
            widthFlags.get(width).apply();
        }
    }

    private void notifyDefaultWidthFlag(){
        if(defaultWidthFlag != null){
            defaultWidthFlag.apply();
        }
    }

    private void notifyHeightChangeFlag(double height){
        if(heightFlagsValues.contains(height)){
            heightFlags.get(height).apply();
        }
    }

    private void notifyDefaultHeightFlag(){
        if(defaultHeightFlag != null){
            defaultHeightFlag.apply();
        }
    }

    private void sortSizeFlagsValues(List<Double> sizeFlags){
        sizeFlags.sort((a, b) -> {
            if(a > b){
                return 1;
            }
            else if(a < b){
                return -1;
            }
            return 0;
        });
    }
}
