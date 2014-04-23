/* ===========================================================================
 * IBA CZ Confidential
 *
 * (c) Copyright IBA CZ 2011 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.sample.portlet.bascispring.pto;

import org.joda.time.DateTime;

/**
 * @author Miroslav Ligas <miroslav.ligas@ibacz.eu>
 */
public class PersonPto {
    private String name;
    private DateTime dateOfBirth;

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonPto{" +
                "dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
