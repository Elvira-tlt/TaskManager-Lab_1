package views;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

class DateFormatter extends JFormattedTextField.AbstractFormatter {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormat.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        Calendar calendarValue;
        if (value != null) {
            calendarValue = (Calendar) value;
            return dateFormat.format(calendarValue.getTime());
        }
        return " ";

    }
}
