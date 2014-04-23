/* ===========================================================================
 * IBA CZ Confidential
 *
 * (c) Copyright IBA CZ 2011 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.sample.portlet.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;

/**
 * @author Miroslav Ligas <miroslav.ligas@ibacz.eu>
 */
public class JodaDateEditor extends PropertyEditorSupport {

    private DateTimeFormatter dtf;

    public JodaDateEditor(String dateTimePattern) {
        this.dtf = DateTimeFormat.forPattern(dateTimePattern);
    }

    @Override
    public String getAsText() {
        DateTime date = (DateTime) this.getValue();
        if (date != null) {
            return dtf.print(date);
        }else{
            return null;
        }

    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(dtf.parseDateTime(text));
    }
}
