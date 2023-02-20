package es.proof.grpc.clients.domain.repository;

import java.util.List;

import es.proof.grpc.clients.domain.model.Product;

public interface ProductRespository<P extends Product> {

	List<Product> getAllProducts();
	
	Product getProductId(Integer id);
}
