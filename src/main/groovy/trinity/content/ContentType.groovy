package trinity.content

import org.apache.commons.lang.builder.ToStringBuilder
import trinity.field.TextField

class ContentType {

    String id
    String name
    List<TextField> fields

    @Override
    public String toString() {
        ToStringBuilder.reflectionToString(this)
    }
}
