package trinity.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import trinity.content.ContentType

import static org.springframework.data.mongodb.core.query.Query.query

@RestController
@RequestMapping("/content-type")
class ContentTypeController {

    @Autowired
    MongoTemplate mongoTemplate

    @RequestMapping(method = RequestMethod.GET)
    List<ContentType> list() {
        mongoTemplate.findAll(ContentType)
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    String delete(@PathVariable("id") String id) {
        ContentType contentType = mongoTemplate.findById(id, ContentType)

        if (!contentType) {
            throw new ResourceNotFoundException()
        } else {
            mongoTemplate.remove(contentType)
        }

        contentType.id
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ContentType view(@PathVariable("id") String id) {
        ContentType contentType = mongoTemplate.findById(id, ContentType)

        if (!contentType) {
            throw new ResourceNotFoundException()
        }

        contentType
    }

    @RequestMapping(method = RequestMethod.POST)
    String save(@RequestBody ContentType contentType) {
        ContentType existingContentType = mongoTemplate.findOne(query(Criteria.where("name").is(contentType.name)), ContentType)

        if (!existingContentType) {
            mongoTemplate.insert(contentType)
        } else {
            throw new ResourceExistsException()
        }

        contentType.id
    }

}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
}

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceExistsException extends RuntimeException {
}
