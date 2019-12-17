package com.ecommerce.loginmicroservice.feignInterface;

import com.ecommerce.loginmicroservice.constants.MicroServiceConstants;
import com.ecommerce.loginmicroservice.constants.MicroServiceConstants.*;
import com.ecommerce.loginmicroservice.exceptionHandler.DataDuplicationException;
import com.ecommerce.loginmicroservice.requestDTO.ClientRequestDTO;
import com.ecommerce.loginmicroservice.requestDTO.RegisterRequestDTO;
import com.ecommerce.loginmicroservice.responseDTO.ClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.ecommerce.loginmicroservice.constants.SecurityConstants.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = ClientMicroServiceConstants.BASE)
@Service
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface ClientInterface {

    @RequestMapping(value = ClientMicroServiceConstants.SEARCH_CLIENT)
    ClientResponseDTO searchClient(@RequestBody ClientRequestDTO requestDTO, @RequestHeader(value= REFERER_HEADER) String referer);

    @RequestMapping(value = ClientMicroServiceConstants.UPDATE_CLIENT)
    void updateClient(@RequestBody ClientResponseDTO responseDTO);

    @RequestMapping(value = ClientMicroServiceConstants.SAVE, method = POST)
    void saveClient(@RequestBody RegisterRequestDTO requestDTO, @RequestHeader(value= REFERER_HEADER) String referer) throws DataDuplicationException;
}