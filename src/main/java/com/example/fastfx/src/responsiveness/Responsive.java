package com.example.fastfx.src.responsiveness;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Responsive {

    private Pane pane;
    private double currPaneWidth;
    private double currPaneHeight;
    private double higherWidthFlag;
    private double currentWidthFlag;
    private double lowerWidthFlag;
    private int lastWidthFlagIndex;
    private double higherHeightFlag;
    private double currentHeightFlag;
    private double lowerHeightFlag;
    private int lastHeightFlagIndex;
    private double lastWidth;
    private double lastHeight;
    private boolean widthIncreasing;
    private boolean heightIncreasing;
    private boolean initializedWidthFlags;
    private boolean initializedHeightFlags;
    private boolean widthListenerNotified;
    private boolean defaultWidthtFlagNotified;
    private boolean heightListenerNotified;
    private boolean defaultHeightFlagNotified;
    private Map<Double, SizeChangeFlag> widthFlags;
    private SizeChangeFlag defaultWidthFlag;
    private List<Double> widthFlagsValues;
    private Map<Double, SizeChangeFlag> heightFlags;
    private SizeChangeFlag defaultHeightFlag;
    private List<Double> heightFlagsValues;

    public Responsive(Pane pane){
        this.pane = pane;
        widthIncreasing = false;
        heightIncreasing = false;
        widthFlags = new HashMap<>();
        widthFlagsValues = new ArrayList<>();
        heightFlags = new HashMap<>();
        heightFlagsValues = new ArrayList<>();
        initializedWidthFlags = false;
        initializedHeightFlags = false;
        widthListenerNotified = false;
        heightListenerNotified = false;
        initWidthListener();
        initHeightListener();
    }

    /**
     * Adds a flag which gets notified when the current pane width gets lower than<br>
     * the specified width.
     */
    public Responsive widthLowerThan(double width, SizeChangeFlag sizeFlag){
        widthFlags.put(width, sizeFlag);
        widthFlagsValues.add(width);
        sortSizeFlagsValues(widthFlagsValues);
        return this;
    }

    /**
     * Adds a flag which is notified when the current pane width gets<br>
     * higher than the highest width flag.
     */
    public Responsive defaultWidthFlag(SizeChangeFlag sizeFlag){
        defaultWidthFlag = sizeFlag;
        return this;
    }

    /**
     * Adds a flag which gets notified when the current pane height gets lower than<br>
     * the specified height.
     */
    public Responsive heightLowerThan(double height, SizeChangeFlag sizeFlag){
        heightFlags.put(height, sizeFlag);
        heightFlagsValues.add(height);
        sortSizeFlagsValues(heightFlagsValues);
        return this;
    }

    /**
     * Adds a flag which is notified when the current pane height gets<br>
     * higher than the highest height flag.
     */
    public Responsive defaultHeightFlag(SizeChangeFlag sizeFlag){
        return this;
    }

    private void initWidthListener(){
        pane.prefWidthProperty().addListener((valType, oldVal, newVal) -> {
            setWidthResizeDirection((double) newVal);
            currPaneWidth = (double) newVal;
            if(!initializedWidthFlags){
                initCurrentWidthFlags();
                initializedWidthFlags = true;
            }
            else{
                setWidthFlags();
            }
        });
    }

    private void initHeightListener(){
        pane.prefHeightProperty().addListener((valType, oldVal, newVal) -> {
            setHeightResizeDirection((double) newVal);
            currPaneHeight = (double) newVal;
        });
    }

    private void initCurrentWidthFlags(){
        for(int i = widthFlagsValues.size() - 1; i >= 1; i--){
            if(currPaneWidth > widthFlagsValues.get(i)) {
                if(i == widthFlagsValues.size() - 1){
                    higherWidthFlag = -1;
                    currentWidthFlag = widthFlagsValues.get(i);
                    lowerWidthFlag = widthFlagsValues.get(i - 1);
                    lastWidthFlagIndex = i;
                    notifyDefaultWidthFlag();
                    defaultWidthtFlagNotified = true;
                    break;
                }
                else{
                    currentWidthFlag = widthFlagsValues.get(i + 1);
                    lastWidthFlagIndex = i + 1;
                }
                if(i < widthFlagsValues.size() - 2){
                    higherWidthFlag = widthFlagsValues.get(i + 2);
                }
                else{
                    higherWidthFlag = -1;
                }
                lowerWidthFlag = widthFlagsValues.get(0);
                notifyWidthChangeFlag(currentWidthFlag);
                widthListenerNotified = true;
                break;
            }
        }
        if(currPaneWidth < widthFlagsValues.get(0)){
            higherWidthFlag = widthFlagsValues.get(1);
            currentWidthFlag = widthFlagsValues.get(0);
            lowerWidthFlag = -1;
            notifyWidthChangeFlag(widthFlagsValues.get(0));
            widthListenerNotified = true;
        }
//        System.out.println("higherFlag: " + higherWidthFlag);
//        System.out.println("currFlag: " + currentWidthFlag);
//        System.out.println("lowerFlag: " + lowerWidthFlag);
//        System.out.println("index: " + lastWidthFlagIndex);
    }

    private void setWidthFlags(){
        if(!widthIncreasing){
            if(currPaneWidth < lowerWidthFlag && lowerWidthFlag != -1){
                if(higherWidthFlag == -1){
                    lastWidthFlagIndex--;
                }
                higherWidthFlag = currentWidthFlag;
                currentWidthFlag = lowerWidthFlag;
                if(lastWidthFlagIndex > 0){
                    lastWidthFlagIndex--;
                    lowerWidthFlag = widthFlagsValues.get(lastWidthFlagIndex);
                }
                else{
                    lowerWidthFlag = -1;
                }
                widthListenerNotified = false;
            }
            else if(currPaneWidth < widthFlagsValues.get(widthFlagsValues.size() - 1) && !defaultWidthtFlagNotified){
                notifyDefaultWidthFlag();
                defaultWidthtFlagNotified = true;
            }
        }
        else{
            if(currPaneWidth > currentWidthFlag && higherWidthFlag != -1){
                if(lowerWidthFlag == -1){
                    lastWidthFlagIndex++;
                }
                lowerWidthFlag = currentWidthFlag;
                currentWidthFlag = higherWidthFlag;
                if(lastWidthFlagIndex < widthFlagsValues.size() - 1){
                    lastWidthFlagIndex++;
                    higherWidthFlag = widthFlagsValues.get(lastWidthFlagIndex);
                }
                else{
                    higherWidthFlag = -1;
                }
                widthListenerNotified = false;
            }
            else if(currPaneWidth > widthFlagsValues.get(widthFlagsValues.size() - 1) && !defaultWidthtFlagNotified){
                notifyDefaultWidthFlag();
                defaultWidthtFlagNotified = true;
            }
        }
//        System.out.println("currWidth: " + currPaneWidth);
        if(!widthListenerNotified){
             notifyWidthChangeFlag(currentWidthFlag);
             widthListenerNotified = true;
//             defaultWidthtFlagNotified = true;
        }
    }

    private void setHeightFlags(){

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
