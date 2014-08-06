package trinity.field

import org.apache.commons.lang.builder.ToStringBuilder

class TextField implements Field {

    String name

    @Override
    public String toString() {
        ToStringBuilder.reflectionToString(this)
    }
}
