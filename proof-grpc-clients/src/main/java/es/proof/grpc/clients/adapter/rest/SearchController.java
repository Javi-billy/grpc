package es.proof.grpc.clients.adapter.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.proof.grpc.clients.domain.model.CompleteInfo;
import es.proof.grpc.clients.domain.model.Product;
import es.proof.grpc.clients.domain.service.SearchService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SearchController {
	
	private SearchService searchService;

	public SearchController(final SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> search() {
		try {
			List<Product> products = searchService.getProducts();		
		return ResponseEntity.status(HttpStatus.OK).body(products);
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/complete-info")
	public ResponseEntity<List<CompleteInfo>> completeInfo() {
		try {
			List<CompleteInfo> completeInfo = searchService.getProductsPrices();		
		return ResponseEntity.status(HttpStatus.OK).body(completeInfo);
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> searchProduct(@PathVariable Integer id) {
		try {
			Product product = searchService.getProductId(id);		
		return ResponseEntity.status(HttpStatus.OK).body(product);
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping(value = "/complete-info/{id}")
	public ResponseEntity<CompleteInfo> searchProductComplete(@PathVariable Integer id) {
		try {
			CompleteInfo completeInfo = searchService.getProductsPrices(id);		
		return ResponseEntity.status(HttpStatus.OK).body(completeInfo);
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
	}
}
