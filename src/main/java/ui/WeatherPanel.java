package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by baghyeongi on 2017. 6. 2..
 */
public class WeatherPanel extends Box {

    public WeatherPanel(int axis) {
        super(axis);
    }

    public void addAll(Component... components) {
        Arrays.stream(components).forEach(component -> add(component));
    }

}
