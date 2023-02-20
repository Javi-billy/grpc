package es.proof.grpc.clients.adapter.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.google.protobuf.Descriptors;

import es.proof.grpc.clients.domain.model.PriceGrpc;
import es.proof.grpc.clients.domain.service.SearchService;

@RestController
public class PriceController {
	
	private SearchService searchService;

	public PriceController(final SearchService searchService) {
		this.searchService = searchService;
	}

    @GetMapping(value = "/search/{productId}")
	public Map<Descriptors.FieldDescriptor, Object> getPrice(@PathVariable Integer productId) {
    	return searchService.getPrice(productId); 		
	}
    
    @GetMapping(value = "/search/v2/{productId}")
	public PriceGrpc getPriceV2(@PathVariable Integer productId) {
    	return searchService.getPriceV2(productId); 		
	}
    
    @GetMapping(value = "/search/async/{productId}")
	public Map<String, Map<Descriptors.FieldDescriptor, Object>> getPriceAsync(@PathVariable Integer productId) { // throws InterruptedException {
    	return searchService.getPriceAsync(productId); 		
	}
    
    @GetMapping(value = "/search-all")
	public List<Map<Descriptors.FieldDescriptor, Object>> getAllPrices() throws InterruptedException {
    	return searchService.getAllPrices(); 		
	}
    
}
