package es.proof.grpc.servers.adapter.rest;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.model.PriceRequest;
import es.proof.grpc.servers.domain.service.SearchService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SearchController {
		
	private SearchService<Price> searchService;

	public SearchController(final SearchService<Price> searchService) {
		this.searchService = searchService;
	}

	/*@GetMapping(value = "/search/{startDate}/{productId}/{brandId}")
	public Price search(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@PathVariable Integer productId, @PathVariable String brandId) {
		return searchService.searchPricesCriteria(startDate, productId, brandId);
	}
	
	@GetMapping(value = "/search2/{startDate}/{productId}/{brandId}")
	public ResponseEntity<Price> search2(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@PathVariable Integer productId, @PathVariable String brandId) {
		Price p = searchService.searchPricesCriteria(startDate, productId, brandId);
		return ResponseEntity.ok(p);		
	}*/
	
	@PostMapping
	public ResponseEntity<Price> createPrice(@RequestBody PriceRequest priceRequest) {		
		try {
			Price p = searchService.create(priceRequest);		
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
	}
}
