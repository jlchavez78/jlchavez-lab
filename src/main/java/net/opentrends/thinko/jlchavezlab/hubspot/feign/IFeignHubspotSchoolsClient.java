package net.opentrends.thinko.jlchavezlab.hubspot.feign;

import net.opentrends.thinko.jlchavezlab.hubspot.model.Contact;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Parameters;
import net.opentrends.thinko.jlchavezlab.hubspot.model.School;
import net.opentrends.thinko.jlchavezlab.hubspot.model.SchoolsPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "schools", url = "https://matcher.tekman.cloud/")
public interface IFeignHubspotSchoolsClient {

    @GetMapping(value = "/search")
    String getSchools(@SpringQueryMap Parameters params);

}
