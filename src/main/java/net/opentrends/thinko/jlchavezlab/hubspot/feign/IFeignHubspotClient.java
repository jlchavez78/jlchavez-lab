package net.opentrends.thinko.jlchavezlab.hubspot.feign;

import net.opentrends.thinko.jlchavezlab.hubspot.model.Contact;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Parameters;
import net.opentrends.thinko.jlchavezlab.hubspot.model.SearchPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "contacts", url = "https://api.hubapi.com/crm/v3/objects")
public interface IFeignHubspotClient {

    @GetMapping(value = "/contacts/{dataId}")
    Contact getContact(@PathVariable long dataId, @SpringQueryMap Parameters params);

    @PostMapping(value = "/contacts/search")
    Object searchContact(@RequestBody SearchPayload payload, @SpringQueryMap Parameters params);

    @RequestMapping(value = "/contacts/{dataId}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Contact update(@PathVariable("dataId") Long dataId, @RequestBody Map<String, Object> fields, @SpringQueryMap Parameters params);

    @PostMapping(value = "/contacts")
    Contact addContact(@RequestBody Map<String, Object> fields, @SpringQueryMap Parameters params);

}
