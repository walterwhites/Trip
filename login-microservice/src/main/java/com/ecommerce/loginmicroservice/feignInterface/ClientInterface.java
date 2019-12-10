package com.ecommerce.loginmicroservice.feignInterface;

import com.ecommerce.loginmicroservice.beans.ClientBean;
import com.ecommerce.loginmicroservice.constants.MicroServiceConstants;
import com.ecommerce.loginmicroservice.constants.MicroServiceConstants.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.ecommerce.loginmicroservice.constants.SecurityConstants.*;

@FeignClient(name = ClientMicroServiceConstants.BASE)
@Service
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface ClientInterface {

    @RequestMapping(value = ClientMicroServiceConstants.SEARCH_CLIENT)
    ClientBean searchClient(@RequestBody ClientBean clientBean, @RequestHeader(value= REFERER_HEADER) String referer);

    @RequestMapping(value = ClientMicroServiceConstants.UPDATE_CLIENT)
    void updateClient(@RequestBody ClientBean clientBean);
}